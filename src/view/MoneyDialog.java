package view;

import model.ExchangeRate;
import model.Money;

public interface MoneyDialog {
    Money money();
    ExchangeRate rate();
}
