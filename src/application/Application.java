package application;

import control.Command;
import control.ConverCommand;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.MoneyDialog;
import view.MoneyDisplay;

public class Application extends JFrame{
    public static void main(String[] args) {
        new Application().setVisible(true);
    }
    
    private HashMap<String, Command> commands = new HashMap();
    private MoneyDialog dialog;
    private MoneyDisplay display;
    
    public Application() throws HeadlessException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        deployUI();
        createCommands();
        this.setLocationRelativeTo(null);
    }

    private void deployUI() {
        this.getContentPane().add(textPanel(), BorderLayout.CENTER);
        this.getContentPane().add(convertButton(), BorderLayout.SOUTH);
        this.pack();
        this.validate();
        this.setTitle("*** MONEY CALCULATOR *** ");
    }

    private void createCommands() {
        commands.put("convert", new ConverCommand(display, dialog));
    }
    
    private JPanel textPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(dialog());
        panel.add(display());
        return panel;
    }
    
    private JPanel dialog() {
        SwingMoneyDialog moneyDialog = new SwingMoneyDialog();
        dialog = moneyDialog;
        return moneyDialog;
    }

    private JPanel display() {
        SwingMoneyDisplay swingDisplay = new SwingMoneyDisplay();
        display = swingDisplay;
        return swingDisplay;
    }

    private JButton convertButton() {
        JButton button = new JButton("Convert");
        button.addActionListener(convert());
        return button;
    }

    private ActionListener convert() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("convert").execute();
                Application.this.validate();
            }
        };
    }

    
}
