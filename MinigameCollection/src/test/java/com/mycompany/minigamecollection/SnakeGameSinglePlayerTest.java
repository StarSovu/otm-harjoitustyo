package com.mycompany.minigamecollection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SnakeGameSinglePlayerTest {
    
    public SnakeGameSinglePlayerTest() {
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
        game = new SnakeGame(30, 30, new Score(), false);
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
    public void startingXIsHalfOfWidth() {
        Piece startPiece = this.game.getSnake().getPieces().get(0);
        assertTrue(startPiece.getX() == 15);
        
    }
    
    @Test
    public void startingYIsHalfOfHeight() {
        Piece startPiece = this.game.getSnake().getPieces().get(0);
        assertTrue(startPiece.getY() == 15);
        
    }
    
    @Test
    public void orangeIsNotOnTopOfSnake() {
        Fruit orange = this.game.getOrange();
        assertFalse(orange.getX() == 15 && orange.getY() == 15);
    }
    
    @Test
    public void lemonIsOutsideX() {
        Fruit lemon = this.game.getLemon();
        assertTrue(lemon.getX() == 31);
    }
    
    @Test
    public void lemonIsOutsideY() {
        Fruit lemon = this.game.getLemon();
        assertTrue(lemon.getY() == 31);
    }
    
    @Test
    public void endIsFalse() {
        assertFalse(this.game.end());
    }
    
    @Test
    public void endIsTrueWhenSnakeGoesDownTooMuch() {
        for (int i = 0; i < 20; i++) {
            this.game.update();
        }
        
        assertTrue(this.game.end());
    }
    
    @Test
    public void endIsTrueWhenSnakeGoesUpTooMuch() {
        this.game.getSnake().setDirection(Direction.LEFT);
        this.game.getSnake().setDirection(Direction.UP);
        
        for (int i = 0; i < 20; i++) {
            this.game.update();
        }
        
        assertTrue(this.game.end());
    }
    
    @Test
    public void endIsTrueWhenSnakeGoesLeftTooMuch() {
        this.game.getSnake().setDirection(Direction.LEFT);
        
        for (int i = 0; i < 20; i++) {
            this.game.update();
        }
        
        assertTrue(this.game.end());
    }
    
    @Test
    public void endIsTrueWhenSnakeGoesRightTooMuch() {
        this.game.getSnake().setDirection(Direction.RIGHT);
        
        for (int i = 0; i < 20; i++) {
            this.game.update();
        }
        
        assertTrue(this.game.end());
    }
    
    @Test
    public void setSnakeSetsSnake() {
        Snake snake = new Snake(17, 17, Direction.LEFT);
        this.game.setSnake(snake);
        
        assertEquals(snake, this.game.getSnake());
    }
    
    @Test
    public void setFruitSetsOrangeWhenTrue() {
        Fruit orange = new Fruit(13, 13);
        orange.setType(true);
        this.game.setFruit(orange);
        
        assertEquals(orange, this.game.getOrange());
    }
    
    @Test
    public void setFruitSetsLemonWhenFalse() {
        Fruit lemon = new Fruit(13, 13);
        lemon.setType(false);
        this.game.setFruit(lemon);
        
        assertEquals(lemon, this.game.getLemon());
    }
    
    @Test
    public void gameEndsWhenSnakeHitsItself() {
        Snake snake = this.game.getSnake();
        game.update();
        
        snake.setDirection(Direction.LEFT);
        game.update();
        
        snake.grow();
        snake.setDirection(Direction.UP);
        game.update();
        
        snake.grow();
        snake.setDirection(Direction.RIGHT);
        game.update();
        
        assertTrue(game.end());
    }
    
    @Test
    public void eatingOrangeCausesSnakeToGrow() {
        Fruit orange = new Fruit(15, 18);
        orange.setType(true);
        this.game.setFruit(orange);
        
        for (int i = 0; i < 5; i++) {
            this.game.update();
        }
        
        assertTrue(game.getSnake().getLength() == 4);
    }
    
    @Test
    public void lemonAppearsAfterTenOrangesX() {
        for (int i = 0; i < 10; i++) {
            Fruit orange = new Fruit(15, 16 + i);
            orange.setType(true);
            this.game.setFruit(orange);
            this.game.update();
        }
        
        Fruit lemon = this.game.getLemon();
        
        assertTrue(lemon.getX() < 30);
    }
    
    @Test
    public void lemonAppearsAfterTenOrangesY() {
        for (int i = 0; i < 10; i++) {
            Fruit orange = new Fruit(15, 16 + i);
            orange.setType(true);
            this.game.setFruit(orange);
            this.game.update();
        }
        
        Fruit lemon = this.game.getLemon();
        
        assertTrue(lemon.getY() < 30);
    }
    
    @Test
    public void scoreIncreasesWhenOrangeIsEaten() {
        Score score = new Score();
        game = new SnakeGame(30, 30, score, false);
        
        Fruit orange = new Fruit(15, 16);
        orange.setType(true);
        this.game.setFruit(orange);
        
        game.update();
        
        assertTrue(score.getScore() == 1);
    }
    
    @Test
    public void scoreIncreasesBy2WhenLemonIsEaten() {
        Score score = new Score();
        game = new SnakeGame(30, 30, score, false);
        
        Fruit orange = new Fruit(15, 1);
        orange.setType(true);
        this.game.setFruit(orange);
        
        Fruit lemon = new Fruit(15, 16);
        lemon.setType(false);
        this.game.setFruit(lemon);
        
        game.update();
        
        assertTrue(score.getScore() == 2);
    }
    
    @Test
    public void snakeMovesWhenUpdating() {
        this.game.update();
        
        Piece updatedPiece = this.game.getSnake().getPieces().get(1);
        
        assertTrue(updatedPiece.getY() == 16);
    }
}
