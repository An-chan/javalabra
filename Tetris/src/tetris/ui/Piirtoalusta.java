package tetris.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import tetris.domain.*;
import tetris.peli.Tetris;

public class Piirtoalusta extends JPanel{
    private Tetris peli;
    
    public Piirtoalusta(Tetris peli){
        this.peli = peli;
        super.setSize(400, 200);
        super.setBackground(Color.CYAN);
    }
    
    public Muodostelma haePutoava(){
        return peli.getPutoava();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for (Palikka palikka : peli.getPalikat()){
            palikka.piirra(g);
        }
        for (Palikka palikka : peli.getPutoava().getPalikat()){
            palikka.piirra(g);
        }
    }

}
