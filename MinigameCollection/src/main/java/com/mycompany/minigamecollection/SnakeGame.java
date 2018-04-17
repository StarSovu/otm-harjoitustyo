
package com.mycompany.minigamecollection;

import java.util.Random;


public class SnakeGame {
    private int width;
    private int height;
    private boolean end;
    private SnakeOrange orange;
    private Snake snake;
    private Random random;
    
    public SnakeGame(int width, int height) {
        this.width = width;
        this.height = height;
        
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
    
    public void setMato(Snake snake) {
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
           
           while (this.snake.hits(this.orange)) {
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
