package com.mycompany.minigamecollection;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SnakeTest {
    
    public SnakeTest() {
    }
    
    Snake snake;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.snake = new Snake(15, 15, Direction.DOWN);
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
    public void startXMatchesX() {
        Piece startPiece = this.snake.getPieces().get(0);
        assertTrue(startPiece.getX() == 15);
    }
    
    @Test
    public void startYMatchesY() {
        Piece startPiece = this.snake.getPieces().get(0);
        assertTrue(startPiece.getY() == 15);
    }
    
    @Test
    public void startsByGoingDown() {
        assertTrue(this.snake.getDirection() == Direction.DOWN);
    }
    
    @Test
    public void newSnakeHasLengthOf1() {
        assertTrue(this.snake.getLength() == 1);
    }
    
    @Test
    public void snakeStopsGrowingAfterLength3() {
        for (int i = 0; i < 100; i++) {
            this.snake.move();
        }
        assertTrue(this.snake.getLength() == 3);
    }
    
    @Test
    public void snakeLengthIs2AfterFirstMovement() {
        this.snake.move();
        assertTrue(this.snake.getLength() == 2);
    }
    
    @Test
    public void snakeGrowsWhenGrowAndMove() {
        for (int i = 0; i < 100; i++) {
            this.snake.move();
        }
        
        this.snake.grow();
        this.snake.move();
        
        assertTrue(this.snake.getLength() == 4);
    }
    
    @Test
    public void impossibleToTurnUpWhenMovingDown() {
        this.snake.setDirection(Direction.UP);
        
        assertTrue(this.snake.getDirection() == Direction.DOWN);
    }
    
    @Test
    public void impossibleToTurnDownWhenMovingUp() {
        this.snake.setDirection(Direction.LEFT);
        this.snake.setDirection(Direction.UP);
        this.snake.setDirection(Direction.DOWN);
        
        assertTrue(this.snake.getDirection() == Direction.UP);
    }
    
    @Test
    public void impossibleToTurnLeftWhenMovingRight() {
        this.snake.setDirection(Direction.RIGHT);
        this.snake.setDirection(Direction.LEFT);
        
        assertTrue(this.snake.getDirection() == Direction.RIGHT);
    }
    
    @Test
    public void impossibleToTurnRightWhenMovingLeft() {
        this.snake.setDirection(Direction.LEFT);
        this.snake.setDirection(Direction.RIGHT);
        
        assertTrue(this.snake.getDirection() == Direction.LEFT);
    }
    
    @Test
    public void hitsItself() {
        this.snake.move();
        
        this.snake.setDirection(Direction.LEFT);
        this.snake.move();
        
        this.snake.grow();
        this.snake.setDirection(Direction.UP);
        this.snake.move();
        
        this.snake.grow();
        this.snake.setDirection(Direction.RIGHT);
        this.snake.move();
        
        assertTrue(this.snake.hitsItself());
    }
    
    public void normalSnakeDoesNotHitItself() {
        assertFalse(this.snake.hitsItself());
    }
    
    @Test
    public void hitsOtherSnakeWhenOtherSnakesHeadHits() {
        for (int i = 0; i < 2; i++) {
            this.snake.move();
        }
        
        Snake snake2 = new Snake(13, 16, Direction.RIGHT);
        for (int i = 0; i < 2; i++) {
            snake2.move();
        }
        
        assertTrue(snake.hitsOtherSnake(snake2));
    }
    
    @Test
    public void doesNotHitOtherSnakeWhenOtherPartHits() {
        for (int i = 0; i < 2; i++) {
            this.snake.move();
        }
        
        Snake snake2 = new Snake(13, 16, Direction.RIGHT);
        for (int i = 0; i < 2; i++) {
            snake2.move();
        }
        
        assertFalse(snake2.hitsOtherSnake(snake));
    }
    
    @Test
    public void hitsPiece() {
        Piece piece = new Piece(15, 16);
        this.snake.move();
        
        assertTrue(this.snake.hits(piece));
    }
    
    @Test
    public void doesNotHitPiece() {
        Piece piece = new Piece(15, 16);
        
        assertFalse(this.snake.hits(piece));
    }
    
    @Test
    public void whenSnakeMovesDownNewLastPieceYIncreasesBy1() {
        this.snake.move();
        
        Piece piece = this.snake.getPieces().get(1);
        
        assertTrue(piece.getY() == 16);
    }
    
    @Test
    public void whenSnakeMovesUpNewLastPieceYDecreasesBy1() {
        this.snake.setDirection(Direction.LEFT);
        this.snake.setDirection(Direction.UP);
        
        this.snake.move();
        
        Piece piece = this.snake.getPieces().get(1);
        
        assertTrue(piece.getY() == 14);
    }
    
    @Test
    public void whenSnakeMovesLeftNewLastPieceXDecreasesBy1() {
        this.snake.setDirection(Direction.LEFT);
        
        this.snake.move();
        
        Piece piece = this.snake.getPieces().get(1);
        
        assertTrue(piece.getX() == 14);
    }
    
    @Test
    public void whenSnakeMovesRightNewLastPieceXIncreasesBy1() {
        this.snake.setDirection(Direction.RIGHT);
        
        this.snake.move();
        
        Piece piece = this.snake.getPieces().get(1);
        
        assertTrue(piece.getX() == 16);
    }
    
    @Test
    public void whenSnakeMovesButDoesNotGrowItsFirstPieceIsRemoved() {
        Piece piece1 = this.snake.getPieces().get(0);
        
        for (int i = 0; i < 3; i++) {
            snake.move();
        }
        
        Piece piece2 = this.snake.getPieces().get(0);
        
        assertFalse(piece1.equals(piece2));
    }
}
