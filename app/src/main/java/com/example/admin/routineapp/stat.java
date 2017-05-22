package com.example.admin.routineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class stat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        GraphView graph;
        int i;
        int size =20;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        graph = (GraphView) findViewById(R.id.graphs1);
        double count,attended;
        double percentage,req;
       int[] attendance = new int[20];
        Intent in = getIntent();
        Bundle extra = in.getExtras();
        count = extra.getInt("count");
        attended = extra.getInt("attended");
        percentage = (attended/count)*100;
        percentage = Math.round((percentage)*100.0)/100.0;
        req = (0.75 * count - attended)/0.25;
       attendance = extra.getIntArray("attendance");
        int x ,y;
        for(i = 0; i < 20; i++) {
            x = i;
            y = attendance[i];

            series.appendData(new DataPoint(x,y),true,size);
        }
        graph.addSeries(series);
        TextView text = (TextView)findViewById(R.id.statText);
        String s ="";
        if(req < 0){
            s = "The total number of class is :<b><h3>" + (int)count + "</h3></b>"
                    +"Out of which you have attended <b><h3>" + (int)attended + "</h3></b>" +
                    "And have a total percentage :<b><h3>" + percentage + "%</h3></b>" +
                    "You can miss the next <h3>" + (int)Math.ceil(req) + "</h3> classes and yet maintain a 75% attendance";
        }
        else{
           s = "The total number of class is :<b><h3>" + (int)count + "</h3></b>"
                   +"Out of which you have attended <b><h3>" + (int)attended + "</h3></b>" +
                   "And have a total percentage :<b><h3>" + percentage + "%</h3></b>" +
                    "You need to attend the next <h3>" + (int)Math.ceil(req) + "</h3> classes to maintain a 75% attendance";
        }
        text.setText(Html.fromHtml(s));
    }
}
