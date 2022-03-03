package com.ksinfo.tomodomo.controller.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.model.itf.MemberItf;
import com.ksinfo.tomodomo.model.vo.member.MemberVO;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyProfileActivity extends AppCompatActivity {
    TextView modifyNickNameEditText;

    MemberItf memberItf = RetrofitFactory.createJsonRetrofit().create(MemberItf.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profile);

        findViewById(R.id.modifyProfileBtn).setOnClickListener(onClickListener);

        memberItf.modifyProfileApp("149").enqueue(new Callback<MemberVO>() {

            String userNickName;

            @Override
            public void onResponse(Call<MemberVO> call, Response<MemberVO> response) {
                MemberVO memberVO = response.body();
                userNickName = memberVO.getUserNickname();
                Log.d("modifyprofileVO", userNickName);

                modifyNickNameEditText = findViewById(R.id.modifyNickNameEditText);
                modifyNickNameEditText.setText(userNickName);

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

        memberItf.checkUpdateProfileApp(params).enqueue(new Callback<MemberVO>() {


            @Override
            public void onResponse(Call<MemberVO> call, Response<MemberVO> response) {

            }

            @Override
            public void onFailure(Call<MemberVO> call, Throwable t) {

            }
        });

    }

}