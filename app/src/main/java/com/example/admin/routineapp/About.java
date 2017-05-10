package com.example.admin.routineapp;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import org.w3c.dom.Text;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView ab = (TextView)findViewById(R.id.abt);
        String str = "<b>Routine App</b> aims to help students of IIEST, Shibpur to improve their academic performance " +
                "by supplying previous year papers, keeping track of subject wise attendance and also helps in making college life easy by " +
                "providing semester time table, calendar of events and several other important informations " +
                "right in your pocket!<br><br> ";
        ab.setText(Html.fromHtml(str));

    }
}
