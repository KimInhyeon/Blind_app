package com.ksinfo.tomodomo.controller.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.model.itf.AnnualDataInterface;
import com.ksinfo.tomodomo.util.RetrofitFactory;

public class InviteActivity extends AppCompatActivity {

    private String textToShare= "" ; //invite code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_iv_main);
    }


    // 메모 영상기반으로 제작한 share기능
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.share_menu){
            //메모 텍스트 콘텐츠 보내기
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        }
        else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    //ImageButton btnInviteToShate = (ImageButton) findViewById(R.id.share_menu_invite);

    Button btnInvite = (Button) findViewById(R.id.shareBtn);


    // 메모 사용화면의 이미지버튼에 share기능삽입.
    // 동영상의 share버튼 기능은 menu 레이아웃을 별개로 만들기에 그대로 활용은 어렵다 판단하여 재구성 실시.
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.id.share_menu_invite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //메모 Android 인텐트 리졸버 사용
        if(item.getItemId() == R.id.share_menu){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
    */



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

