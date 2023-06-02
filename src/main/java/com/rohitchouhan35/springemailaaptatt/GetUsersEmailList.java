package com.rohitchouhan35.springemailaaptatt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GetUsersEmailList {
    public static void addUsersToList(List<String> emailList) {
        String url = "jdbc:mysql://localhost:3306/email_list";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT email FROM all_emails")) {

            while (resultSet.next()) {
                String email = resultSet.getString("email");
                emailList.add(email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
