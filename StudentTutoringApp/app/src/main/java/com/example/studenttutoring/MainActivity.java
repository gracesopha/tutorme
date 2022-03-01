package com.example.studenttutoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.studenttutoring.calendarpage.CalendarPage;
import com.example.studenttutoring.studentpage.StudentPage;
import com.example.studenttutoring.tutorpage.TutorPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

    }

    TutorPage firstFragment = new TutorPage();
    StudentPage secondFragment = new StudentPage();
    CalendarPage thirdFragment = new CalendarPage();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, firstFragment).commit();
                return true;

            case R.id.navigation_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, thirdFragment).commit();
                return true;

            case R.id.navigation_calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, secondFragment).commit();
                return true;
            case R.id.navigation_rating:
                startActivity((new Intent(this, Ratings.class)));
                return true;
        }
        return false;
    }
}