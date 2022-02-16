package com.example.studenttutoring;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    String user, pass, ip, port, database;

    public Connection connectionclass(){

        pass="";
        ip="";
        port="";
        database="";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            ConnectionURL = "jdbc:mysql://" + ip + "/" + port +"/" +database;
            connection = DriverManager.getConnection(ConnectionURL,user,pass);
        }
        catch (Exception ex){
            Log.e("Error",ex.getMessage());
        }
        return connection;
    }
}
