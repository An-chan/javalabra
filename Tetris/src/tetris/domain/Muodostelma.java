
package tetris.domain;

import java.util.ArrayList;
import java.util.List;
import tetris.peli.Tetris;

public class Muodostelma {
    private Muoto muoto;
    private ArrayList<Palikka> palikat;
    public boolean putoaa;
    private Tetris peli;
    
    public Muodostelma(Muoto muoto, Tetris peli){
        this.palikat = new ArrayList<Palikka>();
        this.muoto = muoto;
        this.putoaa = true;
        this.peli = peli;
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
    
    // pudotetaan palikkamuodostelmaa yksi ruutu alaspäin
    public void putoa(){
        for (Palikka palikka : palikat) {
            palikka.putoa();
        }
        tormays();
    }
    
    //siirretään koko muodostelmaa joko yhden ruudun verran vasemmalle (-1) tai oikealle (1)
    public void siirra(int ymuutos){
        for (Palikka palikka : palikat){
            palikka.siirra(ymuutos);
        }
    }
    
    // palikkaa kierretään aina oikealle, koska tämä on nyt old school -versio :D
    public void kierra(){
        switch (this.muoto){
            case nelio:
                return;            
            case I:
                
            case L:
                
            case peiliL:
                
            case S:
                
            case peiliS:
                
            case T:
                
        }
    }
    
    // jos muodostelma osuu johonkin, se lakkaa putoamasta
    public void tormays(){
        this.tormays(peli.getPalikat());
    }
    
    public void tormays(List<Palikka> palikkalista){
        for (Palikka palikka : palikat) {
            if(palikka.tormaa(palikkalista)){
                this.putoaa=false;
                return;
            }
        }
    }
    
    public List<Palikka> getPalikat(){
        return this.palikat;
    }

}
