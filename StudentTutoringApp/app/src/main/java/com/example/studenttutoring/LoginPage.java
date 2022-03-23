package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("serial")
public class LoginPage extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Connection connect;
    private static final String TAG = "LoginPage";
    public static String userEmail = "";


    public static void main (String[] args) {
        new LoginPage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPass);
        final Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pulledEmail = email.getText().toString();
                String pulledPass = password.getText().toString();
                Log.d(TAG, "onClick: Started connection");
                try{
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();
                    if(connect!=null){
                        String query = String.format("SELECT * FROM LOGIN_ACCT where (email='%1$s') AND (password='%2$s');",pulledEmail,pulledPass);//Insert Query here
                        Log.d(TAG, "onClick: "+query);
                        Statement st = connect.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        rs.last();
                        if(rs.getRow() == 0){
                            Log.d(TAG, "onClick: no success");
                            Toast.makeText(getApplicationContext(), "Email or Password does not exist", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "onClick: success");
                            Intent intent = new Intent(LoginPage.this, MainActivity.class);
                            userEmail = email.getText().toString();
                            startActivity(intent);
                            finish();
                        }

                    }
                }
                catch (Exception ex) {
                    Log.e("Error",ex.getMessage());
                }
            }
        });
    }
}