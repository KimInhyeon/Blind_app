package com.ksinfo.tomodomo.controller.member;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.controller.annualincome.AnnualIncomeRankCalculatorActivity;
import com.ksinfo.tomodomo.databinding.MbJoinBinding;
import com.ksinfo.tomodomo.model.itf.MemberInterface;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class MemberJoinActivity extends AppCompatActivity {
    private MbJoinBinding binding;
    @Inject MemberInterface memberInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MbJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);

        binding.mbJoinSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    RadioGroup role = binding.mbJoinRole;
                    int selectedId = role.getCheckedRadioButtonId();
                    RadioButton selectedRole = findViewById(selectedId);

                    HashMap<String, String> params = new HashMap<>();
                    params.put("username", binding.mbJoinEmailEt.getText().toString());
                    params.put("userNickname", binding.mbJoinNickEt.getText().toString());
                    params.put("password", binding.mbJoinPwEt.getText().toString());

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
        memberInterface.registerMemberApp(params).enqueue(new Callback<HashMap<String, String>>() {
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