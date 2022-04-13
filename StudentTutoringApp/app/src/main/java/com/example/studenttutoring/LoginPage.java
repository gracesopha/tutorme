package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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
                        String query = String.format("SELECT * FROM LOGIN_ACCT where (email='%1$s') AND (password='%2$s');", pulledEmail, pulledPass);//Insert Query here
                        Log.d(TAG, "onClick: "+query);
                        Statement st = connect.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        rs.last();
                        if(rs.getRow() == 0){
                            Log.d(TAG, "onClick: no success");
                            Toast.makeText(getApplicationContext(), "Email or Password does not exist", Toast.LENGTH_SHORT).show();
                        } else {
                            String accType = "0";
                            userEmail = pulledEmail;
                            String query2 = String.format("SELECT type FROM LOGIN_ACCT where (email='%1$s') AND (password='%2$s');", pulledEmail, pulledPass);//Insert Query here
                            Log.d(TAG, "onClick: "+query2);
                            ResultSet rs2 = st.executeQuery(query2);
                            while (rs2.next()) {
                                accType = rs2.getString(1);
                            }
                            Log.d(TAG, "onClick: return student type" + accType);
                            if (accType.equals("0")) {
                                Intent intent1 = new Intent(LoginPage.this, MainActivity.class);
                                startActivity(intent1);
                                finish();
                            }
                            else {
                                Intent intent2 = new Intent(LoginPage.this, MainActivityTutor.class);
                                startActivity(intent2);
                                finish();
                            }
                        }

                    }
                }
                catch (Exception ex) {
                    Log.e("Error",ex.getMessage());
                }
            }
        });
        final TextView signUpButton = findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, SignupPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}