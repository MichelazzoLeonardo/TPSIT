package Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class statsForm {
    private javax.swing.JPanel JPanel;
    private JTextField textFieldElements;
    private JLabel ArrayCreationLabel;
    private JLabel ArrayInsertLabel;
    private JLabel ArrayListCreationLabel;
    private JLabel ArrayListInsertLabel;
    private JLabel LinkedListCreationLabel;
    private JLabel LinkedListInsertLabel;
    private JLabel HashMapCreationLabel;
    private JLabel HashMapInsertLabel;
    private JLabel TreeMapCreationLabel;
    private JLabel TreeMapInsertLabel;
    private JButton buttonStart;

    public statsForm() {
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Start();
            }
        });
    }

    public void Start() {
        try {
            long tempoInizio;
            int elements = Integer.parseInt(textFieldElements.getText());

            //////////////////////////////////////////////////////////////////
            //              ARRAY
            //////////////////////////////////////////////////////////////////
            tempoInizio = System.nanoTime();
            int[] array = new int[elements];
            ArrayCreationLabel.setText(System.nanoTime() - tempoInizio + " ns");

            tempoInizio = System.nanoTime();
            for (int i = 0; i < elements; i++)
                array[i] = 1;
            ArrayInsertLabel.setText(System.nanoTime() - tempoInizio + " ns");
            //////////////////////////////////////////////////////////////////
            //              ARRAYLIST
            //////////////////////////////////////////////////////////////////
            tempoInizio = System.nanoTime();
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            ArrayListCreationLabel.setText(System.nanoTime() - tempoInizio + " ns");

            tempoInizio = System.nanoTime();
            for (int i = 0; i < elements; i++)
                arrayList.add(1);
            ArrayListInsertLabel.setText(System.nanoTime() - tempoInizio + " ns");
            //////////////////////////////////////////////////////////////////
            //              LINKEDLIST
            //////////////////////////////////////////////////////////////////
            tempoInizio = System.nanoTime();
            LinkedList<Integer> linkedList=new LinkedList<Integer>();
            LinkedListCreationLabel.setText(System.nanoTime() - tempoInizio + " ns");

            tempoInizio = System.nanoTime();
            for (int i = 0; i < elements; i++)
                linkedList.add(1);
            LinkedListInsertLabel.setText(System.nanoTime() - tempoInizio + " ns");
            //////////////////////////////////////////////////////////////////
            //              HASHMAP
            //////////////////////////////////////////////////////////////////
            tempoInizio = System.nanoTime();
            HashMap<Integer,Integer> hashMap=new HashMap<Integer,Integer>();
            HashMapCreationLabel.setText(System.nanoTime() - tempoInizio + " ns");

            tempoInizio = System.nanoTime();
            for (int i = 0; i < elements; i++)
                hashMap.put(1,1);
            HashMapInsertLabel.setText(System.nanoTime() - tempoInizio + " ns");
            //////////////////////////////////////////////////////////////////
            //              TREEMAP
            //////////////////////////////////////////////////////////////////
            tempoInizio = System.nanoTime();
            TreeMap<Integer,Integer> treeMap=new TreeMap<Integer, Integer>();
            TreeMapCreationLabel.setText(System.nanoTime() - tempoInizio + " ns");

            tempoInizio = System.nanoTime();
            for (int i = 0; i < elements; i++)
                treeMap.put(1,1);
            TreeMapInsertLabel.setText(System.nanoTime() - tempoInizio + " ns");
        } catch (Exception e) {
            textFieldElements.setText("Exception Found");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("statsForm");
        frame.setContentPane(new statsForm().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
