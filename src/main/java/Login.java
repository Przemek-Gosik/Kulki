import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    public JFrame frame;
    private JPanel loginPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton goToRegisterButton;

    public Login(JFrame jFrame) {
        this.frame =jFrame;
        frame.setContentPane(getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = new Dimension(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(dimension);
        frame.pack();
        frame.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login=textField1.getText();
                String password=String.valueOf(passwordField1.getPassword());
                DBOperations dbOperations = new DBOperations();

                    if (dbOperations.validateLogin(login, password)) {
                        User user = dbOperations.getUser();
                        jFrame.getContentPane().removeAll();
                        FirstMenu firstMenu = new FirstMenu(jFrame,user);
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Niepoprawne dane!",
                                "Błąd logowania",
                                JOptionPane.ERROR_MESSAGE);

                    }


            }
        });
        goToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signUpPanel = new SignUp(jFrame);
                jFrame.getContentPane().removeAll();
                jFrame.setContentPane(signUpPanel.getSignUpPanel());
                jFrame.revalidate();
                jFrame.pack();
            }
        });
    }

    public JPanel getPanel1() {
        return loginPanel;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JButton getZalogujSięButton() {
        return loginButton;
    }

}
