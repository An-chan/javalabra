import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.domain.*;
import tetris.Main;
import tetris.peli.Tetris;


public class PalikkajaMuodostelmaTest {
    
    public PalikkajaMuodostelmaTest() {
    }
    
    Palikka eka;
    Tetris peli;
    
    @Before
    public void setUp() {
        eka = new Palikka(2, 3);
        peli = new Tetris();
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void tormaysTrueTest(){
        Palikka toka = new Palikka(3, 3);
        assertEquals(eka.tormaa(toka), true);
    }
    
    @Test
    public void tormaysFalseTest(){
        Palikka toka = new Palikka(5, 3);
        assertEquals(eka.tormaa(toka), false);
    }
    
    @Test
    public void yksiPalikkaPutoaaOikeinTest(){
        Palikka toka = new Palikka(4, 3);
        eka.putoa();
        assertEquals(eka.tormaa(toka), true);
    }
    
    @Test
    public void muodostelmaLuodaanOikeinTest(){
        Muodostelma muod = new Muodostelma(Muoto.I, peli);
        List<Palikka> palikat = muod.getPalikat();
        assertEquals(palikat.get(0).getX(), 0);
        assertEquals(palikat.get(0).getY(), 3);
        assertEquals(palikat.get(1).getX(), 0);
        assertEquals(palikat.get(1).getY(), 4);
        assertEquals(palikat.get(2).getX(), 0);
        assertEquals(palikat.get(2).getY(), 5);
        assertEquals(palikat.get(3).getX(), 0);
        assertEquals(palikat.get(3).getY(), 6);
    }
    
    @Test
    public void nelioMuodostelmaPutoaaOikeinTest(){
        Muodostelma nelio = new Muodostelma(Muoto.nelio, peli);
        nelio.putoa();
        List<Palikka> palikat = nelio.getPalikat();
        assertEquals(palikat.get(0).getX(), 1);
        assertEquals(palikat.get(0).getY(), 4);
        assertEquals(palikat.get(1).getX(), 1);
        assertEquals(palikat.get(1).getY(), 5);
        assertEquals(palikat.get(2).getX(), 2);
        assertEquals(palikat.get(2).getY(), 4);
        assertEquals(palikat.get(3).getX(), 2);
        assertEquals(palikat.get(3).getY(), 5);
    }
    
    @Test
    public void IMuodostelmaPutoaaOikeinTest(){
        Muodostelma imuod = new Muodostelma(Muoto.I, peli);
        imuod.putoa();
        List<Palikka> palikat = imuod.getPalikat();
        assertEquals(palikat.get(0).getX(), 1);
        assertEquals(palikat.get(0).getY(), 3);
        assertEquals(palikat.get(1).getX(), 1);
        assertEquals(palikat.get(1).getY(), 4);
        assertEquals(palikat.get(2).getX(), 1);
        assertEquals(palikat.get(2).getY(), 5);
        assertEquals(palikat.get(3).getX(), 1);
        assertEquals(palikat.get(3).getY(), 6);
    }
    
    @Test
    public void SMuodostelmaPutoaaOikeinTest(){
        Muodostelma smuod = new Muodostelma(Muoto.S, peli);
        smuod.putoa();
        List<Palikka> palikat = smuod.getPalikat();
        assertEquals(palikat.get(0).getX(), 2);
        assertEquals(palikat.get(0).getY(), 4);
        assertEquals(palikat.get(1).getX(), 1);
        assertEquals(palikat.get(1).getY(), 5);
        assertEquals(palikat.get(2).getX(), 1);
        assertEquals(palikat.get(2).getY(), 6);
        assertEquals(palikat.get(3).getX(), 2);
        assertEquals(palikat.get(3).getY(), 5);
    }
    
    @Test
    public void peiliSMuodostelmaPutoaaOikeinTest(){
        Muodostelma peilismuod = new Muodostelma(Muoto.peiliS, peli);
        peilismuod.putoa();
        List<Palikka> palikat = peilismuod.getPalikat();
        assertEquals(palikat.get(0).getX(), 1);
        assertEquals(palikat.get(0).getY(), 3);
        assertEquals(palikat.get(1).getX(), 1);
        assertEquals(palikat.get(1).getY(), 4);
        assertEquals(palikat.get(2).getX(), 2);
        assertEquals(palikat.get(2).getY(), 4);
        assertEquals(palikat.get(3).getX(), 2);
        assertEquals(palikat.get(3).getY(), 5);
    }
    
    @Test
    public void TMuodostelmaPutoaaOikeinTest(){
        Muodostelma tmuod = new Muodostelma(Muoto.T, peli);
        tmuod.putoa();
        List<Palikka> palikat = tmuod.getPalikat();
        assertEquals(palikat.get(0).getX(), 1);
        assertEquals(palikat.get(0).getY(), 4);
        assertEquals(palikat.get(1).getX(), 1);
        assertEquals(palikat.get(1).getY(), 5);
        assertEquals(palikat.get(2).getX(), 1);
        assertEquals(palikat.get(2).getY(), 6);
        assertEquals(palikat.get(3).getX(), 2);
        assertEquals(palikat.get(3).getY(), 5);
    }
    
    @Test
    public void LMuodostelmaPutoaaOikeinTest(){
        Muodostelma lmuod = new Muodostelma(Muoto.L, peli);
        lmuod.putoa();
        List<Palikka> palikat = lmuod.getPalikat();
        assertEquals(palikat.get(0).getX(), 1);
        assertEquals(palikat.get(0).getY(), 4);
        assertEquals(palikat.get(1).getX(), 1);
        assertEquals(palikat.get(1).getY(), 5);
        assertEquals(palikat.get(2).getX(), 1);
        assertEquals(palikat.get(2).getY(), 6);
        assertEquals(palikat.get(3).getX(), 2);
        assertEquals(palikat.get(3).getY(), 4);
    }
    
    @Test
    public void peiliLMuodostelmaPutoaaOikeinTest(){
        Muodostelma peililmuod = new Muodostelma(Muoto.peiliL, peli);
        peililmuod.putoa();
        List<Palikka> palikat = peililmuod.getPalikat();
        assertEquals(palikat.get(0).getX(), 1);
        assertEquals(palikat.get(0).getY(), 4);
        assertEquals(palikat.get(1).getX(), 1);
        assertEquals(palikat.get(1).getY(), 5);
        assertEquals(palikat.get(2).getX(), 1);
        assertEquals(palikat.get(2).getY(), 6);
        assertEquals(palikat.get(3).getX(), 2);
        assertEquals(palikat.get(3).getY(), 6);
    }
    
    @Test
    public void siirretaanVasemmalleTest(){
        Muodostelma muod = new Muodostelma(Muoto.nelio, peli);
        muod.siirra(-1);
        List<Palikka> palikat = muod.getPalikat();
        assertEquals(palikat.get(0).getX(), 0);
        assertEquals(palikat.get(0).getY(), 3);
        assertEquals(palikat.get(1).getX(), 0);
        assertEquals(palikat.get(1).getY(), 4);
        assertEquals(palikat.get(2).getX(), 1);
        assertEquals(palikat.get(2).getY(), 3);
        assertEquals(palikat.get(3).getX(), 1);
        assertEquals(palikat.get(3).getY(), 4);
    }
    
    @Test
    public void siirretaanOikealleTest(){
        Muodostelma muod = new Muodostelma(Muoto.nelio, peli);
        muod.siirra(1);
        List<Palikka> palikat = muod.getPalikat();
        assertEquals(palikat.get(0).getX(), 0);
        assertEquals(palikat.get(0).getY(), 5);
        assertEquals(palikat.get(1).getX(), 0);
        assertEquals(palikat.get(1).getY(), 6);
        assertEquals(palikat.get(2).getX(), 1);
        assertEquals(palikat.get(2).getY(), 5);
        assertEquals(palikat.get(3).getX(), 1);
        assertEquals(palikat.get(3).getY(), 6);
    }
        
    @Test
    public void muodostelmaTormaaOikeinTest(){
        Muodostelma muod = new Muodostelma(Muoto.T, peli);
        Palikka testi = new Palikka(3, 4);
        ArrayList<Palikka> palikat = new ArrayList<Palikka>();
        palikat.add(testi);
        muod.putoa();
        muod.tormays(palikat);
        assertEquals(muod.putoaa, true);
        muod.putoa();
        muod.tormays(palikat);
        assertEquals(muod.putoaa, false);
    }

}
