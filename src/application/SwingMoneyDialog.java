package application;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Currency;
import model.ExchangeRate;
import model.Money;
import persistance.SQLiteCurrencyLoader;
import persistance.SQLiteExchangeRateLoader;
import view.MoneyDialog;

public class SwingMoneyDialog extends JPanel implements MoneyDialog{
    private final HashMap<String, Component> components = new HashMap();
    private ArrayList<Currency> currencies;

    public SwingMoneyDialog() {
        deployUI();
    }
    
    @Override
    public Money money() {
        try{
            JTextField amountTextField = (JTextField) components.get("amount");
            Double amountQuantity = Double.parseDouble(amountTextField.getText());
            JComboBox fromComboBox = (JComboBox) components.get("from");
            Currency fromCurrency = new Currency((String)fromComboBox.getSelectedItem());
            return new Money(amountQuantity, fromCurrency);
        }catch (Exception e){
            System.out.println("INGRESE LA CANTIDAD");
            return null;
        }
    }

    @Override
    public ExchangeRate rate() {
        JComboBox fromComboBox = (JComboBox) components.get("from");
        Currency fromCurrency = new Currency((String)fromComboBox.getSelectedItem());
        JComboBox toComboBox = (JComboBox) components.get("to");
        Currency toCurrency = new Currency((String)toComboBox.getSelectedItem());
        return SQLiteExchangeRateLoader.get(fromCurrency, toCurrency);
    }

    private void deployUI() {
        this.setLayout(new FlowLayout());
        this.add(amount());
        this.add(from());
        this.add(new JLabel("  to   "));
        this.add(to());
    }

    private JTextField amount() {
        JTextField jTextField = new JTextField(9);
        components.put("amount", jTextField);
        return jTextField;
    }

    private JComboBox from() {
        currencies = new SQLiteCurrencyLoader().get();
        JComboBox<String> box = new JComboBox();
        for (Currency currency : currencies) {
            box.addItem(currency.getCode());
        }
        components.put("from", box);
        return box;
    }

    private JComboBox to() {
        JComboBox<String> box = new JComboBox();
        for (Currency currency : currencies) {
            box.addItem(currency.getCode());
        }
        components.put("to", box);
        return box;
    }
}