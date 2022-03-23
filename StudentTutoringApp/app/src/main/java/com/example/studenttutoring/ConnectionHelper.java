package com.example.studenttutoring;

import android.os.StrictMode;
import android.util.Log;

import java.sql.*;

public class ConnectionHelper {
    // instance fields
    Connection con;
    String user, pass, ip, port, database;
    private static final String TAG = "TutorPage";

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
        Log.d(TAG, "ConnectionHelper : Attempting Connection");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            ConnectionURL = "jdbc:mysql://" + ip + ":" + port +"/" +database;
            Log.d(TAG, "ConnectionHelper : The ConnectionURL is : " + ConnectionURL);
            connection = DriverManager.getConnection(ConnectionURL,user,pass);
            Log.d(TAG, "ConnectionHelper : Successful Connection");
        }
        catch (Exception ex){
            Log.d(TAG, "ConnectionHelper : Connection Failed");
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
