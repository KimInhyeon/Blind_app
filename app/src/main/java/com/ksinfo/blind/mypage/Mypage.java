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
import com.ksinfo.blind.member.CertificationActivity;


public class Mypage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);


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

        Button move_certification_btn1 = (Button)findViewById(R.id.move_certification_btn1);
        move_certification_btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CertificationActivity.class);
                startActivity(intent);
            }
        });

        //１．アカウントの情報更新
        Button move_certification_btn2 = (Button)findViewById(R.id.move_certification_btn2);
        move_certification_btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CertificationActivity.class);
                startActivity(intent);
            }
        });

        Button move_annualIncomeCalculator = (Button)findViewById(R.id.move_annual_incomeCalculator_Btn);
        move_annualIncomeCalculator.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnnualIncomeRankCalculatorActivity.class);
                startActivity(intent);
            }
        });

        //２．招待、Webログイン
        Button move_invite = (Button)findViewById(R.id.move_invite_btn);
        move_invite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InviteActivity.class);
                startActivity(intent);
            }
        });

        Button move_web_login = (Button)findViewById(R.id.move_web_login_btn);
        move_web_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WebLoginActivity.class);
                startActivity(intent);
            }
        });

        Button move_notice = (Button)findViewById(R.id.move_notice_btn);
        move_notice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });




    }
}