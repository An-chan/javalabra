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
 * Tetris-luokka käsittelee pelialueen toimintoja ja pitää kirjaa pelissä olevista
 * palikoista (sekä putoava muodostelma että jo kasatut palikat).
 */

public class Tetris extends Timer implements ActionListener{
    private Muodostelma putoava;
    private Palikka[][] pelipalikat;
    private Piirtoalusta pelialue;
    private boolean pause;
    private boolean jatkuu;
    private int pisteet;
    
    public Tetris(){
        super(1000, null);
        this.pause = false;
        this.jatkuu = true;
        this.pisteet = 0;
        this.pelipalikat = new Palikka[20][];
        for (int i = 0; i < 20; i++){
            pelipalikat[i] = new Palikka[10];
        }
        luoUusiPutoava();
        super.addActionListener(this);
        super.setInitialDelay(2000);
    }
    
    /**
     * Metodi luo uuden muodostelman putoavaksi muodostelmaksi
     * ja antaa sille satunnaisen muodon.
     * 
     * Ei palauta mitään, vaan asettaa tuloksen this.putoava.
     */
    public void luoUusiPutoava(){
        Random rand = new Random();
        int satun = rand.nextInt(7);
        switch (satun){
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
    
    public void setAlusta(Piirtoalusta alusta){
        this.pelialue = alusta;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
            if (pause){
                super.stop();
            }
            if (!jatkuu){
                
            }
            this.putoava.putoa();
            tulosta(putoava.getPalikat());
            if (!putoava.putoaa){ // kun putoava muodostelma törmää, sen palikat lisätään pelipalikoihin
                lisaaPalikatPeliin(putoava.getPalikat());
                luoUusiPutoava(); // luodaan uusi putoava muodostelma
            }
            List<Integer> tayttyneet = tarkastaTaydetRivit();
            if (tayttyneet != null){ //tarkastetaan tuliko rivejä täyteen
                poistaTaydetRivit(tayttyneet);
            }
            // jos palikkapino kasvaa kattoon asti, jatkuu = false
            this.pelialue.repaint();           
    }
    
    /**
     * Metodi pysäyttää pelin tilapäisesti tai
     * palauttaa sen pysäytystilasta.
     */
    public void pausePaallePois(){
        if (this.pause){
            this.pause = false;
            super.start();
        } else {
            this.pause = true;
        }
    }
    
    public boolean onkoPause(){
        return this.pause;
    }
    
    /**
     * Metodi tarkastaa, onko jokin pelissä olevien palikoiden riveistä tullut täyteen,
     * ja palauttaa täysien rivien numerot listana poistamista varten
     * 
     * @return List<Integer> lista poistettavista riveistä
     */
    public List<Integer> tarkastaTaydetRivit(){
        List<Integer> taydet = new ArrayList<Integer>();
        for (int i = pelipalikat.length-1; i >= 0; i--){
            if (pelipalikat[i][0] != null){
                boolean taysi = true;
                for (int j = 0; j < pelipalikat[i].length; j++){
                    if (pelipalikat[i][j] == null){
                        taysi = false;
                        break;
                    }
                }
                if(taysi){
                    taydet.add(i);
                }
            }
        }
        if (taydet.size() == 0){
            return null;
        }
        return taydet;
    }
    
    /**
     * Metodi poistaa täyttyneet rivit pelistä ja laskee niiden yläpuolella
     * olevia rivejä alas tarvittavan määrän.
     * KESKEN!!
     * 
     * @param taydet lista poistettavista riveistä
     */
    public void poistaTaydetRivit(List<Integer> taydet){
        for (Integer i : taydet) {
            pelipalikat[i] = new Palikka[10];
            for (int x = i; x < pelipalikat.length-1; x++){
                Palikka[] temp = pelipalikat[x-1];
                pelipalikat[x-1] = pelipalikat[x];
                pelipalikat[x]= temp;
            }
        }
        this.pisteet += 100*taydet.size();
        super.setDelay(super.getDelay()-100);
        
    }
    
    /**
     * Metodi palauttaa listan kaikista pelissä olevista palikoista,
     * paitsi senhetkisen putoavan mukaikissaodostelman palikat.
     * 
     * @return List<Palikka>, lista jolla kaikki pelin palikat
     */
    public List<Palikka> getPalikat(){
        List<Palikka> palautuva = new ArrayList<Palikka>();
        for (int i = 0; i < pelipalikat.length; i++){
            for (int j = 0; j < pelipalikat[i].length; j++){
                if (pelipalikat[i][j] != null){
                    palautuva.add(pelipalikat[i][j]);
                }
            }
        }
        return palautuva;
    }
    
    /**
     * Metodi lisää listana annetut palikat peliin oikeille paikoille.
     * 
     * @param palikat lista lisättävistä palikoista
     */
    public void lisaaPalikatPeliin(List<Palikka> palikat){
        for (Palikka palikka : palikat){
            pelipalikat[palikka.getY()][palikka.getX()] = palikka;
        }
    }
    
    public Muodostelma getPutoava(){
        return this.putoava;
    }
    
    public Piirtoalusta getAlusta(){
        return this.pelialue;
    }
    
    public int getPisteet(){
        return this.pisteet;
    }
    
    /**
     * Debuggaamiseen tarkoitettu metodi, joka tulostaa palikoiden koordinaatteja
     * 
     * @param palikat tulostettavat palikat
     */
    public void tulosta(List<Palikka> palikat){
        System.out.println(putoava.putoaa);
        for (Palikka palikka : palikat) {
            System.out.println("X: "+palikka.getX()+", Y: "+palikka.getY());
        }
    }

}
