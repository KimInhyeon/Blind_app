package com.ksinfo.tomodomo.controller.mypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.model.itf.MytaskItf;
import com.ksinfo.tomodomo.controller.mypage.util.MyReportRecyclerViewAdapter;
import com.ksinfo.tomodomo.controller.mypage.util.RecyclerViewAdapter;

import com.ksinfo.tomodomo.model.vo.mypage.MyCompanyReviewVO;
import com.ksinfo.tomodomo.model.vo.mypage.MyTaskReportVO;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);

        MytaskItf mytaskItf = RetrofitFactory.createJsonRetrofit().create(MytaskItf.class);

        mytaskItf.getMyCompanyReview("149").enqueue(new Callback<List<MyCompanyReviewVO>>() {

            @Override
            public void onResponse(@NonNull Call<List<MyCompanyReviewVO>> call, Response<List<MyCompanyReviewVO>> response) {
                RecyclerViewAdapter adapter = new RecyclerViewAdapter();
                if (response.isSuccessful()) {
                    List<MyCompanyReviewVO> myTaskList = response.body();
                    for (MyCompanyReviewVO vo : myTaskList) {
                        adapter.addItem(vo);
                    }

                    RecyclerView recyclerView = findViewById(R.id.myTaskRecyclerView);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyTaskActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

//                    adapter = new RecyclerVierAdapter();
                    recyclerView.setAdapter(adapter);
                }

            }
            @Override
            public void onFailure(Call<List<MyCompanyReviewVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        mytaskItf.getMyTaskReport("149").enqueue(new Callback<List<MyTaskReportVO>>() {

            @Override
            public void onResponse(@NonNull Call<List<MyTaskReportVO>> call, Response<List<MyTaskReportVO>> response) {
                MyReportRecyclerViewAdapter adapter = new MyReportRecyclerViewAdapter();
                if (response.isSuccessful()) {
                    List<MyTaskReportVO> myTaskReportList = response.body();
                    for (MyTaskReportVO vo : myTaskReportList) {
                        adapter.addItem(vo);
                    }

                    RecyclerView recyclerView = findViewById(R.id.myTaskReportRecyclerView);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyTaskActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

//                    adapter = new RecyclerVierAdapter();
                    recyclerView.setAdapter(adapter);
                }

            }
            @Override
            public void onFailure(Call<List<MyTaskReportVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        ImageButton backBtn = findViewById(R.id.backPress);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}