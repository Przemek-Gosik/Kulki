import javax.swing.*;

public class ChangeLogin {
    public JFrame jFrame;
    private JTextField textField1;
    private JButton acceptNewLoginButton;
    private JButton deleteAccountButton;
    private JButton backToFirstMenuButton;

    public JPanel getChangeLogin() {
        return changeLogin;
    }

    private JPanel changeLogin;

    public ChangeLogin(JFrame jFrame,User user) {
        this.jFrame=jFrame;
        acceptNewLoginButton.addActionListener(e -> {
            String newLogin;
            newLogin=textField1.getText();
            try {
                DBOperations.changeLogin(user, newLogin);
                JOptionPane.showMessageDialog(new JFrame(),
                        "Dokonano zmiany loginu","Sukces",JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception exception){
                JOptionPane.showMessageDialog(new JFrame(),"Podany login jest zajęty!","Błąd",JOptionPane.ERROR_MESSAGE);
            }



        });
        backToFirstMenuButton.addActionListener(e-> {

                FirstMenu firstMenu=new FirstMenu(jFrame,user);

        });
        deleteAccountButton.addActionListener(e-> {

                DBOperations.deleteAccount(user.getId());
                JOptionPane.showMessageDialog(new JFrame(),
                        "Usunięto użytkownika","Sukces",JOptionPane.INFORMATION_MESSAGE);
                jFrame.getContentPane().removeAll();
                Login login=new Login(jFrame);

        });

    }
}
