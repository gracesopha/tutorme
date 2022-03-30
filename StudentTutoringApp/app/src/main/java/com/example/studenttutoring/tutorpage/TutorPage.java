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
import android.widget.Toast;

import com.example.studenttutoring.ConnectionHelper;
import com.example.studenttutoring.EditTutorPage;
import com.example.studenttutoring.LoginPage;
import com.example.studenttutoring.MainActivity;
import com.example.studenttutoring.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TutorPage extends Fragment {
    private static final String TAG = "TutorPage";
    private TextView email;
    private TextView name;
    private TextView phone;
    private TextView welcomeName;
    private Connection connect;
    Button logoutButton;
    Button editButton;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tutor_page_fragment, container, false);
        email = v.findViewById(R.id.email_tutor);
        name = v.findViewById(R.id.firstname_tutor);
        phone = v.findViewById(R.id.contact_tutor);
        welcomeName = v.findViewById(R.id.tutor_name);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if (connect != null) {
                String query = String.format("SELECT * FROM LOGIN_ACCT where (email='%1$s');", LoginPage.userEmail);//Insert Query here
                Log.d(TAG, "TutorPage: " + query);
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                rs.last();
                String tutorName = rs.getString("firstname") + " " + rs.getString("lastname");
                name.setText(tutorName);
                welcomeName.setText(tutorName);
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("contactnum"));
            }
        }
        catch (Exception ex) {
            Log.e("Error",ex.getMessage());
        }

        logoutButton = (Button) v.findViewById(R.id.logout_tutor);
        editButton = (Button) v.findViewById(R.id.edit_profile_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LoginPage.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EditTutorPage.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });
        return v;
    }

}