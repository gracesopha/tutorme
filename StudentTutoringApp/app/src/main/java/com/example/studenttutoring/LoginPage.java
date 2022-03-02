package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

@SuppressWarnings("serial")
public class LoginPage extends AppCompatActivity {

    private TextInputEditText email;

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