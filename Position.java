public class Position {

    int x;
    int y;
    int filling;
    int distance;

    public Position(int x, int y, int distance){
        this.x = x;
        this.y = y;
        this.filling = 0;
        this.distance = distance;
    }

    protected void setFilling(int newFill){
        filling = newFill;
    }

    protected int getFilling(){
        return filling;
    }

    protected int getDistance(){
        return distance;
    }
}
