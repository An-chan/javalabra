
package tetris.domain;

import java.util.ArrayList;
import java.util.List;

public class Muodostelma {
    private Muoto muoto;
    private ArrayList<Palikka> palikat;
    // Nämä ehkä tarpeettomia?
    private int x;
    private int y;
    
    public Muodostelma(Muoto muoto){
        this.palikat = new ArrayList<Palikka>();
        this.muoto = muoto;
        luoPalikat();
    }
    
    public void luoPalikat(){
        // palikkamuodostelman keskikohta luodaan ruudun ylälaidan keskelle, ts. kohtaan (0, 5)
        // x-koordinaatti kasvaa alaspäin, y-koordinaatti kasvaa oikealle
        switch (muoto){
            case I:
                palikat.add(new Palikka(0, 3));
                palikat.add(new Palikka(0, 4));
                palikat.add(new Palikka(0, 5));
                palikat.add(new Palikka(0, 6));
                break;
            case L:
                palikat.add(new Palikka(0, 4));
                palikat.add(new Palikka(0, 5));
                palikat.add(new Palikka(0, 6));
                palikat.add(new Palikka(1, 4));
                break;
            case peiliL:
                palikat.add(new Palikka(0, 4));
                palikat.add(new Palikka(0, 5));
                palikat.add(new Palikka(0, 6));
                palikat.add(new Palikka(1, 6));
                break;
            case nelio:
                palikat.add(new Palikka(0, 4));
                palikat.add(new Palikka(0, 5));
                palikat.add(new Palikka(1, 4));
                palikat.add(new Palikka(1, 5));
                break;
            case S:
                palikat.add(new Palikka(1, 4));
                palikat.add(new Palikka(0, 5));
                palikat.add(new Palikka(0, 6));
                palikat.add(new Palikka(1, 5));
                break;
            case peiliS:
                palikat.add(new Palikka(0, 3));
                palikat.add(new Palikka(0, 4));
                palikat.add(new Palikka(1, 4));
                palikat.add(new Palikka(1, 5));
                break;
            case T:
                palikat.add(new Palikka(0, 4));
                palikat.add(new Palikka(0, 5));
                palikat.add(new Palikka(0, 6));
                palikat.add(new Palikka(1, 5));
                break;
        
        }
    }
    
    public void putoa(){
        for (Palikka palikka : palikat) {
            palikka.putoa();
        }
    }
    
    public List getPalikat(){
        return this.palikat;
    }

}
