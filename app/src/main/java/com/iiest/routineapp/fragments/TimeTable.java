package com.iiest.routineapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iiest.routineapp.R;
import com.iiest.routineapp.stat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TimeTable extends Fragment {

    int cs1, cs2, cs3, cs4, cs5, a1, a2, a3, a4, a5;
    int[] as1 = new int[20], as2 = new int[20], as3 = new int[20], as4 = new int[20], as5 = new int[20];
    DatabaseReference myRef, myRef2, myRef3, myRef4, myRef5;
    String at1, at2, at3, at4, at5;
    int j;
    int size;
    Map<String, Integer> val = new HashMap<String, Integer>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_time_table, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (int i = 0; i < 20; i++) {
            as1[i] = 0;
            as2[i] = 0;
            as3[i] = 0;
            as4[i] = 0;
            as5[i] = 0;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("attendance").child("s1");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                a1 = 0;
                at1 = dataSnapshot.getValue().toString();
                cs1 = at1.length();
                for (int j = 0; j < cs1; j++) {
                    as1[j] = Integer.parseInt(at1.substring(j, j + 1));
                    if (as1[j] == 1)
                        a1++;
                }
                val.put("a1", a1);
                val.put("cs1", cs1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef2 = database.getReference("attendance").child("s2");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                j = 0;
                a2 = 0;
                at2 = dataSnapshot.getValue().toString();
                cs2 = at2.length();
                for (j = 0; j < cs2; j++) {
                    as2[j] = Integer.parseInt(at2.substring(j, j + 1));
                    if (as2[j] == 1)
                        a2++;
                }
                val.put("a2", a2);
                val.put("cs2", cs2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef3 = database.getReference("attendance").child("s3");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                j = 0;
                a3 = 0;
                at3 = dataSnapshot.getValue().toString();
                cs3 = at3.length();
                for (j = 0; j < cs3; j++) {
                    as3[j] = Integer.parseInt(at3.substring(j, j + 1));
                    if (as3[j] == 1)
                        a3++;
                }
                val.put("a3", a3);
                val.put("cs3", cs3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef4 = database.getReference("attendance").child("s4");
        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                j = 0;
                a4 = 0;
                at4 = dataSnapshot.getValue().toString();
                cs4 = at4.length();
                for (j = 0; j < cs4; j++) {
                    as4[j] = Integer.parseInt(at4.substring(j, j + 1));
                    if (as4[j] == 1)
                        a4++;
                }
                val.put("a4", a4);
                val.put("cs4", cs4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef5 = database.getReference("attendance").child("s5");
        myRef5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                j = 0;
                a5 = 0;
                at5 = dataSnapshot.getValue().toString();
                cs5 = at5.length();
                for (j = 0; j < cs5; j++) {
                    as5[j] = Integer.parseInt(at5.substring(j, j + 1));
                    if (as5[j] == 1)
                        a5++;
                }
                val.put("a5", a5);
                val.put("cs5", cs5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //cs1 = 6;
       /* a1 = 3;
        as1[1] = 0 ;
        as1[2] = 1 ;
        as1[3] = 0 ;
        as1[4] = 1 ;
        as1[5] = 1 ;
        as1[6] = 0 ;*/
        size = 100;
        int percent = 0;
        // int i =0;
        /*val.put("a1",a1);
        val.put("a2",a2);
        val.put("a3",a3);
        val.put("a4",a4);
        val.put("a5",a5);
        val.put("cs1",cs1);
        val.put("cs2",cs2);
        val.put("cs3",cs3);
        val.put("cs4",cs4);
        val.put("cs5",cs5);*/
        Context context = getActivity();
        try {
            readFromAssets(context, "tt.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
        String mLine = reader.readLine();
        String p, s, r, ta;
        int resId;
        while (mLine != null) {
            p = " ";
            r = "";
            ta = "";
            s = " ";
            p = "text" + mLine.substring(0, 2);
            if (mLine.substring(0, 1).equals("s")) {
                r = "a" + mLine.substring(1, 2);
                ta = "cs" + mLine.substring(1, 2);
                s = "S" + mLine.substring(1, 2) + " : " + mLine.substring(3) + "<br>Attendance : " + val.get(r) + " / " + val.get(ta);
            } else {
                s = mLine.substring(3, 5);
            }
            resId = getResources().getIdentifier(p, "id", getActivity().getPackageName());
            TextView t = (TextView) getActivity().findViewById(resId);
            t.setText(Html.fromHtml(s));
            mLine = reader.readLine();
        }
        reader.close();
    }


    public void buttons1(View view) {
        Intent in = new Intent(getActivity(), stat.class);
        switch (view.getId()) {
            case R.id.bs1:
                in.putExtra("count", cs1);
                in.putExtra("attended", a1);
                in.putExtra("attendance", as1);
                in.putExtra("subject", "s1");
                break;
            case R.id.bs2:
                in.putExtra("count", cs2);
                in.putExtra("attended", a2);
                in.putExtra("attendance", as2);
                in.putExtra("subject", "s2");
                break;
            case R.id.bs3:
                in.putExtra("count", cs3);
                in.putExtra("attended", a3);
                in.putExtra("attendance", as3);
                in.putExtra("subject", "s3");
                break;
            case R.id.bs4:
                in.putExtra("count", cs4);
                in.putExtra("attended", a4);
                in.putExtra("attendance", as4);
                in.putExtra("subject", "s4");
                break;
            case R.id.bs5:
                in.putExtra("count", cs5);
                in.putExtra("attended", a5);
                in.putExtra("attendance", as5);
                in.putExtra("subject", "s5");
                break;
        }
        startActivity(in);
    }
}
