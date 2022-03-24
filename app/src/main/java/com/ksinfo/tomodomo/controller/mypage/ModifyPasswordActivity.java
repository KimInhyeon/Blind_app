package com.ksinfo.tomodomo.controller.mypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.controller.member.LoginActivity;
import com.ksinfo.tomodomo.model.itf.MemberItf;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyPasswordActivity extends AppCompatActivity {
    MemberItf memberItf = RetrofitFactory.createJsonRetrofit().create(MemberItf.class);
    String userId = "149";
    boolean passwordComplete = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        findViewById(R.id.modifyPasswordBtn).setOnClickListener(onClickListener);
        TextInputLayout originalPassword = ((TextInputLayout)findViewById(R.id.originalPassword));
//        originalPassword.setCounterEnabled(true);
//        originalPassword.setCounterMaxLength(20);

    ImageButton backBtn = findViewById(R.id.backPress);
     backBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    });


        TextInputEditText newPasswordEditText = (TextInputEditText)findViewById(R.id.newPasswordEditText);
        TextInputEditText newPassword2EditText = (TextInputEditText)findViewById(R.id.newPassword2EditText);
        TextView newPasswordTextView = (TextView)findViewById((R.id.newPasswordTextView));
        TextView newPassword2TextView = (TextView)findViewById((R.id.newPassword2TextView));


        newPasswordEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()<6 || s.length()>20){
                    newPasswordTextView.setText("6~20文字の半角英文字で入力してください。");
                    newPasswordTextView.setTextColor(Color.parseColor("#ff2222"));
                    newPasswordEditText.setError("6~20文字の半角英文字で入力してください。");
                    passwordComplete = false;
                }else if(isHalfWord(s.toString()) == true){
                    newPasswordTextView.setText("");
                    if (!newPassword2EditText.equals("")) {
                        String newPassword2Text = newPassword2EditText.getText().toString();
                        if(!newPassword2Text.equals(s.toString())){
                            newPassword2TextView.setText("確認用のパスワードが一致しません。");
                            newPassword2TextView.setTextColor(Color.parseColor("#ff2222"));
                            passwordComplete = false;
                        }else{
                            newPasswordTextView.setText("");
                            newPassword2TextView.setText("");
                            newPasswordEditText.setError(null);
                            newPassword2EditText.setError(null);
                            passwordComplete = true;
                        }
                    }
                }else{
                    newPasswordTextView.setText("半角英文字で入力してください。（特殊記号不可）");
                    newPasswordEditText.setError("半角英文字で入力してください。（特殊記号不可）");
                    passwordComplete = false;
                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        newPassword2EditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()<6 || s.length()>20){
                    newPassword2TextView.setText("6~20文字の半角英文字で入力してください。");
                    newPassword2TextView.setTextColor(Color.parseColor("#ff2222"));
                    newPassword2EditText.setError("6~20文字の半角英文字で入力してください。");
                    passwordComplete = false;
                }else if(isHalfWord(s.toString()) == true){
                    newPassword2TextView.setText("");
                    String newPasswordText = newPasswordEditText.getText().toString();
                    if(!newPasswordText.equals(s.toString())){
                        newPasswordTextView.setText("新しいパスワードを一致させてください。");
                        newPassword2TextView.setText("新しいパスワードを一致させてください。");
                        newPasswordTextView.setTextColor(Color.parseColor("#ff2222"));
                        newPassword2TextView.setTextColor(Color.parseColor("#ff2222"));
                        newPassword2EditText.setError("新しいパスワードを一致させてください");
                        newPasswordEditText.setError("新しいパスワードを一致させてください");
                        passwordComplete = false;
                    }else{
                        newPasswordTextView.setText("");
                        newPassword2TextView.setText("");
                        newPasswordEditText.setError(null);
                        newPassword2EditText.setError(null);
                        passwordComplete = true;
                    }

                }else{
                    newPassword2TextView.setText("半角英文字で入力してください。（特殊記号不可）");
                    newPassword2EditText.setError("半角英文字で入力してください。（特殊記号不可）");
                    passwordComplete = false;
                }

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
                case R.id.modifyPasswordBtn:
                    modifyPasswordCheck();
                    break;
            }
        }
    };
    private void modifyPasswordCheck() {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId);
        Log.d("LogD", userId);
        TextInputEditText originalPasswordEditText = ((TextInputEditText) findViewById(R.id.originalPasswordEditText));
        String originalPassword = originalPasswordEditText.getText().toString();
        params.put("originalPassword", originalPassword);
        TextInputEditText newPassword2EditText = (TextInputEditText)findViewById(R.id.newPassword2EditText);
        String newPassword = newPassword2EditText.getText().toString();
        params.put("newPassword", newPassword);
        Log.d("LogD", originalPassword);
        TextView originalPWCheckTextView = (TextView) findViewById(R.id.originalPWCheckTextView);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!originalPasswordEditText.equals("") && passwordComplete == true){
            memberItf.checkChangePasswordApp(params).enqueue(new Callback<HashMap<String, String>>() {
                @Override
                public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                    HashMap<String, String> message = response.body();
                    if (response.isSuccessful()) {
                        Log.d("guide", message.toString());
                        if (message.get("code").equals("false")) {
                            originalPWCheckTextView.setText(message.get("guide"));
                            originalPWCheckTextView.setTextColor(Color.parseColor("#ff2222"));
                            originalPasswordEditText.setError(message.get("guide"));
                            originalPasswordEditText.requestFocus();
                            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                            //키보드 숨김
//                                InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//                                immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);


                        }else if(message.get("code").equals("true")){
                            startToast(message.get("guide"));
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }else if(message.get("code").equals("error")){
                            startToast(message.get("guide"));
                        }

                    }

                }

                @Override
                public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

                }
            });
    }
    }

    private void modifyPassword() {

    }

    public static boolean isHalfWord(String strTarget){
        byte[] byteArray = null;
        byteArray = strTarget.getBytes();
        for(int i = 0; i < byteArray.length; i++){
            if((byteArray[i] >= (byte)0x81 && byteArray[i] <= (byte)0x9f) ||
                    (byteArray[i] >= (byte)0xe0 && byteArray[i] <= (byte)0xef)) {
                if((byteArray[i+1] >= (byte)0x40 && byteArray[i+1] <= (byte)0x7e) ||
                        (byteArray[i+1] >= (byte)0x80 && byteArray[i+1] <= (byte)0xfc)) {
                    return false;
                }
            }
            if (String.valueOf(strTarget.charAt(i)).matches("[^a-zA-Z0-9\\s]")) {
                System.out.println(strTarget.charAt(i)+" : "+"특수문자");
                return false;
            }
        }
        return true;
    }

    private void startToast(String msg){
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
    }
}