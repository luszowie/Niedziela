package budget;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.math.BigDecimal;
import java.sql.*;
import java.text.NumberFormat;

public class HomeBudget {

    private static final String DB_Host = "morfeusz.wszib.edu.pl";
    private static final int DB_PORT = 1433;
    private static final String DB_USER = "luszowie";
    private static final String DB_PASS = "Luki11";
    private static final String DB_NAME = "luszowie";

    private static final String INSERT_ENTRY_SQL = "insert into budget.BudgetEtries (EntryName,Amount) values (?,?)";
    private static final String GET_BALANCE_SQL = "select sum(Amount) as Balance from budget.BudgetEtries";
    private static final String GET_TOP_TEN_ENTRIES = "select top(10) budget.BudgetEtries.Amount from budget.BudgetEtries";


    public static void main(String[] args) {

        //TODO - required validation

        BudgetEntry be = new BudgetEntry();
        be.setEntryName(args[0]);
        be.setAmount(new BigDecimal(args[1]));



        HomeBudget hb = new HomeBudget();
        try(Connection con = hb.connect(DB_USER, DB_PASS, DB_NAME);
            Statement stmt = con.createStatement())
        {
            /*if (args != null && args.length <1) {
                PreparedStatement ps1 = con.prepareStatement(GET_TOP_TEN_ENTRIES);
                System.out.println("--------------------------------------");
            }*/


                PreparedStatement ps = con.prepareStatement(INSERT_ENTRY_SQL);
                ps.setString(1, be.getEntryName());
                ps.setBigDecimal(2, be.getAmount());
                ps.execute();



            BigDecimal balance;
            ResultSet rs = stmt.executeQuery(GET_BALANCE_SQL);
            if (rs.next()) {
                balance = rs.getBigDecimal("Balance");
            } else {
                balance = new BigDecimal(0);
            }

            System.out.print("Zapisano! nazwa: " + be.getEntryName());

            System.out.print(" Kwota: " + hb.currencyFormat(be.getAmount()) );

            System.out.print(" Saldo: " + hb.currencyFormat(balance) );



        } catch (SQLException e) {
            System.out.println("Wystapił błąd bazy danych:" + e.getMessage());
            }


    }

    public Connection connect (String username, String password, String dbName) throws SQLServerException {

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(DB_Host);
        ds.setPortNumber(DB_PORT);
        ds.setUser(username);
        ds.setPassword(password);
        ds.setDatabaseName(dbName);
        return ds.getConnection();


    }


public String currencyFormat (BigDecimal bd) {
    return NumberFormat.getCurrencyInstance().format(bd);
}
}

//C:\Users\luszowie.DYDAKTYKA.001\IdeaProjects\Niedziela\src\budget\BudgetEntry.java
        //C:\Users\luszowie.DYDAKTYKA.001\Downloads\Microsoft JDBC Driver 7.0 for SQL Server\sqljdbc_7.0\enu\mssql-jdbc-7.0.0.jre8.jar