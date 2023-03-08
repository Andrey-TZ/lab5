package Model;

public class Coordinates {
    private Float x; //Значение поля должно быть больше -478, Поле не может быть null
    private float y;

    public Coordinates(Float x, float y){
        setX(x);
        setY(y);
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getX() {
        return x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
