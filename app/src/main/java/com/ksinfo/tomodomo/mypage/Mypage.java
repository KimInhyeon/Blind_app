package com.ksinfo.tomodomo.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.annualincome.AnnualIncomeRankCalculatorActivity;


public class Mypage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        Button move_annualIncome = (Button)findViewById(R.id.annualIncomeBtn);
        move_annualIncome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnnualIncomeRankCalculatorActivity.class);
                startActivity(intent);
            }
        });
    }
}