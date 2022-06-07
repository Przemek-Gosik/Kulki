import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalMenu {
    private JButton zapiszGręButton;
    private JButton backToFirstMenuButton;

    private JLabel resultText;
    private JPanel jPanel3;
    private JLabel timeResult;
    private JButton finishButton;
    private User user;
    private Result result;
    private JFrame jFrame;

    public FinalMenu(Result result,User user,JFrame jFrame) {
        this.result=result;
        this.user=user;
        this.jFrame=jFrame;
        timeResult.setText(timeFormat());
        resultText.setText(Integer.toString(result.getScore()));
        zapiszGręButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBOperations.addToDatabase(result);
                DBOperations.updateUser(user,result);
                JOptionPane.showMessageDialog(new JFrame(),
                        "Zapisano wynik","Sukces",JOptionPane.INFORMATION_MESSAGE);

            }
        });
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        backToFirstMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.getContentPane().removeAll();
                FirstMenu firstMenu = new FirstMenu(jFrame,user);
            }
        });
    }

    public JPanel getjPanel3() {
        return jPanel3;
    }
    private String timeFormat(){
        float timePom=result.getTime();
        String sTime;
        if(timePom>60){
            timePom /=60.0;
            int min = (int)timePom;
            float sec=timePom-min;
            sTime=Integer.toString(min)+" min "+String.valueOf(sec)+" sec";
        }else {
            sTime=String.valueOf(timePom)+" sec";
        }
        return sTime;
    }
}
