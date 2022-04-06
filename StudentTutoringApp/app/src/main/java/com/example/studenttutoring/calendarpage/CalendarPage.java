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

import com.example.studenttutoring.MainActivity;
import com.example.studenttutoring.R;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CalendarPage extends Fragment {
    private static final String TAG = "CalendarPage";
    private CalendarPageViewModel mViewModel;
    private Spinner spinner;
    public static CalendarPage newInstance() {
        return new CalendarPage();
    }
    Button startTimeButton, endTimeButton;
    int sHour,sMinute,eHour,eMinute;
    LocalTime startTime,endTime;
    CalendarView calendar;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calendar_page_fragment, container, false);
        spinner = v.findViewById(R.id.subjects_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.subject_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        startTimeButton = v.findViewById(R.id.startTimeButton);
        endTimeButton = v.findViewById(R.id.endTimeButton);
        calendar = v.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String curDate = String.valueOf(dayOfMonth);
                Log.d(TAG, "onSelectedDayChange: "+curDate);
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