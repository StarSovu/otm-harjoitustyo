
package com.mycompany.minigamecollection;

import java.util.Random;


public class SnakeGame {
    private int width;
    private int height;
    private boolean end;
    private SnakeOrange orange;
    private Snake snake;
    private Random random;
    private SnakeScore score;
    
    public SnakeGame(int width, int height, SnakeScore score) {
        this.width = width;
        this.height = height;
        this.score = score;
        
        this.snake = new Snake(width/2, height/2, Direction.DOWN);
        
        this.random = new Random();
        int x = width/2;
        int y = width/2;
        while (x == width/2 && y == width/2) {
            x = this.random.nextInt(width);
            y = this.random.nextInt(height);
        }
        this.orange = new SnakeOrange(x, y);
        
        this.end = false;
    }
    
    public Snake getSnake() {
        return this.snake;
    }
    
    public void setSnake(Snake snake) {
        this.snake = snake;
    }
    
    public SnakeOrange getOrange() {
        return this.orange;
    }
    
    public void setOrange(SnakeOrange orange) {
        this.orange = orange;
    }
    
    public boolean end() {
        return this.end;
    }
    
    public void update() {
        this.snake.move();
        
        if (this.snake.hits(this.orange)) {
           this.snake.grow();
           this.score.increaseScore();
           
           while (this.snake.hits(this.orange)) {
               System.out.println(this.score.getScore());
               this.orange = new SnakeOrange(random.nextInt(width), random.nextInt(height));
           }
        }
        
        int x = this.snake.getPieces().get(this.snake.getLength() - 1).getX();
        int y = this.snake.getPieces().get(this.snake.getLength() - 1).getY();
        
        if(this.snake.hitsItself()|| x < 0 || x >= this.width || y < 0 || y >= this.height) {
            this.end = true;
        }
    }
}
