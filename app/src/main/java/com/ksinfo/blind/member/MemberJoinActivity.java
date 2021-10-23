package com.ksinfo.blind.member;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class MemberJoinActivity extends AppCompatActivity {
    Disposable backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_member);


        Button accessByBrowser = (Button)findViewById(R.id.accessByBrowser);

        accessByBrowser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.0.6:8282//blind"));
                startActivity(myIntent);
            }
        });


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
                    params.put("nickName", nick.getText().toString());
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

        //onPreExecute(task 시작 전 실행될 코드 여기에 작성)
        backgroundTask = Observable.fromCallable(() -> {
            //doInBackground(task에서 실행할 코드 여기에 작성)

            return HttpClientAccessor.accessByPost("registMemberApp", params);

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HashMap<String, String>>() {
            @Override
            public void accept(HashMap<String, String> map) {
                //onPostExecute(task 끝난 후 실행될 코드 여기에 작성)
                setContentView(R.layout.join_member);
                TextView textView = (TextView)findViewById(R.id.messageArea);
                textView.setText(map.get("message"));
                backgroundTask.dispose();
            }
        });
    }
}
