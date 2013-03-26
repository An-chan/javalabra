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

public class Tetris extends Timer implements ActionListener{
    private final int LEVEYS = 200;
    private final int KORKEUS = 400;
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
    
    // luodaan uusi sattumanvarainen muodostelma putoavaksi muodostelmaksi
    public void luoUusiPutoava(){
        Random rand = new Random();
        int satun = rand.nextInt(7);
        switch (satun){
            case 0:
                this.putoava = new Muodostelma(Muoto.I, this);
            case 1:
                this.putoava = new Muodostelma(Muoto.S, this);
            case 2:
                this.putoava = new Muodostelma(Muoto.peiliS, this);
            case 3:
                this.putoava = new Muodostelma(Muoto.L, this);
            case 4:
                this.putoava = new Muodostelma(Muoto.peiliL, this);
            case 5:
                this.putoava = new Muodostelma(Muoto.T, this);
            case 6:
                this.putoava = new Muodostelma(Muoto.nelio, this);
        }
    }
    
    public void setAlusta(Piirtoalusta alusta){
        this.pelialue = alusta;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        while (jatkuu){
            this.putoava.putoa();
            if (!putoava.putoaa){ // kun putoava muodostelma törmää, sen palikat lisätään pelipalikoihin
                for (Palikka palikka : putoava.getPalikat()){
                    pelipalikat[palikka.getX()][palikka.getY()] = palikka;
                }
                luoUusiPutoava(); // luodaan uusi putoava muodostelma
            }
            if (tarkastaTaydetRivit()){ //tarkastetaan tuliko rivejä täyteen
                poistaTaydetRivit();
            }
            // jos palikkapino kasvaa kattoon asti, jatkuu = false
            this.pelialue.repaint();           
        }
    }
    
    // laitetaan peli paussille ja lopetetaan paussi
    public void pausePaallePois(){
        if (this.pause){
            this.pause = false;
        } else {
            this.pause = true;
        }
    }
    
    public boolean onkoPause(){
        return this.pause;
    }
    
    // KESKEN: metodi tarkastaa onko jokin rivi tullut täyteen
    public boolean tarkastaTaydetRivit(){
        return false;
    }
    
    //KESKEN: metodi poistaa täydet rivit, tuo niiden yläpuoliset rivit alas, ja antaa pelaajalle pisteet
    public void poistaTaydetRivit(){
        
    }
    
    // palautetaan kaikki pelissä olevat palikat - HUOM! ei putoavia! -
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

}
