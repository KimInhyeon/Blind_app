package com.ksinfo.blind.member;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.TomodomoApplication;
import com.ksinfo.blind.annualincome.AnnualIncomeRankCalculatorActivity;
import com.ksinfo.blind.board.activity.SearchPostActivity;
import com.ksinfo.blind.databinding.LoginBinding;
import com.ksinfo.blind.home.Home;
import com.ksinfo.blind.member.api.MemberApi;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @Inject MemberApi memberApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginBinding binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TomodomoApplication tomodomoApplication = ((TomodomoApplication) getApplication());
        tomodomoApplication.getApplicationComponent().inject(this);

        binding.topicMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchPostActivity.class);
                startActivity(intent);
            }
        });

        binding.annualIncomeCalculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnnualIncomeRankCalculatorActivity.class);
                startActivity(intent);
            }
        });

        binding.passBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        binding.join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemberJoinActivity.class);
                startActivity(intent);
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HashMap<String, String> params = new HashMap<>(2);
                    params.put("username", binding.emailEt.getText().toString());
                    params.put("password", binding.pwEt.getText().toString());

                    loginApp(params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loginApp(HashMap<String, String> params) {
        memberApi.loginApp(params).enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(
                @NonNull Call<HashMap<String, String>> call,
                @NonNull Response<HashMap<String, String>> response
            ) {
                if (response.isSuccessful()) {
                    HashMap<String, String> message = response.body();
                    Log.d("message", message.toString());
                    if (message.get("message").equals("OK")) {
                        Intent intent = new Intent(getApplicationContext(), SearchPostActivity.class);
                        startActivity(intent);
                    } else {
                        Log.d("fail", "fail");
                    }
                } else {
                    Log.d("fail", "fail");
                }
            }

            @Override
            public void onFailure(
                @NonNull Call<HashMap<String, String>> call,
                @NonNull Throwable t
            ) {
                Log.d("fail", "fail");
                t.printStackTrace();
            }
        });
    }
}