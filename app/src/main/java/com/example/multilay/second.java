package com.example.multilay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class second extends AppCompatActivity {
    double tdeemulti,wgt,hgt,bmi,age,bmr,tdee;
    int gen,spin;
    String sug,bp,wgt1;

    Button addbutton,viewbutton;

    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


      Intent intent = getIntent();
        wgt = intent.getDoubleExtra("wtag",0.00);
        hgt = intent.getDoubleExtra("htag", 0.00);
        age = intent.getDoubleExtra("agtag",0.00);
        gen = intent.getIntExtra("gentag",0);
        spin =intent.getIntExtra("spintag",0);
        sug =intent.getStringExtra("sugtag");
        bp =intent.getStringExtra("bptag");
        wgt1=intent.getStringExtra("wgt1tag");

        switch(spin){
            case 0:tdeemulti=1.2;
                break;
            case 1:tdeemulti=1.375;
                break;
            case 2:tdeemulti=1.55;
                break;
            case 3:tdeemulti=1.725;
                break;
            case 4:tdeemulti=1.9;
                break;
        }

       bmi = (wgt/(hgt*hgt));
        if(gen==1){
            bmr=(10*wgt)+(6.25*hgt*100)-(5*age)+5;
        }
        else{
            bmr=(10*wgt)+(6.25*hgt*100)-(5*age)-161;

        }
        tdee=bmr*tdeemulti;


       TextView textView1= (TextView) findViewById(R.id.bmiid2);
        TextView textView2=(TextView)findViewById(R.id.bmrid2);
        TextView textView3=(TextView)findViewById(R.id.tdeeid2);
        TextView textView4=(TextView)findViewById(R.id.bmiresid2);
        TextView textView5=(TextView)findViewById(R.id.tdeeresid2);


        textView1.setText("Your BMI is: "+bmi);
        textView2.setText("Your BMR is :"+bmr);
        textView3.setText("Your TDEE is :"+tdee);
        textView5.setText("If you want to lose fat, you need to eat fewer calories than your TDEE.");


if(bmi<18.9) {
    textView4.setText("Sorry!You are Underweight!!");
    textView5.setText("If you want gain weight, you need to eat more calories than your TDEE.");
}
else if(bmi>18.9 && bmi<24.9) {
    textView4.setText("Wow!You are Healthy!");
    textView5.setText("To maintain your weight you need to consume calories as much as your TDEE");
}

else if (bmi>24.9 && bmi<29.9) {
    textView4.setText("Ooops!You are Overweight");
    textView5.setText("If you want loose weight, you need to eat fewer calories than your TDEE.");
}
else {
    textView4.setText("Sad!You are suffering from obesity!");
    textView5.setText("If you want loose weight, you need to eat fewer calories than your TDEE.");

}

mydb=new DatabaseHelper(this);
addbutton=(Button)findViewById(R.id.addbuttonid);
viewbutton=(Button)findViewById(R.id.viewbuttonid);
adddata();
viewall();
    }
    public void adddata(){
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isinserted= mydb.insertdata(wgt1,sug,bp);
               if(isinserted==true)
                   Toast.makeText(second.this,"Data inserted",Toast.LENGTH_LONG).show();
               else
                   Toast.makeText(second.this,"Data is not inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
  public void viewall(){
        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb.getalldata();
                if(res.getCount()==0){
                    showmessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Day :"+res.getString(0)+"\n");
                    buffer.append("Weight :"+res.getString(1)+"\n");
                    buffer.append("Blood sugar :"+res.getString(2)+"\n");
                    buffer.append("Blood pressure :"+res.getString(3)+"\n\n");
                }
                showmessage("Data",buffer.toString());
            }
        });

    }
    public  void showmessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

        }
