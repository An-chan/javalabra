
package tetris;

import javax.swing.SwingUtilities;
import tetris.domain.*;
import tetris.peli.Tetris;
import tetris.ui.Kayttoliittyma;

public class Main {

    public static void main(String[] args) {
        Tetris tetris = new Tetris();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(tetris);
        SwingUtilities.invokeLater(kayttoliittyma);
        kayttoliittyma.run();
        tetris.setAlusta(kayttoliittyma.getAlusta());
        tetris.pelaa();
    }
}
