package com.ksinfo.tomodomo.member;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.annualincome.AnnualIncomeRankCalculatorActivity;
import com.ksinfo.tomodomo.board.TopicMainActivity;
import com.ksinfo.tomodomo.controller.company.CompanyReviewActivity;
import com.ksinfo.tomodomo.home.Home;
import com.ksinfo.tomodomo.member.api.MemberApi;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    MemberApi memberApi = RetrofitFactory.createJsonRetrofit().create(MemberApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        Button move_topic_main = findViewById(R.id.topicMain);
        move_topic_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TopicMainActivity.class);
                startActivity(intent);
            }
        });

        Button move_annualIncomeCalculator = (Button)findViewById(R.id.annualIncomeCalculatorBtn);
        move_annualIncomeCalculator.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnnualIncomeRankCalculatorActivity.class);
                startActivity(intent);
            }
        });


        Button review_write = (Button)findViewById(R.id.reviewWrite);
        review_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CompanyReviewActivity.class);
                startActivity(intent);
            }
        });




        Button passAccess = (Button)findViewById(R.id.passBtn);
        passAccess.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        Button member_join = (Button)findViewById(R.id.join);
        member_join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemberJoinActivity.class);
                startActivity(intent);
            }
        });








        HashMap<String, String> params = new HashMap<>();

        Button login = (Button)findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    EditText email = (EditText) findViewById(R.id.emailEt);
                    EditText password = (EditText) findViewById(R.id.pwEt);

                    params.put("username", email.getText().toString());
                    params.put("password", password.getText().toString());

                    loginApp(params);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void loginApp(HashMap<String, String> params) {

        memberApi.loginApp(params).enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(@NonNull Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {

                if (response.isSuccessful()) {
                    HashMap<String, String> message = response.body();
                    Log.d("message", message.toString());
                    if (message.get("message").equals("OK")) {
                        Intent intent = new Intent(getApplicationContext(), TopicMainActivity.class);
                        startActivity(intent);
                    } else {
                        Log.d("fail", "fail");
                    }
                } else {
                    Log.d("fail", "fail");
                }
            }
            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                Log.d("fail", "fail");
                t.printStackTrace();
            }
        });
    }
}