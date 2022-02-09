package com.ksinfo.blind.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.blind.R;
import com.ksinfo.blind.mypage.api.NoticeApi;
import com.ksinfo.blind.mypage.util.NoticeRecyclerViewAdapter;
import com.ksinfo.blind.mypage.vo.NoticeVO;
import com.ksinfo.blind.util.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_login);

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

