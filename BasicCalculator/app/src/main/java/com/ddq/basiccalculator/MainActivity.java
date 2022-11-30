package com.ddq.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private static Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnMinus, btnDot, btnMul, btnDiv, btnEqual, btnAC, btnC, btnOpen, btnClose;
    private static TextView resultTextView, functionTextView;

    private String number = null;

    double firstNumber = 0;
    double lastNumber = 0;

    String status = null;
    boolean operator = false;

    DecimalFormat myFormatter = new DecimalFormat("######.######");

    String history, currentResult;

    boolean dot = true;

    boolean btnACcontrol = true;

    boolean btnEqualsControl = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnOpen = findViewById(R.id.btnOpen);
        btnClose = findViewById(R.id.btnClose);
        btnMul = findViewById(R.id.btnMul);
        btnMinus = findViewById(R.id.btnMinus);
        btnAdd = findViewById(R.id.btnAdd);
        btnDiv = findViewById(R.id.btnDiv);
        btnAC = findViewById(R.id.btnAC);
        btnC = findViewById(R.id.btnCCC);
        btnDot = findViewById(R.id.btnDot);
        btnEqual = findViewById(R.id.btnEqual);

        resultTextView = findViewById(R.id.resultTextView);
        functionTextView = findViewById(R.id.functionTextView);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("9");
            }
        });
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("(");
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick(")");
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number = null;
                status = null;
                resultTextView.setText("0");
                functionTextView.setText("");
                firstNumber = 0;
                lastNumber = 0;
                dot = true;
                btnACcontrol = true;

            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                functionTextView.setText("");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = functionTextView.getText().toString();
                currentResult = resultTextView.getText().toString();
                functionTextView.setText(history+currentResult+"+");

                if (operator)
                {
                    if (status =="multiplacition")
                    {
                        multiply();
                    }
                    else if (status == "division")
                    {
                        divide();
                    }
                    else if (status == "subtraction")
                    {
                        minus();
                    }
                    else
                    {
                        plus();
                    }
                }

                status = "sum";
                operator = false;
                number = null;

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = functionTextView.getText().toString();
                currentResult = resultTextView.getText().toString();
                functionTextView.setText(history+currentResult+"-");

                if (operator)
                {
                    if (status == "multiplacition")
                    {
                        multiply();
                    }
                    else if (status == "division")
                    {
                        divide();
                    }
                    else if (status == "sum")
                    {
                        plus();
                    }
                    else
                    {
                        minus();
                    }
                }

                status = "subtraction";
                operator = false;
                number = null;

            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = functionTextView.getText().toString();
                currentResult = resultTextView.getText().toString();
                functionTextView.setText(history+currentResult+"*");

                if (operator)
                {
                    if (status == "sum")
                    {
                        plus();
                    }
                    else if (status == "division")
                    {
                        divide();
                    }
                    else if (status == "subtraction")
                    {
                        minus();
                    }
                    else
                    {
                        multiply();
                    }

                }

                status = "multiplacition";
                operator = false;
                number = null;

            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = functionTextView.getText().toString();
                currentResult = resultTextView.getText().toString();
                functionTextView.setText(history+currentResult+"/");

                if (operator)
                {
                    if (status == "multiplacition")
                    {
                        multiply();
                    }
                    else if (status == "sum")
                    {
                        plus();
                    }
                    else if (status == "subtraction")
                    {
                        minus();
                    }
                    else
                    {
                        divide();
                    }
                }

                status = "division";
                operator = false;
                number = null;

            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (operator)
                {
                    if (status == "sum")
                    {
                        plus();
                    }
                    else if (status == "subtraction")
                    {
                        minus();
                    }
                    else if (status == "multiplacition")
                    {
                        multiply();
                    }
                    else if (status == "division")
                    {
                        divide();
                    }
                    else
                    {
                        firstNumber = Double.parseDouble(resultTextView.getText().toString());
                    }
                }

                operator = false;
                btnEqualsControl = true;

            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dot)
                {

                    if (number == null)
                    {
                        number = "0.";
                    }
                    else
                    {
                        number = number + ".";
                    }

                }

                resultTextView.setText(number);
                dot = false;
            }
        });

    }

    public void numberClick(String view)
    {
        if (number == null)
        {
            number = view;
        }
        else if (btnEqualsControl)
        {
            firstNumber = 0;
            lastNumber = 0;
            number = view;
        }
        else
        {
            number = number + view;
        }

        resultTextView.setText(number);
        operator = true;
        btnACcontrol = false;
        btnC.setClickable(true);
        btnEqualsControl = false;
    }

    public void plus()
    {
        lastNumber = Double.parseDouble(resultTextView.getText().toString());
        firstNumber = firstNumber + lastNumber;

        resultTextView.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void minus()
    {
        if (firstNumber == 0)
        {
            firstNumber = Double.parseDouble(resultTextView.getText().toString());
        }
        else
        {
            lastNumber = Double.parseDouble(resultTextView.getText().toString());
            firstNumber = firstNumber - lastNumber;
        }

        resultTextView.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void multiply()
    {
        if (firstNumber == 0)
        {
            firstNumber = 1;
            lastNumber = Double.parseDouble(resultTextView.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }
        else
        {
            lastNumber = Double.parseDouble(resultTextView.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }

        resultTextView.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void divide()
    {
        if (firstNumber == 0)
        {
            lastNumber = Double.parseDouble(resultTextView.getText().toString());
            firstNumber = lastNumber / 1;
        }
        else
        {
            lastNumber = Double.parseDouble(resultTextView.getText().toString());
            firstNumber = firstNumber / lastNumber;
        }

        resultTextView.setText(myFormatter.format(firstNumber));
        dot = true;
    }
}