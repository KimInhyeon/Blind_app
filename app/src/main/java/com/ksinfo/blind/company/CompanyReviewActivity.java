package com.ksinfo.blind.company;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.ksinfo.blind.R;
import com.ksinfo.blind.company.api.CompanyApi;
import com.ksinfo.blind.util.RetrofitFactory;

public class CompanyReviewActivity extends AppCompatActivity {
    CompanyApi companyApi = RetrofitFactory.createJsonRetrofit().create(CompanyApi.class);
    private long companyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        ActivityCompanyReviewBinding binding = ActivityCompanyReviewBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.activity_company_review);
/*        Intent intent=getIntent();
        String companyName= intent.getStringExtra("companyName");
        long companyId=intent.getLongExtra("companyId",0L);
        Log.d("companyName",companyName);
        Log.d("companyId", String.valueOf(companyId));*/




        ImageView search = (ImageView) findViewById(R.id.iv_arrow);
        Intent i = new Intent(this, CompanySearchActivity.class);
        search.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
                startActivityForResult(i,9223);
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




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        long companyId=data.getLongExtra("companyId",0L);
        if(companyId>0) {
            this.companyId = companyId;//위의 변수


            TextView text = findViewById(R.id.companyName);
            String companyName = data.getStringExtra("companyName");
            Log.e("companyName", companyName);
            text.setText(companyName);
         //glide 라이브러리

            ImageView imageView= findViewById(R.id.iv_company_logo);
            String logoUrl = getResources().getString(R.string.base_url)+ "resources/images/company/"+companyId +".png";
            Glide.with(this).load(logoUrl).into(imageView);
        
        }
    }

   /* private void setCompanyInfo(){
        Intent intent = getIntent();
        ClearEditText text = findViewById(R.id.companySearch);
        String companyName = intent.getStringExtra("companyName");
         Log.e("companyName",companyName);
         text.setText(companyName);


    }*/



}

