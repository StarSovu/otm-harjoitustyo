
package com.mycompany.minigamecollection;

import java.util.ArrayList;
import java.util.Collections;


public class ScoreList {
    private ArrayList<Score> scores;
    
    public ScoreList() {
        this.scores = new ArrayList<>();
    }
    
    public void addScore(Score score) {
        this.scores.add(score);
        Collections.sort(this.scores);
        
        if (scores.size() > 10) {
            scores.remove(10);
        }
    }
    
    public ArrayList<Score> getScores() {
        return this.scores;
    }
    
}
