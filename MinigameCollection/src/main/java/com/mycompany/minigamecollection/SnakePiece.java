
package com.mycompany.minigamecollection;


public class SnakePiece {
    private int x;
    private int y;
    
    public SnakePiece(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public boolean hits(SnakePiece piece) {
        return (piece.getX() == this.x && piece.getY() == this.y);
    }
    
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
