package com.ksinfo.tomodomo.controller.annualincome;

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
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.model.itf.AnnualDataInterface;
import com.ksinfo.tomodomo.model.itf.JobGroupInterface;
import com.ksinfo.tomodomo.model.itf.WorkTypeInterface;
import com.ksinfo.tomodomo.model.vo.annualincome.CompanyJobGroupVO;
import com.ksinfo.tomodomo.model.vo.annualincome.CompanyWorkTypeVO;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalculatorActivity extends AppCompatActivity {
    @Inject JobGroupInterface jobGroupInterface;

    //메모 유저가 editText, spinner을 통하여 입력한 값들을 임시저장하다가 서버로 전송시 값을 제공한다.
    Integer annualIncome = 0;
    String selectJob;
    Integer selectWorkPeriod = 0;
    Integer selectWorkType = 0;
    Long testUserId = Long.valueOf(300); //메모 로그인한 유저의 id값을 300으로 가정하여 설정.


    final String[] ListOfWorkPeriod = {
        "1年未満", "1年", "2年", "3年", "4年", "5年", "6年", "7年", "8年", "9年", "10年", "11年", "12年",
        "13年", "14年", "15年", "16年", "17年", "18年", "19年", "20年", "21年", "22年", "23年", "24年",
        "25年", "26年", "27年", "28年", "29年", "30年", "30年以上"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_calculator);

        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);

        EditText inputAnnualIncome;
        inputAnnualIncome = findViewById(R.id.editTextAnnualIncome);


        //메모 입력한 값들을 초기화 시키는 버튼.
        TextView buttonClearInput;
        buttonClearInput = findViewById(R.id.button_input_clear);
        buttonClearInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //메모 클리어처리할 xml 대상물들을 입력할 것.
                inputAnnualIncome.setText(null);
                Toast.makeText(getApplicationContext(), "リセットを完了しました。", Toast.LENGTH_SHORT).show();
            }
        });


        //[메모] 웹서버에게 직군(職群/companyJobGroupApi)을 수신받고 Spinner에 배치하는 Api.
        jobGroupInterface = RetrofitFactory.createJsonRetrofit().create(JobGroupInterface.class);
        jobGroupInterface.getJobGroupListAll().enqueue(new Callback<List<CompanyJobGroupVO>>() {
            @Override
            public void onResponse(@NonNull Call<List<CompanyJobGroupVO>> call, Response<List<CompanyJobGroupVO>> response) {
                if (response.isSuccessful()) {
                    List<CompanyJobGroupVO> getJobGroupListAll = response.body();
                    Log.d("JobGroupListAll", getJobGroupListAll.toString());


                    final String[] listOfJob = new String[getJobGroupListAll.size()];
                    int arrCount = 0;

                    for (CompanyJobGroupVO vo : getJobGroupListAll) {
                        listOfJob[arrCount] = vo.getJobGroupName();
                        arrCount++;
                    }

                    Spinner spinnerJob;
                    spinnerJob = (Spinner) findViewById(R.id.spinnerOfJobList);  //inputData：ユーザの勤務期間（ラヂオボソンと同じ）。
                    ArrayAdapter adapter1 = new ArrayAdapter(CalculatorActivity.this, android.R.layout.simple_spinner_item, listOfJob);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerJob.setAdapter(adapter1);

                    spinnerJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int tempPositionSelectJob = position + 1;
                            if (tempPositionSelectJob < 10) {
                                selectJob = '0' + String.valueOf(tempPositionSelectJob);
                            } else {
                                selectJob = String.valueOf(tempPositionSelectJob);
                            }

                            System.out.println("selectJob : " + selectJob);
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
            public void onFailure(Call<List<CompanyJobGroupVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });


        //메모 근무기간(근무기간은 굳이 DB에서 수신할 필요가 없다고 판단하여 진행
        // 선택된 position값이 0 : 1년 미만 / 1 : 1년차 / 5 : 5년차 / 31 : 30년 이상 식으로 처리할 예정.
        Spinner spinnerWorkPeriod;

        spinnerWorkPeriod = (Spinner) findViewById(R.id.spinnerOfWorkPeriod);  //inputData：ユーザの勤務期間（ラヂオボソンと同じ）。
        ArrayAdapter adapter2 = new ArrayAdapter(CalculatorActivity.this,
                android.R.layout.simple_spinner_item, ListOfWorkPeriod);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWorkPeriod.setAdapter(adapter2);

        spinnerWorkPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectWorkPeriod = position;
                System.out.println("selectWorkPeriod : " + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //[메모] 고용유형(雇用タイプ/companyWorktypeApi)을 수신받고 Spinner에 배치하는 Api.
        WorkTypeInterface workTypeInterface = RetrofitFactory.createJsonRetrofit().create(WorkTypeInterface.class);
        workTypeInterface.getWorkTypeAll().enqueue(new Callback<List<CompanyWorkTypeVO>>() {
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
                Spinner spinnerEmployeeType; // spinnerEmployeeType : 雇用タイプ
                spinnerEmployeeType = (Spinner) findViewById(R.id.spinnerOfEmploymentType);

                ArrayAdapter adapter3 = new ArrayAdapter(CalculatorActivity.this, android.R.layout.simple_spinner_item, listOfEmploymentType);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerEmployeeType.setAdapter(adapter3);

                spinnerEmployeeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(getApplicationContext(), "Selected employeeType.", Toast.LENGTH_SHORT).show();
                        selectWorkType = position;
                        System.out.println("selectWorkType : " + position);
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


        //「ランキング計算」ボタンを押すと、
        // (1) サーバーに給料データーを送信。
        // (2)　AnnualIncomeRankCalculatorActivityShowUserRank.xmlに移動する。
        AnnualDataInterface annualDataInterface = RetrofitFactory.createJsonRetrofit().create(AnnualDataInterface.class);
        Button move_buttonCalculateRank = (Button) findViewById(R.id.buttonCalculateRank);
        move_buttonCalculateRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    annualIncome = Integer.parseInt(inputAnnualIncome.getText().toString());

                    annualDataInterface.saveAnnualData(annualIncome, selectJob, selectWorkPeriod, selectWorkType, testUserId).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            System.out.println("server send start");
                            if (response.isSuccessful()) {
                                System.out.println("server send success");
                                System.out.println("server send annualIncome : " + annualIncome);
                                System.out.println("server send selectJob : " + selectJob);
                                System.out.println("server send selectWorkPeriod : " + selectWorkPeriod);
                                System.out.println("server send selectWorkType : " + selectWorkType);
                                System.out.println("server send userId : " + testUserId);
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            System.out.println("server send fail");
                        }
                    });

                    /*
                    String testSendString = "Hello!";
                    jobGroupInterface.requestSample999(testSendString).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            System.out.println("server send start");
                            if (response.isSuccessful()) {
                                System.out.println("server send success");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            System.out.println("server send fail");
                        }
                    });
                    */

                    Intent intent = new Intent(getApplicationContext(), ShowRankActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}