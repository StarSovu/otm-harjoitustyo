
package com.mycompany.minigamecollection.Pieces;

import com.mycompany.minigamecollection.Pieces.Piece;

public class Fruit extends Piece {
    private boolean type; //true: orange, false: lemon
    
    public Fruit(int x, int y) {
        super(x, y);
    }
    
    public void setType(boolean orange) {
        this.type = orange;
    }
    
    public boolean isOrange() {
        return this.type;
    }
    
}
