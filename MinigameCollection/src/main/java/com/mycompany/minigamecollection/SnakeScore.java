
package com.mycompany.minigamecollection;


public class SnakeScore {
    private int score;
    
    public SnakeScore() {
        this.score = 0;
    }
    
    public void resetScore() {
        this.score = 0;
    }
    
    public void increaseScore() {
        this.score++;
    }
    
    public int getScore() {
        return this.score;
    }
    
}
