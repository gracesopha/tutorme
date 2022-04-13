package com.example.studenttutoring.calendarpage;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.studenttutoring.ConnectionHelper;
import com.example.studenttutoring.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CalendarPage extends Fragment {
    private static final String TAG = "CalendarPage";
    private CalendarPageViewModel mViewModel;
    private Spinner spinner;
    public static CalendarPage newInstance() {
        return new CalendarPage();
    }
    Button startTimeButton, endTimeButton, searchButton;
    int sHour,sMinute,eHour,eMinute;
    LocalTime startTime,endTime;
    int dayOfWeek;
    CalendarView calendar;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dayOfWeek = 0;
        View v = inflater.inflate(R.layout.calendar_page_fragment, container, false);
        searchButton = v.findViewById(R.id.searchButton);
        spinner = v.findViewById(R.id.subjects_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.subject_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        startTimeButton = v.findViewById(R.id.startTimeButton);
        endTimeButton = v.findViewById(R.id.endTimeButton);
        calendar = v.findViewById(R.id.calendarView);
        calendar.setMinDate((new Date().getTime()));
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Calendar selected = Calendar.getInstance();
                selected.set(year,month,dayOfMonth);
                dayOfWeek = selected.get(Calendar.DAY_OF_WEEK);
                Log.d(TAG, "onSelectedDayChange: "+ dayOfWeek);
            }
        });
        View.OnClickListener timeButtonListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                popTimePicker(view);
            }
        };
        startTimeButton.setOnClickListener(timeButtonListener);
        endTimeButton.setOnClickListener(timeButtonListener);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startTime != null && endTime != null && dayOfWeek!=0 && spinner.getSelectedItem()!=null){
                    if (startTime.isAfter(endTime)){
                        Toast.makeText(getActivity(),"Please choose an end time after the start time",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Connection connect;
                    ArrayList<tutorEntry> tutorList = new ArrayList<>();
                    try{
                        ConnectionHelper connectionHelper = new ConnectionHelper();
                        connect = connectionHelper.connectionclass();
                        if(connect!=null){
                            String query = String.format("Select * from SCHEDULE s " +
                                    "INNER JOIN LOGIN_ACCT l on s.tutor = l.userid " +
                                    "where (s.dayofweek=%1$s) and (s.start_time < CAST('%2$s' as time)) and (s.end_time > CAST('%3$s' as time)) and (s.subject = '%4$s')",dayOfWeek, endTime, startTime,spinner.getSelectedItem().toString());//Insert Query here
                            Statement st = connect.createStatement();
                            ResultSet rs = st.executeQuery(query);
                            while(rs.next()){
                                tutorEntry currTutor = new tutorEntry(rs.getString("firstname")+" " + rs.getString("lastname"),rs.getString("start_time"),rs.getString("end_time"),rs.getString("contactnum"),rs.getString("email"));
                                tutorList.add(currTutor);
                            }
                            if(tutorList.isEmpty()){
                                Toast.makeText(getActivity(),"There are no tutors that fit this criteria",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                    catch (Exception ex) {
                        Log.e("Error",ex.getMessage());
                    }


                } else {
                    Toast.makeText(getActivity(), "Please fill in all parameters", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        return v;

    }

    public void popTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm a");
                switch(view.getId()) {
                    case R.id.startTimeButton:
                        sHour = selectedHour;
                        sMinute = selectedMinute;
                        startTime = LocalTime.of(sHour,sMinute);
                        startTimeButton.setText(startTime.format(dtf));
                        Log.d(TAG, "onTimeSet: time is : "+startTime.format(dtf));
                        break;
                    case R.id.endTimeButton:
                        eHour = selectedHour;
                        eMinute = selectedMinute;
                        endTime = LocalTime.of(eHour,eMinute);
                        endTimeButton.setText(endTime.format(dtf));
                        Log.d(TAG, "onTimeSet: time is : "+endTime.format(dtf));
                }
            }
        };
        TimePickerDialog timePickerDialog;
        switch(view.getId()) {
            case R.id.startTimeButton:
                timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Dialog, onTimeSetListener, sHour, sMinute, false);
                break;
            case R.id.endTimeButton:
                timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Dialog, onTimeSetListener, eHour, eMinute, false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }


}