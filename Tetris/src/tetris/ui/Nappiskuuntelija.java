package tetris.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import tetris.peli.Tetris;

/**
 * @author Anni
 * 
 * Näppiskuuntelija-luokka vastaanottaa käyttäjän syöttämät näppäinkomennot ja
 * tulkitsee ne ohjelman sisäisiksi toiminnoiksi. Se kommunikoi Tetris-luokan kanssa,
 * mutta käyttää oikeastaan lähinnä Muodostelmasta löytyviä metodeita.
 * 
 * @see Muodostelma
 */

public class Nappiskuuntelija extends KeyAdapter {
    private Tetris peli;
    
    public Nappiskuuntelija(Tetris tetris){
        this.peli = tetris;
    }
    
    @Override
         public void keyPressed(KeyEvent e) {
             int keycode = e.getKeyCode();
             
             switch (keycode) {
             case KeyEvent.VK_LEFT:
                 peli.getPutoava().siirra(-1); // jostain syystä siirtyvät kaksi ruutua?
                 break;
             case KeyEvent.VK_RIGHT:
                 peli.getPutoava().siirra(1);
                 break;
             case KeyEvent.VK_DOWN:
                 peli.getPutoava().putoa();
                 break;
             case KeyEvent.VK_UP:
                 peli.getPutoava().kierra();
                 break;
             case KeyEvent.VK_SPACE:
                 while (peli.getPutoava().putoaa){
                     peli.getPutoava().putoa();
                 }
                 break;
             case KeyEvent.VK_P:
                 peli.pausePaallePois();
                 break;
             }
             peli.getAlusta().repaint();
         }
     }