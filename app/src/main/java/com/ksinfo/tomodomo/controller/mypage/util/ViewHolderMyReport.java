package com.ksinfo.tomodomo.controller.mypage.util;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.model.vo.mypage.MyTaskReportVO;


public class ViewHolderMyReport extends RecyclerView.ViewHolder {

    TextView myReportTitle;
    TextView myReportBoardName;
    TextView myReportCompanyName;
    TextView myReportUserNickName;
    TextView myReportType;
    TextView myReportDate;
    TextView myReportStatus;
    LinearLayout myTaskReportlinearlayout;


    public ViewHolderMyReport(@NonNull View itemView) {
        super(itemView);

        myReportTitle = itemView.findViewById(R.id.myReportTitle);
        myReportBoardName = itemView.findViewById(R.id.myReportBoardName);
        myReportCompanyName = itemView.findViewById(R.id.myReportCompanyName);
        myReportUserNickName = itemView.findViewById(R.id.myReportUserNickName);
        myReportType = itemView.findViewById(R.id.myReportType);
        myReportDate = itemView.findViewById(R.id.myReportDate);
        myReportStatus = itemView.findViewById(R.id.myReportStatus);

        myTaskReportlinearlayout = itemView.findViewById(R.id.myTaskReportlinearlayout);

    }

    public void onBind(MyTaskReportVO data, int position, SparseBooleanArray selectedItems){
        myReportTitle.setText(data.getTitle());
        myReportBoardName.setText(data.getGroupName());
        if(data.getCompanyName()==null){
                myReportCompanyName.setText("会社名無し");
        }else {
            myReportCompanyName.setText(data.getCompanyName());
        }
        myReportUserNickName.setText(data.getUserNickname());
        myReportType.setText(data.getType());
        myReportDate.setText(data.getRecCreateDate());
        myReportStatus.setText(data.getStatus());
        if(data.getStatus().equals("承認完了")){
            myReportStatus.setTextColor(Color.parseColor("#00ff00"));
        }else if(data.getStatus().equals("却下")){
            myReportStatus.setTextColor(Color.parseColor("#ff2222"));
        }
        changeVisibility(selectedItems.get(position));
    }



    private void changeVisibility(final boolean isExpanded) {
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 600) : ValueAnimator.ofInt(600, 0);
        // Animation이 실행되는 시간, n/1000초
        va.setDuration(500);

        // Animation start
        va.start();
    }

}