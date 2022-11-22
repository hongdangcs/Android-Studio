package com.ddq.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private static TextView correctTextView, wrongTextView, emptyTextView, scoreTextView;
    private static Button playAgainBtn, quitBtn;
    private int correct, wrong, empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        correctTextView = findViewById(R.id.textViewTotalCorrect);
        wrongTextView = findViewById(R.id.textViewTotalWrong);
        emptyTextView = findViewById(R.id.textViewTotalEmpty);
        scoreTextView = findViewById(R.id.textViewTotalScore);

        playAgainBtn = findViewById(R.id.buttonPlayAgain);
        quitBtn = findViewById(R.id.buttonQuit);

        correct = getIntent().getIntExtra("correct", 0);
        wrong = getIntent().getIntExtra("wrong", 0);
        empty = getIntent().getIntExtra("empty", 0);

        correctTextView.setText("Correct answers: "+ correct);
        wrongTextView.setText("Wrong answers: "+ wrong);
        emptyTextView.setText("Empty answers: "+ empty);

        scoreTextView.setText("Your Score: " + (10*correct));

        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(Intent.ACTION_MAIN);
                newIntent.addCategory(Intent.CATEGORY_HOME);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newIntent);
                finish();
            }
        });

    }
}