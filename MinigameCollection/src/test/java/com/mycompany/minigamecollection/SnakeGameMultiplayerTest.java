
package com.mycompany.minigamecollection;

import com.mycompany.minigamecollection.Others.SnakeGame;
import com.mycompany.minigamecollection.Others.Direction;
import com.mycompany.minigamecollection.Pieces.Fruit;
import com.mycompany.minigamecollection.Score.Score;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SnakeGameMultiplayerTest {
    
    public SnakeGameMultiplayerTest() {
    }
    
    SnakeGame game;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new SnakeGame(30, 30, new Score(), true);
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
    public void startsWithNoWinner() {
        assertTrue(this.game.getWinner() == 0);
    }
    
    @Test
    public void doingNothingResultsInTie() {
        for (int i = 0; i < 16; i++) {
            this.game.update();
        }
        
        assertTrue(this.game.getWinner() == 3);
    }
    
    @Test
    public void snake1StartsAtX17() {
        assertTrue(this.game.getSnake().getPieces().get(0).getX() == 17);
    }
    
    @Test
    public void snake1StartsAtY15() {
        assertTrue(this.game.getSnake().getPieces().get(0).getY() == 15);
    }
    
    @Test
    public void snake2StartsAtX12() {
        assertTrue(this.game.getSnake2().getPieces().get(0).getX() == 12);
    }
    
    @Test
    public void snake2StartsAtY14() {
        assertTrue(this.game.getSnake2().getPieces().get(0).getY() == 14);
    }
    
    @Test
    public void snake1GoesDown() {
        assertTrue(this.game.getSnake().getDirection() == Direction.DOWN);
    }
    
    @Test
    public void snake2GoesUp() {
        assertTrue(this.game.getSnake2().getDirection() == Direction.UP);
    }
    
    @Test
    public void greenWinsBlueHitsBoundary() {
        Fruit orange = new Fruit(1, 1);
        orange.setType(true);
        this.game.setFruit(orange);
        
        
        for (int i = 0; i < 15; i++) {
            switch (i % 4) {
                case 0:
                    this.game.getSnake().setDirection(Direction.LEFT);
                    break;
                case 1:
                    this.game.getSnake().setDirection(Direction.UP);
                    break;
                case 2:
                    this.game.getSnake().setDirection(Direction.RIGHT);
                    break;
                case 3:
                    this.game.getSnake().setDirection(Direction.DOWN);
                    break;
                default:
                    break;
            }
            
            this.game.update();
        }
        
        assertTrue(this.game.getWinner() == 1);
    }
    
    @Test
    public void blueWinsGreenHitsBoundary() {
        Fruit orange = new Fruit(1, 1);
        orange.setType(true);
        this.game.setFruit(orange);
        
        
        for (int i = 0; i < 15; i++) {
            switch (i % 4) {
                case 0:
                    this.game.getSnake2().setDirection(Direction.LEFT);
                    break;
                case 1:
                    this.game.getSnake2().setDirection(Direction.DOWN);
                    break;
                case 2:
                    this.game.getSnake2().setDirection(Direction.RIGHT);
                    break;
                case 3:
                    this.game.getSnake2().setDirection(Direction.UP);
                    break;
                default:
                    break;
            }
            
            this.game.update();
        }
        
        assertTrue(this.game.getWinner() == 2);
    }
}
