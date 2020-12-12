package com.example.projecttaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class WaitTimer extends AppCompatActivity {
    TextView txtTimer;
    TextView txtExpectation;
    Button btnCancel;
    Button btnStop;
    int hours =0,minutes = 0, seconds =0, mlseconds =0 ;
    int milliseconds = 900000;
    int touch = 0;
    private boolean running = true;
    private Handler handler = new Handler();
    private Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_timer);
         txtTimer = (TextView)findViewById(R.id.textForTimer);
        txtExpectation = (TextView)findViewById(R.id.expectation);
         btnCancel = (Button)findViewById(R.id.btnCancel);
         btnStop = (Button)findViewById(R.id.btnStop);
         thread.start();
         btnStop.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 touch+=1;
                 if(touch ==2)
                 {
                     
                 }
                 if(touch ==1)
                 {
                     AnimationStart();

                 }



             }
         });
    }

      Thread thread = new Thread() {

         @Override
         public void run() {

             if (milliseconds >= 0) {
                 minutes = (int) ((milliseconds / (1000 * 60)) % 60);
                 seconds = (int) (milliseconds / 1000) % 60;
                 hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
                 txtTimer.setText(String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds));
             }
             else
             {
                 running = false;
             }

             if (running)
             {
                 milliseconds -= 1000;
             }
             else
             {
                 txtTimer.setText("00:00:00");
                 txtExpectation.setText("Drive timer");

             }
             handler.postDelayed(this, 1000);
         }
     };
    private void AnimationStart()
    {
        btnCancel.setVisibility(View.GONE);
        float translationX = btnStop.getTranslationX();
        float translationY = btnStop.getTranslationY();
        TranslateAnimation animation = new TranslateAnimation(translationX, translationX, translationY,translationY+300);
        animation.setDuration(TimeUnit.SECONDS.toMillis(1L));
        animation.setFillAfter(true);
        btnStop.startAnimation(animation);
        running = false;
    }




 }
