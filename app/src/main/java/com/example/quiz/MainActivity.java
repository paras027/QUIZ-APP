package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView numberofQuestion, question, op1, op2, op3, op4;
    ImageView image;
    Button next;
    ArrayList<dataModalClass> arrayList;
    int score = 0;
    int totalQuestion = dataModalClass.question.length;
    int curr = 0;
    int check=0;
    String Selected_Answer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.textView);
        numberofQuestion = findViewById(R.id.textView6);
        op1 = findViewById(R.id.textView2);
        op2 = findViewById(R.id.textView3);
        op3 = findViewById(R.id.textView4);
        op4 = findViewById(R.id.textView5);
        image = findViewById(R.id.imageView);
        next = findViewById(R.id.button1);

        numberofQuestion.setText("Questions: "+curr+"/"+totalQuestion);
        loadNewQuestion();
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op1.setBackgroundColor(Color.MAGENTA);
                op2.setBackgroundColor(Color.WHITE);
                op3.setBackgroundColor(Color.WHITE);
                op4.setBackgroundColor(Color.WHITE);
                Selected_Answer = op1.getText().toString();
                check=1;
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op2.setBackgroundColor(Color.MAGENTA);
                op1.setBackgroundColor(Color.WHITE);
                op3.setBackgroundColor(Color.WHITE);
                op4.setBackgroundColor(Color.WHITE);
                Selected_Answer = op2.getText().toString();
                check=1;
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op3.setBackgroundColor(Color.MAGENTA);
                op1.setBackgroundColor(Color.WHITE);
                op2.setBackgroundColor(Color.WHITE);
                op4.setBackgroundColor(Color.WHITE);
                Selected_Answer = op3.getText().toString();
                check=1;
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op4.setBackgroundColor(Color.MAGENTA);
                op1.setBackgroundColor(Color.WHITE);
                op3.setBackgroundColor(Color.WHITE);
                op2.setBackgroundColor(Color.WHITE);
                check=1;
                Selected_Answer = op4.getText().toString();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check==1)
                {
                    if(Selected_Answer.equals(dataModalClass.Answers[curr]))
                    {
                        score++;
                    }
                    curr++;
                    loadNewQuestion();
                    op4.setBackgroundColor(Color.WHITE);
                    op1.setBackgroundColor(Color.WHITE);
                    op3.setBackgroundColor(Color.WHITE);
                    op2.setBackgroundColor(Color.WHITE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
                    image.startAnimation(animation);
                    op1.startAnimation(animation);
                    op2.startAnimation(animation);
                    op3.startAnimation(animation);
                    op4.startAnimation(animation);
                    question.startAnimation(animation);


                }
                else
                {
                    Toast.makeText(MainActivity.this, "No Options Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadNewQuestion() {

        if(curr==totalQuestion)
        {
            finishQuiz();
            return;
        }
        int val=curr+1;
        numberofQuestion.setText("Questions: "+val+"/"+totalQuestion);
        int resId = getResources().getIdentifier(dataModalClass.image[curr], "drawable", getPackageName());
        image.setImageResource(resId);
        question.setText(dataModalClass.question[curr]);
        op1.setText(dataModalClass.options[curr][0]);
        op2.setText(dataModalClass.options[curr][1]);
        op3.setText(dataModalClass.options[curr][2]);
        op4.setText(dataModalClass.options[curr][3]);


    }

    private void finishQuiz() {
        String pass="";
        if(score>totalQuestion*0.60)
        {
            pass="Passed";
        }
        else
        {
            pass="failed";
        }

        new AlertDialog.Builder(this).setTitle(pass).setMessage("Score is: "+ score+ " Out of "+totalQuestion)
                .setPositiveButton("Restart",((dialogInterface, i) -> RestartQuiz()))
                .setCancelable(false).show();
    }

    private void RestartQuiz() {
        curr=0;
        score=0;
        loadNewQuestion();
    }
}