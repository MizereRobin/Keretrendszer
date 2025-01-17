package com.example.hasznaltjarmuhu.db;

import java.sql.*;

public class database {

    private String URL = "jdbc:mysql://localhost:3306/hasznaltjarmuhu?useSSL=false&serverTimezone=UTC";

    private Connection connection;

    public void Init(){
        this.connection = DriverManager.GetConnection(URL,"root","");
    }

    public void Register(String name, String pass){  //TODO Void --> Bool
        String loginq = "Insert into users Values(name = ?, pass = ?)";
        try(PreparedStatement yes = connection.PrepareStatement(loginq)){
            // A YES a query statement

            yes.SetString(1,name);
            yes.SetString(2,pass);
            ResultSet res = yes.ExecuteQuery();
            updateAuthenticationStatus(name, true);
            //return res.next();
        }
        catch(SQLException e){
            system.out.println(e);
            //return false;

        }
    }

    public boolean Login(String name, String pass){
        String loginq = "SELECT * FROM users WHERE users.name = ?  AND users.pwd = PASSWORD(?)";
        try(PreparedStatement yes = connection.PrepareStatement(loginq)){
            // A YES a query statement

            yes.SetString(1,name);
            yes.SetString(2,pass);
            ResultSet res = yes.ExecuteQuery();
            updateAuthenticationStatus(name, true);
            return res.next();
        }
        catch(SQLException e){
            system.out.println(e);
            return false;

        }
    }
    public void LogOut(String name){
        updateAuthenticationStatus(name, false);
    }

    private boolean updateAutenticationStatus(String name, bool isAuthenticated) {
        String query = "UPDATE users SET auth = ? WHERE name = ?";
        try (PreparedStatement yes = connection.prepareStatement(query)){
        yes.setBoolean(1, isAuthenticated);
        yes.setInt(2, name);
        int row = yes.executeUpdate();
        return row > 0;
    } catch (SQLException e) {
        System.err.println(e.getMessage());
        return false;
    }
}




}
