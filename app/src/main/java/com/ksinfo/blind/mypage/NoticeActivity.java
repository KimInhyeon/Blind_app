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

                //메모 RecyclerView단계1 어답터 변수 생성.
                //메모 adapter의 역할 : RecyclerView에 출력할 데이터를 받는다. 그리고 이를 1개씩 출력할 수 있도록 넘겨주는 역할같다.
                NoticeRecyclerViewAdapter adapter = new NoticeRecyclerViewAdapter();

                if (response.isSuccessful()) {
                    //메모 서버로부터 공지사항목록을 수신(단, 공지하겠다고 결정한 것들만 수신)
                    List<NoticeVO> noticeList = response.body();
                    Log.d("NoticeAct-noticeLists", noticeList.toString());

                    for (NoticeVO vo : noticeList) {
                        adapter.addItem(vo);
                    }

                    //메모 1단계 : RecyclerView 변수 생성 및 xml R객체와 연결.
                    //메모 recyclerViewOfNoticeList : 레이아웃상에서 리스트(목록)형태로 출력해주는 역할.(#그릇 역할)
                    RecyclerView recyclerView = findViewById(R.id.recyclerViewOfNoticeList);

                    //메모 2단계 LinearLayoutManager : 수평,수직으로 배치시켜주는 레이아웃 매니저. 일반적으로 1개씩 보여주는 스크롤방식을 지원한다.
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NoticeActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    //메모 3단계
                    recyclerView.setAdapter(adapter);

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

