package com.ksinfo.blind.annualincome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.R;
import com.ksinfo.blind.annualincome.api.CompanyJobGroupApi;
import com.ksinfo.blind.annualincome.api.CompanyWorktypeApi;
import com.ksinfo.blind.annualincome.vo.CompanyJobGroupVO;
import com.ksinfo.blind.annualincome.vo.CompanyWorkTypeVO;
import com.ksinfo.blind.mypage.Mypage;
import com.ksinfo.blind.util.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnualIncomeRankCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annual_income_rank_calculator);

        TextView textView;
        EditText editText;


        final String[] ListOfWorkPeriod = {"1年未満", "1年", "2年", "3年", "4年", "5年", "6年", "7年", "8年", "9年", "10年", "11年", "12年",
                "13年", "14年", "15年", "16年", "17年", "18年", "19年", "20年", "21年", "22年", "23年", "24年",
                "25年", "26年", "27年", "28年", "29年", "30年", "30年以上"};

        //annual_income_rank_calculator.xml画面を構成する要素とコネクト。
        textView = (TextView) findViewById(R.id.title);      //テキスト’契約年俸’
        textView = (TextView) findViewById(R.id.textView1);  //テキスト’年棒等数計算機’
        textView = (TextView) findViewById(R.id.textView2);  //テキスト’賞与金’
        textView = (TextView) findViewById(R.id.textView3);  //テキスト’職群’
        textView = (TextView) findViewById(R.id.textView4);  //テキスト’総キャリア’
        textView = (TextView) findViewById(R.id.textView5);  //テキスト’雇用タイプ’
        textView = (TextView) findViewById(R.id.textView9);  //テキスト’円’。
        textView = (TextView) findViewById(R.id.textView10); //テキスト’円’。

        editText = (EditText) findViewById(R.id.editTextAnnualIncome);//inputData：年俸（給料）
        editText = (EditText) findViewById(R.id.editTextbonus);       //inputData：賞与金


        CompanyWorktypeApi companyWorktypeApi = RetrofitFactory.createJsonRetrofit().create(CompanyWorktypeApi.class);
        companyWorktypeApi.getWorkTypeAll().enqueue(new Callback<List<CompanyWorkTypeVO>>() {
            @Override
            public void onResponse(Call<List<CompanyWorkTypeVO>> call, Response<List<CompanyWorkTypeVO>> response) {

                List<CompanyWorkTypeVO> getWorkTypeAll = response.body();
                Log.d("WorkTypeAll", getWorkTypeAll.toString());

                final String[] listOfEmploymentType = new String[getWorkTypeAll.size()];
                int arrCount = 0;

                for (CompanyWorkTypeVO vo : getWorkTypeAll) {
                    listOfEmploymentType[arrCount] = vo.getWorkTypeName();
                    arrCount++;
                }
                Spinner spinnerEmployeeType;
                spinnerEmployeeType = (Spinner) findViewById(R.id.spinnerOfEmploymentType);    //inputData：ユーザの雇用タイプ（ラヂオボソンと同じ）。

                ArrayAdapter adapter3 = new ArrayAdapter(AnnualIncomeRankCalculatorActivity.this, android.R.layout.simple_spinner_item, listOfEmploymentType);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerEmployeeType.setAdapter(adapter3);

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

            @Override
            public void onFailure(Call<List<CompanyWorkTypeVO>> call, Throwable t) {

            }
        });

        CompanyJobGroupApi companyJobGroupApi = RetrofitFactory.createJsonRetrofit().create(CompanyJobGroupApi.class);
        companyJobGroupApi.getJobGroupListAll().enqueue(new Callback<List<CompanyJobGroupVO>>(){
            @Override
            public void onResponse(@NonNull Call<List<CompanyJobGroupVO>> call, Response<List<CompanyJobGroupVO>> response) {
                if (response.isSuccessful()) {
                    List<CompanyJobGroupVO> getJobGroupListAll = response.body();
                    Log.d("JobGroupListAll", getJobGroupListAll.toString());

                    TextView button_clear_input;
                    button_clear_input = findViewById(R.id.buttonAllInputClear);

                    Spinner spinnerJob, spinnerWorkPeriod;

                    final String[] listOfJob = new String[getJobGroupListAll.size()];
                    int arrCount = 0;

                    for (CompanyJobGroupVO vo : getJobGroupListAll) {
                        listOfJob[arrCount] = vo.getJobGroupName();
                        arrCount++;
                    }

                    spinnerJob = (Spinner) findViewById(R.id.spinnerOfJobList);  //inputData：ユーザの勤務期間（ラヂオボソンと同じ）。
                    ArrayAdapter adapter1 = new ArrayAdapter(AnnualIncomeRankCalculatorActivity.this, android.R.layout.simple_spinner_item, listOfJob);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerJob.setAdapter(adapter1);

                    spinnerWorkPeriod = (Spinner) findViewById(R.id.spinnerOfWorkPeriod);  //inputData：ユーザの勤務期間（ラヂオボソンと同じ）。
                    ArrayAdapter adapter2 = new ArrayAdapter(AnnualIncomeRankCalculatorActivity.this, android.R.layout.simple_spinner_item, ListOfWorkPeriod);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerWorkPeriod.setAdapter(adapter2);


                    button_clear_input.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "リセットを完了しました。", Toast.LENGTH_SHORT).show();
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


                } else {
                    Log.d("error", "error");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyJobGroupVO>>call, Throwable t) {
                t.printStackTrace();
            }
        });


        //「ランキング計算」ボタンを押すと、
        // (1) サーバーに給料データーを送信。
        // (2)　AnnualIncomeRankCalculatorActivityShowUserRank.xmlに移動する。
        Button move_annualIncomeRankCalculatorActivityShowUserRank = (Button)findViewById(R.id.buttonCalculateRank);
        move_annualIncomeRankCalculatorActivityShowUserRank.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    // CompanyJobGroupApi companyJobGroupApi = RetrofitFactory.createJsonRetrofit().create(CompanyJobGroupApi.class);
                    String testSendString = "Hello!";
                    //Call<Void> sendCall = companyJobGroupApi.requestSample999(testSendString);
                    companyJobGroupApi.requestSample999(testSendString).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            System.out.println("server send start");
                            if(response.isSuccessful()){
                                System.out.println("server send success");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            System.out.println("server send fail");
                        }
                    });

                    Intent intent = new Intent(getApplicationContext(), AnnualIncomeRankCalculatorActivityShowUserRank.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        /*
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
        */

    }
}