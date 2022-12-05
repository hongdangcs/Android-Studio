package com.ddq.flagquiz;

import static android.graphics.Color.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private static TextView correctTextView, wrongTextView, emptyTextView, textViewQuestion;
    private static ImageView flagImage, nextImage;
    private static Button btn1, btn2, btn3, btn4;

    private FlagsDatabase flagsDatabase;
    private ArrayList<FlagsModel> questions;
    private ArrayList<FlagsModel> wrongAns;
    HashSet<FlagsModel> mixOptions = new HashSet<>();
    ArrayList<FlagsModel> options = new ArrayList<>();

    int correct = 0, empty = 0, wrong = 0, question = 0;

    private FlagsModel correctFlag;

    boolean btnControl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        flagsDatabase = new FlagsDatabase(QuizActivity.this);
        questions = new FlagsDAO().getRandomTenQuestion(flagsDatabase);


        correctTextView = findViewById(R.id.textViewCorrect);
        wrongTextView = findViewById(R.id.textViewWrong);
        emptyTextView = findViewById(R.id.textViewEmpty);

        flagImage = findViewById(R.id.imageViewFlag);
        nextImage = findViewById(R.id.next);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);

        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
    
        loadQuestion();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansControl(btn1);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansControl(btn2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansControl(btn3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansControl(btn4);
            }
        });
        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question++;

                if (!btnControl && question < 10) {
                    empty++;
                    emptyTextView.setText("Empty: " + empty);
                    loadQuestion();
                } else if(btnControl && question < 10){
                    loadQuestion();
                    btn1.setClickable(true);
                    btn2.setClickable(true);
                    btn3.setClickable(true);
                    btn4.setClickable(true);

                    btn1.setBackgroundColor(getResources().getColor(R.color.button_color));
                    btn2.setBackgroundColor(getResources().getColor(R.color.button_color));
                    btn3.setBackgroundColor(getResources().getColor(R.color.button_color));
                    btn4.setBackgroundColor(getResources().getColor(R.color.button_color));
                } else if(question == 10){
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("correct", correct);
                    intent.putExtra("wrong", wrong);
                    intent.putExtra("empty", empty);
                    startActivity(intent);
                    finish();
                }
                btnControl = false;
            }
        });
    }

    public void loadQuestion(){
        textViewQuestion.setText("Question: "+ (question +1));
        correctFlag = questions.get(question);
        flagImage.setImageResource(getResources().getIdentifier(correctFlag.getImage(), "drawable", getPackageName()));
        wrongAns = new FlagsDAO().getWrongAns(flagsDatabase, correctFlag.getId());
        mixOptions.clear();
        mixOptions.add(correctFlag);
        mixOptions.add(wrongAns.get(0));
        mixOptions.add(wrongAns.get(1));
        mixOptions.add(wrongAns.get(2));

        options.clear();
        for(FlagsModel flag : mixOptions){
            options.add(flag);
        }
        btn1.setText(options.get(0).getName());
        btn2.setText(options.get(1).getName());
        btn3.setText(options.get(2).getName());
        btn4.setText(options.get(3).getName());

    }

    public void ansControl(Button button){
        String buttonText = button.getText().toString();
        String correctAns = correctFlag.getName();

        if(buttonText.equals(correctAns)){
            correct++;
            button.setBackgroundColor(GREEN);

        }
        else{
            wrong++;
            button.setBackgroundColor(RED);

            if(btn1.getText().toString().equals(correctAns)){
                btn1.setBackgroundColor(GREEN);
            }
            if(btn2.getText().toString().equals(correctAns)){
                btn2.setBackgroundColor(GREEN);
            }
            if(btn3.getText().toString().equals(correctAns)){
                btn3.setBackgroundColor(GREEN);
            }
            if(btn4.getText().toString().equals(correctAns)){
                btn4.setBackgroundColor(GREEN);
            }

        }
        btn1.setClickable(false);
        btn2.setClickable(false);
        btn3.setClickable(false);
        btn4.setClickable(false);

        correctTextView.setText("Correct: "+correct);
        wrongTextView.setText("Wrong: "+wrong);

        btnControl = true;
    }
}