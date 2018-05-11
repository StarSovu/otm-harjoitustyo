
package com.mycompany.minigamecollection.Score;

import com.mycompany.minigamecollection.Score.Score;
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
    
    @Override
    public String toString() {
        String highscoretext = "Highscores:\n";
        for (int i = 0; i < this.scores.size(); i++) {
            Score score = this.scores.get(i);
            highscoretext += "\n" + (i+1) + ". " + score.getUsername() + ": " + score.getScore();
        }
        
        return highscoretext;
    }
    
}
