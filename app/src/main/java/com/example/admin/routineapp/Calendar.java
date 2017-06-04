package com.example.admin.routineapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Map;
import java.util.Scanner;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Spinner spin = (Spinner)findViewById(R.id.spinner3);
        //ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this,R.array.update,android.R.layout.simple_spinner_item);
       // adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin.setAdapter(adapt);


        setContentView(R.layout.activity_calendar);
        TextView tv = (TextView) findViewById(R.id.textView3);
        String str = "";
        Context context = this;
        try {
           str = readFromAssets(context,"dates.txt");
            tv.setText(Html.fromHtml(str));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append("\n" + mLine);
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }

}
