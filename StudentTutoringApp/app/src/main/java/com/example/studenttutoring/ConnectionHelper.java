package com.example.studenttutoring;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionHelper {
    // instance fields
    Connection con;
    String user, pass, ip, port, database;


    public Connection connectionclass(){
        user="sql5473138";
        pass="5fqPUtNQhn";
        ip="54.84.79.252";
        port="3306";
        database="sql5473138";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            ConnectionURL = "jdbc:mysql://" + ip + "/" + port +"/" +database;
            connection = DriverManager.getConnection(ConnectionURL,user,pass);
            System.out.println("Connected to database");
        }
        catch (Exception ex){
            Log.e("Error",ex.getMessage());
            System.out.println("Cannot connect to database");
        }
        return connection;
    }

}



/*
        Connection connect;
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if(connect!=null){
                String query = "";//Insert Query here
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    //then you use rs.get**** for whatever you want ie rs.getString("Username");
                }
            }
        }
        catch (Exception ex) {
            Log.e("Error",ex.getMessage());
        }
 */
