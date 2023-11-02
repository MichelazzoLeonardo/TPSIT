package itis.rossi.quintaAII.tinder;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formLogin {
    private javax.swing.JPanel JPanel;
    private JLabel formTitle;
    private JLabel labelUsername;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JLabel labelPassword;
    private JButton buttonLogin;

    public formLogin() {
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldUsername.getText().isEmpty())
                    JOptionPane.showMessageDialog(null,"inserire un nome utente");
                else if (passwordField.getPassword().length==0)
                    JOptionPane.showMessageDialog(null,"inserire una password");
                else {
                    JOptionPane.showMessageDialog(null, "Login effettuato come '" + textFieldUsername.getText() + '\'');
                    formTinder.RunApplication();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("formLogin");
        frame.setContentPane(new formLogin().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
