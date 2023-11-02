package Calcolatrice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;

public class Calcolatrice {
    private javax.swing.JPanel JPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button0;
    private JButton buttonPlus;
    private JButton buttonMin;
    private JButton buttonMol;
    private JButton buttonDiv;
    private JButton buttonResult;
    private JLabel labelInput;
    private JButton buttonC;
    private JLabel labelOutput;
    private JRadioButton radioButtonInfissa;
    private JRadioButton radioButtonRPN;
    private JButton buttonOpen;
    private JButton buttonClose;
    private JButton buttonCanc;

    public void Esegui() {
        labelOutput.setText("");
        if (CheckBrackets(labelInput.getText())) {
            if (radioButtonInfissa.isSelected())
                labelOutput.setText(CalcolaRPN(ConvertToRPN(CreateArray(labelInput.getText()))));
            else if (radioButtonRPN.isSelected()) {
                Stack<String> s = new Stack<String>();
                for (char c:labelInput.getText().toCharArray())
                    s.push(c+"");
                labelOutput.setText(labelOutput.getText()+CalcolaRPN(s));
            }
        }
        else
            labelOutput.setText("Brackets are not balanced");
    }

    public Calcolatrice() {
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "0");
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "1");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "2");
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "3");
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "4");
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "5");
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "6");
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "7");
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "8");
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "9");
            }
        });
        buttonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(labelInput.getText().isEmpty())
                    labelInput.setText(labelInput.getText() + "(");
                else if(!isNum(labelInput.getText().charAt(labelInput.getText().length()-1)) &&
                        labelInput.getText().charAt(labelInput.getText().length()-1)!=')')
                    labelInput.setText(labelInput.getText() + "(");
                else
                    labelInput.setText(labelInput.getText() + "*(");
            }
        });
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(labelInput.getText().isEmpty())
                    labelInput.setText("");
                else if(!isNum(labelInput.getText().charAt(labelInput.getText().length()-1)) &&
                        labelInput.getText().charAt(labelInput.getText().length()-1)!=')' &&
                        labelInput.getText().charAt(labelInput.getText().length()-1)!='(') {
                    buttonCanc.doClick();
                    labelInput.setText(labelInput.getText() + ")");
                }
                else if(labelInput.getText().charAt(labelInput.getText().length()-1)=='(')
                    buttonCanc.doClick();
                else
                    labelInput.setText(labelInput.getText() + ")");
            }
        });
        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "+");
            }
        });
        buttonMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "-");
            }
        });
        buttonMol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "*");
            }
        });
        buttonDiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText(labelInput.getText() + "/");
            }
        });
        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInput.setText("");
                labelOutput.setText("");
            }
        });
        buttonResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Esegui();
            }
        });
        buttonCanc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!labelInput.getText().isEmpty())
                    labelInput.setText(labelInput.getText().substring(0,labelInput.getText().length()-1));
            }
        });
    }

    public String[] CreateArray(String input) {
        int counter = 0;
        for (char value : input.toCharArray())
            if (!isNum(value) && value != ')')
                counter++;
        String[] result = new String[counter * 2 + 1];
        Arrays.fill(result, "");

        int outputPos = 0;
        for (int i = 0; i < input.length() - 1; i++) {
            if (isNum(input.charAt(i))) {
                result[outputPos] += input.charAt(i);
                if (!isNum(input.charAt(i + 1)))
                    outputPos++;
            } else {
                result[outputPos] += input.charAt(i);
                outputPos++;
            }
        }
        result[result.length - 1] += input.charAt(input.length() - 1);
        return result;
    }

    public Stack<String> ConvertToRPN(String[] input) {
        Stack<String> RPN = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String value : input) {
            if (isNum(value))
                RPN.push(value);
            else {
                if (value.equals(")")) {
                    do {
                        if (!isNum(operators.peek()) && !Objects.equals(operators.peek(), "(") && !Objects.equals(operators.peek(), ")"))
                            RPN.push(operators.pop());
                    } while (!Objects.equals(operators.peek(), "("));
                    operators.pop();
                } else if (operators.isEmpty() || Priority(operators.peek()) < Priority(value) || value.equals("(") || Objects.equals(operators.peek(), "("))
                    operators.push(value);
                else if (Priority(operators.peek()) >= Priority(value)) {
                    for (int i = operators.size(); i > 0; i--) //malvageria
                        if (Priority(operators.peek()) >= Priority(value))
                            RPN.push(operators.pop());
                    operators.push(value);
                }
            }
        }
        while (!operators.isEmpty())
            RPN.push(operators.pop());

        return RPN;
    }

    public String CalcolaRPN(Stack<String> input) {
        //REVERSE STACK
        Stack<String> stack=new Stack<>();
        while (!input.isEmpty())
            stack.push(input.pop());
        Stack<String> numbers=new Stack<>();
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (isNum(stack.get(i)))
                numbers.push(stack.pop());
            else
                numbers.push(Operation(numbers.pop(), numbers.pop(), stack.pop()));
        }
        return numbers.pop();
    }

    public boolean isNum(String string) {
        if (string.length() > 1 && string.charAt(0) == '-')
            string = string.substring(1);
        string=string.replace('.','0');
        for (char value : string.toCharArray()) {
            if (!isNum(value))
                return false;
        }
        return true;
    }

    public boolean isNum(char c) {
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }

    public boolean CheckBrackets(String input) {
        int balance = 0;
        for (char value : input.toCharArray()) {
            if (value == '(')
                balance++;
            else if (value == ')')
                balance--;
            if (balance < 0)
                return false;
        }
        return balance == 0;
    }

    public int Priority(String operator) {
        if (Objects.equals(operator, "+") || Objects.equals(operator, "-"))
            return 0;
        else if (Objects.equals(operator, "*") || Objects.equals(operator, "/"))
            return 1;
        else return -1;
    }

    public String Operation(String n2, String n1, String operator) {
        float num1 = Float.parseFloat(n1), num2 = Float.parseFloat(n2);
        float result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                break;
        }
        return "" + result;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calcolatrice");
        frame.setContentPane(new Calcolatrice().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
