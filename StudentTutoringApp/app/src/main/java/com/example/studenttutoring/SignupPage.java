package com.example.studenttutoring;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignupPage extends AppCompatActivity {

    private EditText userPass, confirmPass, firstName, lastName, userEmail, userPhone;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        save = findViewById(R.id.save_button);
        firstName = findViewById(R.id.signUpFirstName);
        lastName = findViewById(R.id.signUpLastName);
        userPhone = findViewById(R.id.signUpPhone);
        userEmail = findViewById(R.id.signUpEmail);
        userPass = findViewById(R.id.signUpPass);
        confirmPass = findViewById(R.id.signUpPass2);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { submit(); }
        });

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
    public void submit() {
        Intent intent = new Intent(this, LoginPage.class); //source, destination
        if(confirmPass.getText().toString().trim().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setPositiveButton("OK", (dialog, id) -> confirmPass.setText(""));
            builder.setMessage("Must Confirm Password");

            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        startActivity(intent);
        finish();
    }
}