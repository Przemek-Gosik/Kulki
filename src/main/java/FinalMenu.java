import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.awt.*;


public class FinalMenu {
    private JButton zapiszGręButton;
    private JButton backToFirstMenuButton;
    private JFrame gameFrame, jFrame;
    private JLabel resultText;
    private JPanel jPanel3;
    private JLabel timeResult;
    private JButton finishButton;
    private User user;
    private EntityManagerFactory managerFactory;
    private Result result;


    public FinalMenu(JFrame jFrame1, Result result, User user, EntityManagerFactory managerFactory1, JFrame gameFrame1) {
        this.jFrame = jFrame1;
        this.result = result;
        this.user = user;
        this.managerFactory = managerFactory1;
        this.gameFrame = gameFrame1;
        timeResult.setText(timeFormat());
        resultText.setText(Integer.toString(result.getScore()));
        zapiszGręButton.addActionListener(e -> {
            DBOperations operations;
            operations = DBOperations.getInstance(managerFactory);
            operations.addToDatabase(result);
            operations.updateUser(user, result);
            zapiszGręButton.setEnabled(false);
            JOptionPane.showMessageDialog(new JFrame(),
                    "Zapisano wynik", "Sukces", JOptionPane.INFORMATION_MESSAGE);


        });
        finishButton.addActionListener(e -> System.exit(0));
        backToFirstMenuButton.addActionListener(e -> {
            gameFrame.dispose();
            jFrame.getContentPane().removeAll();
            new FirstMenu(jFrame, user, managerFactory);

        });
    }

    public JPanel getjPanel3() {
        return jPanel3;
    }

    private String timeFormat() {
        float timePom = result.getTime();
        String sTime;
        if (timePom > 60) {
            timePom /= 60.0;
            int min = (int) timePom;
            float sec = timePom - min;
            sTime = min + " min " + sec + " sec";
        } else {
            sTime = timePom + " sec";
        }
        return sTime;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        jPanel3 = new JPanel();
        jPanel3.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        label1.setText("Koniec !");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        jPanel3.add(label1, gbc);
        zapiszGręButton = new JButton();
        zapiszGręButton.setText("Zapisz grę");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel3.add(zapiszGręButton, gbc);
        backToFirstMenuButton = new JButton();
        backToFirstMenuButton.setText("Powrót do menu głównego");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel3.add(backToFirstMenuButton, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Twój wynik to:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jPanel3.add(label2, gbc);
        resultText = new JLabel();
        resultText.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel3.add(resultText, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Czas:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jPanel3.add(label3, gbc);
        timeResult = new JLabel();
        timeResult.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel3.add(timeResult, gbc);
        finishButton = new JButton();
        finishButton.setText("Zakończ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel3.add(finishButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return jPanel3;
    }

}
