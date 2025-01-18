package com.example.hasznaltjarmuhu.db;

import java.sql.*;

public class database {

    private String URL = "jdbc:mysql://localhost:3306/hasznaltjarmuhu?useSSL=false&serverTimezone=UTC";

    private static Connection connection;

    public void Init() throws SQLException {
        this.connection = DriverManager.getConnection(URL,"root","");
    }

    public static void Register(String name, String pass){  //TODO Void --> Bool
        String loginq = "Insert into users Values(name = ?, pass = ?)";
        try(PreparedStatement yes = connection.prepareStatement(loginq)){
            // A YES a query statement

            yes.setString(1,name);
            yes.setString(2,pass);
            ResultSet res = yes.executeQuery();
            updateAuthenticationStatus(name, true);
            //return res.next();
        }
        catch(SQLException e){
            System.out.println(e);
            //return false;

        }
    }

    public static void Login(String name, String pass){
        String loginq = "SELECT * FROM users WHERE users.name = ?  AND users.pwd = PASSWORD(?)";
        try(PreparedStatement yes = connection.prepareStatement(loginq)){
            // A YES a query statement

            yes.setString(1,name);
            yes.setString(2,pass);
            ResultSet res = yes.executeQuery();
            updateAuthenticationStatus(name, true);
            //return res.next();
        }
        catch(SQLException e){
            System.out.println(e);
            //return false;

        }
    }
    public static void LogOut(String name){
        updateAuthenticationStatus(name, false);
    }

    private static void updateAuthenticationStatus(String name, boolean isAuthenticated) {
        String query = "UPDATE users SET auth = ? WHERE name = ?";
        try (PreparedStatement yes = connection.prepareStatement(query)){
        yes.setBoolean(1, isAuthenticated);
        yes.setString(2, name);
        int row = yes.executeUpdate();
        //return row > 0;
    } catch (SQLException e) {
        System.err.println(e.getMessage());
        //return false;
    }
}




}
