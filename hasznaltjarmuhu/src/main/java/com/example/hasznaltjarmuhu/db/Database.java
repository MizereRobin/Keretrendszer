package com.example.hasznaltjarmuhu.db;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;


@Service
public class Database {

    private static String url = "jdbc:mysql://localhost:3306/hasznaltjarmuhu";
    private static String username = "root";
    private static String password = "";

    public static boolean login(String name, String password, HttpSession session) {
        String query = "SELECT * FROM users WHERE name = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                session.setAttribute("name", name);
                session.setAttribute("role", rs.getString("role"));
                return true;
            }
            else{System.out.println("Sikertelen login session");}
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Sikertelen login");
        }
        return false;
    }


    public static boolean register(String name, String password) {
        String query = "INSERT INTO users (name, password) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static List<Map<String, Object>> getAllAds() {
        String query = "SELECT car.manufacturer, car.model, ads.year, ads.price " +
                "FROM ads " +
                "INNER JOIN car ON ads.carid = car.id";
        List<Map<String, Object>> cars = new ArrayList<Map<String, Object>>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Map<String, Object> car = new HashMap<>();
                car.put("manufacturer", resultSet.getString("manufacturer"));
                car.put("model", resultSet.getString("model"));
                car.put("year", resultSet.getInt("year"));
                car.put("price", resultSet.getDouble("price"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public static boolean postAd(String manufacturer, String model, int year, double price, int userId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String carQuery = "INSERT INTO car (manufacturer, model) VALUES (?, ?)";
            PreparedStatement carStatement = connection.prepareStatement(carQuery, Statement.RETURN_GENERATED_KEYS);
            carStatement.setString(1, manufacturer);
            carStatement.setString(2, model);
            carStatement.executeUpdate();

            ResultSet rs = carStatement.getGeneratedKeys();
            if (rs.next()) {
                int carId = rs.getInt(1);
                String adQuery = "INSERT INTO ads (carid, year, price, seller_id) VALUES (?, ?, ?, ?)";
                PreparedStatement adStatement = connection.prepareStatement(adQuery);
                adStatement.setInt(1, carId);
                adStatement.setInt(2, year);
                adStatement.setDouble(3, price);
                adStatement.setInt(4, userId);

                return adStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getUserIdByName(String username) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT id FROM users WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<Map<String, Object>> getAllUsers() {
        List<Map<String, Object>> users = new ArrayList<>();
        String query = "SELECT name, role FROM users";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> user = new HashMap<>();
                user.put("name", rs.getString("name"));
                user.put("role", rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
