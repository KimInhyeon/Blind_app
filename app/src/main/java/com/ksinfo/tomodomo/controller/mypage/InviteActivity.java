package com.ksinfo.tomodomo.controller.mypage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;

public class InviteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iv_main);

    }

    /*
    public void sendSMS(View v){
        String smsNum = smsNumber.getText().toString();
        String smsText = smsTextContext.getText().toString();

        if (smsNum.length()>0 && smsText.length()>0){
            sendSMS(smsNum, smsText);
        }else{
            Toast.makeText(this, "全部入力してください。", Toast.LENGTH_SHORT).show();
        }
    }
    */
}

