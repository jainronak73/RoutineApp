package com.iiest.routineapp;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Files extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        Spinner spin = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapt);
        String s = "";
        Context context = this;
        try {
           s = readFromAssets(context,"links.txt");
        }catch (IOException e) {
            e.printStackTrace();
        }
        TextView tv = (TextView)findViewById(R.id.links);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setLinkTextColor(Color.BLACK);

        tv.setText(Html.fromHtml(s));


    }
    public void submit(View view){
        EditText desc = (EditText) findViewById(R.id.editText2);
        desc.setBackgroundColor(Color.WHITE);
        String des = desc.getText().toString();
        Spinner year = (Spinner)findViewById(R.id.spinner);
        EditText gd = (EditText) findViewById(R.id.gd);
        gd.setBackgroundColor(Color.WHITE);
        String link = gd.getText().toString();
        AlertDialog alertDialog = new AlertDialog.Builder(Files.this).create();
        String yr = year.getSelectedItem().toString();
        if(link.length()==0 || !link.matches(".*drive\\.google\\.com.*")){
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Link not valid!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            gd.setBackgroundColor(Color.rgb(255,237,237));
        }
        else if(des.length() == 0){
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Description of file mandatory!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            desc.setBackgroundColor(Color.rgb(255,237,237));
        }else{
            alertDialog.setTitle("Congratulations");
            alertDialog.setMessage("The file you have attached will be added to the app soon!\n Keep uploading files and helping others.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            desc.setBackgroundColor(Color.rgb(3,253,117));
            gd.setBackgroundColor(Color.rgb(3,253,117));
        }



    }
    public String readFromAssets(Context context, String filename) throws IOException {
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
