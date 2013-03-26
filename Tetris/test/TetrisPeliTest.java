
import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.domain.*;
import tetris.peli.Tetris;
import tetris.ui.Piirtoalusta;

public class TetrisPeliTest {
    
    public TetrisPeliTest() {
    }
    
    Tetris peli;
    
    @Before
    public void setUp() {
        peli = new Tetris();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void peliinLuodaanYksiPutoavaTest(){
        Muodostelma putoava = peli.getPutoava();
        assertEquals(putoava.putoaa, true);
    }
    
    @Test
    public void piirtoalustaAsettuuOikeinTest(){
        Piirtoalusta alusta = new Piirtoalusta(peli);
        peli.setAlusta(alusta);
        assertEquals(peli.getAlusta(), alusta);
    }
    
    @Test
    public void pausePaalleToimiiTest(){
        peli.pausePaallePois();
        assertEquals(peli.onkoPause(), true);
    }
    
    @Test
    public void pausePoisToimiiTest(){
        peli.pausePaallePois();
        peli.pausePaallePois();
        assertEquals(peli.onkoPause(), false);
    }
}
