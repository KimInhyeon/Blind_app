package com.ksinfo.blind.mypage;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ksinfo.blind.R;
import com.ksinfo.blind.mypage.api.NoticeApi;
import com.ksinfo.blind.mypage.vo.NoticeVO;
import com.ksinfo.blind.util.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);

        NoticeApi noticeApi = RetrofitFactory.createJsonRetrofit().create(NoticeApi.class);

        noticeApi.getNoticeList().enqueue(new Callback<List<NoticeVO>>() {
            @Override
            public void onResponse(@NonNull Call<List<NoticeVO>> call, Response<List<NoticeVO>> response) {
                System.out.println("start getNoticeList-onResponse.");
                if (response.isSuccessful()) {
                    List<NoticeVO> noticeList = response.body();
                    Log.d("received noticeLists :", noticeList.toString());

                    ArrayList<String>list = new ArrayList<>();
                    for (NoticeVO vo : noticeList) {
                        list.add(vo.getNoticeTitle());
                    }

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

                    //메모 - Recyleview 출력
                    //ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
                    //viewPager.setAdapter(new simpleAdapter(getSupportFragmentManager()));


                } else {
                    Log.d("error", "error");
                }

            }

            @Override
            public void onFailure(Call<List<NoticeVO>> call, Throwable t) {

            }
        });
    }
    /*
    private class simpleAdapter extends FragmentPagerAdapter {
        public simpleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Fragment getItem(int position) {
            return new NoticeRecyleViewFragment();
        }
    }
    */
}

