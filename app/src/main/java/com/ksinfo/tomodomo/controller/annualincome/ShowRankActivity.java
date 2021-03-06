package com.ksinfo.tomodomo.controller.annualincome;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.controller.mypage.MypageActivity;
import com.ksinfo.tomodomo.model.itf.AnnualIncomeInterface;
import com.ksinfo.tomodomo.model.itf.CompanyBusinessTypeInterface;
import com.ksinfo.tomodomo.model.itf.JobGroupInterface;
import com.ksinfo.tomodomo.model.vo.annualincome.AnnualIncomeRankVO;
import com.ksinfo.tomodomo.model.vo.annualincome.CompanyBusinessTypeVO;
import com.ksinfo.tomodomo.model.vo.annualincome.CompanyJobGroupVO;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowRankActivity extends AppCompatActivity {
    @Inject CompanyBusinessTypeInterface companyBusinessTypeInterface;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_rank);

        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);

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

        TextView annualComparisonResultNumber = (TextView) findViewById(R.id.textView_annual_comparisonResultNumber);
        TextView annualComparisonResultExplainText = (TextView) findViewById(R.id.textView_annual_comparisonResultExplainText);
        TextView countOfParticipant = (TextView) findViewById(R.id.textView_countOfParticipant);
        TextView annualRankPercent = (TextView) findViewById(R.id.textView_annual_rank_percent);
        ImageView annualRankPercentTail = (ImageView) findViewById(R.id.imageView_annual_rank_percent_tail);
        ImageView annualRankPointer = (ImageView) findViewById(R.id.annual_rank_pointer);

        //[??????] ??????????????? ??????(??????(businessType))??? ???????????? Spinner??? ???????????? Api.
        companyBusinessTypeInterface.getBusinessTypeNameList().enqueue(new Callback<List<CompanyBusinessTypeVO>>() {
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
                    spinnerBusinessTypeName = (Spinner) findViewById(R.id.spinner_businessTypeName_showRank);  //inputData???????????????????????????????????????????????????????????????
                    ArrayAdapter adapter2 = new ArrayAdapter(ShowRankActivity.this,
                            android.R.layout.simple_spinner_item, listOfBusinessTypeName);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerBusinessTypeName.setAdapter(adapter2);

                    spinnerBusinessTypeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int tempPositionSelectJob = position + 1;
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

        //[??????] ??????????????? ??????(??????/companyJobGroupApi)??? ???????????? Spinner??? ???????????? Api.
        JobGroupInterface companyJobGroupApi = RetrofitFactory.createJsonRetrofit().create(JobGroupInterface.class);
        companyJobGroupApi.getJobGroupListAll().enqueue(new Callback<List<CompanyJobGroupVO>>() {
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
                    spinnerJob = (Spinner) findViewById(R.id.spinner_jobGroup_showRank);  //inputData???????????????????????????????????????????????????????????????
                    ArrayAdapter adapter1 = new ArrayAdapter(ShowRankActivity.this, android.R.layout.simple_spinner_item, listOfJob);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerJob.setAdapter(adapter1);

                    spinnerJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int tempPositionSelectJob = position + 1;
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
            public void onFailure(Call<List<CompanyJobGroupVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        //[??????] ???????????? ??????????????? & ???????????? ??????????????? ??????.
        AnnualIncomeInterface companyAnnualIncomeApi = RetrofitFactory.createJsonRetrofit().create(AnnualIncomeInterface.class);
        Long testUserId = 1L; //?????? userId?????? 1??? ????????? ??????????????? ?????????????????? ?????????????????? ??????.
        companyAnnualIncomeApi.getAnnualIncomeFristPage(testUserId).enqueue(new Callback<AnnualIncomeRankVO>() {
            @Override
            public void onResponse(Call<AnnualIncomeRankVO> call, Response<AnnualIncomeRankVO> response) {
                if (response.isSuccessful()) {
                    System.out.println("success companyAnnualIncomeApi-getAnnualIncomeFristPage ");
                    AnnualIncomeRankVO getAnnualIncomeFristPage = response.body();
                    Log.d("getAnnualIncomeFristPag", getAnnualIncomeFristPage.toString());


                    annualValueMin1.setText(Integer.toString(getAnnualIncomeFristPage.getMinAnnualIncome()));
                    annualValueMin2.setText(Integer.toString(getAnnualIncomeFristPage.getMinAnnualIncome()));
                    annualValueMin3.setText(Integer.toString(getAnnualIncomeFristPage.getMinAnnualIncome()));

                    annualValueMiddle1.setText(Integer.toString(getAnnualIncomeFristPage.getAvgAnnualIncome()));
                    annualValueMiddle2.setText(Integer.toString(getAnnualIncomeFristPage.getAvgAnnualIncome()));
                    annualValueMiddle3.setText(Integer.toString(getAnnualIncomeFristPage.getAvgAnnualIncome()));

                    annualValueMax1.setText(Integer.toString(getAnnualIncomeFristPage.getMaxAnnualIncome()));
                    annualValueMax2.setText(Integer.toString(getAnnualIncomeFristPage.getMaxAnnualIncome()));
                    annualValueMax3.setText(Integer.toString(getAnnualIncomeFristPage.getMaxAnnualIncome()));

                    //[??????] ???????????? ??????????????? ??????.
                    annualComparisonResultNumber.setText(Integer.toString(Math.abs(getAnnualIncomeFristPage.getUserAnnualIncome() - getAnnualIncomeFristPage.getAvgAnnualIncome())));
                    if ((getAnnualIncomeFristPage.getUserAnnualIncome() - getAnnualIncomeFristPage.getAvgAnnualIncome()) > 0) {
                        //annualComparisonResultExplainText.setTextColor( Integer.parseInt("#0000ff") );
                        annualComparisonResultExplainText.setText("???????????????");
                    } else {
                        //annualComparisonResultExplainText.setTextColor( Integer.parseInt("#ff0000") );
                        annualComparisonResultExplainText.setText("???????????????");
                    }

                    countOfParticipant.setText(Integer.toString(getAnnualIncomeFristPage.getCountOfParticipant()));

                    annualRankPercent.setText(Float.toString(getAnnualIncomeFristPage.getUserRank()));
                    int xPositionByRank = (int) (layoutWidth * getAnnualIncomeFristPage.getUserRank() * 0.01);
                    System.out.println("xPositionByRank : " + xPositionByRank);

                    annualRankPercent.setX(xPositionByRank);
                    annualRankPercentTail.setX(xPositionByRank);
                    annualRankPointer.setX(xPositionByRank);
                }
            }

            @Override
            public void onFailure(Call<AnnualIncomeRankVO> call, Throwable t) {

            }
        });


        //[??????] ???????????? spinner ????????????.
        //?????? ????????????(??????????????? ?????? DB?????? ????????? ????????? ????????? ???????????? ??????
        // ????????? position?????? 0:1?????????/ 1:1??????/ 5:5??????/ 31:30?????????/ 32:?????? ??????(?????????) ????????? ????????? ??????.

        /*
        //??????????????????Spinner??????????????????????????????
        final String[] ListOfWorkPeriod = {"1?????????", "1???", "2???", "3???", "4???", "5???", "6???", "7???", "8???", "9???", "10???", "11???", "12???",
                "13???", "14???", "15???", "16???", "17???", "18???", "19???", "20???", "21???", "22???", "23???", "24???",
                "25???", "26???", "27???", "28???", "29???", "30???", "30?????????","?????????"};
        Spinner spinnerWorkPeriod;
        spinnerWorkPeriod = (Spinner) findViewById(R.id.spinner_work_period_showRank);
        ArrayAdapter adapter3 = new ArrayAdapter(ShowAnnualIncomeRankActivity.this,
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
        */

        //[??????] ???????????????(mypage.xml)??? ???????????? ??????.
        Button move_mypageMain = (Button) findViewById(R.id.btn_move_mypage);
        move_mypageMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
            }
        });

        //[??????] ???????????????????????? ?????????(annual_income_rank_calculator.xml)??? ???????????? ??????.
        Button move_annualIncomeCalculator = (Button) findViewById(R.id.move_annual_incomeCalculator_Btn3);
        move_annualIncomeCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
                startActivity(intent);
            }
        });

        //[??????] ?????????????????? ???????????? ??????.
        //[??????] ??? ????????? ?????? ?????? ???????????????.
    }
}
