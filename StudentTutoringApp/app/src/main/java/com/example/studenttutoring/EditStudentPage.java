package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.Statement;

public class EditStudentPage extends AppCompatActivity {

    private static final String TAG = "EditStudentPage";
    private Button save_button;
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student_page);

        // Checking if edit text is empty
        firstName = findViewById(R.id.firstname_student);
        lastName = findViewById(R.id.lastname_student);
        phoneNumber = findViewById(R.id.contact_student);

        // Save Button
        save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Connecting to database");
                // display the input from the edit text first name
                String firstNameInput = firstName.getText().toString();
                String lastNameInput = lastName.getText().toString();
                String phoneNumberInput = phoneNumber.getText().toString();

                // Display the input in log
                Log.d(TAG, "onClick: First Name: " + firstNameInput);
                Log.d(TAG, "onClick: Last Name: " + lastNameInput);
                Log.d(TAG, "onClick: Phone Number: " + phoneNumberInput);

                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();
                    if (connect != null) {
                        // Check if first name is empty
                        if (firstNameInput.isEmpty()) {
                            Log.d(TAG, "onClick: First Name is empty");
                        } else {
                            // If the first name is not empty, then update the database
                            String query = "UPDATE LOGIN_ACCT SET firstname = '" + firstNameInput + "' WHERE email = '" + LoginPage.userEmail + "'";
                            Log.d(TAG, "onClick: Query: " + query);
                            Statement st = connect.createStatement();
                            st.executeUpdate(query);
                            Log.d(TAG, "onClick: First Name updated");
                        }

                        // Check if last name is empty
                        if (lastNameInput.isEmpty()) {
                            Log.d(TAG, "onClick: Last Name is empty");
                        } else {
                            // If the last name is not empty, then update the database
                            String query = "UPDATE LOGIN_ACCT SET lastname = '" + lastNameInput + "' WHERE email = '" + LoginPage.userEmail + "'";
                            Log.d(TAG, "onClick: Query: " + query);
                            Statement st = connect.createStatement();
                            st.executeUpdate(query);
                            Log.d(TAG, "onClick: Last Name updated");
                        }

                        // Check if phone number is empty
                        if (phoneNumberInput.isEmpty()) {
                            Log.d(TAG, "onClick: Phone Number is empty");
                        } else {
                            // If the phone number is not empty, then update the database
                            String query = "UPDATE LOGIN_ACCT SET phonenumber = '" + phoneNumberInput + "' WHERE email = '" + LoginPage.userEmail + "'";
                            Log.d(TAG, "onClick: Query: " + query);
                            Statement st = connect.createStatement();
                            st.executeUpdate(query);
                            Log.d(TAG, "onClick: Phone Number updated");
                        }
                    }
                    // Close the connection
                    connect.close();
                } catch (Exception ex) {
                    Log.d(TAG, "onClick: Error: " + ex.toString());
                }
                Intent intent = new Intent(EditStudentPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}