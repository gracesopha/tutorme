package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
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
    }
}