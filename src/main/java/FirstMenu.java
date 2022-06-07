import javax.swing.*;
import java.awt.*;

public class FirstMenu {
    private JButton zacznijGręButton;
    private User user;

    private JPanel panel1;
    private JButton changeLoginButton;
    private JButton gameListButton;
    JFrame jFrame;
    public FirstMenu(JFrame jFrame1,User user){
        this.jFrame=jFrame1;
        this.user=user;
        jFrame.setPreferredSize(new Dimension(400,200));
        jFrame.setContentPane(getPanel1());
        jFrame.revalidate();
        jFrame.pack();
        zacznijGręButton.addActionListener(e -> {
            jFrame.getContentPane().removeAll();
            DifficultChoice difficultChoice= new DifficultChoice(user);
            jFrame.setContentPane(difficultChoice.getJpanel2());
            jFrame.setPreferredSize(new Dimension(400,300));
            jFrame.pack();
            jFrame.revalidate();
        });
        changeLoginButton.addActionListener(e ->{

                jFrame.getContentPane().removeAll();
                ChangeLogin changeLogin=new ChangeLogin(jFrame,user);
                jFrame.setContentPane(changeLogin.getChangeLogin());
                jFrame.pack();
                jFrame.revalidate();

        });
        gameListButton.addActionListener(e -> {
            jFrame.getContentPane().removeAll();

            //GameList list = new GameList(user,jFrame);
            jFrame.setPreferredSize(new Dimension(600,300));
            //jFrame.setContentPane(list.getjPanel());
            GList gList=new GList(jFrame,user);
            jFrame.setContentPane(gList.getPanelList());
            jFrame.revalidate();
            jFrame.pack();



        });

    }



    public JPanel getPanel1() {
        return panel1;
    }

}
