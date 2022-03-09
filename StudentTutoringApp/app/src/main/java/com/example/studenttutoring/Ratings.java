package com.example.studenttutoring;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ratings extends AppCompatActivity {

    private Button submit_button;
    private Button cancel_button;
    private EditText name;
    private EditText comment;
    private Switch reccomend_switch;
    private boolean reccomend = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        name = findViewById(R.id.editTextTextPersonName);
        submit_button = findViewById(R.id.submit_button);
        cancel_button = findViewById(R.id.cancel_button);
        comment = findViewById(R.id.editTextTextMultiLine);
        reccomend_switch = findViewById(R.id.switch2);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { submit(); }
        });

        cancel_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goHome();
                //openActivity2();
            }
        });

        reccomend_switch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reccomend = true;
            }
        });

    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class); //source, destination
        startActivity(intent);
        finish();
    }

    public void submit() {
        Intent intent = new Intent(this, RatingsConfirmation.class); //source, destination
        if(name.getText().toString().trim().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setPositiveButton("OK", (dialog, id) -> name.setText(""));
            builder.setMessage("Rating must include Tutor name. Click OK to add name");

            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        else{
            //get data from text fields
            Connection conn;
            String comment_text = comment.getText().toString();
            String name_text = name.getText().toString();
            int ratings_value = (reccomend) ? (1) : (0);
            //Log.v("comment_text:",comment_text);
            //Log.v("name_text", name_text);
            //Log.v("recommend:", String.valueOf(reccomend));
            try {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                conn = connectionHelper.connectionclass();
                Statement stmt = conn.createStatement();
                String add = "";
                add = "INSERT INTO REVIEW VALUES ('"+name_text+"', '"+comment_text+"', '"+ratings_value+"')";
                stmt.executeUpdate(add);
                Log.v("connection test:",comment_text);
            }
            catch (Exception ex){
                Log.e("Error",ex.getMessage());
            }

        }
        startActivity(intent);
        finish();
    }
}