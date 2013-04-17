
package tetris.domain;

import java.util.ArrayList;
import java.util.List;
import tetris.peli.Tetris;
/**
 * @author Anni Perheentupa
 * 
 * Tämä luokka käsittelee Palikka-olioista muodostuvia muodostelmia ja
 * suorittaa niille yhteisiä operaatioita, kuten siirtämisen, kiertämisen
 * ja putoamisen.
 * 
 * @see Palikka
 */
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
    
    /**
     * Palikkamuodostelmaa siirretään yhden ruudun verran alaspäin.
     */
    public void putoa(){
        for (Palikka palikka : palikat) {
            palikka.putoa();
        }
        tormays();
    }
    
    /**
     * Metodi siirtää palikkaa joko oikealle tai vasemmalle
     * annetun syötteen mukaisesti, kuitenkin vain ruutu kerrallaan.
     * 
     * @param ymuutos sijainnin muutos, 1 jos siirrytään oikealle, -1 vasemmalle
     */
    public void siirra(int ymuutos){
        for (Palikka palikka : palikat){
            palikka.siirra(ymuutos);
        }
    }
    
    /** 
     *  Palikkaa kierretään aina oikealle, käyttäen palikkaa nro 1 
     *  (eli toista palikkaa) keskipisteenä.
     * 
     * Huom! I-muodostelman kiertoa ei vielä implementoitu.
     * */
    public void kierra(){
        int keskiY = palikat.get(1).getY();
        int keskiX = palikat.get(1).getX();
        if (this.muoto == Muoto.nelio){
            return;             // nelio-muodostelma on aina samanlainen
        }
        if (keskiX-1 < 0 || keskiY-1 < 0 || keskiY +1 > 10){
            return;
        }
        for (int i = 0; i < palikat.size(); i++){
            if (i == 1){
                continue;
            }
            Palikka tama = palikat.get(i);
            if (tama.getX() == keskiX-1 && tama.getY() == keskiY){         //keskikohdan yläpuolella
                tama.siirraKierrossa(1, 1);
            } else if (tama.getX() == keskiX+1 && tama.getY() == keskiY){ //keskikohdan alapuolella
                tama.siirraKierrossa(-1, -1);
            } else if (tama.getX() == keskiX && tama.getY() == keskiY+1){ //keskikohdan oikealla
                tama.siirraKierrossa(1, -1);
            } else if (tama.getX() == keskiX && tama.getY() == keskiY-1){ //keskikohdan vasemmalla
                tama.siirraKierrossa(-1, 1);
            } else if (tama.getX() == keskiX-1 && tama.getY() == keskiY+1){ //keskikohdan yläoikealla
                tama.siirraKierrossa(0, 2);
            }  else if (tama.getX() == keskiX-1 && tama.getY() == keskiY-1){ //keskikohdan ylävasemmalla
                tama.siirraKierrossa(2, 0);
            }  else if (tama.getX() == keskiX+1 && tama.getY() == keskiY+1){ //keskikohdan alaoikealla
                tama.siirraKierrossa(0, -2);
            }  else if (tama.getX() == keskiX+1 && tama.getY() == keskiY-1){ //keskikohdan alavasemmalla
                tama.siirraKierrossa(-2, 0);
            }
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
