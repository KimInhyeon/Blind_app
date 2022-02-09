package com.ksinfo.blind.mypage;

import android.os.Bundle;
import android.util.Log;

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

public class InviteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite);

    }

}

