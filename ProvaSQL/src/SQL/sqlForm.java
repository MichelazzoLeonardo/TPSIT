package SQL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class sqlForm {
    private JButton aggiungiButton;
    private JTextField textFieldNome;
    private JTextField textFieldCognome;
    private JSpinner spinnerEta;
    private javax.swing.JPanel JPanel;
    private DB db;

    public sqlForm() {
        db=new DB("05provasql","localhost","root","");
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    db.InsertInto(
                            textFieldNome.getText(),
                            textFieldCognome.getText(),
                            (Integer)spinnerEta.getValue()
                    );
                    JOptionPane.showMessageDialog(null, "inserimento avvenuto con successo");
                    textFieldNome.setText("");
                    textFieldCognome.setText("");
                    spinnerEta.setValue(0);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("sqlForm");
        frame.setContentPane(new sqlForm().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,250);
        frame.setVisible(true);
    }
}
