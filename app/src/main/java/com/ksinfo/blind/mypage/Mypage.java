package com.ksinfo.blind.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.R;
import com.ksinfo.blind.annualincome.AnnualIncomeRankCalculatorActivity;


public class Mypage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);



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


        ImageButton logout = findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Mypage.this);
                builder.setTitle("lログアウトしますか。");
                builder.setMessage("パスワードを忘れたら、計定の復舊ができないので注意してください。");
                builder.setPositiveButton("lログアウトする。",null);
                builder.setNegativeButton("キャンセル。",null);
                builder.create().show();
            }
        });


    }
}