package com.ksinfo.blind.member;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.R;
import com.ksinfo.blind.annualincome.AnnualIncomeRankCalculatorActivity;
import com.ksinfo.blind.member.api.MemberApi;
import com.ksinfo.blind.util.RetrofitFactory;

import java.util.HashMap;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberJoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_member);

        Button signIn = (Button)findViewById(R.id.signBtn);

        signIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                try{
                    HashMap<String, String> params = new HashMap<>();

                    EditText email = (EditText) findViewById(R.id.emailEt);
                    EditText nick = (EditText) findViewById(R.id.nickEt);
                    EditText password = (EditText) findViewById(R.id.pwEt);
                    RadioGroup role = (RadioGroup) findViewById(R.id.role);
                    int selectedId = role.getCheckedRadioButtonId();
                    RadioButton selectedRole = (RadioButton) findViewById(selectedId);

                    params.put("username", email.getText().toString());
                    params.put("userNickname", nick.getText().toString());
                    params.put("password", password.getText().toString());

                    if (selectedRole.getText().toString().equals("一般会員")) {
                        params.put("role", "NM");
                    } else if (selectedRole.getText().toString().equals("正会員")) {
                        params.put("role", "RM");
                    } else if (selectedRole.getText().toString().equals("管理者")) {
                        params.put("role", "SV");
                    }

                    joinMember(params);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }

    private void joinMember(HashMap<String, String> params) {
        MemberApi memberApi = RetrofitFactory.createJsonRetrofit().create(MemberApi.class);

        memberApi.registMemberApp(params).enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(@NonNull Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
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
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
