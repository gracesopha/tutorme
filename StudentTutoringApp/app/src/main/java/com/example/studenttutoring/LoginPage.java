package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("serial")
public class LoginPage extends AppCompatActivity {

    private TextInputEditText email;
    private static final String TAG = "LoginPage";
    ConnectionHelper conn;
    public LoginPage () {
        conn = new ConnectionHelper();

    }

    public static void main (String[] args) {
        new LoginPage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Connection connect;
        String pulled = "";
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if(connect!=null){
                String query = "select * from LOGIN_ACCT";//Insert Query here
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    pulled = rs.getString("email");
                    Log.d(TAG, "LoginPage : Pulled email : "+pulled);
                }
            }
        }
        catch (Exception ex) {
            Log.e("Error",ex.getMessage());
        }
        final Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                email = findViewById(R.id.loginEmail);
                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                intent.putExtra("userEmail", email.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}