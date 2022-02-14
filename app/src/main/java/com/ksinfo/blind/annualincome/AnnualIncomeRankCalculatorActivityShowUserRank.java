package com.ksinfo.blind.annualincome;

import android.content.Intent;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.R;
import com.ksinfo.blind.annualincome.api.CompanyAnnualIncomeApi;
import com.ksinfo.blind.annualincome.api.CompanyBusinessTypeApi;
import com.ksinfo.blind.annualincome.api.CompanyJobGroupApi;
import com.ksinfo.blind.annualincome.vo.CompanyAnnualIncomeForAndroidVO;
import com.ksinfo.blind.annualincome.vo.CompanyBusinessTypeVO;
import com.ksinfo.blind.annualincome.vo.CompanyJobGroupVO;
import com.ksinfo.blind.mypage.Mypage;
import com.ksinfo.blind.util.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.TextView;
import android.widget.ImageView;

public class AnnualIncomeRankCalculatorActivityShowUserRank extends AppCompatActivity {

    @Override public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annual_income_rank_calculator_show_user_rank);


        LinearLayout layoutSizeCheck = (LinearLayout) findViewById(R.id.layoutSizeCheck);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        System.out.println("device.width = " + size.x);
        System.out.println("device.height = " + size.y);
        int layoutWidth = size.x;
        int layoutHeight = size.y;



        TextView annualValueMin1 = (TextView) findViewById(R.id.textView_annual_value_min1);
        TextView annualValueMin2 = (TextView) findViewById(R.id.textView_annual_value_min2);
        TextView annualValueMin3 = (TextView) findViewById(R.id.textView_annual_value_min3);

        TextView annualValueMiddle1 = (TextView) findViewById(R.id.textView_annual_value_middle1);
        TextView annualValueMiddle2 = (TextView) findViewById(R.id.textView_annual_value_middle2);
        TextView annualValueMiddle3 = (TextView) findViewById(R.id.textView_annual_value_middle3);

        TextView annualValueMax1 = (TextView) findViewById(R.id.textView_annual_value_max1);
        TextView annualValueMax2 = (TextView) findViewById(R.id.textView_annual_value_max2);
        TextView annualValueMax3 = (TextView) findViewById(R.id.textView_annual_value_max3);

        TextView annualComparisonResultNumber  = (TextView) findViewById(R.id.textView_annual_comparisonResultNumber);
        TextView annualComparisonResultExplainText  = (TextView) findViewById(R.id.textView_annual_comparisonResultExplainText);
        TextView countOfParticipant  = (TextView) findViewById(R.id.textView_countOfParticipant);
        TextView annualRankPercent = (TextView) findViewById(R.id.textView_annual_rank_percent);
        ImageView annualRankPercentTail = (ImageView) findViewById(R.id.imageView_annual_rank_percent_tail);
        ImageView annualRankPointer = (ImageView) findViewById(R.id.annual_rank_pointer);
        //[메모] 웹서버에게 업계(業界(businessType))을 수신받고 Spinner에 배치하는 Api.
        CompanyBusinessTypeApi companyBusinessTypeApi = RetrofitFactory.createJsonRetrofit().create(CompanyBusinessTypeApi.class);
        companyBusinessTypeApi.getBusinessTypeNameList().enqueue(new Callback<List<CompanyBusinessTypeVO>>() {
            @Override
            public void onResponse(Call<List<CompanyBusinessTypeVO>> call, Response<List<CompanyBusinessTypeVO>> response) {

                if (response.isSuccessful()) {
                    List<CompanyBusinessTypeVO> getBusinessTypeNameList = response.body();
                    Log.d("getBusinessTypeNameList", getBusinessTypeNameList.toString());

                    final String[] listOfBusinessTypeName = new String[getBusinessTypeNameList.size()];
                    int arrCount = 0;

                    for (CompanyBusinessTypeVO vo : getBusinessTypeNameList) {
                        listOfBusinessTypeName[arrCount] = vo.getBusinessTypeName();
                        arrCount++;
                    }

                    Spinner spinnerBusinessTypeName;
                    spinnerBusinessTypeName = (Spinner) findViewById(R.id.spinner_businessTypeName_showRank);  //inputData：ユーザの勤務期間（ラヂオボソンと同じ）。
                    ArrayAdapter adapter2 = new ArrayAdapter(AnnualIncomeRankCalculatorActivityShowUserRank.this,
                                                            android.R.layout.simple_spinner_item, listOfBusinessTypeName);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerBusinessTypeName.setAdapter(adapter2);

                    spinnerBusinessTypeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int tempPositionSelectJob = position+1;
                            /*
                            if( tempPositionSelectJob < 10 ){ selectJob = '0'+ String.valueOf(tempPositionSelectJob); }
                            else{ selectJob = String.valueOf(tempPositionSelectJob); }
                            System.out.println("selectJob : "+ selectJob);
                             */
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
            public void onFailure(Call<List<CompanyBusinessTypeVO>> call, Throwable t) {

            }
        });

        //[메모] 웹서버에게 직군(職群/companyJobGroupApi)을 수신받고 Spinner에 배치하는 Api.
        CompanyJobGroupApi companyJobGroupApi = RetrofitFactory.createJsonRetrofit().create(CompanyJobGroupApi.class);
        companyJobGroupApi.getJobGroupListAll().enqueue(new Callback<List<CompanyJobGroupVO>>(){
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
                    spinnerJob = (Spinner) findViewById(R.id.spinner_jobGroup_showRank);  //inputData：ユーザの勤務期間（ラヂオボソンと同じ）。
                    ArrayAdapter adapter1 = new ArrayAdapter(AnnualIncomeRankCalculatorActivityShowUserRank.this, android.R.layout.simple_spinner_item, listOfJob);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerJob.setAdapter(adapter1);

                    spinnerJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int tempPositionSelectJob = position+1;
                            /*
                            if( tempPositionSelectJob < 10 ){ selectJob = '0'+ String.valueOf(tempPositionSelectJob); }
                            else{ selectJob = String.valueOf(tempPositionSelectJob); }
                            System.out.println("selectJob : "+ selectJob);
                             */
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

        //[메모] 연봉정보 텍스트출력 & 그래프의 출력위치를 설정.
        CompanyAnnualIncomeApi companyAnnualIncomeApi  = RetrofitFactory.createJsonRetrofit().create(CompanyAnnualIncomeApi.class);
        Long testUserId = 1L; //메모 userId값이 1인 유저가 로그인하여 연봉계산기를 들어간것으로 전제.
        companyAnnualIncomeApi.getAnnualIncomeFristPage(testUserId).enqueue(new Callback<CompanyAnnualIncomeForAndroidVO>() {
            @Override
            public void onResponse(Call<CompanyAnnualIncomeForAndroidVO> call, Response<CompanyAnnualIncomeForAndroidVO> response) {
                if(response.isSuccessful()){
                    System.out.println("success companyAnnualIncomeApi-getAnnualIncomeFristPage ");
                    CompanyAnnualIncomeForAndroidVO getAnnualIncomeFristPage = response.body();
                    Log.d("getAnnualIncomeFristPag", getAnnualIncomeFristPage.toString());


                    annualValueMin1.setText( Integer.toString(getAnnualIncomeFristPage.getMinAnnualIncome() ) );
                    annualValueMin2.setText( Integer.toString(getAnnualIncomeFristPage.getMinAnnualIncome() ) );
                    annualValueMin3.setText( Integer.toString(getAnnualIncomeFristPage.getMinAnnualIncome() ) );

                    annualValueMiddle1.setText( Integer.toString(getAnnualIncomeFristPage.getAvgAnnualIncome() ) );
                    annualValueMiddle2.setText( Integer.toString(getAnnualIncomeFristPage.getAvgAnnualIncome() ) );
                    annualValueMiddle3.setText( Integer.toString(getAnnualIncomeFristPage.getAvgAnnualIncome() ) );

                    annualValueMax1.setText( Integer.toString(getAnnualIncomeFristPage.getMaxAnnualIncome() ) );
                    annualValueMax2.setText( Integer.toString(getAnnualIncomeFristPage.getMaxAnnualIncome() ) );
                    annualValueMax3.setText( Integer.toString(getAnnualIncomeFristPage.getMaxAnnualIncome() ) );

                    //[메모] 그래프의 출력위치를 설정.
                    annualComparisonResultNumber.setText( Integer.toString( Math.abs(getAnnualIncomeFristPage.getUserAnnualIncome()-getAnnualIncomeFristPage.getAvgAnnualIncome())) );
                    if( (getAnnualIncomeFristPage.getUserAnnualIncome()-getAnnualIncomeFristPage.getAvgAnnualIncome())>0 ){
                        //annualComparisonResultExplainText.setTextColor( Integer.parseInt("#0000ff") );
                        annualComparisonResultExplainText.setText("高いです。");
                    }
                    else{
                        //annualComparisonResultExplainText.setTextColor( Integer.parseInt("#ff0000") );
                        annualComparisonResultExplainText.setText("低いです。");
                    }

                    countOfParticipant.setText( Integer.toString(getAnnualIncomeFristPage.getCountOfParticipant()) );

                    annualRankPercent.setText( Float.toString(getAnnualIncomeFristPage.getUserRank() ) );
                    int xPositionByRank = (int) (layoutWidth*getAnnualIncomeFristPage.getUserRank()*0.01 );
                    System.out.println("xPositionByRank : "+xPositionByRank);

                    annualRankPercent.setX(xPositionByRank);
                    annualRankPercentTail.setX(xPositionByRank);
                    annualRankPointer.setX(xPositionByRank);
                }
            }

            @Override
            public void onFailure(Call<CompanyAnnualIncomeForAndroidVO> call, Throwable t) {

            }
        });


        //[메모] 근무년수 spinner 출력설정.
        //메모 근무기간(근무기간은 굳이 DB에서 수신할 필요가 없다고 판단하여 진행
        // 선택된 position값이 0:1년미만/ 1:1년차/ 5:5년차/ 31:30년이상/ 32:모든 연차(全年次) 식으로 처리할 예정.
        final String[] ListOfWorkPeriod = {"1年未満", "1年", "2年", "3年", "4年", "5年", "6年", "7年", "8年", "9年", "10年", "11年", "12年",
                "13年", "14年", "15年", "16年", "17年", "18年", "19年", "20年", "21年", "22年", "23年", "24年",
                "25年", "26年", "27年", "28年", "29年", "30年", "30年以上","全年次"};
        Spinner spinnerWorkPeriod;
        spinnerWorkPeriod = (Spinner) findViewById(R.id.spinner_work_period_showRank);
        ArrayAdapter adapter3 = new ArrayAdapter(AnnualIncomeRankCalculatorActivityShowUserRank.this,
                                                   android.R.layout.simple_spinner_item, ListOfWorkPeriod);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWorkPeriod.setAdapter(adapter3);

        spinnerWorkPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("selectWorkPeriod : "+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //[메모] 마이페이지(mypage.xml)로 돌아가는 버튼.
        Button move_mypageMain = (Button)findViewById(R.id.btn_move_mypage);
        move_mypageMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                startActivity(intent);
            }
        });

        //[메모] 연봉정보입력하는 페이지(annual_income_rank_calculator.xml)로 이동하는 버튼.
        Button move_annualIncomeCalculator = (Button)findViewById(R.id.move_annual_incomeCalculator_Btn3);
        move_annualIncomeCalculator.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnnualIncomeRankCalculatorActivity.class);
                startActivity(intent);
            }
        });

        //[메모] 다른사람에게 홍보하는 버튼.
        //[메모] 이 기능은 아직 구현 미정입니다.
    }
}
