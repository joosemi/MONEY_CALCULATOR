package control;

import model.Money;
import model.ExchangeRate;
import view.MoneyDialog;
import view.MoneyDisplay;

public class ConverCommand implements Command{
    private MoneyDisplay display;
    private MoneyDialog dialog;

    public ConverCommand(MoneyDisplay display, MoneyDialog dialog) {
        this.display = display;
        this.dialog = dialog;
    }

    @Override
    public void execute() {
        ExchangeRate rate = dialog.rate();
        Money newMoney = new Money(dialog.money().getAmount()*rate.getRate(), rate.getTo());
        display.show(newMoney);
    }
    
}
