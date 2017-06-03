package com.example.admin.routineapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class stat extends AppCompatActivity {

    double count;
    int[] attendance = new int[20];
    double attended;
    String subject;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        GraphView graph;
        int i;
        int size =100;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        graph = (GraphView) findViewById(R.id.graphs1);
        double percentage,req;
        Intent in = getIntent();
        Bundle extra = in.getExtras();
        count = extra.getInt("count");
        subject = extra.getString("subject");
        attended = extra.getInt("attended");
        percentage = (attended/count)*100;
        percentage = Math.round((percentage)*100.0)/100.0;
        req = (0.75 * count - attended)/0.25;
       attendance = extra.getIntArray("attendance");
        Spinner spin = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this,R.array.bool,android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapt);
        double x ,y;
        x = 0;
        y = attendance[0];
        series.appendData(new DataPoint(x,y),true,size);
        for(i = 1; i < count; i++) {
            x = i;
            y = ((double)(attendance[i] + (i)*y))/(i+1);
            y = Math.round(y*10.0)/10.0;
            series.appendData(new DataPoint(x,y),true,size);
        }
        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(20);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1);
        graph.getGridLabelRenderer().setNumHorizontalLabels(21);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        TextView text = (TextView)findViewById(R.id.statText);
        String s ="";
        if(req < 0){
            s = "The total number of class is :<b><h3>" + (int)count + "</h3></b>"
                    +"Out of which you have attended <b><h3>" + (int)attended + "</h3></b>" +
                    "And have a total percentage :<b><h3>" + percentage + "%</h3></b>" +
                    "You can miss the next <h3>" + -(int)Math.ceil(req) + "</h3> classes and yet maintain a 75% attendance";
        }
        else{
           s = "The total number of class is :<b><h3>" + (int)count + "</h3></b>"
                   +"Out of which you have attended <b><h3>" + (int)attended + "</h3></b>" +
                   "And have a total percentage :<b><h3>" + percentage + "%</h3></b>" +
                    "You need to attend the next <h3>" + (int)Math.ceil(req) + "</h3> classes to attain a 75% attendance";
        }
        text.setText(Html.fromHtml(s));
    }

    public void submit(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(stat.this).create();
        EditText et = (EditText)findViewById(R.id.editText3);
        String str = et.getText().toString();
        Spinner sp = (Spinner)findViewById(R.id.spinner2);
        String s = sp.getSelectedItem().toString();
        Intent in = new Intent(this,timeTable.class);
        int cn = Integer.parseInt(str);
        if(cn > (int)count + 1){
            alertDialog.setTitle("Sorry");
            alertDialog.setMessage("Those many classes have not been held yet!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            et.setBackgroundColor(Color.rgb(253,237,237));

        }
        else{
            int i = 0;
            String fin = "";
            String temp;
            int tem = 0;
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference("attendance").child(subject);
            alertDialog.setTitle("Success!");
            alertDialog.setMessage("Your attendance has been upated!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            et.setBackgroundColor(Color.rgb(3,253,117));
            if(s.compareTo("True") == 0){
                attendance[cn - 1] = 1;
            }
            else {
                attendance[cn - 1] = 0;
            }
            if (cn == count + 1)
                count = count+1;
            for(i=0;i<count;i++){
               // tem = tem*10 + attendance[i];
                fin = fin.concat(Integer.toString(attendance[i]));
            }
            //fin = Integer.toString(tem);
            myRef.setValue(fin);

        }

    }
}
