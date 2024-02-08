package com.thantronguyenkhanh.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtResult,txtFormula;
    MaterialButton btnClear,btnOpen,btnClose;
    MaterialButton btnDivide,btnMultiply,btnAdd,btnSubtract,btnEqual;
    MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    MaterialButton btnAllClear,btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        txtResult = findViewById(R.id.txtResult);
        txtFormula = findViewById(R.id.txtFormula);
        assignId(btn0,R.id.btn0);
        assignId(btn1,R.id.btn1);
        assignId(btn2,R.id.btn2);
        assignId(btn3,R.id.btn3);
        assignId(btn4,R.id.btn4);
        assignId(btn5,R.id.btn5);
        assignId(btn6,R.id.btn6);
        assignId(btn7,R.id.btn7);
        assignId(btn8,R.id.btn8);
        assignId(btn9,R.id.btn9);
        assignId(btnClear,R.id.btnClear);
        assignId(btnOpen,R.id.btnOpen);
        assignId(btnClose,R.id.btnClose);
        assignId(btnDivide,R.id.btnDivide);
        assignId(btnMultiply,R.id.btnMultiply);
        assignId(btnAdd,R.id.btnAdd);
        assignId(btnSubtract,R.id.btnSubtract);
        assignId(btnEqual,R.id.btnEqual);
        assignId(btnAllClear,R.id.btnAllClear);
        assignId(btnDot,R.id.btnDot);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = txtFormula.getText().toString();

        if (buttonText.equals("AC")) {
            txtFormula.setText("");
            txtResult.setText("0");
            return;
        }

        if (buttonText.equals("=")) {
            String finalResult = getResult(dataToCalculate);

            if (!finalResult.equals("Error")) {
                txtResult.setText(finalResult);
            }
            return;
        }

        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        txtFormula.setText(dataToCalculate);
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "Error";
        }
    }
}