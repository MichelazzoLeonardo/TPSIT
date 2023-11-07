package SQL;

import Calcolatrice.Calcolatrice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class sqlForm {
    private JButton buttonLogin;
    private JTextField textFieldUsername;
    private JPasswordField passwordFieldPassword;
    private javax.swing.JPanel JPanel;
    private JButton buttonRegister;
    private DB db;
    private static JFrame frame;

    public sqlForm() {
        db = new DB("06calcolatricesql", "localhost", "root", "");
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    db.InsertIntoUserdata(
                            textFieldUsername.getText(),
                            toPassword(passwordFieldPassword.getPassword())
                    );
                    textFieldUsername.setText("");
                    passwordFieldPassword.setText("");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = db.GetUsername(textFieldUsername.getText(), toPassword(passwordFieldPassword.getPassword()));
                if (!Objects.equals(user, null)) {
                    CloseForm();
                    Calcolatrice.OpenCalculator(user);
                } else {
                    JOptionPane.showMessageDialog(null, "username o password errati");
                    textFieldUsername.setText("");
                    passwordFieldPassword.setText("");
                }
            }
        });
    }

    private String toPassword(char[] chars) {
        String password = "";
        for (char c : chars)
            password += c;
        return password;
    }

    public static void main(String[] args) {
        frame = new JFrame("Login");
        frame.setContentPane(new sqlForm().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setVisible(true);
    }
    public static void OpenLoginForm(){
        main(null);
    }
    private static void CloseForm(){
        frame.dispose();
    }
}
