
package com.mycompany.minigamecollection;

import java.util.Random;


public class SnakeGame {
    private int width;
    private int height;
    private boolean multiplayer;
    private boolean end;
    private boolean player1End;
    private boolean player2End;
    private Fruit orange;
    private Fruit lemon;
    private Snake snake;
    private Snake snake2;
    private Random random;
    private Score score;
    private int winner; //0: not over/single player, 1: snake1 (green), 2: snake2 (blue), 3: tie
    
    public SnakeGame(int width, int height, Score score, boolean multiplayer) {
        this.width = width;
        this.height = height;
        this.score = score;
        this.multiplayer = multiplayer;
        
        if (multiplayer) {
            this.snake2 = new Snake(width/2 - 3, height/2 - 1, Direction.UP);
            this.snake = new Snake(width/2 + 2, height/2, Direction.DOWN);
        } else {
            this.snake = new Snake(width/2, height/2, Direction.DOWN);
        }
        
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
        this.player1End = false;
        this.player2End = false;
        this.winner = 0;
    }
    
    public Snake getSnake() {
        return this.snake;
    }
    
    public Snake getSnake2() {
        return this.snake2;
    }
    
    public void setSnake(Snake snake) {
        this.snake = snake;
    }
    
    public void setSnake2(Snake snake2) {
        this.snake2 = snake2;
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
    
    public int getWinner() {
        return this.winner;
    }
    
    public void update() {
        this.snake.move();
        
        if (multiplayer) {
            this.snake2.move();
        }
        
        if (this.snake.hits(this.orange)) {
            this.snake.grow();
            
            if (!multiplayer) {
                this.score.increaseScore();
                System.out.println(this.score.getScore());
            }

            while (this.snake.hits(this.orange)) {
                this.orange = new Fruit(random.nextInt(width), random.nextInt(height));
            }
            this.orange.setType(true);
           
            if ((this.score.getScore() % 10 == 0) && !multiplayer) {
                this.lemon = new Fruit(random.nextInt(width), random.nextInt(height));
                while (this.snake.hits(this.lemon)) {
                    this.lemon = new Fruit(random.nextInt(width), random.nextInt(height));
                }
                this.lemon.setType(false);
           }
        }
        
        if (multiplayer) {
            if (this.snake2.hits(this.orange)) {
                this.snake2.grow();

                while (this.snake2.hits(this.orange)) {
                    this.orange = new Fruit(random.nextInt(width), random.nextInt(height));
                }
                this.orange.setType(true);
            }
        }
        
        if (!multiplayer) {
            if (this.snake.hits(this.lemon)) {
                this.score.increaseScore();
                this.score.increaseScore();
                System.out.println(this.score.getScore());

                this.lemon = new Fruit(width + 1, height + 1);
                this.lemon.setType(false);
            }
        }
        
        int x = this.snake.getPieces().get(this.snake.getLength() - 1).getX();
        int y = this.snake.getPieces().get(this.snake.getLength() - 1).getY();
        
        if (multiplayer) {
            
            int x2 = this.snake2.getPieces().get(this.snake2.getLength() - 1).getX();
            int y2 = this.snake2.getPieces().get(this.snake2.getLength() - 1).getY();
            
            if(this.snake.hitsItself()|| x < 0 || x >= this.width || y < 0 || y >= this.height || snake2.hitsOtherSnake(snake)) {
                this.player1End = true;
            }
            
            if(this.snake2.hitsItself()|| x2 < 0 || x2 >= this.width || y2 < 0 || y2 >= this.height || snake.hitsOtherSnake(snake2)) {
                this.player2End = true;
            }
            
            if(player1End || player2End) {
                
                if(player1End) {
                    winner += 2;
                }
                if(player2End) {
                    winner ++;
                }
                
                this.end = true;
            }
            
        } else {
            if(this.snake.hitsItself()|| x < 0 || x >= this.width || y < 0 || y >= this.height) {
                this.end = true;
            }
        }
    }
}
