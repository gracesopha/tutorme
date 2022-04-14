package com.example.studenttutoring;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.studenttutoring.tutorpage.TutorPage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

public class TutorAvailability extends AppCompatActivity {

    private Button cancel_button, submit_button, start_time, end_time;
    private Spinner spinner, spinner2;
    private int sHour, sMinute, eHour, eMinute;
    private String startTime, endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_availability);
        spinner = findViewById(R.id.subjects_spinner);
        spinner2 = findViewById(R.id.weekday_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.subject_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.weekday_array, R.layout.spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        start_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);
        cancel_button = findViewById(R.id.cancel_avail_button);
        submit_button = findViewById(R.id.submit_tutor);
        View.OnClickListener timeButtonListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                popTimePicker(view);
            }
        };
        start_time.setOnClickListener(timeButtonListener);
        end_time.setOnClickListener(timeButtonListener);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivityTutor.class); //source, destination
                startActivity(intent);
                finish();
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    public void popTimePicker(View v) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                switch(v.getId()) {
                    case R.id.start_time:
                        sHour = selectedHour;
                        sMinute = selectedMinute;
                        startTime = String.format(Locale.getDefault(), "%02d:%02d %s", sHour < 13 ? sHour == 0 ? 12 : sHour : sHour - 12, sMinute, sHour < 12 ? "AM" : "PM");
                        start_time.setText(startTime);
                        break;
                    case R.id.end_time:
                        eHour = selectedHour;
                        eMinute = selectedMinute;
                        endTime = String.format(Locale.getDefault(), "%02d:%02d %s", eHour < 13 ? eHour == 0 ? 12 : eHour : eHour - 12, eMinute, eHour < 12 ? "AM" : "PM");
                        end_time.setText(endTime);
                        break;
                }
            }
        };
        TimePickerDialog timePickerDialog;
        switch(v.getId()) {
            case R.id.start_time:
                timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Dialog, onTimeSetListener, sHour, sMinute, false);
                break;
            case R.id.end_time:
                timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Dialog, onTimeSetListener, eHour, eMinute, false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }


    public void submit() {
       if(startTime.isEmpty() || endTime.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Must select start and end times");

            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        Connection connect;
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if(connect!=null){
                String query = String.format("INSERT INTO `SCHEDULE`(`tutor`, `subject`, `start_time`, `end_time`, `dayofweek`) " +
                                "VALUES ('%1$s','%2$s','%3$s','%4$s','%5$s')", spinner.getSelectedItem().toString(), startTime, endTime, spinner2.getSelectedItemPosition());
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    //then you use rs.get**** for whatever you want ie rs.getString("Username");
                }
            }
        }
        catch (Exception ex) {
            Log.e("Error",ex.getMessage());
        }
        Intent intent = new Intent(this, LoginPage.class); //source, destination
        startActivity(intent);
        finish();
    }
}