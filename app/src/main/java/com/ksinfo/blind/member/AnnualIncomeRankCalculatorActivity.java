package com.ksinfo.blind.member;

import android.content.Intent;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.ksinfo.blind.MainActivity;
import com.ksinfo.blind.R;
import com.ksinfo.blind.util.HttpClientAccessor;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AnnualIncomeRankCalculatorActivity extends AppCompatActivity {
    Disposable backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView textView;
        EditText editText;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.annual_income_rank_calculator);

        //annual_income_rank_calculator.xml画面を構成する要素とコネクト。
        textView = (TextView) findViewById(R.id.title);      //テキスト’契約年俸’
        textView = (TextView) findViewById(R.id.textView1);  //テキスト’年棒等数計算機’
        textView = (TextView) findViewById(R.id.textView2);  //テキスト’賞与金’
        textView = (TextView) findViewById(R.id.textView3);  //テキスト’職群’
        textView = (TextView) findViewById(R.id.textView4);  //テキスト’総キャリア’
        textView = (TextView) findViewById(R.id.textView5);  //テキスト’雇用タイプ’
        //textView = (TextView)findViewById(R.id.textView6);  //案内事項を説明するテキスト。
        //textView = (TextView)findViewById(R.id.textView7);  //案内事項を説明するテキスト。
        //textView = (TextView)findViewById(R.id.textView8);  //案内事項を説明するテキスト。
        textView = (TextView) findViewById(R.id.textView9);  //テキスト’円’。
        textView = (TextView) findViewById(R.id.textView10); //テキスト’円’。

        editText = (EditText) findViewById(R.id.editTextAnnualIncome);//inputData：年俸（給料）
        editText = (EditText) findViewById(R.id.editTextbonus);       //inputData：賞与金

        TextView button_clear_input;
        button_clear_input = findViewById(R.id.buttonAllInputClear);


        Spinner spinnerJob, spinnerWorkPeriod, spinnerEmployeeType;
        ArrayAdapter adapter;

        final String[] listOfJob = {"jobGroup1", "jobGroup2", "jobGroup3", "jobGroup4", "jobGroup5", "jobGroup6", "jobGroup7",
                "jobGroup8", "jobGroup9", "jobGroup10", "jobGroup11", "jobGroup12", "jobGroup13", "jobGroup14",
                "jobGroup15", "jobGroup16", "jobGroup17", "jobGroup18", "jobGroup19", "jobGroup20"};
        final String[] ListOfWorkPeriod = {"1年未満", "1年", "2年", "3年", "4年", "5年", "6年", "7年", "8年", "9年", "10年", "11年", "12年",
                "13年", "14年", "15年", "16年", "17年", "18年", "19年", "20年", "21年", "22年", "23年", "24年",
                "25年", "26年", "27年", "28年", "29年", "30年", "30年以上"};
        final String[] ListOfEmploymentType = {"インターン", "年雇い", "正規職"};


        spinnerJob = findViewById(R.id.spinnerOfJobList);     //inputData：ユーザの職群（ラヂオボソンと同じ）。
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfJob);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJob.setAdapter(adapter1);

        spinnerWorkPeriod = (Spinner) findViewById(R.id.spinnerOfWorkPeriod);  //inputData：ユーザの勤務期間（ラヂオボソンと同じ）。
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ListOfWorkPeriod);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWorkPeriod.setAdapter(adapter2);

        spinnerEmployeeType = (Spinner) findViewById(R.id.spinnerOfEmploymentType);    //inputData：ユーザの雇用タイプ（ラヂオボソンと同じ）。
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ListOfEmploymentType);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployeeType.setAdapter(adapter3);


        button_clear_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "リセットを完了しました。", Toast.LENGTH_SHORT).show();
            }
        });

        Button move_calculateRank = (Button) findViewById(R.id.buttonCalculateRank);
        move_calculateRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AnnualIncomeRankCalculatorActivity.this);
                builder.setTitle("年俸のランキング計算が始まります。");
                builder.setMessage("ランキングの精度をため、入力した情報は100日間修正ができません。入力した情報でランキング計算を初めてもいいですか。");
                builder.setPositiveButton("はい。",null);
                builder.setNegativeButton("いいえ。",null);
                builder.create().show();
            }
        });

        spinnerJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected job.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerWorkPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected careerYears.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerEmployeeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected employeeType.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
}
