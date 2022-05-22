import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Panel extends JPanel {
    private ArrayList<Kula> listaKul;
    public double size = 20;
    private Timer timer;
    private final int DELAY = 33;
    //dla 30fps -> 1s/30 = 0,033s
    public Panel() {
        listaKul = new ArrayList<>();
        setBackground(Color.BLACK);
        addMouseListener(new Event());
        addMouseWheelListener(new Event());
        timer = new Timer(DELAY, new Event());
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Kula k : listaKul) {
            g.setColor(k.color);
            g.drawOval((int)k.x,(int) k.y, (int)k.size,(int) k.size);
            g.fillOval((int)k.x,(int)k.y,(int)k.size,(int)k.size);
        }
        g.setColor(Color.RED);
        g.drawString(Integer.toString(listaKul.size()),40,40);
    }
    private class Event implements MouseListener, ActionListener, MouseWheelListener {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1){
                listaKul.add(new Kula(e.getX(), e.getY(), size));
            }
            if(e.getButton() == MouseEvent.BUTTON3 && listaKul.isEmpty()==false) {
                listaKul.remove(listaKul.size()-1);
            }
            repaint();
        }
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {
            for (Kula k : listaKul) {
                k.update3();

                repaint();
            }

        }
        @Override
        public void mouseExited(MouseEvent e) {
            for (Kula k : listaKul) {
                k.update2();

                repaint();
            }
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Kula k : listaKul) {
                k.update();
                k.kolizje();
            }

            repaint();
        }
        @Override
        public void mouseWheelMoved(MouseWheelEvent e){
            int ruch = e.getWheelRotation();
            if(ruch<0){
                size-=1;

            }
            else{
                size+=1;
            }
            if(size<=0){
                size=20;
            }
            repaint();
        }

    }
    private class Kula {
        public double x, y, size, xspeed, yspeed,xspeedzap,yspeedzap;
        public Color color;
        private final double MAX_SPEED = 10;
        public Kula(double x, double y, double size) {
            this.x = x;
            this.y = y;
            this.size = size;
            color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
            do{
                xspeed = (double) (Math.random() * MAX_SPEED * 2 -MAX_SPEED);
                yspeed = (double) (Math.random() * MAX_SPEED * 2 -MAX_SPEED);
            }while(xspeed==0 && yspeed==0);
        }
        public void update() {
            x += xspeed;y += yspeed;

            if (x <= 0 || x >= getWidth()) {
                xspeed = -xspeed;
            }
            if (y <= 0 || y >= getHeight()) {yspeed = -yspeed;
            }
        }
        public void update2(){
            xspeedzap=xspeed;
            yspeedzap=yspeed;
            xspeed=0;
            yspeed=0;

            setBackground(Color.WHITE);

        }
        public void update3(){
            xspeed=xspeedzap;
            yspeed=yspeedzap;
            setBackground(Color.BLACK);
        }
        public void prostyZapis(double x, double y, double size) {
            File file = new File("WynikZapisu.txt");
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                fileWriter.append(x+"\n"+y+"\n"+size+"\n");
                fileWriter.close();
            }
            catch (IOException ex) {
                System.err.println(ex.getCause());
            }
        }
        public void kolizje(){
            double dist,distmin;
            double pomx,pomy;
            for (Kula k : listaKul) {
                distmin=(double)(this.size+k.size);
                dist=Math.sqrt(Math.pow(this.x-k.x,2)+Math.pow(this.y-k.y,2));
                if(dist<=distmin){
                    pomx=k.xspeed;
                    pomy=k.yspeed;
                    k.xspeed=this.xspeed;
                    k.yspeed=this.yspeed;
                    this.xspeed=pomx;
                    this.yspeed=pomy;
                    if(this!=k){
                        prostyZapis(this.x,this.y,this.size);
                        prostyZapis(k.x,k.y,k.size);
                    }


                }


            }
        }

    }

}
