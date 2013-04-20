package tetris.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import tetris.domain.*;
import tetris.ui.*;

/**
 * @author Anni
 *
 * Tetris-luokka käsittelee pelialueen toimintoja ja pitää kirjaa pelissä
 * olevista palikoista (sekä putoava muodostelma että jo kasatut palikat).
 * **!!**Korjaamattomia ongelmia toistaiseksi:**!!**
 * -vain yksi rivi poistuu kerrallaan
 */
public class Tetris {

    private Muodostelma putoava;
    private Palikka[][] pelipalikat;
    private Piirtoalusta pelialue;
    private int viive;
    private boolean pause;
    private boolean jatkuu;
    private int pisteet;

    public Tetris() {
        this.pause = false;
        this.jatkuu = true;
        this.pisteet = 0;
        this.viive = 2000;
        this.pelipalikat = new Palikka[20][];
        for (int i = 0; i < 20; i++) {
            pelipalikat[i] = new Palikka[10];
        }
        luoUusiPutoava();
    }

    /**
     * Metodi luo uuden muodostelman putoavaksi muodostelmaksi ja antaa sille
     * satunnaisen muodon.
     *
     * Ei palauta mitään, vaan asettaa tuloksen this.putoava.
     */
    public void luoUusiPutoava() {
        Random rand = new Random();
        int satun = rand.nextInt(7);
        switch (satun) {
            case 0:
                this.putoava = new Muodostelma(Muoto.I, this);
                break;
            case 1:
                this.putoava = new Muodostelma(Muoto.S, this);
                break;
            case 2:
                this.putoava = new Muodostelma(Muoto.peiliS, this);
                break;
            case 3:
                this.putoava = new Muodostelma(Muoto.L, this);
                break;
            case 4:
                this.putoava = new Muodostelma(Muoto.peiliL, this);
                break;
            case 5:
                this.putoava = new Muodostelma(Muoto.T, this);
                break;
            case 6:
                this.putoava = new Muodostelma(Muoto.nelio, this);
                break;
        }
    }

    public void setAlusta(Piirtoalusta alusta) {
        this.pelialue = alusta;
    }

    /**
     * Metodi ajaa pelin normaalin syklin, jossa odotetaan viiveen ajan ja
     * sitten pudotetaan putoavaa muodostelmaa yhden verran alaspäin. Kun
     * muodostelma osuu johonkin ja lakkaa putoamasta, metodi kutsuu rivien
     * täyttymisen ja täysien rivien poistavia metodeja ennen uuden muodostelman
     * luomista ja syklin jatkumista.
     */
    public void peliSykli() {
        while (jatkuu) {
            try {
                Thread.sleep(viive);
            } catch (Exception e) {
            }
            if (pause) {
                while (pause) {
                    //käyttiksen tekstikenttään viesti "peli pysäytetty"
                }
            }
            this.putoava.putoa();
            if (!putoava.putoaa) { // kun putoava muodostelma törmää, sen palikat lisätään pelipalikoihin
                lisaaPalikatPeliin(putoava.getPalikat());
                luoUusiPutoava(); // luodaan uusi putoava muodostelma
                List<Integer> tayttyneet = tarkastaTaydetRivit();
                if (tayttyneet != null) { //tarkastetaan tuliko rivejä täyteen
                    poistaTaydetRivit(tayttyneet);
                }
            }

            this.pelialue.repaint();

        }
        // käyttiksen tekstikenttään lähetetään viesti "peli päättynyt"
    }

    /**
     * Metodi pysäyttää pelin tilapäisesti tai palauttaa sen pysäytystilasta.
     */
    public void pausePaallePois() {
        if (this.pause) {
            this.pause = false;
        } else {
            this.pause = true;
        }
    }

    public boolean onkoPause() {
        return this.pause;
    }

    /**
     * Metodi tarkastaa, onko jokin pelissä olevien palikoiden riveistä tullut
     * täyteen, ja palauttaa täysien rivien numerot listana poistamista varten
     *
     * @return List<Integer> lista poistettavista riveistä
     */
    public List<Integer> tarkastaTaydetRivit() {
        List<Integer> taydet = new ArrayList<Integer>();
        for (int i = pelipalikat.length - 1; i >= 0; i--) {
            if (pelipalikat[i][0] != null) {
                boolean taysi = true;
                for (int j = 0; j < pelipalikat[i].length; j++) {
                    if (pelipalikat[i][j] == null) {
                        taysi = false;
                        break;
                    }
                }
                if (taysi) {
                    taydet.add(i);
                }
            }
        }
        if (taydet.isEmpty()) {
            return null;
        }
        return taydet;
    }

    /**
     * Metodi poistaa täyttyneet rivit pelistä ja laskee niiden yläpuolella
     * olevia rivejä alas tarvittavan määrän. KESKEN!!
     *
     * @param taydet lista poistettavista riveistä
     */
    public void poistaTaydetRivit(List<Integer> taydet) {
        for (Integer i : taydet) {
            pelipalikat[i] = new Palikka[10];
            for (int x = i-1; x > 0; x--){
                for (int j = 0; j < pelipalikat[x].length; j++){
                    if (pelipalikat[x][j] != null){
                        pelipalikat[x][j].putoa();
                        pelipalikat[x+1][j] = pelipalikat[x][j];
                        pelipalikat[x][j] = null;
                    }
                }
            }
        }
        this.pisteet += 100 * taydet.size();
        this.viive *= 0.8;

    }

    /**
     * Metodi palauttaa listan kaikista pelissä olevista palikoista, paitsi
     * senhetkisen putoavan mukaikissaodostelman palikat.
     *
     * @return List<Palikka>, lista jolla kaikki pelin palikat
     */
    public List<Palikka> getPalikat() {
        List<Palikka> palautuva = new ArrayList<Palikka>();
        for (int i = 0; i < pelipalikat.length; i++) {
            for (int j = 0; j < pelipalikat[i].length; j++) {
                if (pelipalikat[i][j] != null) {
                    palautuva.add(pelipalikat[i][j]);
                }
            }
        }
        return palautuva;
    }

    /**
     * Metodi lisää listana annetut palikat peliin oikeille paikoille. Jos pino
     * on kasvanut kattoon asti, peli päättyy.
     *
     * @param palikat lista lisättävistä palikoista
     */
    public void lisaaPalikatPeliin(List<Palikka> palikat) {
        for (Palikka palikka : palikat) {
            pelipalikat[palikka.getY()][palikka.getX()] = palikka;
            if (palikka.getY() == 0) {
                jatkuu = false;
            }
        }
    }

    public Muodostelma getPutoava() {
        return this.putoava;
    }

    public Piirtoalusta getAlusta() {
        return this.pelialue;
    }

    public int getPisteet() {
        return this.pisteet;
    }

    /**
     * Debuggaamiseen tarkoitettu metodi, joka tulostaa palikoiden
     * koordinaatteja
     *
     * @param palikat tulostettavat palikat
     */
    public void tulosta(List<Palikka> palikat) {
        System.out.println(putoava.putoaa);
        for (Palikka palikka : palikat) {
            System.out.println("X: " + palikka.getX() + ", Y: " + palikka.getY());
        }
    }
}
