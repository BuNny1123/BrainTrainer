package com.bunny.braintrainer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean isGameActive = false;
    CountDownTimer countDownTimer;
    TextView timeCounter;
    GridLayout gridLayout;
    Button ans1;
    Button ans2;
    Button ans3;
    Button ans4;
    TextView statusText;
    int correctLocation;
    int correctAsnwer=0;
    int totalQues=0;
    Button startButton;


    public void updateQuestion() {
        totalQues++;
        Random r = new Random();
        int a = r.nextInt(50);
        int b = r.nextInt(50);
        int c = a + b;
        TextView questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText(a + " + " + b);
        correctLocation = r.nextInt(4);
        Log.i("correct Location", correctLocation + "");
        int[] answers = new int[4];
        for (int i = 0; i < 4; i++) {
            if (i == correctLocation)
                answers[i] = c;
            else {
                int temp = r.nextInt(100);
                while (temp == c) {
                    temp = r.nextInt(100);
                }
                answers[i] = (temp);
            }
        }

        ans1 = (Button) findViewById(R.id.ans1);
        ans2 = (Button) findViewById(R.id.ans2);
        ans3 = (Button) findViewById(R.id.ans3);
        ans4 = (Button) findViewById(R.id.ans4);
        ans1.setText(answers[0] + "");
        ans2.setText(answers[1] + "");
        ans3.setText(answers[2] + "");
        ans4.setText(answers[3] + "");
    }

    public void updateStatus(){
        TextView questionCounter=(TextView)findViewById(R.id.questionCounter);
       questionCounter.setText(correctAsnwer+"/"+totalQues);

    }


    public void answerButton(View view) {
        String tag = (String) view.getTag();
        if (tag.equals(correctLocation + "")) {
            statusText.setText("Correct Answer!");
            correctAsnwer++;
        }
        else
            statusText.setText("Wrong Answer!");
        updateStatus();
        updateQuestion();
    }

    public void startGame(View view) {
        if (isGameActive == false) {

            ans1 = (Button)findViewById(R.id.ans1);
            ans1.setEnabled(true);
            ans2 = (Button)findViewById(R.id.ans2);
            ans2.setEnabled(true);
            ans3 = (Button)findViewById(R.id.ans3);
            ans3.setEnabled(true);
            ans4 = (Button)findViewById(R.id.ans4);
            ans4.setEnabled(true);
            statusText = (TextView) findViewById(R.id.statusText);
            statusText.setText("Your Score is : "+correctAsnwer);
            isGameActive = false;
            startButton=(Button)findViewById(R.id.startButton);
            startButton.setEnabled(false);
            updateQuestion();
            isGameActive = true;
            timeCounter = (TextView) findViewById(R.id.timeCounter);
            timeCounter.setText("0:30");
            statusText = (TextView) findViewById(R.id.statusText);
            statusText.setText("");
            countDownTimer = new CountDownTimer(30 * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    long minutes = l / 60000;
                    long seconds = (l - (minutes * 60)) / 1000;
                    String secondString = seconds + "";
                    if (seconds <= 9) {
                        secondString = "0" + secondString;
                        timeCounter.setTextColor(Color.parseColor("#800000"));
                    }
                    timeCounter.setText(minutes + ":" + secondString);
                }

                @Override
                public void onFinish() {
                    startButton=(Button)findViewById(R.id.startButton);
                    startButton.setEnabled(true);
                    ans1 = (Button)findViewById(R.id.ans1);
                    ans1.setEnabled(false);
                    ans2 = (Button)findViewById(R.id.ans2);
                    ans2.setEnabled(false);
                    ans3 = (Button)findViewById(R.id.ans3);
                    ans3.setEnabled(false);
                    ans4 = (Button)findViewById(R.id.ans4);
                    ans4.setEnabled(false);
                    statusText = (TextView) findViewById(R.id.statusText);
                    statusText.setText("Your Score is : "+correctAsnwer);
                    isGameActive = false;
                    timeCounter.setText("Time Is Up!");

                }
            }.start();

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
