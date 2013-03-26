package tetris.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import tetris.domain.*;
import tetris.peli.Tetris;

public class Piirtoalusta extends JPanel{
    private Muodostelma putoava;
    private List<Palikka> palikat;
    private Tetris peli;
    
    public Piirtoalusta(Tetris peli){
        this.peli = peli;
        haePutoava();
        this.palikat = peli.getPalikat();
        this.addKeyListener(new NappisKuuntelija(peli));
        super.setBackground(Color.GRAY);
    }
    
    public void haePutoava(){
        this.putoava = peli.getPutoava();
    }
    
    @Override
    public void repaint(){
        super.repaint();
        requestFocus();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for (Palikka palikka : peli.getPalikat()){
            g.setColor(Color.WHITE);
            g.fillRect(palikka.getX()*20, palikka.getY()*20, 20, 20);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(palikka.getX()*20, palikka.getY()*20, 20, 20);
        }
        for (Palikka palikka : peli.getPutoava().getPalikat()){
            g.setColor(Color.WHITE);
            g.fillRect(palikka.getX()*20, palikka.getY()*20, 20, 20);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(palikka.getX()*20, palikka.getY()*20, 20, 20);
        }
    }

}
