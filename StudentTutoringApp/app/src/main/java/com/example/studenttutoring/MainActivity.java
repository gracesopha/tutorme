package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studenttutoring.studentpage.StudentPage;
import com.example.studenttutoring.tutorpage.TutorPage;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openLoginPage() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void openSignupPage() {
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }

    public void openTutorPage() {
        Intent intent = new Intent(this, TutorPage.class);
        startActivity(intent);
    }

    public void openStudentPage() {
        Intent intent = new Intent(this, StudentPage.class);
        startActivity(intent);
    }

}