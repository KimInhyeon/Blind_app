package com.ksinfo.tomodomo.controller.member.bookmark;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.controller.annualincome.CalculatorActivity;
import com.ksinfo.tomodomo.controller.member.CertificationActivity;
import com.ksinfo.tomodomo.controller.notice.NoticeActivity;
import com.ksinfo.tomodomo.controller.notice.NoticeRecyclerViewAdapter;
import com.ksinfo.tomodomo.model.itf.AnnualIncomeInterface;
import com.ksinfo.tomodomo.model.itf.BookMarkInterface;
import com.ksinfo.tomodomo.model.itf.JobGroupInterface;
import com.ksinfo.tomodomo.model.vo.annualincome.AnnualIncomeRankVO;
import com.ksinfo.tomodomo.model.vo.member.BookmarkPostVO;
import com.ksinfo.tomodomo.model.vo.notice.NoticeVO;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookMarkActivity extends AppCompatActivity {
    @Inject BookMarkInterface bookMarkInterface;

    Long userId = Long.valueOf(295); //메모 userId값이 295인 유저가 로그인하여 북마크확인하는 것으로 가정.
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bm_recycleview);

        //메모 필수3  NetworkModule.java와
        //
        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);

        System.out.println("bookMarkInterface - userId : " + userId);
        bookMarkInterface.getMyPostList(userId).enqueue(new Callback<List<BookmarkPostVO>>() {
            @Override
            public void onResponse(Call<List<BookmarkPostVO>> call, Response<List<BookmarkPostVO>> response) {
                if(response.isSuccessful()){
                    System.out.println("success BookMarkInterface-getMyPostList ");
                    List<BookmarkPostVO> myPostList = response.body();

                    //메모 RecyclerView 단계1 : 어답터 변수 생성.
                    //메모 adapter의 역할 : RecyclerView에 출력할 데이터를 받는다. 그리고 이를 1개씩 출력할 수 있도록 넘겨주는 역할같다.
                    BookMarkAdapter adapter = new BookMarkAdapter();

                    for (BookmarkPostVO vo : myPostList) {
                        adapter.addItem(vo);
                    }
                    System.out.println("adapter-size check : "+ adapter.getItemCount() );


                    //메모 RecyclerView 단계2 : RecyclerView 변수 생성 및 xml R객체와 연결.
                    //메모 recyclerViewOfNoticeList : 레이아웃상에서 리스트(목록)형태로 출력해주는 역할.(#그릇 역할)
                    RecyclerView recyclerView = findViewById(R.id.recyclerViewOfBookmarkList);

                    //메모 RecyclerView 단계3 : LinearLayoutManager 설정
                    //메모 LinearLayoutManager : 수평,수직으로 배치시켜주는 레이아웃 매니저. 일반적으로 1개씩 보여주는 스크롤방식을 지원한다.
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BookMarkActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    //메모 4단계 : setAdapter를 통해 List 출력.
                    recyclerView.setAdapter(adapter);

                }
            }
            @Override
            public void onFailure(Call<List<BookmarkPostVO>> call, Throwable t) {
                System.out.println("failed BookMarkActivity-getMyPostList ");
            }
        });
    }

}