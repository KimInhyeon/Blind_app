package com.ksinfo.blind.member;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.TomodomoApplication;
import com.ksinfo.blind.annualincome.AnnualIncomeRankCalculatorActivity;
import com.ksinfo.blind.databinding.JoinMemberBinding;
import com.ksinfo.blind.member.api.MemberApi;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberJoinActivity extends AppCompatActivity {
    @Inject MemberApi memberApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JoinMemberBinding binding = JoinMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TomodomoApplication tomodomoApplication = ((TomodomoApplication) getApplication());
        tomodomoApplication.getApplicationComponent().inject(this);

        binding.signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    RadioGroup role = binding.role;
                    int selectedId = role.getCheckedRadioButtonId();
                    RadioButton selectedRole = findViewById(selectedId);

                    HashMap<String, String> params = new HashMap<>();
                    params.put("username", binding.emailEt.getText().toString());
                    params.put("userNickname", binding.nickEt.getText().toString());
                    params.put("password", binding.pwEt.getText().toString());

                    if (selectedRole.getText().toString().equals("一般会員")) {
                        params.put("role", "NM");
                    } else if (selectedRole.getText().toString().equals("正会員")) {
                        params.put("role", "RM");
                    } else if (selectedRole.getText().toString().equals("管理者")) {
                        params.put("role", "SV");
                    }

                    joinMember(params);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void joinMember(HashMap<String, String> params) {
        memberApi.registMemberApp(params).enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(
                @NonNull Call<HashMap<String, String>> call,
                @NonNull Response<HashMap<String, String>> response
            ) {
                HashMap<String, String> message = response.body();
                if (response.isSuccessful()) {
                    Log.d("message", message.toString());
                    if (message.get("code").equals("BLIND_SCS_MSG_001")) {
                        Intent intent = new Intent(getApplicationContext(), AnnualIncomeRankCalculatorActivity.class);
                        startActivity(intent);
                    } else {
                        Log.d("fail", "fail");
                    }
                } else {
                    Log.d("fail", message.get("code"));
                }
            }

            @Override
            public void onFailure(
                @NonNull Call<HashMap<String, String>> call,
                @NonNull Throwable t
            ) {
                t.printStackTrace();
            }
        });
    }
}