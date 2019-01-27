import java.sql.*;

public class lab61 {

           public static void main(String[] args) throws SQLException {

            String conectionUrl =
                    "jdbc:sqlserver://morfeusz.wszib.edu.pl:1433;databaseName=AdventureWorks;user=luszowie;password=Luki11";
            Connection con = DriverManager.getConnection(conectionUrl);

            Statement stmt = con.createStatement();
            ResultSet rs =stmt.executeQuery("select top 10 LastName, FirstName From Person.Contact where LastName = 'Anderson'");


            while (rs.next()) {
                System.out.println(rs.getString("LastName")+ " " + rs.getString("FirstName"));
            }

               ResultSet rs1 =stmt.executeQuery("select Distinct Title From HumanResources.Employee");

               while (rs1.next()) {
                   System.out.println(rs1.getString("Title"));
               }


               ResultSet rs2 =stmt.executeQuery("select Distinct Title From HumanResources.Employee");

               while (rs2.next()) {
                   System.out.println(rs2.getString("Title"));
               }







            rs.close();
            stmt.close();
            con.close();



        }
    }

