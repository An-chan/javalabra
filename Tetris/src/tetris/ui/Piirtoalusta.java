package tetris.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.domain.*;
import tetris.peli.Tetris;

public class Piirtoalusta extends JPanel{
    private Tetris peli;
    
    public Piirtoalusta(Tetris peli){
        this.peli = peli;
        super.setSize(400, 200);
        super.setBackground(Color.WHITE);
    }
    
    public Muodostelma haePutoava(){
        return peli.getPutoava();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        System.out.println("Piirtyy edelleen");
        for (Palikka palikka : peli.getPalikat()){
            palikka.piirra(g);
        }
        for (Palikka palikka : peli.getPutoava().getPalikat()){
            palikka.piirra(g);
        }
    }

}
