

import com.mycompany.minigamecollection.FoodCollectingScore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FoodCollectingScoreUnitTest {
    
    public FoodCollectingScoreUnitTest() {
    }
    
    FoodCollectingScore score;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        score = new FoodCollectingScore();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void startingScore0() {
        assertTrue(score.score() == 0);
    }
    
    @Test
    public void firstStrawberryGives1Point() {
        score.collectStrawberry();
        assertTrue(score.score() == 1);
    }
}
