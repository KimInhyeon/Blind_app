package com.ksinfo.tomodomo.controller.member;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.controller.annualincome.AnnualIncomeRankCalculatorActivity;
import com.ksinfo.tomodomo.controller.board.SearchPostActivity;
import com.ksinfo.tomodomo.controller.home.Home;
import com.ksinfo.tomodomo.databinding.MbLoginBinding;
import com.ksinfo.tomodomo.model.itf.MemberInterface;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class LoginActivity extends AppCompatActivity {
    private MbLoginBinding binding;
    @Inject MemberInterface memberInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MbLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);

        binding.mbLoginTopicMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchPostActivity.class);
                startActivity(intent);
            }
        });

        binding.mbLoginAnnualIncomeCalculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnnualIncomeRankCalculatorActivity.class);
                startActivity(intent);
            }
        });

        binding.mbLoginPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        binding.mbLoginJoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemberJoinActivity.class);
                startActivity(intent);
            }
        });

        binding.mbLoginLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HashMap<String, String> params = new HashMap<>(2);
                    params.put("username", binding.mbLoginEmailEt.getText().toString());
                    params.put("password", binding.mbLoginPwEt.getText().toString());

                    loginApp(params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loginApp(HashMap<String, String> params) {
        memberInterface.loginApp(params).enqueue(new Callback<HashMap<String, String>>() {
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