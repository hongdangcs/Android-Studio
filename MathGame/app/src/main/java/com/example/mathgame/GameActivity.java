package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView score;
    TextView time;
    TextView life;
    TextView question;
    EditText answer;
    Button okButton;
    Button nextButton;

    Random random = new Random();
    int no1, no2, userAns, correctAns, userScore = 0;
    int userLife = 3;

    CountDownTimer timer;
    private static final long START_TIMER = 61000;
    Boolean time_counter;
    long timeLeft = START_TIMER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = findViewById(R.id.textViewScore);
        time = findViewById(R.id.textViewTime);
        life = findViewById(R.id.textViewLife);
        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextAnswer);
        okButton = findViewById(R.id.buttonOk);
        nextButton = findViewById(R.id.buttonNextQuestion);

        okButton.setEnabled(false);
        answer.addTextChangedListener(ansTextWatcher);

        gameContinue();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userAns = Integer.parseInt(answer.getText().toString());

                pauseTimer();

                if (userAns == correctAns) {
                    userScore += 10;
                    score.setText("" + userScore);
                    question.setText("Correct!");
                } else {
                    userLife--;
                    life.setText("" + userLife);
                    question.setText("Wrong!!!");
                }
                okToNext();

            }

        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
                answer.setText("");
                if(userLife==0){
                    Toast.makeText(getApplicationContext(), "Game Over!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                    intent.putExtra("score", userScore);
                    startActivity(intent);
                    finish();
                } else {
                    gameContinue();
                }
            }
        });
    }

    private TextWatcher ansTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String ans = answer.getText().toString();
            okButton.setEnabled(!ans.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void gameContinue() {
        no1 = random.nextInt(100);
        no2 = random.nextInt(100);
        question.setText(no1 + " + " + no2);
        correctAns = no1 + no2;
        startTimer();
        nextToOk();
    }

    public void startTimer() {
        timer = new CountDownTimer(START_TIMER, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished / 1000;
                updateText();

            }

            @Override
            public void onFinish() {
                time_counter = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife--;
                life.setText("" + userLife);
                question.setText("Time is up!");
                okToNext();
            }
        }.start();
        time_counter = true;
    }

    public void updateText() {
        time.setText("" + timeLeft);

    }

    public void pauseTimer() {
        timer.cancel();
        time_counter = false;

    }

    public void resetTimer() {
        time.setText("" + START_TIMER);
    }

    public void okToNext() {
        nextButton.setVisibility(View.VISIBLE);
        okButton.setVisibility(View.GONE);
    }

    public void nextToOk() {
        nextButton.setVisibility(View.GONE);
        okButton.setVisibility(View.VISIBLE);
    }
}