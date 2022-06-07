import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DifficultChoice {
    private JRadioButton easyRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton hardRadioButton;
    private JRadioButton veryHardRadioButton;
    private JRadioButton veryEasyRadioButton;
    private JButton startGameButton;

    public JPanel getJpanel2() {
        return jpanel2;
    }

    private JPanel jpanel2;
    private Level choice;

    public DifficultChoice(User user) {
        veryEasyRadioButton.setSelected(true);
        choice=Level.VeryEasy;
        ButtonGroup buttonGroup= new ButtonGroup();
        buttonGroup.add(veryEasyRadioButton);
        buttonGroup.add(easyRadioButton);
        buttonGroup.add(mediumRadioButton);
        buttonGroup.add(hardRadioButton);
        buttonGroup.add(veryHardRadioButton);
        veryEasyRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if(state == ItemEvent.SELECTED){
                    choice=Level.VeryEasy;
                }
            }
        });
        easyRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if(state == ItemEvent.SELECTED){
                    choice=Level.Easy;
                }
            }
        });
        mediumRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if(state == ItemEvent.SELECTED){
                    choice=Level.Medium;
                }
            }
        });
        hardRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if(state == ItemEvent.SELECTED){
                    choice=Level.Hard;
                }
            }
        });
        veryHardRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if(state == ItemEvent.SELECTED){
                    choice=Level.VeryHard;
                }
            }
        });
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Moje okno!");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Panel panel = new Panel(choice,user);
                frame.getContentPane().add(panel);
                frame.setPreferredSize(new Dimension(800, 600));
                frame.pack();
                frame.setLocationRelativeTo(null);
                panel.setFocusable(true);
                panel.requestFocus();
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });


    }
}
