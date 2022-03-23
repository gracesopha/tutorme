package com.example.studenttutoring;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignupPage extends AppCompatActivity {

    private EditText userPass, confirmPass, firstName, lastName, userEmail, userPhone;
    private Button save;
    private Switch tutorState;
    private boolean isTutor;
    private String yesTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        save = findViewById(R.id.save_button);
        firstName = findViewById(R.id.signUpFirstName);
        lastName = findViewById(R.id.signUpLastName);
        userPhone = findViewById(R.id.signUpPhone);
        userEmail = findViewById(R.id.signUpEmail);
        userPass = findViewById(R.id.signUpPass);
        confirmPass = findViewById(R.id.signUpPass2);
        tutorState = findViewById(R.id.tutor_switch);
        isTutor = tutorState.isChecked();
        if (isTutor) {
            yesTutor = "1";
        } else
            yesTutor = "0";

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { submit(); }
        });
    }

    public void submit() {
        if(confirmPass.getText().toString().trim().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setPositiveButton("OK", (dialog, id) -> confirmPass.setText(""));
            builder.setMessage("Must Confirm Password");

            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        Connection connect;
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if(connect!=null){
                String query = String.format("INSERT INTO `LOGIN_ACCT`(`email`, `password`, `firstname`, `lastname`, `contactnum`, `type`) " +
                        "VALUES ('%1$s','%2$s','%3$s','%4$s','%5$s', '%6$s')", userEmail.getText().toString(), userPass.getText().toString(), firstName.getText().toString(),
                        lastName.getText().toString(), userPhone.getText().toString(), yesTutor);
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