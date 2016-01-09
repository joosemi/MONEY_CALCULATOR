package persistance;

import java.util.ArrayList;
import model.Currency;

public interface CurrencyReader {
    ArrayList<Currency> get();
}
