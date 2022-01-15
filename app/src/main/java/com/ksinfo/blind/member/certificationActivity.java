package com.ksinfo.blind.member;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.R;

public class certificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certification);

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
