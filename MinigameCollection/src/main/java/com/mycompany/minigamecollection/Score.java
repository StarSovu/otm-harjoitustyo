package com.mycompany.minigamecollection;


public class Score implements Comparable {
    private int score;
    private String username;
    
    public Score() {
        this.score = 0;
    }
    
    public void resetScore() {
        this.score = 0;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void increaseScore() {
        this.score++;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return this.username;
    }

    @Override
    public int compareTo(Object o) {
        
        Score score2 = (Score) o;
        
        return score2.getScore() - this.score;
    }
    
}
