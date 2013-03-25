package tetris.peli;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import tetris.domain.*;

public class Tetris {
    private final int LEVEYS = 200;
    private final int KORKEUS = 400;
    private Muodostelma putoava;
    private List<Palikka> palikat;
    
    public Tetris(){
        this.palikat = new ArrayList<Palikka>();
    }
    
    public void luoUusiPutoava(){
        Random rand = new Random();
        int satun = rand.nextInt(7);
        switch (satun){
            case 0:
                this.putoava = new Muodostelma(Muoto.I);
            case 1:
                this.putoava = new Muodostelma(Muoto.S);
            case 2:
                this.putoava = new Muodostelma(Muoto.peiliS);
            case 3:
                this.putoava = new Muodostelma(Muoto.L);
            case 4:
                this.putoava = new Muodostelma(Muoto.peiliL);
            case 5:
                this.putoava = new Muodostelma(Muoto.T);
            case 6:
                this.putoava = new Muodostelma(Muoto.nelio);
        }
    }
    
    public List<Palikka> getPalikat(){
        return this.palikat;
    }
    
    public Muodostelma getPutoava(){
        return this.putoava;
    }

}
