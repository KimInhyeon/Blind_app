package com.ksinfo.tomodomo.controller.annualincome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.model.itf.CompanyJobGroupItf;
import com.ksinfo.tomodomo.controller.annualincome.util.RecyclerVierAdapter;
import com.ksinfo.tomodomo.model.vo.annualincome.CompanyJobGroupVO;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnualIncomeRcViewSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rcv_sample);

        CompanyJobGroupItf companyJobGroupItf = RetrofitFactory.createJsonRetrofit().create(CompanyJobGroupItf.class);

        companyJobGroupItf.getJobGroupListAll().enqueue(new Callback<List<CompanyJobGroupVO>>() {
            @Override
            public void onResponse(@NonNull Call<List<CompanyJobGroupVO>> call, Response<List<CompanyJobGroupVO>> response) {
                RecyclerVierAdapter adapter = new RecyclerVierAdapter();
                if (response.isSuccessful()) {
                    List<CompanyJobGroupVO> getJobGroupListAll = response.body();

                    for (CompanyJobGroupVO vo : getJobGroupListAll) {
                        adapter.addItem(vo);
                    }

                    RecyclerView recyclerView = findViewById(R.id.recyclerView);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AnnualIncomeRcViewSampleActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

//                    adapter = new RecyclerVierAdapter();
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CompanyJobGroupVO>> call, Throwable t) {

            }
        });


    }
}