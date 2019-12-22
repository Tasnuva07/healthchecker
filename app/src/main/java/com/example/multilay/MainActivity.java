package com.example.multilay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String WEIGHT= "com.example.mulitlay.example.EXTRA_TEXT";
    public static final String HEIGHT = "com.example.mulitlay.example.EXTRA_NUMBER";
    public static final String AGE = "com.example.mulitlay.example.EXTRA_NUMBER";
    public static final String GEN = "com.example.mulitlay.example.GEN";
    public static final String SPIN = "com.example.mulitlay.example.SPIN";

    String[] activitynames;
    private Spinner spinner;
    private TextView spinnertextview;
    private Button spinnerbutton;

    private RadioGroup radgrp;
    private RadioButton sexbut;
    int gen=0;

    int x;
    double bmi,bmr,tdee,tdeemulti;
    double weight,height;
    double age;
    String sugar,bp,wgt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activitynames=getResources().getStringArray(R.array.dailyactivity_names);

        spinner=(Spinner)findViewById(R.id.spinnerid);
        //spinnertextview=(TextView)findViewById(R.id.spinnertextviewid);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.actlayout,R.id.actlayoutid,activitynames);
        spinner.setAdapter(adapter);

        Button button=(Button)findViewById(R.id.buttonid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity2();
            }
        });
    }
    public void openactivity2()
    {
        radgrp=(RadioGroup) findViewById(R.id.radid);

        EditText editText1 = (EditText) findViewById(R.id.weightid) ;
        EditText editText2 = (EditText) findViewById(R.id.heightid);
        EditText editText3 = (EditText) findViewById(R.id.ageid);
        EditText editText4 = (EditText)findViewById(R.id.sugarid);
        EditText editText5 = (EditText)findViewById(R.id.bpid);

        weight = Double.parseDouble(editText1.getText().toString());
        height = Double.parseDouble(editText2.getText().toString());
        age = Double.parseDouble(editText3.getText().toString());
        sugar=editText4.getText().toString();
        bp=editText5.getText().toString();
        wgt=editText1.getText().toString();

        String spinnervalue=spinner.getSelectedItem().toString();
        for(int i=0;i<5;i++)
        {
            if(spinnervalue.equals(activitynames[i]))
            {
                x=i;
                break;
            }
        }

        int selectedid=radgrp.getCheckedRadioButtonId();
        sexbut = (RadioButton) findViewById (selectedid);

        String value=sexbut.getText().toString();
        if(value.equals("Male")){
            gen=1;
        }


        Intent intent=new Intent(MainActivity.this,second.class);


        intent.putExtra("wtag",weight).putExtra("htag",height).putExtra("agtag",age).putExtra("gentag",gen).putExtra("spintag",x).putExtra("sugtag",sugar).putExtra("bptag",bp).putExtra("wgt1tag",wgt);

        startActivity(intent);

    }
}
