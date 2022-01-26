
package com.ksinfo.blind.company;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.R;
import com.ksinfo.blind.company.api.CompanyApi;
import com.ksinfo.blind.company.vo.CompanySearchVO;
import com.ksinfo.blind.util.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.ksinfo.blind.databinding.ActivityCompanySearchBinding;

public class CompanySearchActivity extends  AppCompatActivity{
    CompanyApi companyApi = RetrofitFactory.createJsonRetrofit().create(CompanyApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_search);
        LinearLayout companyListLayout = findViewById(R.id.companyList);

        EditText text = findViewById(R.id.companySearch);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("태그","호출됨");
                Log.e("tag",s.toString());


               // companyListLayout.removeAllViewsInLayout();


                    companySearch(s.toString(),companyListLayout);




            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
   /*     ActivityCompanySearchBinding binding = ActivityCompanySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.e("태그", "서치 액티비티 호출됨");
*/


    }




    private void companySearch(String companyName, LinearLayout companyListLayout) {
//        CompanySearchActivity companySearchActivity = this;
        companyApi.getCompanySearchResult(companyName).enqueue(new Callback<List<CompanySearchVO>>(){
            @Override
            public void onResponse(@NonNull Call<List<CompanySearchVO>> call, Response<List<CompanySearchVO>> response) {

                if (response.isSuccessful()) {
                    List<CompanySearchVO> companyList = response.body();
                    companyListLayout.removeAllViews();
                        if(!companyName.equals("")){
                            for (CompanySearchVO company : companyList) {
                                TextView companyView = new TextView(getApplicationContext());
                                companyView.setText(company.getCompanyName());
                                companyView.setOnClickListener(new View.OnClickListener(){

                                    @Override
                                    public void onClick(View view) {
                                        Log.d("dd", (String) companyView.getText());

                                       Intent intent = new Intent();

                                        intent.putExtra("companyName",company.getCompanyName());
                                        intent.putExtra("companyId",company.getCompanyId());
                                        setResult(RESULT_OK,intent);
                                        finish();
                                        try{

                                        } catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                companyListLayout.addView(companyView);



                            }
                        }








                } else {
                    Log.d("fail", "fail");
                }
            }
            @Override
            public void onFailure(Call<List<CompanySearchVO>> call, Throwable t) {
                Log.d("fail", "fail");
                t.printStackTrace();
            }
        });
    }











}


