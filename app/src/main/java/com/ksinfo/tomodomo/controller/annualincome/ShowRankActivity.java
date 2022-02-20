package com.ksinfo.tomodomo.controller.annualincome;

import static com.ksinfo.tomodomo.util.RetrofitFactory.createJsonRetrofit;

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
    @Inject JobGroupInterface jobGroupInterface;
    @Inject AnnualIncomeInterface annualIncomeInterface;

    Long userId = Long.valueOf(5); //메모 로그인한 유저의 id값을 300으로 가정하여 설정.
    String tempPositionSelectBusinessType; //메모 '01','02'같은 코드로 찾기위해 +1한 다음 String 변화.
    int tempPositionSelectJob = 0; //메모 '01','02'같은 코드로 찾기위해 +1한 다음 String 변화.


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

        //메모 xml 레이아웃 객체와 변수 연결(단, 스피너는 제외)
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



        //[메모] 1.유저가 일하는 회사의 업계를 수신.
        //[메모] 최초의 화면구성을 실시에 사용한다.
        companyBusinessTypeInterface.getUserBusinessTypeCode(userId).enqueue(new Callback<CompanyBusinessTypeVO>() {
            @Override
            public void onResponse(Call<CompanyBusinessTypeVO> call, Response<CompanyBusinessTypeVO> response) {
                if (response.isSuccessful()) {
                    CompanyBusinessTypeVO userBusinessTypeCode = response.body();
                    tempPositionSelectBusinessType = userBusinessTypeCode.getBusinessTypeCode();
                }
            }
            @Override
            public void onFailure(Call<CompanyBusinessTypeVO> call, Throwable t) {
            }
        });

        //[메모] 2.유저의 업종을 수신.
        //[메모] 최초의 화면구성을 실시에 사용한다.
        jobGroupInterface.getUserJobGroupCode(userId).enqueue(new Callback<CompanyJobGroupVO>() {
            @Override
            public void onResponse(Call<CompanyJobGroupVO> call, Response<CompanyJobGroupVO> response) {
                if (response.isSuccessful()) {
                    CompanyJobGroupVO userJobGroupCode = response.body();
                    tempPositionSelectJob = Integer.parseInt(userJobGroupCode.getJobGroupCode());
                }
            }

            @Override
            public void onFailure(Call<CompanyJobGroupVO> call, Throwable t) {
            }
        });
        /*
        //[메모] 3. 연봉정보 텍스트출력 & 그래프의 출력위치를 설정.(최초출력)
        annualIncomeInterface.getAnnualIncomeAndRank(String.valueOf(tempPositionSelectBusinessType),
                String.valueOf(tempPositionSelectJob), userId).enqueue(new Callback<AnnualIncomeRankVO>() {
            @Override
            public void onResponse(Call<AnnualIncomeRankVO> call, Response<AnnualIncomeRankVO> response) {
                if (response.isSuccessful()) {
                    System.out.println("success companyAnnualIncomeApi-rankInitPage ");
                    AnnualIncomeRankVO annualIncomeAndRankFirstOutput = response.body();

                    annualValueMin1.setText(Integer.toString(annualIncomeAndRankFirstOutput.getMinAnnualIncome()));
                    annualValueMin2.setText(Integer.toString(annualIncomeAndRankFirstOutput.getMinAnnualIncome()));
                    annualValueMin3.setText(Integer.toString(annualIncomeAndRankFirstOutput.getMinAnnualIncome()));

                    annualValueMiddle1.setText(Integer.toString(annualIncomeAndRankFirstOutput.getAvgAnnualIncome()));
                    annualValueMiddle2.setText(Integer.toString(annualIncomeAndRankFirstOutput.getAvgAnnualIncome()));
                    annualValueMiddle3.setText(Integer.toString(annualIncomeAndRankFirstOutput.getAvgAnnualIncome()));

                    annualValueMax1.setText(Integer.toString(annualIncomeAndRankFirstOutput.getMaxAnnualIncome()));
                    annualValueMax2.setText(Integer.toString(annualIncomeAndRankFirstOutput.getMaxAnnualIncome()));
                    annualValueMax3.setText(Integer.toString(annualIncomeAndRankFirstOutput.getMaxAnnualIncome()));

                    //[메모] 그래프의 출력위치를 설정.
                    annualComparisonResultNumber.setText(Integer.toString(Math.abs(annualIncomeAndRankFirstOutput.getUserAnnualIncome() - annualIncomeAndRankFirstOutput.getAvgAnnualIncome())));
                    if ((annualIncomeAndRankFirstOutput.getUserAnnualIncome() - annualIncomeAndRankFirstOutput.getAvgAnnualIncome()) > 0) {
                        //annualComparisonResultExplainText.setTextColor( Integer.parseInt("#0000ff") );
                        annualComparisonResultExplainText.setText("高いです。");
                    } else {
                        //annualComparisonResultExplainText.setTextColor( Integer.parseInt("#ff0000") );
                        annualComparisonResultExplainText.setText("低いです。");
                    }

                    countOfParticipant.setText(Integer.toString(annualIncomeAndRankFirstOutput.getCountOfParticipant()));

                    annualRankPercent.setText(Float.toString(annualIncomeAndRankFirstOutput.getUserRank()));
                    int xPositionByRank = (int) (layoutWidth * annualIncomeAndRankFirstOutput.getUserRank() * 0.005);
                    System.out.println("xPositionByRank : " + xPositionByRank);

                    annualRankPercent.setX(xPositionByRank);
                    annualRankPercentTail.setX(xPositionByRank);
                    annualRankPointer.setX(xPositionByRank);

                    //tempPositionSelectBusinessType = annualIncomeAndRankFirstOutput.getBusinessTypeCode();
                    tempPositionSelectJob = annualIncomeAndRankFirstOutput.getJobGroupCode();
                }
            }

            @Override
            public void onFailure(Call<AnnualIncomeRankVO> call, Throwable t) {
            }
        });
        */

        //[메모] 스피너 변수들 선언
        Spinner spinnerBusinessTypeName;
        spinnerBusinessTypeName = (Spinner) findViewById(R.id.spinner_businessTypeName_showRank);  //inputData：ユーザの勤務期間（ラヂオボソンと同じ）。
        Spinner spinnerJob;
        spinnerJob = (Spinner) findViewById(R.id.spinner_jobGroup_showRank);  //inputData：ユーザの勤務期間（ラヂオボソンと同じ）。

        //[메모] 3?4? 업계(業界, businessType)Spinner 설정.
        companyBusinessTypeInterface.getBusinessTypeListExistAIData().enqueue(new Callback<List<CompanyBusinessTypeVO>>() {
            @Override
            public void onResponse(Call<List<CompanyBusinessTypeVO>> call, Response<List<CompanyBusinessTypeVO>> response) {
                if (response.isSuccessful()) {
                    List<CompanyBusinessTypeVO> businessTypeList = response.body();
                    Log.d("getBusinessTypeNameList", businessTypeList.toString());

                    final String[] listOfBusinessTypeName = new String[businessTypeList.size()];
                    int arrCount = 0;

                    for (CompanyBusinessTypeVO vo : businessTypeList) {
                        listOfBusinessTypeName[arrCount] = vo.getBusinessTypeName();
                        arrCount++;
                    }


                    ArrayAdapter adapter2 = new ArrayAdapter(ShowRankActivity.this, android.R.layout.simple_spinner_item, listOfBusinessTypeName);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerBusinessTypeName.setAdapter(adapter2);

                    spinnerBusinessTypeName.setSelection(0, false);
                    //메모 사용자가 스피너를 통해 다른 업계를 택한 경우 데이터 재로드 및 각 변수에게 데이터 재입력.
                    spinnerBusinessTypeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tempPositionSelectBusinessType = businessTypeList.get(position).getBusinessTypeCode();
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


        //[메모] 4?5? 직군Spinner에 삽입할 직군(jobGroup) 목록 수신.
        jobGroupInterface.getJobGroupListExistAIData().enqueue(new Callback<List<CompanyJobGroupVO>>() {
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


                    ArrayAdapter adapter1 = new ArrayAdapter(ShowRankActivity.this, android.R.layout.simple_spinner_item, listOfJob);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerJob.setAdapter(adapter1);

                    spinnerJob.setSelection(0, false);
                    spinnerJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tempPositionSelectJob = position + 1;
                            annualIncomeInterface.getAnnualIncomeAndRank(
                                String.valueOf(tempPositionSelectBusinessType),
                                String.valueOf(tempPositionSelectJob),
                                userId
                            ).enqueue(new Callback<AnnualIncomeRankVO>() {
                                @Override
                                public void onResponse(Call<AnnualIncomeRankVO> call, Response<AnnualIncomeRankVO> response) {
                                    System.out.println("success Rank Refresh by Spinner-SelectBusinessType ");
                                    AnnualIncomeRankVO getAnnualIncomeFristPage = response.body();

                                    annualValueMin1.setText(Integer.toString(getAnnualIncomeFristPage.getMinAnnualIncome()));
                                    annualValueMin2.setText(Integer.toString(getAnnualIncomeFristPage.getMinAnnualIncome()));
                                    annualValueMin3.setText(Integer.toString(getAnnualIncomeFristPage.getMinAnnualIncome()));

                                    annualValueMiddle1.setText(Integer.toString(getAnnualIncomeFristPage.getAvgAnnualIncome()));
                                    annualValueMiddle2.setText(Integer.toString(getAnnualIncomeFristPage.getAvgAnnualIncome()));
                                    annualValueMiddle3.setText(Integer.toString(getAnnualIncomeFristPage.getAvgAnnualIncome()));

                                    annualValueMax1.setText(Integer.toString(getAnnualIncomeFristPage.getMaxAnnualIncome()));
                                    annualValueMax2.setText(Integer.toString(getAnnualIncomeFristPage.getMaxAnnualIncome()));
                                    annualValueMax3.setText(Integer.toString(getAnnualIncomeFristPage.getMaxAnnualIncome()));

                                    //[메모] 그래프의 출력위치를 설정.
                                    annualComparisonResultNumber.setText(Integer.toString(Math.abs(getAnnualIncomeFristPage.getUserAnnualIncome() - getAnnualIncomeFristPage.getAvgAnnualIncome())));
                                    if ((getAnnualIncomeFristPage.getUserAnnualIncome() - getAnnualIncomeFristPage.getAvgAnnualIncome()) > 0) {
                                        //annualComparisonResultExplainText.setTextColor( Integer.parseInt("#0000ff") );
                                        annualComparisonResultExplainText.setText("高いです。");
                                    } else {
                                        //annualComparisonResultExplainText.setTextColor( Integer.parseInt("#ff0000") );
                                        annualComparisonResultExplainText.setText("低いです。");
                                    }

                                    countOfParticipant.setText(Integer.toString(getAnnualIncomeFristPage.getCountOfParticipant()));

                                    annualRankPercent.setText(Float.toString(getAnnualIncomeFristPage.getUserRank()));
                                    int xPositionByRank = (int) (layoutWidth * getAnnualIncomeFristPage.getUserRank() * 0.005);
                                    System.out.println("xPositionByRank : " + xPositionByRank);

                                    annualRankPercent.setX(xPositionByRank);
                                    annualRankPercentTail.setX(xPositionByRank);
                                    annualRankPointer.setX(xPositionByRank);
                                }

                                @Override
                                public void onFailure(Call<AnnualIncomeRankVO> call, Throwable t) {
                                }
                            });
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

        //[메모] 마이페이지(mypage.xml)로 돌아가는 버튼.
        Button move_mypageMain = (Button) findViewById(R.id.btn_move_mypage);
        move_mypageMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
            }
        });

        //[메모] 연봉정보입력하는 페이지(annual_income_rank_calculator.xml)로 이동하는 버튼.
        Button move_annualIncomeCalculator = (Button) findViewById(R.id.move_annual_incomeCalculator_Btn3);
        move_annualIncomeCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
                startActivity(intent);
            }
        });

        //[메모] 근무년수 spinner 출력설정.
        //[메모] 이 기능은 구현일시가 미정입니다.
        /*
        //메모 근무기간(근무기간은 굳이 DB에서 수신할 필요가 없다고 판단하여 진행
        //메모 선택된 position값이 0:1년미만/ 1:1년차/ 5:5년차/ 31:30년이상/ 32:모든 연차(全年次) 식으로 처리할 예정.
        //働いた期間のSpinner機能は後に追加予定。
        final String[] ListOfWorkPeriod = {"1年未満", "1年", "2年", "3年", "4年", "5年", "6年", "7年", "8年", "9年", "10年", "11年", "12年",
                "13年", "14年", "15年", "16年", "17年", "18年", "19年", "20年", "21年", "22年", "23年", "24年",
                "25年", "26年", "27年", "28年", "29年", "30年", "30年以上","全年次"};
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

        //[메모] 다른사람에게 홍보하는 버튼.
        //[메모] 이 기능은 구현일시가 미정입니다.
    }

}
