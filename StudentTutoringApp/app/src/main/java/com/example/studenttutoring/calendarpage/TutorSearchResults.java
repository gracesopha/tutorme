package com.example.studenttutoring.calendarpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.studenttutoring.R;

import java.util.ArrayList;

public class TutorSearchResults extends AppCompatActivity {
    RecyclerView tutorRecycler;
    ArrayList<tutorEntry> tutorList;
    tutorEntryAdapter tutorAdapter;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_search_results);
        tutorRecycler = findViewById(R.id.tutor_recycler);
        tutorList = (ArrayList<tutorEntry>) getIntent().getSerializableExtra("tutorList");
        tutorAdapter = new tutorEntryAdapter(tutorList);
        tutorRecycler.setAdapter(tutorAdapter);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        tutorRecycler.setLayoutManager(RecyclerViewLayoutManager);
    }
}