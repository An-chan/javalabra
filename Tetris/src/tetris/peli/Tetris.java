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
                for (Palikka palikka : putoava.getPalikat()){
                    pelipalikat[palikka.getX()][palikka.getY()] = palikka;
                }
                luoUusiPutoava(); // luodaan uusi putoava muodostelma
            }
            List<Integer> tayttyneet = tarkastaTaydetRivit();
            if (tayttyneet.size() > 0){ //tarkastetaan tuliko rivejä täyteen
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
     * Metodi tarkastaa, onko jokin pelissä olevien palikoiden riveistä tullut täyteen
     * @return boolean true, jos jokin rivi on täynnä, false jos ei
     */
    public List<Integer> tarkastaTaydetRivit(){
        List<Integer> taydet = new ArrayList<Integer>();
        for(int i = 0; i < pelipalikat.length; i++){
            if (pelipalikat[i][0] != null){
                for (int j = 0; j < pelipalikat[i].length; j++){
                    if (pelipalikat[i][j] == null){
                        break;
                    }
                }
                taydet.add(i);
            }
        }
        return taydet;
    }
    
    //KESKEN: metodi poistaa täydet rivit, tuo niiden yläpuoliset rivit alas, ja antaa pelaajalle pisteet
    public void poistaTaydetRivit(List<Integer> taydet){
        for (Integer i : taydet) {
            pelipalikat[i] = new Palikka[10];
        }
        this.pisteet += 100;
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
    
    public Muodostelma getPutoava(){
        return this.putoava;
    }
    
    public Piirtoalusta getAlusta(){
        return this.pelialue;
    }
    public void tulosta(List<Palikka> palikat){
        System.out.println(putoava.putoaa);
        for (Palikka palikka : palikat) {
            System.out.println("X: "+palikka.getX()+", Y: "+palikka.getY());
        }
    }

}
