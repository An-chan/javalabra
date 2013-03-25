package tetris.ui;

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
    }
    
    public void haePutoava(){
        this.putoava = peli.getPutoava();
    }

}
