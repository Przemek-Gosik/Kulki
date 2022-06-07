import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp {
    public JFrame jFrame;
    private JTextField textField1;

    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton registerButton;
    private JPanel signUpPanel;
    private JButton backToLoginButton;

    public JPanel getSignUpPanel() {

        return signUpPanel;
    }



    public SignUp(JFrame jFrame) {
        this.jFrame=jFrame;
        registerButton.addActionListener(e-> {
                String login=textField1.getText();
                String pass1=String.valueOf(passwordField1.getPassword());
                String pass2=String.valueOf(passwordField2.getPassword());
                    if(validateUsername(login) && validatePassword(pass1,pass2)){
                        User user=new User(login,pass1);
                        try {

                            DBOperations.addToDatabase(user);
                            jFrame.getContentPane().removeAll();
                            FirstMenu firstMenu = new FirstMenu(jFrame,user);
                        }catch (Exception exception){
                            JOptionPane.showMessageDialog(new JFrame(),"Nie udalo sie utworzyc konta o podanym loginie, login może być już zajęty",
                                    "Błąd",JOptionPane.ERROR_MESSAGE);
                        }



                    }else{
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Wprowadzone dane nie spełniają wymagań!",
                                "Błąd danych",
                                JOptionPane.ERROR_MESSAGE);

                    }

        });
        backToLoginButton.addActionListener(e-> {
                Login loginPanel=new Login(jFrame);

        });
    }
    public boolean validateUsername(String login){
        return login.length() > 3 && login.length() < 20;
    }
    public boolean validatePassword(String password1,String password2){
        if(password1.equals(password2) && !password1.isEmpty()){

            String regex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.?!@#$%^&-+=()])(?=\\S+$).{8,20}$";
            //hasło musi zawierać przynajmniej jedną małą i duża literę, cyfrę oraz znak specjalny, spacje nie są dopuszczone
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher =  pattern.matcher(password1);
            return matcher.matches();
        }else {

            return false;
        }
    }

}
