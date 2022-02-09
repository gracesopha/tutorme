package com.example.studenttutoring;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String user, pass, ip, port, database;

    public Connection connectionclass(){

        ip="";
        pass="";
        ip="";
        port="";
        database="";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("");
            ConnectionURL = "jdbc:mysql://" + ip + "/" + port +";" + "databasename="+database+";user="+user+";password="+pass+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception ex){
            Log.e("Error",ex.getMessage());
        }
        return connection;
    }
}
