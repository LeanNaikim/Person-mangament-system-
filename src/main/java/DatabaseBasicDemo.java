import java.sql.*;

@Deprecated
public class DatabaseBasicDemo {
    public static void main(String[] args) {
        String databaseUrl="jdbc:postgresql://localhost:5432/postgres";
        String username="postgres";
        String password="943012";

        String getAllPersonSQL= """
                SELECT * FROM persons;
                """;
        try(
                Connection con = DriverManager.getConnection(databaseUrl,username,password);
                Statement statement = con.createStatement();
        ){
          var resultSet=   statement.executeQuery(getAllPersonSQL);
          while(resultSet.next()){
              System.out.println("Person ID:"+resultSet.getInt("id"));
              System.out.println("Person Name:"+resultSet.getString("fullname"));
              System.out.println("Person Email :"+resultSet.getString("email"));
              System.out.println("Person Address:"+resultSet.getString("address"));
              System.out.println("Person Gender :"+resultSet.getString("gender"));
              System.out.println("-----------------------------------------");
          }

            System.out.println("We are able to connect to it now !");
        }catch (SQLException ex ){
            ex.printStackTrace();
        }



    }
}
