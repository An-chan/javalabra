package tetris;

import javax.swing.SwingUtilities;
import tetris.domain.*;
import tetris.peli.Tetris;
import tetris.ui.Kayttoliittyma;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        
        Tetris tetris = new Tetris();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(tetris);
        SwingUtilities.invokeLater(kayttoliittyma);
        tetris.peliSykli();
    }
}
