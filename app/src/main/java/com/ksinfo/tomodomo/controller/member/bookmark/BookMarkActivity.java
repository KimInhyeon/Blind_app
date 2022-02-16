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
import com.ksinfo.tomodomo.controller.annualincome.CalculatorActivity;
import com.ksinfo.tomodomo.controller.member.CertificationActivity;
import com.ksinfo.tomodomo.controller.notice.NoticeActivity;
import com.ksinfo.tomodomo.controller.notice.NoticeRecyclerViewAdapter;
import com.ksinfo.tomodomo.model.itf.AnnualIncomeInterface;
import com.ksinfo.tomodomo.model.itf.BookMarkInterface;
import com.ksinfo.tomodomo.model.vo.annualincome.AnnualIncomeRankVO;
import com.ksinfo.tomodomo.model.vo.member.BookmarkPostVO;
import com.ksinfo.tomodomo.model.vo.notice.NoticeVO;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookMarkActivity extends AppCompatActivity {

    Long testUserId = 295L; //메모 userId값이 295인 유저가 로그인하여 연봉계산기를 들어간것으로 전제.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bm_recycleview);

        BookMarkInterface bookMarkInterface = RetrofitFactory.createJsonRetrofit().create(BookMarkInterface.class);
        bookMarkInterface.getMyPostList(testUserId,0 ).enqueue(new Callback<List<BookmarkPostVO>>() {
            @Override
            public void onResponse(Call<List<BookmarkPostVO>> call, Response<List<BookmarkPostVO>> response) {
                if(response.isSuccessful()){
                    System.out.println("success BookMarkInterface-getMyPostList ");
                    List<BookmarkPostVO> myPostList = response.body();

                    //메모 RecyclerView단계1 어답터 변수 생성.
                    //메모 adapter의 역할 : RecyclerView에 출력할 데이터를 받는다. 그리고 이를 1개씩 출력할 수 있도록 넘겨주는 역할같다.
                    BookMarkAdapter adapter = new BookMarkAdapter();

                    for (BookmarkPostVO vo : myPostList) {
                        adapter.addItem(vo);
                    }
                    System.out.println("adapter-size check : "+ adapter.getItemCount() );


                    //메모 1단계 : RecyclerView 변수 생성 및 xml R객체와 연결.
                    //메모 recyclerViewOfNoticeList : 레이아웃상에서 리스트(목록)형태로 출력해주는 역할.(#그릇 역할)
                    RecyclerView recyclerViewOfBookmarkList = findViewById(R.id.recyclerViewOfBookmarkList);


                    //메모 2단계 LinearLayoutManager : 수평,수직으로 배치시켜주는 레이아웃 매니저. 일반적으로 1개씩 보여주는 스크롤방식을 지원한다.
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BookMarkActivity.this);
                    recyclerViewOfBookmarkList.setLayoutManager(linearLayoutManager);

                    //LinearLayoutManager layoutManager = new LinearLayoutManager(BookMarkActivity.this, LinearLayoutManager.VERTICAL, false);
                    //recyclerView.setLayoutManager(layoutManager);

                    //메모 3단계
                    recyclerViewOfBookmarkList.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<BookmarkPostVO>> call, Throwable t) {
                System.out.println("failed BookMarkInterface-getMyPostList ");
            }
        });
    }
}