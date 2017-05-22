package com.example.admin.routineapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class timeTable extends AppCompatActivity {

    int cs1,cs2,cs3,cs4,cs5,a1,a2,a3,a4,a5;
    int[] as1 = new int[20],as2 = new int[20],as3 = new int[20],as4 = new int[20],as5 = new int[20];

    int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        int i =0;
        for(i=0;i<20;i++){
            as1[i] = 0;
        }
        as1[1] = 0 ;
        as1[2] = 1 ;
        as1[3] = 0 ;
        as1[4] = 1 ;
        as1[5] = 1 ;
        as1[6] = 0 ;
        size = 100;
        int percent = 0;
       // int i =0;
        Context context = this;
        try {
                readFromAssets(context,"tt.txt");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
        String mLine = reader.readLine();
        String p,s;
            int resId;
        while (mLine != null) {
            p = " ";
            s = " ";
            p = "text" + mLine.substring(0,2);
            if(mLine.substring(0,1).equals("s")){
                s = "S" + mLine. substring(1,2) + " : " + mLine.substring(3);
            }else {
                s = mLine.substring(3, 5);
            }
            resId = getResources().getIdentifier(p,"id",getPackageName());
           TextView t= (TextView)findViewById(resId);
           t.setText(s);
            mLine = reader.readLine();
        }
        reader.close();
    }


    public void buttons1(View view){
        cs1 = 6;
        a1 = 3;
        Intent in = new Intent(this, stat.class);
        in.putExtra("count",cs1);
        in.putExtra("attended",a1);
        in.putExtra("attendance",as1);
        startActivity(in);




    }
}
