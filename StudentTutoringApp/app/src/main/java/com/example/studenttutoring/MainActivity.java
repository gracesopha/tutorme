package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studenttutoring.tutorpage.TutorPage;
import com.example.studenttutoring.tutorpage.TutorPageViewModel;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginButton);
        signupButton = (Button) findViewById(R.id.signUpButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignupPage();
            }
        });
    }

    public void openLoginPage() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void openSignupPage() {
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }
}