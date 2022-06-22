import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Panel extends JPanel {
    private JFrame jFrame;
    private User user;
    private Setting setting;
    private ArrayList<Ball> listaKul;
    private ArrayList<Rectangle> listaProst;
    private Rectangle rectangle;
    private long timeStart;
    private long timeEnd;
    private Timer timer;
    private Level level;
    public int score=0;
    private final int DELAY = 22;
    private EntityManagerFactory managerFactory;

    public Panel(JFrame jFrame1,Level level,User user,EntityManagerFactory managerFactory1) {
        this.jFrame=jFrame1;
        this.level=level;
        this.user=user;
        this.managerFactory=managerFactory1;
        setting=new Setting(level);
        listaKul = new ArrayList<>();
        for(int i=0;i<setting.getNumberOfBalls();i++) {
            int min = 50+100*i;
            int max = 100+100*i;
            int randomNum = ThreadLocalRandom.current().nextInt(min,(min+max));
            Ball ball = new Ball(randomNum, 450, 20, -2, -10);
            listaKul.add(ball);
        }
        listaProst = new ArrayList<>();
        for (int i=0;i<setting.getNumberOfRows();i++) {
            for (int j = 0; j < 14; j++) {

                listaProst.add(new Rectangle(50 + (50 * j), 20+(50*i), 30, 30, Color.BLUE));
            }
        }
        rectangle = new Rectangle(120,470,setting.getPlatformWidth(),30,Color.WHITE);
        setBackground(Color.BLACK);
        addMouseListener(new Event());
        addMouseWheelListener(new Event());
        addMouseMotionListener(new Event());
        addKeyListener(new Event());
        timer = new Timer(DELAY, new Event());
        timer.start();
        timeStart=System.currentTimeMillis();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Ball k : listaKul) {
            g.setColor(k.getColor());
            g.drawOval((int)k.getX(),(int) k.getY(), (int)k.getSize(),(int) k.getSize());
            g.fillOval((int)k.getX(),(int)k.getY(),(int)k.getSize(),(int)k.getSize());
        }
        for(Rectangle r: listaProst){
            g.setColor(r.getColor());
            g.drawRect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
            g.fillRect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
        }
        g.setColor(rectangle.getColor());
        g.drawRoundRect(rectangle.getX(),rectangle.getY(),rectangle.getWidth(),rectangle.getHeight(),10,10);
        g.fillRoundRect(rectangle.getX(),rectangle.getY(),rectangle.getWidth(),rectangle.getHeight(),10,10);
        g.drawString(String.valueOf(score),40,40);
    }
    private void endgame(){
        timeEnd=System.currentTimeMillis();
        float time = (float)(timeEnd-timeStart)/(float) 1000.0;
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        String date=dtf.format(dateTime);
        int max = 14*setting.getNumberOfRows();
        JFrame frame = new JFrame("Koniec!");
        int numer = user.getResults().size();
        Result result = new Result(numer,date,score,max,level,time);
        FinalMenu menu = new FinalMenu(frame,result,user,managerFactory,jFrame);
        frame.setContentPane(menu.getjPanel3());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = new Dimension(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(dimension);
        frame.pack();
        frame.setVisible(true);
    }

    private class Event implements MouseListener, ActionListener, MouseWheelListener,MouseMotionListener,KeyListener {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i=0;i<listaKul.size();i++) {
                listaKul.get(i).update();
                listaKul.get(i).hitPlatform(rectangle);
                listaKul.get(i).hitWall(getHeight(),getWidth());
                score=listaKul.get(i).hitTarget(listaProst,score);
                listaKul.get(i).kolizje(listaKul);
                if(listaKul.get(i).isDead()){
                    listaKul.remove(i);
                }
                if(listaKul.isEmpty() || listaProst.isEmpty()){
                    timer.stop();

                    endgame();
                    break;
                }
            }

            repaint();
        }
        @Override
        public void mouseWheelMoved(MouseWheelEvent e){
        }

        @Override
        public void keyTyped(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_LEFT :
                    rectangle.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    rectangle.moveRight(getWidth());
                    break;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

                int code= e.getKeyCode();
                switch (code){
                    case KeyEvent.VK_LEFT :
                        rectangle.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        rectangle.moveRight(getWidth());
                        break;
                }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int code= e.getKeyCode();
            switch (code){
                case KeyEvent.VK_LEFT :
                    rectangle.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    rectangle.moveRight(getWidth());
                    break;
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(e.getY()>=rectangle.getY()-rectangle.getHeight()){
                rectangle.setX(e.getX());
            }
        }
    }


}
