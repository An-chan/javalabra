
package tetris;

import javax.swing.SwingUtilities;
import tetris.domain.*;
import tetris.ui.Kayttoliittyma;

public class Main {

    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
