package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ratings extends AppCompatActivity {

    private Button submit_button;
    private Button cancel_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        submit_button = findViewById(R.id.submit_button);
        cancel_button = findViewById(R.id.cancel_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goHome();
                //openActivity2();
            }
        });
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class); //source, destination
        startActivity(intent);
        finish();
    }

    public void submit() {
        Intent intent = new Intent(this, RatingsConfirmation.class); //source, destination
        startActivity(intent);
        finish();
    }
}