package tetris.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Palikka {
    private int leveys = 10;
    private int korkeus = 10;
    private int x;
    private int y;
    
    public Palikka(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Metodi tarkastaa, onko suoraan palikan alla toinen palikka, joka estää putoamisen
    public boolean tormaa(Palikka toinen){
        if (toinen.getY() == this.y){
            if (toinen.getX() == this.x +1){
                return true;
            }
        }
        return false;
    }
    
    // Sama kuin edellinen, mutta listalle palikoita
    public boolean tormaa(List<Palikka> lista){
        for (Palikka palikka : lista) {
            if (this.tormaa(palikka)){
                return true;
            }
        }
        return false;
    }
    
    // Palauttaa x-sijainnin
    public int getX(){
        return this.x;
    }
    
    // Palauttaa y-sijainnin
    public int getY(){
        return this.y;
    }
    
    // Palikka putoaa yhden ruudun alaspäin
    public void putoa(){
        this.x++;
    }
    
    // Palikkaa siirretään joko yksi ruutu oikealle (+1) tai vasemmalle (-1)
    public void siirra(int yMuutos){
        if (this.y + yMuutos > 0 && this.y + yMuutos <= 10){
            this.y = this.y + yMuutos;
        }
    }
    
    // muodostelman kiertämistä varten tarkoitettu metodi
    public void siirraKierrossa(int yMuutos, int xMuutos){
        
    }
    
    // piirretään oliot
    public void piirra(Graphics g){
        g.setColor(Color.white);
        g.fillRect(x*20, y*20, 20, 20);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x*20, y*20, 20, 20);
    }
    
}
