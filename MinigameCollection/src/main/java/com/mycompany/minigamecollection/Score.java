
package com.mycompany.minigamecollection;


public class Score {
    private int score;
    
    public Score() {
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
