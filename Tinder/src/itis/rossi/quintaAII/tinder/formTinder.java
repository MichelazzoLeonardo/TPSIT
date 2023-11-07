package itis.rossi.quintaAII.tinder;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class formTinder {
    private javax.swing.JPanel JPanel;
    private JLabel labelImage;
    private JButton buttonLike;
    private JButton buttonDislike;
    private static Random rnd=new Random();
    private boolean[] likes;
    private boolean[] dislikes;

    public int SetImage(){
        int img=rnd.nextInt(5);
        labelImage.setIcon(new ImageIcon("src/itis/rossi/quintaAII/img/"+img+".jpg"));
        return img;
    }


    public formTinder() {
        SetImage();

        likes=new boolean[5];
        dislikes=new boolean[5];

        buttonLike.setSize(75,75);
        buttonDislike.setSize(75,75);

        buttonLike.setOpaque(false);
        buttonLike.setContentAreaFilled(false);
        buttonLike.setBorderPainted(false);
        buttonLike.setFocusPainted(false);

        buttonDislike.setOpaque(false);
        buttonDislike.setContentAreaFilled(false);
        buttonDislike.setBorderPainted(false);
        buttonDislike.setFocusPainted(false);
        buttonLike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n=SetImage();
                likes[n]=true;
                dislikes[n]=false;
            }
        });

        buttonDislike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n=SetImage();
                dislikes[n]=true;
                likes[n]=false;
            }
        });
    }
    public static void RunApplication() {
        JFrame frame = new JFrame("formTinder");
        frame.setContentPane(new formTinder().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,1000);
        frame.setVisible(true);
    }
}
