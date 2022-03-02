package com.example.studenttutoring;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ratings extends AppCompatActivity {

    private Button submit_button;
    private Button cancel_button;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        name = findViewById(R.id.editTextTextPersonName);
        submit_button = findViewById(R.id.submit_button);
        cancel_button = findViewById(R.id.cancel_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { submit(); }
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
        Intent intent1 = new Intent(this, RatingsConfirmation.class); //source, destination
        Intent intent2 = new Intent(this, MainActivity.class);
        if(name.getText().toString().trim().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(intent2);
                }
            });
            builder.setNegativeButton("CANCEL", (dialog, id) -> name.setText(""));
            builder.setMessage("Rating must include Tutor name. Click OK to return, CANCEL to add name");

            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        startActivity(intent1);
        finish();
    }
}