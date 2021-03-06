
package com.mycompany.minigamecollection.Pieces;


public class Piece {
    private int x;
    private int y;
    
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public boolean hits(Piece piece) {
        if (piece==null) {
            System.out.println("");
        }
        return (piece.getX() == this.x && piece.getY() == this.y);
    }
    
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
