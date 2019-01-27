package jdbc;

import java.sql.*;

public class MyFirstJdbcConection {

    public static void main(String[] args) throws SQLException {

        String conectionUrl =
                "jdbc:sqlserver://morfeusz.wszib.edu.pl:1433;databaseName=AdventureWorks;user=luszowie;password=Luki11";
        Connection con = DriverManager.getConnection(conectionUrl);

        Statement stmt = con.createStatement();
        ResultSet rs =stmt.executeQuery("select * from Person.contact");

        while (rs.next()) {
            System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
        }

        rs.close();
        stmt.close();
        con.close();



    }
}
