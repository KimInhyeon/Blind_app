package com.ksinfo.blind.mypage;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.blind.R;
import com.ksinfo.blind.mypage.api.NoticeApi;
import com.ksinfo.blind.mypage.util.NoticeRecyclerViewAdapter;
import com.ksinfo.blind.mypage.vo.NoticeVO;
import com.ksinfo.blind.util.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_recycleview);

        NoticeApi noticeApi = RetrofitFactory.createJsonRetrofit().create(NoticeApi.class);
        noticeApi.getNoticeList().enqueue(new Callback<List<NoticeVO>>() {
            @Override
            public void onResponse(@NonNull Call<List<NoticeVO>> call, Response<List<NoticeVO>> response) {
                System.out.println("start NoticeActivity-getNoticeList-onResponse.");

                //메모 RecyclerViewer단계1 어답터 변수 생성.
                NoticeRecyclerViewAdapter adapter = new NoticeRecyclerViewAdapter();

                if (response.isSuccessful()) {
                    List<NoticeVO> noticeList = response.body();
                    Log.d("NoticeAct-noticeLists", noticeList.toString());

                    for (NoticeVO vo : noticeList) {
                        adapter.addItem(vo);
                    }

                    //메모 1단계 : RecyclerView 변수 생성 및 xml R객체와 연결.
                    RecyclerView recyclerView = findViewById(R.id.recyclerViewOfNoticeList);


                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NoticeActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    //adapter = new RecyclerVierAdapter();
                    recyclerView.setAdapter(adapter);

                    //----------------------------------------------------------------------------------------------------------------------------
                    final String[] listOfNotice = new String[noticeList.size()];
                    int arrCount = 0;

                    for (NoticeVO vo : noticeList) {
                        listOfNotice[arrCount] = vo.getNoticeTitle();
                        arrCount++;
                    }

                    ArrayAdapter adapter1 = new ArrayAdapter(NoticeActivity.this, android.R.layout.simple_spinner_item, listOfNotice);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    Spinner spinnerTest;
                    spinnerTest = (Spinner) findViewById(R.id.spinnerTest);
                    spinnerTest.setAdapter(adapter1);
                    //-----------------------------------------------------------------------------



                } else {
                    Log.d("error", "error");
                }

            }

            @Override
            public void onFailure(Call<List<NoticeVO>> call, Throwable t) {

            }
        });
    }

}

