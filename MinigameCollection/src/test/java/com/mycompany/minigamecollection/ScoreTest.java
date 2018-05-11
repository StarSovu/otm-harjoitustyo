
package com.mycompany.minigamecollection;

import com.mycompany.minigamecollection.Score.Score;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sovu
 */
public class ScoreTest {
    
    public ScoreTest() {
    }
    
    Score score;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.score = new Score();
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
    public void startingScoreIs0() {
        assertTrue(this.score.getScore() == 0);
    }
    
    @Test
    public void increaseScoreIncreasesScoreBy1() {
        this.score.increaseScore();
        assertTrue(this.score.getScore() == 1);
    }
    
    @Test
    public void increaseScoreIncreasesScoreBy1MultipleTimes() {
        for (int i = 0; i < 10000; i++) {
            this.score.increaseScore();
        }
        assertTrue(this.score.getScore() == 10000);
    }
    
    @Test
    public void setScoreTo0() {
        for (int i = 0; i < 10000; i++) {
            this.score.increaseScore();
        }
        this.score.setScore(0);
        assertTrue(this.score.getScore() == 0);
    }
    
    @Test
    public void setScoreTo10() {
        this.score.setScore(10);
        assertTrue(this.score.getScore() == 10);
    }
    
    @Test
    public void addUsername() {
        this.score.setUsername("user");
        assertEquals(this.score.getUsername(), "user");
    }
    
    @Test
    public void comparing() {
        Score score2 = new Score();
        score2.increaseScore();
        assertTrue(this.score.compareTo(score2) == 1);
    }
}
