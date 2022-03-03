package com.ksinfo.tomodomo.controller.mypage.util;

import android.annotation.SuppressLint;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.model.vo.mypage.MyTaskReportVO;


import java.util.ArrayList;

public class MyReportRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<MyTaskReportVO> listData = new ArrayList<>();

    // Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mytask_report_items, parent, false);
        return new ViewHolderMyReport(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ViewHolderMyReport viewHolderMyReport = (ViewHolderMyReport)holder;
        viewHolderMyReport.onBind(listData.get(position),position, selectedItems);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(MyTaskReportVO data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

}