package com.example.studenttutoring.tutorpage;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studenttutoring.MainActivity;
import com.example.studenttutoring.R;

public class TutorPage extends Fragment {
    private static final String TAG = "TutorPage";

    private TutorPageViewModel mViewModel;
    private TextView email;
    String userEmail = "abc@gmail.com";
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
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TutorPageViewModel.class);
        // TODO: Use the ViewModel
    }

}