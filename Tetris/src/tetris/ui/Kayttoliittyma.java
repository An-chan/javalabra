package tetris.ui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetris.peli.Tetris;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Tetris peli;
    private Piirtoalusta alusta;

    public Kayttoliittyma(Tetris peli) {
        this.peli = peli;
    }

    @Override
    public void run() {
        frame = new JFrame("Tetris");
        frame.setPreferredSize(new Dimension(250, 440));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());

        peli.setAlusta(alusta);
        
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        
        Piirtoalusta pelialue = new Piirtoalusta(peli);
        this.alusta = pelialue;
        
        frame.addKeyListener(new Nappiskuuntelija(peli));
        container.add(this.alusta);
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public Piirtoalusta getAlusta(){
        return this.alusta;
    }
}