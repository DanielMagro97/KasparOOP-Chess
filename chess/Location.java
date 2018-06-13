package chess;

public class Location{
    private int x;
    private int y;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    // toString override, which returns a Location on the GameBoard in a readable manner (eg. a1, a2, ..., g8, h8 etc.)
    @Override
    public String toString() {
        return Character.toString((char) (x+65)).toLowerCase() + (y+1);
    }
}