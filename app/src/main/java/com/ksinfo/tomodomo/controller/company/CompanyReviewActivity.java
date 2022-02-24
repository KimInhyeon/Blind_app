package com.ksinfo.tomodomo.controller.company;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.controller.member.MemberJoinActivity;
import com.ksinfo.tomodomo.model.itf.CompanyInterface;
import com.ksinfo.tomodomo.model.itf.JobGroupInterface;
import com.ksinfo.tomodomo.model.vo.annualincome.CompanyJobGroupVO;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyReviewActivity extends AppCompatActivity {
    @Inject CompanyInterface companyInterface;
    @Inject JobGroupInterface jobGroupInterface;
    private long companyId;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//상속된 메소드,부모슈퍼먼저 실행, 첫번째로 나온다.
        setContentView(R.layout.cp_review_activity);

        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);

        TextView textView2 = findViewById(R.id.tv_help4);
        EditText simpleComment = findViewById(R.id.cp_review_et_simple_comment);
        EditText strongPoint = findViewById(R.id.cp_review_et_strongPoint);

        EditText weakPoint = findViewById(R.id.cp_review_et_weakPoint);

        EditText changeJob = findViewById(R.id.cp_review_et_changeJob);


        simpleComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView textView = findViewById(R.id.cp_review_tv_min_comment);


                String input = simpleComment.getText().toString();
                int length = input.length();
                if (length == 0) {
                    textView.setText("最小30文字");


                } else {

                    textView.setText(length + " 文字/（最小30文字）");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        strongPoint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView textView = findViewById(R.id.cp_review_tv_min_strong);


                String input = strongPoint.getText().toString();
                int length = input.length();
                if (length == 0) {
                    textView.setText("最小30文字");


                } else {

                    textView.setText(length + " 文字/（最小30文字）");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weakPoint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView textView = findViewById(R.id.cp_review_tv_min_weak);


                String input = weakPoint.getText().toString();
                int length = input.length();
                if (length == 0) {
                    textView.setText("最小30文字");


                } else {

                    textView.setText(length + " 文字/（最小30文字）");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        changeJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView textView = findViewById(R.id.cp_review_tv_min_change);


                String input = changeJob.getText().toString();
                int length = input.length();
                if (length == 0) {
                    textView.setText("最小30文字");


                } else {

                    textView.setText(length + " 文字/（最小30文字）");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Spannable span = (Spannable) textView2.getText();
        span.setSpan(new ForegroundColorSpan(Color.BLUE), 10, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        span.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                NestedScrollView scrollView = findViewById(R.id.cp_review_sv);
                scrollView.fullScroll(NestedScrollView.FOCUS_UP);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FragmentHelp fragmentHelp = new FragmentHelp();
                transaction.replace(R.id.cp_review_frag_container, fragmentHelp);
                View view = findViewById(R.id.background);
                view.setVisibility(View.VISIBLE);

                transaction.addToBackStack(null);

                //fragmentHelp = (FragmentHelp) getSupportFragmentManager().findFragmentById(R.id.iv_back);
                transaction.commit();
            }
        }, 10, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView2.setMovementMethod(LinkMovementMethod.getInstance());


        Button submit = findViewById(R.id.btn_complete);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //유효성 검사 필요
                //activity member f

              //  CompanyReviewVO companyReviewVO = new CompanyReviewVO();
                RatingBar careerPoint = findViewById(R.id.cp_review_rb_career_point);

                RatingBar workLifeBalance = findViewById(R.id.cp_review_rb_work_and_balance);

                RatingBar payPoint = findViewById(R.id.cp_review_rb_pay_point);

                RatingBar companyCulturePoint = findViewById(R.id.cp_review_rb_company_culture_point);

                RatingBar headPoint = findViewById(R.id.cp_review_rb_head_Point);


                Spinner start = findViewById(R.id.cp_review_sp_startDate);

                Spinner end = findViewById(R.id.cp_review_sp_finishDate);

                EditText workArea = findViewById(R.id.cp_review_et_jobArea);


                Map<String, Object> params = new HashMap<>();
                if(companyId==0){
                    Toast.makeText(getApplicationContext(), "企業名を入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(start.getSelectedItemPosition()==0){
                    Toast.makeText(getApplicationContext(), "startを入力してください", Toast.LENGTH_SHORT).show();

                    return;
                }
                if(end.getSelectedItemPosition()==0){
                    Toast.makeText(getApplicationContext(), "endを入力してください", Toast.LENGTH_SHORT).show();

                    return;
                }
                if(Math.round(careerPoint.getRating()) == 0 ){
                    Toast.makeText(getApplicationContext(), "careerpointを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Math.round(workLifeBalance.getRating()) == 0 ){
                    Toast.makeText(getApplicationContext(), "workLifeBalanceを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Math.round(companyCulturePoint.getRating()) == 0 ){
                    Toast.makeText(getApplicationContext(), "companyCulturePointを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Math.round(payPoint.getRating()) == 0 ){
                    Toast.makeText(getApplicationContext(), "payPointを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Math.round(headPoint.getRating()) == 0 ){
                    Toast.makeText(getApplicationContext(), "headPointを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(simpleComment.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "onecommentを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(simpleComment.getText().toString().length() >100){
                    Toast.makeText(getApplicationContext(), "onecomment100以内文字を入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strongPoint.getText().toString().length() == 0 ){
                    Toast.makeText(getApplicationContext(), "strongPointを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strongPoint.getText().toString().length() >100){
                    Toast.makeText(getApplicationContext(), "strongPoint100以内文字を入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(weakPoint.getText().toString().length() == 0 ){
                    Toast.makeText(getApplicationContext(), "weakPointを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(weakPoint.getText().toString().length() >100){
                    Toast.makeText(getApplicationContext(), "weakPoint100以内を入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(changeJob.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "changeJobを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(changeJob.getText().toString().length() >100){
                    Toast.makeText(getApplicationContext(), "changeJob100以内文字を入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                params.put("companyId", companyId);
                params.put("careerPoint", Math.round(careerPoint.getRating()));//math round 리턴 인티저
                params.put("workLifeBalancePoint", Math.round(workLifeBalance.getRating()));
                params.put("companyCulturePoint", Math.round(companyCulturePoint.getRating()));
                params.put("payPoint", Math.round(payPoint.getRating()));
                params.put("headPoint", Math.round(headPoint.getRating()));


                params.put("simpleComment", simpleComment.getText().toString());
                params.put("advantages", strongPoint.getText().toString());
                params.put("disadvantages", weakPoint.getText().toString());
                params.put("resignReason", changeJob.getText().toString());
                params.put("workStartDate", start.getSelectedItem().toString());
                params.put("workEndDate", end.getSelectedItem().toString());
                params.put("workArea", workArea.getText().toString());


                companyInterface.writeCompanyReviewApi(params).enqueue(new Callback<HashMap<String, String>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                        Log.d("response", String.valueOf(response.body()));


                        HashMap<String, String> result = response.body();
                        if (response.isSuccessful()) {
                            Log.d("result", result.toString());
                            if (result.get("code").equals("0")) {
                                Toast.makeText(getApplicationContext(), "完了しました.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MemberJoinActivity.class);
                                startActivity(intent);
                            } else {
                                Log.d("fail", "fail");
                            }
                        } else {
                            Log.d("fail", result.get("code"));
                        }


                    }

                    @Override
                    public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                        t.printStackTrace();
                    }


                });
            }
        });


//        ImageView guideBack = (ImageView) findViewById(R.id.iv_back);
//        guideBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View view = findViewById(R.id.background);
//                view.setVisibility(View.GONE);
//            }
//        });


        jobGroupInterface.getJobGroupListAll().enqueue(new Callback<List<CompanyJobGroupVO>>() {
            @Override
            public void onResponse(@NonNull Call<List<CompanyJobGroupVO>> call, Response<List<CompanyJobGroupVO>> response) {
                if (response.isSuccessful()) {
                    List<CompanyJobGroupVO> jobGroupList = response.body();

                    int listSize = jobGroupList.size() + 1;

                    String[] listOfJob = new String[listSize];
                    listOfJob[0] = "選んでください";
                    for (int i = 1; i < listSize; i++) {
                        listOfJob[i] = jobGroupList.get(i - 1).getJobGroupName();

                    }


                    Spinner spinnerJob = (Spinner) findViewById(R.id.sp_jobGroup);  //inputData：ユーザの勤務期間（ラヂオボソンと同じ）。
                    ArrayAdapter adapter1 = new ArrayAdapter(CompanyReviewActivity.this, android.R.layout.simple_spinner_item, listOfJob) {
                        @Override
                        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                            View v = null;

                            if (position == 0) {
                                TextView tv = new TextView(getContext());
                                tv.setHeight(0);
                                tv.setVisibility(View.GONE);
                                v = tv;
                            } else {

                                v = super.getDropDownView(position, null, parent);
                            }

                            parent.setVerticalScrollBarEnabled(false);
                            return v;
                        }
                    };

                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerJob.setAdapter(adapter1);


                } else {
                    Log.d("error", "error");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyJobGroupVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });


//        ActivityCompanyReviewBinding binding = ActivityCompanyReviewBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

/*        Intent intent=getIntent();
        String companyName= intent.getStringExtra("companyName");
        long companyId=intent.getLongExtra("companyId",0L);
        Log.d("companyName",companyName);
        Log.d("companyId", String.valueOf(companyId));*/


        textView = findViewById(R.id.cp_review_tv_cancel);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }


        });


        ImageView search = (ImageView) findViewById(R.id.cp_review_iv_arrow);
        Intent intent = new Intent(this, CompanySearchActivity.class);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int search = 37;
                startActivityForResult(intent, search);//37 핸들링 수정
//                setCompanyInfo();
            }
        });


//        Intent i = new Intent(this, CompanySearchActivity.class);
//        binding.ivArrow.setOnClickListener(new View.OnClickListener(
//        ) {
//            @Override
//            public void onClick(View v) {
//                // 클릭시 이동하는 화면 띄우는 로직
//                startActivity(i);
//            }
//        });


        Spinner startDate;
        startDate = (Spinner) findViewById(R.id.cp_review_sp_startDate);

//        String[] date = {"선택해주세요", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010",
//                "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998",
//                "1997", "1996", "1995", "1994", "1993", "1992", "1991"};
        final int workYearLimit = 30;
        String[] date = new String[workYearLimit];
//        int nowYear;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            nowYear = LocalDate.now().getYear();
//        } else {
//            // nowYear = Calendar.getInstance().setTime(new Date());
//            nowYear = new GregorianCalendar().get(Calendar.YEAR);
//        }
        int nowYear = new GregorianCalendar().get(Calendar.YEAR);
        date[0] = "選んでください";
        for (int i = 1; i < workYearLimit; i++) {
            date[i] = String.valueOf(nowYear);
            nowYear--;
        }

        ArrayAdapter adapter1 = new ArrayAdapter(CompanyReviewActivity.this, android.R.layout.simple_spinner_item, date) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View v = null;

                if (position == 0) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                } else {

                    v = super.getDropDownView(position, null, parent);
                }

                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };

        startDate.setAdapter(adapter1);
        startDate.setPrompt("勤務開始日");
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Spinner finishDate = (Spinner) findViewById(R.id.cp_review_sp_finishDate);
        finishDate.setEnabled(false);

        String[] endDate = new String[1];
        endDate[0] = date[0];


        ArrayAdapter adapter2 = new ArrayAdapter(CompanyReviewActivity.this, android.R.layout.simple_spinner_item, endDate) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View v = null;

                if (position == 0) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                } else {

                    v = super.getDropDownView(position, null, parent);
                }


                parent.setVerticalScrollBarEnabled(false);
                return v;
            }


        };

        finishDate.setAdapter(adapter2);
        finishDate.setPrompt("終業日");
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        startDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position > 0) {
                    finishDate.setEnabled(true);
                    String[] endDate = new String[position + 1];

                    for (int i = 0; i <= position; i++) {
                        endDate[i] = date[i];

                    }
                    ArrayAdapter adapter2 = new ArrayAdapter(CompanyReviewActivity.this, android.R.layout.simple_spinner_item, endDate) {
                        @Override
                        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                            View v = null;

                            if (position == 0) {
                                TextView tv = new TextView(getContext());
                                tv.setHeight(0);
                                tv.setVisibility(View.GONE);
                                v = tv;
                            } else {

                                v = super.getDropDownView(position, null, parent);
                            }


                            parent.setVerticalScrollBarEnabled(false);
                            return v;
                        }


                    };
                    finishDate.setAdapter(adapter2);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }

        });


    }


    private void showMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//대화 상자 만들기 위한 빌더 객체 생성
        builder.setMessage("レビュー登録を完了せずに、ページを出ますか？");

        builder.setPositiveButton("出る", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });

        builder.setNegativeButton("留まり", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final int search = 37;
        if (resultCode == RESULT_OK && requestCode == search) {
            long companyId = data.getLongExtra("companyId", 0L);
            if (companyId > 0) {
                this.companyId = companyId;//위의 변수


                TextView text = findViewById(R.id.cp_review_tv_name);
                String companyName = data.getStringExtra("companyName");
                Log.e("companyName", companyName);
                text.setText(companyName);
                //glide 라이브러리

                ImageView imageView = findViewById(R.id.cp_review_iv_company_logo);
                String logoUrl = getResources().getString(R.string.base_url) + "resources/images/company/" + companyId + ".png";
                Glide.with(this).load(logoUrl).into(imageView);

                EditText strongPoint = findViewById(R.id.cp_review_et_strongPoint);
                strongPoint.setHint(companyName + "勤務して良かった点は何ですか。");
                EditText weakPoint = findViewById(R.id.cp_review_et_weakPoint);
                weakPoint.setHint(companyName + "で勤務大変だったことは何ですか");
                EditText changeJob = findViewById(R.id.cp_review_et_changeJob);
                changeJob.setHint(companyName + "で転職を決心するようになったきっかけは");
                TextView cerfitication = findViewById(R.id.cp_review_tv_certification2);
                cerfitication.setHint(companyName + " 前の社員");
            }
        }
    }

   /* private void setCompanyInfo(){
        Intent intent = getIntent();
        ClearEditText text = findViewById(R.id.companySearch);
        String companyName = intent.getStringExtra("companyName");
         Log.e("companyName",companyName);
         text.setText(companyName);


    }*/


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        View view = findViewById(R.id.background);
        view.setVisibility(View.GONE);
    }
}