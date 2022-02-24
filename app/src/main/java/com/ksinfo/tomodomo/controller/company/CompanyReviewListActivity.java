package com.ksinfo.tomodomo.controller.company;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.model.vo.company.CompanyReviewVO;

import java.util.List;

public class CompanyReviewListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<CompanyReviewVO> companyReviewVOList = getIntent().getParcelableArrayListExtra("companyReviewVOArrayList");
        //Log.d("dfdsf", companyReviewVOList.get().getJobGroupName());
        for(int i=0;i<companyReviewVOList.size();i++){
            Log.d("dd",companyReviewVOList.get(i).getSimpleComment());
        }

    }
}
