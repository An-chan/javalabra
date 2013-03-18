import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.domain.Palikka;


public class TetrisTest {
    
    public TetrisTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tormaysTrueTest(){
        Palikka eka = new Palikka(2, 3);
        Palikka toka = new Palikka(3, 3);
        assertEquals(eka.tormaa(toka), true);
    }
    
    @Test
    public void tormaysFalseTest(){
        Palikka eka = new Palikka(2, 3);
        Palikka toka = new Palikka(5, 3);
        assertEquals(eka.tormaa(toka), false);
    }
    // @Test
    // public void hello() {}
}
