package com.ksinfo.tomodomo.controller.mypage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;


public class Mypage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_mypage);

        /*
        Button move_annualIncome = (Button)findViewById(R.id.annualIncomeBtn);
        move_annualIncome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnnualIncomeRankCalculatorActivity.class);
                startActivity(intent);
            }
        });
        */
    }
}