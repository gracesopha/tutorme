package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayRatings extends AppCompatActivity {

    private TextView ratings;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ratings);
        Connection conn;
        homeButton = findViewById(R.id.readReviewsHomeButton);
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            conn = connectionHelper.connectionclass();
            Statement st = conn.createStatement();
            String query = "SELECT name, recommend " + "FROM REVIEW";
            //String query = "SELECT name " + "FROM REVIEW";
            ResultSet rs = st.executeQuery(query);
            //rs.next();  //moving cursor to first row
            //Log.v("connection test:",rs.getString(1));
            String name;
            int rating;
            if (!rs.next()){    // if we have not data in REVIEW table. Moves cursor forward
                Toast.makeText(this, "No reviews have been submitted yet", Toast.LENGTH_LONG).show();
            }
            else{
                rs.beforeFirst();   //reset cursor to point BEFORE the first row
                while (rs.next()){  //move cursor forward
                    name = rs.getString(1);     //first col in rs
                    Log.v("connection test:",name);
                    rating = rs.getInt(2);      //second col in rs
                    Log.v("connection test:",String.valueOf(rating));
                }
            }

        }catch (Exception ex){
            Log.e("Error",ex.getMessage());
        }

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goHome();
            }
        });
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class); //source, destination
        startActivity(intent);
        finish();
    }


}