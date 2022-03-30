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
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class DisplayRatings extends AppCompatActivity {

    private TextView ratings;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ratings);
        Connection conn;
        homeButton = findViewById(R.id.readReviewsHomeButton);
        ratings = findViewById(R.id.ratings_text_view);
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            conn = connectionHelper.connectionclass();
            Statement st = conn.createStatement();
            String query = "SELECT name, recommend " + "FROM REVIEW";
            //String query = "SELECT name " + "FROM REVIEW";
            ResultSet rs = st.executeQuery(query);
            //rs.next();  //moving cursor to first row
            //Log.v("connection test:",rs.getString(1));
            String name = "";
            List<String> namesList = new ArrayList<>();
            HashMap<String, Integer> dataHashMap = new HashMap<>();
            int rating = 0;
            int temp = 0;
            if (!rs.next()){    // if we have no data in REVIEW table. Moves cursor forward
                Toast.makeText(this, "No reviews have been submitted yet", Toast.LENGTH_LONG).show();
            }
            else{
                rs.beforeFirst();   //reset cursor to point BEFORE the first row
                while (rs.next()){  //move cursor forward
                    name = rs.getString(1);     //first col in rs
                    rating = rs.getInt(2);      //second col in rs
                    if (dataHashMap.containsKey(name)){
                        dataHashMap.put(name, dataHashMap.get(name) + rating);
                    }
                    else{
                        dataHashMap.put(name, rating);
                    }
                    //Log.v("connection test:",name);
                    //Log.v("connection test:",String.valueOf(rating));
                    //ratings.append(name + " : " + rating);
                }
                for(String key: dataHashMap.keySet()){
                    ratings.append(key + " : " + dataHashMap.get(key) + "\n\n\n");
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