package com.example.studenttutoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

@SuppressWarnings("serial")
public class LoginPage extends AppCompatActivity {

    ConnectionHelper conn;
    public LoginPage () {
        conn = new ConnectionHelper();

    }

    public static void main (String[] args) {
        new LoginPage();
    }

}