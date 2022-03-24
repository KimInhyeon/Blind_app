package com.ksinfo.tomodomo.controller.mypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.controller.annualincome.AnnualIncomeRankCalculatorActivity;
import com.ksinfo.tomodomo.controller.member.LoginActivity;
import com.ksinfo.tomodomo.model.itf.MemberItf;
import com.ksinfo.tomodomo.model.vo.member.MemberVO;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyProfileActivity extends AppCompatActivity {
    TextView modifyNickNameEditText;
    TextView profileCompanyTextView;

    MemberItf memberItf = RetrofitFactory.createJsonRetrofit().create(MemberItf.class);
    boolean changeCount = false;
    boolean enableButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profile);

        findViewById(R.id.modifyProfileBtn).setOnClickListener(onClickListener);



        memberItf.modifyProfileApp("149").enqueue(new Callback<MemberVO>() {

            String userNickName;
            String companyName;

            @Override
            public void onResponse(Call<MemberVO> call, Response<MemberVO> response) {
                MemberVO memberVO = response.body();
                userNickName = memberVO.getUserNickname();
                companyName = memberVO.getCompanyName();
                Log.d("modifyprofileVO", userNickName);

                modifyNickNameEditText = findViewById(R.id.modifyNickNameEditText);
                profileCompanyTextView = findViewById(R.id.profileCompanyTextView);
                modifyNickNameEditText.setText(userNickName);
                profileCompanyTextView.setText(companyName);
            }

            @Override
            public void onFailure(Call<MemberVO> call, Throwable t) {
                t.printStackTrace();
            }
        });

    ImageButton backBtn = findViewById(R.id.backPress);
     backBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    });

        EditText edit = (EditText)findViewById(R.id.modifyNickNameEditText);


        edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView modifyNickNameCheckTextView = (TextView) findViewById(R.id.modifyNickNameCheckTextView);
                Button modifyProfileBtn = (Button) findViewById(R.id.modifyProfileBtn);
                String userId = "149";
                HashMap<String, String> params = new HashMap<>();
                params.put("newNickname", s.toString());
                params.put("userId", userId);

                modifyProfileBtn.setEnabled(false);
                modifyProfileBtn.setTextColor(Color.parseColor("#8E8B8B"));
                memberItf.checkNickNameApp(params).enqueue(new Callback<HashMap<String, String>>() {

                    @Override
                    public void onResponse(@NonNull Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                        HashMap<String, String> message = response.body();
                        if (response.isSuccessful()) {

                            Log.d("message", message.toString());
                            if(message.get("code").equals("2")){
                                modifyNickNameCheckTextView.setVisibility(View.VISIBLE);
                                modifyNickNameCheckTextView.setText(message.get("guide"));
                                modifyNickNameCheckTextView.setTextColor(Color.parseColor("#ff2222"));
                                modifyNickNameEditText.setClickable(false);
                                modifyNickNameEditText.setFocusable(false);
                            } else if(changeCount == false){
                                modifyNickNameCheckTextView.setVisibility(View.GONE);
                                changeCount = true;
                            }else if(s.length()<1){
                                modifyNickNameCheckTextView.setVisibility(View.GONE);

                            }else if(message.get("code").equals("3") && changeCount == true){
                                modifyNickNameCheckTextView.setVisibility(View.VISIBLE);
                                modifyNickNameCheckTextView.setText(message.get("guide"));
                                modifyNickNameCheckTextView.setTextColor(Color.parseColor("#00ff00"));
                                modifyProfileBtn.setEnabled(true);
                                modifyProfileBtn.setTextColor(Color.parseColor("#000000"));

                            }else if((message.get("code").equals("1") && changeCount == true)){
                                modifyNickNameCheckTextView.setVisibility(View.VISIBLE);
                                modifyNickNameCheckTextView.setText(message.get("guide"));
                                modifyNickNameCheckTextView.setTextColor(Color.parseColor("#ff2222"));

                            }
                        }else {
                            Log.d("fail", message.get("code"));
                        }

                    }

                    @Override
                    public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.modifyProfileBtn:
                    modifyProfile();
                    break;
            }
        }
    };

    private void modifyProfile() {
        String newNickName = ((EditText) findViewById(R.id.modifyNickNameEditText)).getText().toString();
        String userId = "149";
        HashMap<String, String> params = new HashMap<>();
        params.put("newNickname", newNickName);
        params.put("userId", userId);

        memberItf.checkUpdateProfileApp(params).enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(@NonNull Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                HashMap<String, String> message = response.body();
                if (response.isSuccessful()) {
                if (message.get("result").equals("3")) {
                    changeCount = false;
                    startToast("ニックネームが変更されました。");
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                }else{
                    Log.d("fail", message.get("result"));
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

            }
        });

    }
    private void startToast(String msg){
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
    }


}