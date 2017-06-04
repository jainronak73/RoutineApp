package com.iiest.routineapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iiest.routineapp.R;

public class About extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_about, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView ab = (TextView) getView().findViewById(R.id.abt);
        String str = "<b>Routine App</b> aims to help students of IIEST, Shibpur to improve their academic performance " +
                "by supplying previous year papers, keeping track of subject wise attendance and also helps in making college life easy by " +
                "providing semester time table, calendar of events and several other important informations " +
                "right in your pocket!<br><br> ";
        ab.setText(Html.fromHtml(str));
    }

}
