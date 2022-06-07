import java.awt.*;
import java.util.ArrayList;

public class Ball {
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getXspeed() {
        return xspeed;
    }

    public void setXspeed(double xspeed) {
        this.xspeed = xspeed;
    }

    public double getYspeed() {
        return yspeed;
    }

    public void setYspeed(double yspeed) {
        this.yspeed = yspeed;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private double x;
    private double y;
    private double xspeed;
    private double yspeed;
    private double size;
    private Color color;
    private boolean isDead;
    public Ball(double x, double y, double size, double xspeed, double yspeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xspeed=xspeed;
        this.yspeed=yspeed;
        color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
        isDead=false;
    }
    public void update(){
        x += xspeed;y += yspeed;
    }
    public void hitWall(int height,int width) {

        if (x <= 0 || x+size >= width) {
            xspeed = -xspeed;

        }
        if (y <= 0 ) {
            yspeed = -yspeed;
        }
        if(y>= height){
            yspeed = 0 ;
            this.isDead=true;
        }
    }


    public void hitPlatform(Rectangle platform) {
        if (x >= (double) (platform.getX()-size)
                && x <= (double) (platform.getX() + platform.getWidth())
                && y == (double) platform.getY()-size) {
            yspeed = -yspeed;

        }
    }
    public int hitTarget(ArrayList<Rectangle>targets,int score){

        for(int i=0;i<targets.size();i++){
            Rectangle r = targets.get(i);
            int posX= (int)x;
            int posY = (int)y;
            if( (posX == r.getX()+r.getWidth() || posX == r.getX()-r.getWidth()) && posY <= r.getY()+r.getHeight() && posY >= r.getY()-r.getHeight() ){
                xspeed=-xspeed;
                targets.remove(i);
                score++;
                System.out.println(score);
            } else if ( (posY == r.getY()+r.getHeight() || posY==r.getY()- r.getHeight()) && posX <= r.getX()+r.getWidth() && posX >= r.getX()-r.getWidth() ) {
                yspeed=-yspeed;
                targets.remove(i);
                score++;
                System.out.println(score);
            }

        }
        return score;
    }
    public void kolizje(ArrayList<Ball> balls){
        double dist,distmin;
        double pomx,pomy;
        for (Ball k : balls) {
            distmin=(double)(this.size+k.size);
            dist=Math.sqrt(Math.pow(this.x-k.x,2)+Math.pow(this.y-k.y,2));
            if(dist<=distmin){
                pomx=k.xspeed;
                pomy=k.yspeed;
                k.xspeed=this.xspeed;
                k.yspeed=this.yspeed;
                this.xspeed=pomx;
                this.yspeed=pomy;
            }


        }
    }


    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}