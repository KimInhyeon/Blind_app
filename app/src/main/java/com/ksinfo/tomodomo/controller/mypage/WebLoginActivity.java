package com.ksinfo.tomodomo.controller.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;

public class WebLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wl_main);

        Button move_web_login_access = (Button)findViewById(R.id.move_web_login_access_btn);
        move_web_login_access.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WebLoginAccessActivity.class);
                startActivity(intent);
            }
        });
    }

}

