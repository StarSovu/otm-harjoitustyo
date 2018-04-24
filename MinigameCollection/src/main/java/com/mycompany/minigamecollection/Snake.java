
package com.mycompany.minigamecollection;

import java.util.ArrayList;
import java.util.List;


public class Snake {
    private List<Piece> snake;
    private Direction direction;
    private boolean grows;
    
    public Snake(int startX, int startY, Direction startDirection) {
        this.direction = startDirection;
        this.snake = new ArrayList<>();
        this.snake.add(new Piece(startX, startY));
        this.grows = true;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public void setDirection(Direction direction) {
        int snakeDirection = this.direction.ordinal();
        int newDirection = direction.ordinal();
        
        if (snakeDirection + newDirection != 3) {
            this.direction = direction;
        }
    }
    
    public int getLength() {
        return this.snake.size();
    }
    
    public List<Piece> getPieces() {
        return this.snake;
    }
    
    public void move() {
        int x = this.snake.get(this.snake.size() - 1).getX();
        int y = this.snake.get(this.snake.size() - 1).getY();
        if (null != this.direction) switch (this.direction) {
            case UP:
                y--;
                break;
            case RIGHT:
                x++;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            default:
                break;
        }
        
        this.snake.add(new Piece(x, y));
        if (!grows) {
            this.snake.remove(this.snake.get(0));
        }
        
        if (this.snake.size() >= 3) {
            grows = false;
        }
        
    }
    
    public void grow() {
        grows = true;
    }
    
    public boolean hits(Piece piece) {
        boolean hits = false;
        for (int i = 0; i < this.snake.size(); i++) {
            if (this.snake.get(i).hits(piece)) {
                hits = true;
            }
        }
        return hits;
    }
    
    public boolean hitsItself() {
        boolean hits = false;
        
        for (int i = 0; i < this.snake.size() - 1; i++) {
            for (int i2 = i + 1; i2 < this.snake.size(); i2++) {
                if (this.snake.get(i2).hits(this.snake.get(i))) {
                    hits = true;
                }
            }
        }
        return hits;
    }
    
    public boolean hitsOtherSnake(Snake otherSnake) {
        List<Piece> snake2 = otherSnake.getPieces();
        Piece snake2Head = snake2.get(snake2.size() - 1);
        
        return this.hits(snake2Head);
    }
    
}