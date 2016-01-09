package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Currency;
import model.ExchangeRate;

public class SQLiteExchangeRateLoader implements ExchangeRateReader {

    public static ExchangeRate get(Currency from, Currency to) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connect = DriverManager.getConnection("jdbc:sqlite:RATES.DB");
            Statement statement = connect.createStatement();
            String fromQuery = "SELECT EXCHANGE_RATE_DOLLAR FROM RATES WHERE CURRENCY='"
                    + from.getCode()
                    + "'";
            String toQuery = "SELECT EXCHANGE_RATE_DOLLAR FROM RATES WHERE CURRENCY='" 
                    + to.getCode()
                    + "'";
            ResultSet  fromResultSet = statement.executeQuery(fromQuery);
            double fromExchange = 0;
            while (fromResultSet.next()) 
                fromExchange = fromResultSet.getDouble(1);
            fromResultSet.close();
            ResultSet  toResultSet = statement.executeQuery(toQuery);
            double toExchange = 0;
            while (toResultSet.next()) 
                toExchange = toResultSet.getDouble(1);
            toResultSet.close();
            statement.close();
            connect.close();
            return new ExchangeRate(from, to, (float) (toExchange/fromExchange));
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Fallo al conectarse con la base de datos");
        }
        return null;
    }
}
