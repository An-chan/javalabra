package tetris.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * @author Anni Perheentupa
 * 
 * Tämä luokka käsittelee pelin Palikka-olioiden ominaisuuksia ja toimintoja,
 * kuten niiden siirtämistä ja törmäämistä. Muodostelmille on oma luokkansa.
 * 
 * @see Muodostelma
 */

public class Palikka {
    private int leveys = 10;
    private int korkeus = 10;
    private int x;
    private int y;
    
    public Palikka(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Metodi tarkastaa, onko suoraan palikan alla toinen palikka
     * johon se törmää eikä voi enää liikkua.
     * 
     * @param toinen: palikka, johon törmäämistä tutkitaan
     * @return boolean: kertoo, törmääkö palikka vai ei
     */
    public boolean tormaa(Palikka toinen){
        if (toinen.getY() == this.y){
            if (toinen.getX() == this.x +1){
                return true;
            }
        } else if (this.x == 19){
            return true;
        }
        return false;
    }
    
    /**
     * Metodi, joka testaa törmäämistä listaan palikoita
     * 
     * @param lista: palikat, joihin törmäämistä tutkitaan
     * @return boolean: kertoo, törmääkö palikka vai ei
     */
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
    
    /**
     * Metodi siirtää palikkaa joko yhden ruudun verran oikealle tai
     * yhden ruudun verran vasemmalle.
     * 
     * @param yMuutos: joko 1 (siirrytään oikealle) tai -1 (siirrytään vasemmalle)
     */
    public void siirra(int yMuutos){
        if (this.y + yMuutos > 0 && this.y + yMuutos <= 10){
            this.y = this.y + yMuutos;
        }
    }
    
    /**
     * Metodi siirtää palikoita muodostelmien kierrossa ja
     * vaikuttaa siksi sekä y- että x-sijaintiin.
     * 
     * @param xMuutos x-koordinaatin muutos
     * @param yMuutos y-koordinaatin muutos
     */
    public void siirraKierrossa(int xMuutos, int yMuutos){
        this.x = this.x + xMuutos;
        this.y = this.y + yMuutos;
    }
    
    /**
     * Metodi piirtää olion kuvaaman palikan piirtoalustalle.
     * 
     * @param g Graphics-luokan ilmentymä
     */
    public void piirra(Graphics g){
        g.setColor(Color.white);
        g.fillRect(y*20, x*20, 20, 20);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(y*20, x*20, 20, 20);
    }
    
}
