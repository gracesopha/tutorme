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

import com.example.studenttutoring.ConnectionHelper;
import com.example.studenttutoring.LoginPage;
import com.example.studenttutoring.R;

import java.sql.*;

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
        name = v.findViewById(R.id.fullname_tutor);
        phone = v.findViewById(R.id.contact_tutor);
        Log.d(TAG, "TutorPage : pulled string "+userEmail);
        Log.d(TAG, "TutorPage : Email string "+email.getText().toString());

        // First Name and Last Name
        Connection connect;
        String firstName = "";
        String lastName = "";
        try {
            ConnectionHelper conn = new ConnectionHelper();
            connect = conn.connectionclass();
            if(connect != null) {
                String query = "select * from LOGIN_ACCT";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()) {
                    firstName = rs.getString("firstname");
                    lastName = rs.getString("lastname");
                    Log.d(TAG, "TutorPage : Pulled Name : " + firstName + " " + lastName);
                }
            }
        }
         catch (Exception ex) {
            Log.e("Error", ex.getMessage());
         }

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