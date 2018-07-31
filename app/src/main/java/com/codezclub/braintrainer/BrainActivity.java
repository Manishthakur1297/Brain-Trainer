package com.codezclub.braintrainer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class BrainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    TextView timer,question,score,result;

    int a,b;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    int location;
    int correct = 0;
    int total = 0;



    public void generate()
    {

        Random rand = new Random();
        a = rand.nextInt(20) + 1;
        b = rand.nextInt(20) + 1;

        question.setText(String.valueOf(a)+ " + " + Integer.valueOf(b) + " = ");

        location = rand.nextInt(4);

        answers.clear();

        int sum = a+b;

        for (int i=0;i<4;i++)
        {
            if(i == location)
            {
                answers.add(sum);
            }
            else
            {
                int c = rand.nextInt(41);
                while(sum == c)
                {
                    c = rand.nextInt(41);
                }
                answers.add(c);
            }
        }

        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
        btn4.setText(Integer.toString(answers.get(3)));
    }

    public void Answer(View view)
    {

        if(view.getTag().toString().equals(Integer.toString(location)))
        {
            correct++;
            result.setText("Correct");
        }
        else
        {
            result.setText("Incorrect");
        }

        total++;
        score.setText(Integer.toString(correct) + "/" + Integer.toString(total));
        generate();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);

        timer = (TextView)findViewById(R.id.timer);
        question = (TextView)findViewById(R.id.question);
        score = (TextView)findViewById(R.id.score);
        result = (TextView)findViewById(R.id.result);

        timer.setText(" 30s ");
        score.setText(" 0/0 ");

        generate();

        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                timer.setText("0s");
                result.setText("Your score: " + Integer.toString(correct) + "/" + Integer.toString(total));

                int time = 1000;
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(BrainActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                },time);
            }
        }.start();


    }
}
