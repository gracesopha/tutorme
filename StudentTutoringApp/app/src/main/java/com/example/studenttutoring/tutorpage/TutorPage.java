package com.example.studenttutoring.tutorpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.studenttutoring.LoginPage;
import com.example.studenttutoring.R;

public class TutorPage extends Fragment {
    private static final String TAG = "TutorPage";
    private TextView email;
    private TextView name;
    private TextView phone;
    String userEmail = "abc@gmail.com";
    Button logoutButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tutor_page_fragment, container, false);
        email = v.findViewById(R.id.email_tutor);
        name = v.findViewById(R.id.firstname_tutor);
        phone = v.findViewById(R.id.contact_tutor);
        Log.d(TAG, "TutorPage : pulled string "+userEmail);
        Log.d(TAG, "TutorPage : Email string "+email.getText().toString());

        // Logout button
        logoutButton = (Button) v.findViewById(R.id.logout_tutor);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LoginPage.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });
        return v;
    }

}