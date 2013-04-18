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
    private int y;
    private int x;
    
    public Palikka(int x, int y){
        this.y = y;
        this.x = x;
    }
    
    /**
     * Metodi tarkastaa, onko suoraan palikan alla toinen palikka
     * johon se törmää eikä voi enää liikkua.
     * 
     * @param toinen: palikka, johon törmäämistä tutkitaan
     * @return boolean: kertoo, törmääkö palikka vai ei
     */
    public boolean tormaa(Palikka toinen){
        if (toinen.getX() == this.x){
            if (toinen.getY() == this.y +1){
                return true;
            }
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
        if (this.y >= 19){
            return true;
        }
        return false;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getX(){
        return this.x;
    }
    
    /**
     * Metodi "pudottaa" palikan, eli siirtää sitä yhden ruudun alaspäin.
     * 
     */
    public void putoa(){
        this.y++;
    }
    
    /**
     * Metodi siirtää palikkaa joko yhden ruudun verran oikealle tai
     * yhden ruudun verran vasemmalle.
     * 
     * @param xMuutos: joko 1 (siirrytään oikealle) tai -1 (siirrytään vasemmalle)
     */
    public void siirra(int xMuutos){
        if (this.x + xMuutos >= 0 && this.x + xMuutos < 10){
            this.x = this.x + xMuutos;
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
        this.y = this.y + yMuutos;
        this.x = this.x + xMuutos;
    }
    
    /**
     * Metodi piirtää olion kuvaaman palikan piirtoalustalle.
     * 
     * @param g Graphics-luokan ilmentymä
     */
    public void piirra(Graphics g){
        g.setColor(Color.white);
        g.fillRect(x*20, y*20, 20, 20);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x*20, y*20, 20, 20);
    }
    
}
