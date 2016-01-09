package application;

import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Money;
import view.MoneyDisplay;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay{

    public SwingMoneyDisplay() {
        this.add(new JLabel("                              "));
    }
    
    @Override
    public void show(Money money) {
        this.removeAll();
        JLabel label = new JLabel(money.getAmount() + " " + money.getCurrency().getCode());
        this.add(label);
    }
    
}
