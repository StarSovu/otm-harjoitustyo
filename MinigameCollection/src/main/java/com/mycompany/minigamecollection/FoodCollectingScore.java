
package com.mycompany.minigamecollection;


public class FoodCollectingScore {
    private int score;
    private int totalFood;
    private int[] collectedFood;
    
    public FoodCollectingScore() {
        this.score = 0;
        this.collectedFood = new int[5]; //strawberry: 0
        this.totalFood = 0;
    }
    
    public void collectStrawberry() {
        collectedFood[0]++;
        this.totalFood++;
        
        this.score += totalFood/collectedFood[0];
    }
    
    public void collectChocolate() {
        collectedFood[1]++;
        this.totalFood++;
        
        this.score += totalFood/4 - collectedFood[1];
    }
    
    public int score() {
        return this.score;
    }
}
