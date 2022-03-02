package com.ksinfo.tomodomo.controller.company;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.model.itf.CompanyInterface;
import com.ksinfo.tomodomo.model.vo.company.CompanyReviewAverageVO;
import com.ksinfo.tomodomo.model.vo.company.CompanyReviewVO;
import com.ksinfo.tomodomo.model.vo.company.CompanyVoteResultDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyReviewListActivity extends AppCompatActivity {
    @Inject
    CompanyInterface companyInterface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_all_company_review_list);
        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);



        CompanyReviewAverageVO companyReviewAverageVO =getIntent().getParcelableExtra("companyAverageVO");
        List<CompanyReviewVO> companyReviewVOList = getIntent().getParcelableArrayListExtra("companyReviewVOArrayList");
        //Log.d("dfdsf", companyReviewVOList.get().getJobGroupName());
        long companyId = getIntent().getLongExtra("companyId",0);//안끄집어질때 0
        long companyName = getIntent().getLongExtra("companyName",0);
        Log.d("dflsdjflsjf",String.valueOf(companyId));
        ImageView imageView = findViewById(R.id.cp_all_company_reveiw_list_logo);
        String logoUrl = getResources().getString(R.string.base_url) + "resources/images/company/" + companyId + ".png";
        Glide.with(this).load(logoUrl).into(imageView);

        for(int i=0;i<companyReviewVOList.size();i++){
            Log.d("dd",companyReviewVOList.get(i).getSimpleComment());
        }


        TextView textView = findViewById(R.id.cp_all_company_review_list_all_Point);
        TextView textView1 = findViewById(R.id.cp_all_company_review_list_career_point);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyRecommendPush(companyId,1);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyRecommendPush(companyId,0);
            }
        });

        companyInterface.getCompanyRecommendVoteResult(companyId).enqueue(new Callback<CompanyVoteResultDto>() {
            @Override
            public void onResponse(Call<CompanyVoteResultDto> call, Response<CompanyVoteResultDto> response) {
                if (response.isSuccessful()) {
                    Log.d("klsjlkfdj","aaasss");
                    setCompanyVoteResult(response.body());









                }


            }

            @Override
            public void onFailure(Call<CompanyVoteResultDto> call, Throwable t) {
                Log.d("fdlfkjdfkldjf","kjsdlfjslfjsd");
            }
        });



    }


    private void companyRecommendPush(long companyId, int companyVoteValue){
        Map<String,Object> params = new HashMap<>();
        params.put("companyId",companyId);
        params.put("companyVoteValue",companyVoteValue);
        companyInterface.companyRecommendPush(params).enqueue(new Callback<CompanyVoteResultDto>() {
            @Override
            public void onResponse(Call<CompanyVoteResultDto> call, Response<CompanyVoteResultDto> response) {
                if(response.isSuccessful()) {

                    setCompanyVoteResult(response.body());



                }
             }

            @Override
            public void onFailure(Call<CompanyVoteResultDto> call, Throwable t) {

            }
        });
    }

    private  void setCompanyVoteResult(CompanyVoteResultDto companyVoteResultDto){
        TextView textView = findViewById(R.id.cp_all_company_review_list_all_Point);
        TextView textView1 = findViewById(R.id.cp_all_company_review_list_career_point);
        Log.d("kjljljjkjkjk", String.valueOf(companyVoteResultDto.getVoteCountOfGood()));
        textView.setText(String.format("%d%%",companyVoteResultDto.getVoteCountOfGood()));
        textView1.setText(String.format("%d%%",companyVoteResultDto.getVoteCountOfBad()));

    }
}
