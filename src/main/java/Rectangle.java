import java.awt.*;

public class Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }
    public void moveLeft(){
        if(x>=5) {
            this.x -= 5;
        }
    }
    public void moveRight(int width){
        if(x<=width-this.getWidth()) {
            this.x += 5;
        }
    }

    public int getX() {
        return x;
    }
    public void setX(int x){
        this.x=x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Color getColor() {
        return color;
    }
}
