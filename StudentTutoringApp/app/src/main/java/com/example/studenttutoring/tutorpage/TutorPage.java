package com.example.studenttutoring.tutorpage;

import androidx.lifecycle.ViewModelProvider;

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
import com.example.studenttutoring.MainActivity;
import com.example.studenttutoring.R;
import com.example.studenttutoring.SignupPage;

public class TutorPage extends Fragment {
    private static final String TAG = "TutorPage";

    private TutorPageViewModel mViewModel;
    private TextView email;
    String userEmail = "abc@gmail.com";
    Button logoutButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tutor_page_fragment, container, false);
        email = v.findViewById(R.id.emailLabel2);
        Bundle bundle=this.getArguments();
        userEmail = bundle.getString("userEmail");
        email.setText(userEmail);
        Log.d(TAG, "TutorPage : pulled string "+userEmail);
        Log.d(TAG, "TutorPage : Email string "+email.getText().toString());

        // Logout button
        logoutButton = (Button) v.findViewById(R.id.logout);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TutorPageViewModel.class);
        // TODO: Use the ViewModel
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LoginPage.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });
    }
}