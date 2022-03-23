package com.example.studenttutoring;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studenttutoring.calendarpage.CalendarPage;
import com.example.studenttutoring.tutorpage.TutorPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityTutor extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivityTutor";
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tutor);
        Intent intent = getIntent();
        bottomNavigationView = findViewById(R.id.bottomNavViewTutor);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    TutorPage firstFragment = new TutorPage();
    CalendarPage thirdFragment = new CalendarPage();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                Bundle bundle = new Bundle();
                bundle.putString("userEmail", LoginPage.userEmail);
                Log.d(TAG, "MainActivity : pulled string "+LoginPage.userEmail);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, firstFragment).commit();
                return true;

            case R.id.navigation_calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, thirdFragment).commit();
                return true;
        }
        return false;
    }
}
