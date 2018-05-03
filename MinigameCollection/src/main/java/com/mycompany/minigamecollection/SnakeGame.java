
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
    private int winner; //in multiplayer: 0: not over, 1: snake1 (green), 2: snake2 (blue), 3: tie; is useless in single player
    
    public SnakeGame(int width, int height, Score score, boolean multiplayer) {
        this.width = width;
        this.height = height;
        this.score = score;
        this.multiplayer = multiplayer;
        
        if (multiplayer) {
            this.snake2 = new Snake(width / 2 - 3, height / 2 - 1, Direction.UP);
            this.snake = new Snake(width / 2 + 2, height / 2, Direction.DOWN);
        } else {
            this.snake = new Snake(width / 2, height / 2, Direction.DOWN);
            this.snake2 = new Snake(width + 1, height + 1, Direction.UP);
        }
        
        this.random = new Random();
        
        this.lemon = new Fruit(width + 1, height + 1);
        this.lemon.setType(false);
        
        this.orange = new Fruit(width + 1, height + 1);
        this.orange.setType(true);
        this.setRandomOrange();
        
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
    
    public void setRandomOrange() { 
        while (this.snake.hits(this.orange) || this.snake2.hits(this.orange) || this.orange.hits(this.lemon) || this.orange.getX() == width + 1) {
            this.orange = new Fruit(random.nextInt(width), random.nextInt(height));
        }
        this.orange.setType(true);
    }
    
    public void setRandomLemon() { 
        while (this.snake.hits(this.lemon) || this.snake2.hits(this.lemon) || this.orange.hits(this.lemon) || this.lemon.getX() == width + 1) {
            this.lemon = new Fruit(random.nextInt(width), random.nextInt(height));
        }
        this.lemon.setType(false);
    }
    
    public boolean end() {
        return this.end;
    }
    
    public int getWinner() {
        return this.winner;
    }
    
    public void update() {
        if (end) {
            return;
        }
        
        this.moveSnakes();
        
        this.player1End = this.gameOver(snake, snake2);
        
        if (multiplayer) {
            
            this.player2End = this.gameOver(snake2, snake);
        }
            
        if (player1End || player2End) {

            if (player1End) {
                winner += 2;
            }
            if (player2End) {
                winner++;
            }

            this.end = true;
        }
    }
    
    public void moveSnakes() {
        this.snake.move();
        
        if (multiplayer) {
            this.snake2.move();
        }
        
        if (this.snake.hits(this.orange)) {
            this.eatOrange(this.snake);
        }
        
        if (multiplayer) {
            if (this.snake2.hits(this.orange)) {
                this.eatOrange(this.snake2);
            }
        }
        
        if (!multiplayer) {
            if (this.snake.hits(this.lemon)) {
                this.eatLemon();
            }
        }
    }
    
    public void eatOrange(Snake snake) {
        snake.grow();
            
        if (!multiplayer) {
            this.score.increaseScore();
            System.out.println(this.score.getScore());
        }

        setRandomOrange();

        if ((this.score.getScore() % 10 == 0) && !multiplayer) {
            setRandomLemon();
        }
    }
    
    public void eatLemon() {
        this.score.increaseScore();
        this.score.increaseScore();
        System.out.println(this.score.getScore());
        
        if (this.score.getScore() % 10 == 0 || this.score.getScore() % 10 == 1) {
            this.setRandomLemon();
        } else {
            this.lemon = new Fruit(width + 1, height + 1);
            this.lemon.setType(false);
        }
    }
    
    public boolean gameOver(Snake snake, Snake otherSnake) {
        int x = snake.getPieces().get(snake.getLength() - 1).getX();
        int y = snake.getPieces().get(snake.getLength() - 1).getY();
        
        return (snake.hitsItself() || x < 0 || x >= this.width || y < 0 || y >= this.height || snake.hitsOtherSnake(otherSnake));
    }

}
