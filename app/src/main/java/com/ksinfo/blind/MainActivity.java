package com.ksinfo.blind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ksinfo.blind.member.AnnualIncomeRankCalculatorActivity;
import com.ksinfo.blind.member.LoginActivity;
import com.ksinfo.blind.member.MemberJoinActivity;
import com.ksinfo.blind.util.HttpClientAccessor;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Disposable backgroundTask; //[추정] 임시저장(temp역할) 변수추정.(Disposable는 일회용이란 의미를 가짐.)


    @Override
    //onCreate : main()함수의 역할을 한다.(38)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                     //super : 부모클래스의 onCreate함수를 호출하는데 사용하는 예약어.
        setContentView(R.layout.activity_main);                 //setContentView 함수 : 화면에 무엇을 보여줄 것인지를 설정해주는 역할(39)
                                                                //R.layout.activity_main : 사용자가 보게 될 화면 모양의 정보(39)
                                                                //                         즉, layout폴더의 activity_main.xml과 연결했다는 의미.

        //activity_main.xml(맨 처음시작시의 화면)의 구성요소들을 id값을 통해 연결.(html의 id와 같은 개념.)
        Button btn_test = (Button)findViewById(R.id.btn1);
        Button move_join = (Button)findViewById(R.id.join);
        Button move_login = (Button)findViewById(R.id.login);
        Button move_annual_income_rank_calculator = (Button)findViewById(R.id.annual_income_rank_calculator);
        textView = (TextView)findViewById(R.id.text1);


        //setOnClickListener : 소스코드에서 클릭이벤트를 처리하기 위해 사용되는 메소드.(176)
        //btn_test : id가 btn1인 버튼.(CONNECTION TEST버튼)
        btn_test.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {  //클릭
                try{
                    java.util.HashMap<String, String> params = new HashMap<>();
                    params.put("content", "abc");
                    connectionTest(params);     //  private void connectionTest(HashMap<String, String> params) { ... } 를 실행한다.

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        move_annual_income_rank_calculator.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnnualIncomeRankCalculatorActivity.class);
                startActivity(intent);
            }
        });

        move_join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemberJoinActivity.class); //Intent : 앱을 구성하는 화면을 새로 띄우거나, 화면간에 데이터를 전송할 수 있음.(53)
                                                                                               //getApplicationContext() : Context를 상속받지 않은 클래스에서 Context 객체를 전달해야 하는 경우 앱에서 참조 가능한 Context 객체를 사용할 수 있도록 하는 메소드.(122)
                                                                                               //MemberJoinActivity.class :  MemberJoinActivity.java 파일을 통째로 주어서 join_member.xml을 실행 할 수 있도록 해준다.
                startActivity(intent);                                                         //startActivity : 액티비티를 소스코드에서 띄울 경우 사용하는 메소드. 화면에 보이게 해주는 효과를 갖는다.(221)
            }
        });

        move_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //  btn_test(id가 btn1인 버튼.(CONNECTION TEST버튼))이 클릭되면 실행되는 함수.
    private void connectionTest(HashMap<String, String> params) {
        //onPreExecute(task 시작 전 실행될 코드 여기에 작성)
        backgroundTask = Observable.fromCallable(() -> { //자바5 Callable 인터페이스(동시성 API)f를 활용하여 비동기 실행 후 결과를 리턴. 람다식을 활용하여 표현됨. (참고 : https://zzandoli.tistory.com/31)
            //doInBackground(task에서 실행할 코드 여기에 작성)
            return HttpClientAccessor.accessByPost("testAndroidAccess", params); //testAndroidAccess : [추정] HttpClientAccessor.java의 accessByPost메소드에서 실행된 다음 리턴되는 data>)

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HashMap<String, String>>() {
            @Override
            public void accept(HashMap<String, String> map) {
                //onPostExecute(task 끝난 후 실행될 코드 여기에 작성)
                textView.setText(map.get("message")); //[추정] 연결안되었다는 의미로 Stub!가 리턴되는 듯?

                backgroundTask.dispose(); //현재의 frame만 종료시킵니다. (참고 : https://modesty101.tistory.com/173)
            }
        });
    }
}