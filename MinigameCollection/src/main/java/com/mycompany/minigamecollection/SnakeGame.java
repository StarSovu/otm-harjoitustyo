
package com.mycompany.minigamecollection;

import java.util.Random;


public class SnakeGame {
    private int width;
    private int height;
    private boolean end;
    private Fruit orange;
    private Fruit lemon;
    private Snake snake;
    private Random random;
    private Score score;
    
    public SnakeGame(int width, int height, Score score) {
        this.width = width;
        this.height = height;
        this.score = score;
        
        this.snake = new Snake(width/2, height/2, Direction.DOWN);
        
        this.random = new Random();
        int x = width/2;
        int y = height/2;
        while (x == width/2 && y == height/2) {
            x = this.random.nextInt(width);
            y = this.random.nextInt(height);
        }
        this.orange = new Fruit(x, y);
        this.orange.setType(true);
        
        this.lemon = new Fruit(width + 1, height + 1);
        this.lemon.setType(false);
        
        this.end = false;
    }
    
    public Snake getSnake() {
        return this.snake;
    }
    
    public void setSnake(Snake snake) {
        this.snake = snake;
    }
    
    public Fruit getOrange() {
        return this.orange;
    }
    
    public Fruit getLemon() {
        return this.lemon;
    }
    
    public void setFruit(Fruit fruit) {
        if (fruit.isOrange()) {
            this.orange = fruit;
        } else {
            this.lemon = fruit;
        }
    }
    
    public boolean end() {
        return this.end;
    }
    
    public void update() {
        this.snake.move();
        
        if (this.snake.hits(this.orange)) {
           this.snake.grow();
           this.score.increaseScore();
           System.out.println(this.score.getScore());
           
           while (this.snake.hits(this.orange)) {
               this.orange = new Fruit(random.nextInt(width), random.nextInt(height));
           }
           this.orange.setType(true);
           
           if (this.score.getScore() % 10 == 0) {
                this.lemon = new Fruit(random.nextInt(width), random.nextInt(height));
                while (this.snake.hits(this.lemon)) {
                    this.lemon = new Fruit(random.nextInt(width), random.nextInt(height));
                }
                this.lemon.setType(false);
           }
        }
        
        if (this.snake.hits(this.lemon)) {
            this.score.increaseScore();
            this.score.increaseScore();
            System.out.println(this.score.getScore());
            
            this.lemon = new Fruit(width + 1, height + 1);
            this.lemon.setType(false);
        }
        
        int x = this.snake.getPieces().get(this.snake.getLength() - 1).getX();
        int y = this.snake.getPieces().get(this.snake.getLength() - 1).getY();
        
        if(this.snake.hitsItself()|| x < 0 || x >= this.width || y < 0 || y >= this.height) {
            this.end = true;
        }
    }
}
