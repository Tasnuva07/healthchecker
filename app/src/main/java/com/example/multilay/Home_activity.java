package com.example.multilay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Home_activity extends AppCompatActivity {
    private ProgressBar progress;
    private int pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home_activity);
        progress = (ProgressBar) findViewById(R.id.progressid);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                dowork();
                startapp();
            }
        });
        thread.start();
    }
    public void dowork() {

        for (pro = 20; pro <= 100; pro = pro + 20) {
            try {
                Thread.sleep(1000);
                progress.setProgress(pro);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
public void startapp()
{
    Intent intent=new Intent(this,MainActivity.class);
    startActivity(intent);
    finish();
}
}
