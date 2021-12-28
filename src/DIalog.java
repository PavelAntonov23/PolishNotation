import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import PolishNotation.NotationHandler;

public class DIalog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField aBСDTextField;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField3;
    private JTextArea textArea1;
    private JTextField textField4;
    private JButton fillTextAreaButton;

    public DIalog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        parseExpr(aBСDTextField.getText());

        fillTextAreaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fillTextArea();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        NotationHandler instance = new NotationHandler(aBСDTextField.getText(),textArea1.getText());
        textField2.setText(instance.getPostfixFormUsingStack());
        textField1.setText(instance.getPostfixFormUsingTreeScan());
        textField3.setText(instance.getPrefixForm());
        textField4.setText(instance.getCalculateRusult());
    }

    private void fillTextArea() {
        parseExpr(aBСDTextField.getText());
    }

    private void parseExpr(String string) {
        textArea1.setText("");
        String temp = new String();
        temp = string.replaceAll("[^a-z]","");
        for (char symb: temp.toCharArray()
             ) {
            textArea1.setText(textArea1.getText().concat(String.valueOf(symb)));
            textArea1.setText(textArea1.getText().concat(" \n"));
        }
    }

    public static void main(String[] args) {
        DIalog dialog = new DIalog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
