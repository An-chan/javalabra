package tetris.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import tetris.peli.Tetris;

public class NappisKuuntelija extends KeyAdapter {
    private Tetris peli;
    
    public NappisKuuntelija(Tetris tetris){
        this.peli = tetris;
    }
    
    @Override
         public void keyPressed(KeyEvent e) {
             int keycode = e.getKeyCode();

             if (keycode == 'p' || keycode == 'P') {
                 peli.pausePaallePois();
             }

             switch (keycode) {
             case KeyEvent.VK_LEFT:
                 peli.getPutoava().siirra(-1);
             case KeyEvent.VK_RIGHT:
                 peli.getPutoava().siirra(1);
             case KeyEvent.VK_DOWN:
                 peli.getPutoava().putoa();
             case KeyEvent.VK_UP:
                 peli.getPutoava().kierra();
             case KeyEvent.VK_SPACE:
                 // pudotetaan muodostelma välittömästi?
             }
             peli.getAlusta().repaint();

         }
     }