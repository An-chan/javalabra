package tetris.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        frame = new JFrame("Pegasus Tetris");
        frame.setPreferredSize(new Dimension(300, 450));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());

        peli.setAlusta(alusta);
        
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        Piirtoalusta pelialue = new Piirtoalusta(peli);
        this.alusta = pelialue;
        ImageIcon iid = new ImageIcon(this.getClass().getResource("taivas.png"));
        JLabel tausta = new JLabel(iid, JLabel.LEFT);
        tausta.setVerticalAlignment(JLabel.TOP);
        alusta.add(tausta); 
        
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