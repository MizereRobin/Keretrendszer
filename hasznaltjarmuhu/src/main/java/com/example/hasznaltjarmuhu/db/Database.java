package com.example.hasznaltjarmuhu.db;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class Database {

    private final String url = "jdbc:mysql://localhost:3306/hasznaltjarmuhu";
    private final String username = "root";
    private final String password = "root";

    public boolean login(String name, String rawPassword, HttpSession session) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT Name, Role FROM users WHERE Name = ? AND Password = PASSWORD(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, rawPassword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                session.setAttribute("name", resultSet.getString("Name"));
                session.setAttribute("role", resultSet.getString("Role"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(String name, String rawPassword) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO users (Name, Password, Role) VALUES (?, PASSWORD(?), 'user')";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, rawPassword);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
