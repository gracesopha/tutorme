package com.example.studenttutoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.studenttutoring.calendarpage.CalendarPage;
import com.example.studenttutoring.studentpage.StudentPage;
import com.example.studenttutoring.tutorpage.TutorPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    BottomNavigationView bottomNavigationView;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent.hasExtra("userEmail")) {
            userEmail = intent.getExtras().getString("userEmail");
        }

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
                Bundle bundle = new Bundle();
                bundle.putString("userEmail", userEmail);
                Log.d(TAG, "MainActivity : pulled string "+userEmail);
                FragmentTransaction firstFragmentTrans = getSupportFragmentManager().beginTransaction();
                firstFragment.setArguments(bundle);
                firstFragmentTrans.addToBackStack(null);
                firstFragmentTrans.replace(R.id.container, firstFragment);
                firstFragmentTrans.commit();
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