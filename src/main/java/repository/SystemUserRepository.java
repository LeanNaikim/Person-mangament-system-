package repository;


import model.SystemUser;
import utils.PropertyUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SystemUserRepository {
    private final Properties properties;
    public SystemUserRepository() {
        properties = PropertyUtils.loadProperty();
    }

    public static final String getAllUserSql = """
            select * from systemusers;
            """;
    public static final String loginUserSql = """
            select * from "systemusers" where username = ? and password = ?;
            """;

    private Connection startDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("DB_URL"),
                properties.getProperty("USERNAME"),
                properties.getProperty("PASSWORD")
        );
    }

    public  boolean loginSystemUser(String username,String password) {
        try (Connection connection = startDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(loginUserSql)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("System user checking error!!!");
            ex.printStackTrace();
        }

        return false; // Default to invalid
    }

    public List<SystemUser> getAllUser () {
        try (
                Connection connection = startDatabaseConnection();
                Statement statement = connection.createStatement();
        ) {
            var userList = new ArrayList<SystemUser>();
            var rs = statement.executeQuery(getAllUserSql);
            while (rs.next()) {
                userList.add(
                        new SystemUser()
                                .setUsername(rs.getString("username"))
                                .setPassword(rs.getString("password"))
                );
            }
            return userList;

        } catch (SQLException ex) {
            System.out.println("Failed !!! ");
            ex.printStackTrace();
        }
        return null;
    }
}