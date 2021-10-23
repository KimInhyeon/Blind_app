package com.ksinfo.blind.member;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.R;
import com.ksinfo.blind.util.HttpClientAccessor;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    Disposable backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login = (Button)findViewById(R.id.loginBtn);

        //Login버튼클릭시 실시내용.
        // 로그인계정정보(ID역할메일, PW)를 Hash-Map형태로 전송준비.
        // 서버전송 및 로그인 처리는 아래의 private void login(HashMap<String, String> params) {...} 메소드에서 처리.
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    //본 try{...}문의 역할은 로그인할 정보를 HashMap변수(params)에 담는 것이다.
                    HashMap<String, String> params = new HashMap<>();

                    EditText email = (EditText) findViewById(R.id.emailEt);
                    EditText password = (EditText) findViewById(R.id.pwEt);

                    params.put("username", email.getText().toString());
                    params.put("password", password.getText().toString());

                    login(params); //private void login(HashMap<String, String> params) {...}를 통해
                } catch (Exception e){
                    e.printStackTrace(); //예외처리.
                }
            }
        });
    }

    //private void login(){...}의 역할은 다음과 같음.
    //(1)Hash-Map형태의 로그인정보(ID메일,PW)를 서버에게 전송한다.
    //(2)
    private void login(HashMap<String, String> params) {
        //onPreExecute(task 시작 전 실행될 코드 여기에 작성)
        backgroundTask = Observable.fromCallable(() -> {
            //doInBackground(task에서 실행할 코드 여기에 작성)
            return HttpClientAccessor.accessByPost("loginApp", params);

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HashMap<String, String>>() {
            @Override
            public void accept(HashMap<String, String> map) {
                //onPostExecute(task 끝난 후 실행될 코드 여기에 작성)
                if ("OK".equals(map.get("message"))) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.0.6:8282/blind/main"));
                    startActivity(intent);
                } else {
                    TextView textView = (TextView)findViewById(R.id.messageArea);
                    textView.setText(map.get("message"));
                }
                backgroundTask.dispose();
            }
        });
    }
}
