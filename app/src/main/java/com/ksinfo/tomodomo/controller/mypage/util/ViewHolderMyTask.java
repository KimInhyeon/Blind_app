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
import com.ksinfo.tomodomo.model.vo.mypage.MyCompanyReviewVO;


public class ViewHolderMyTask extends RecyclerView.ViewHolder {

    TextView myTaskCompanyName;
    TextView myTaskCompanyName2;
    TextView myTaskrecCreateDate;
    TextView myTaskCompleteFlag;
    LinearLayout myTasklinearlayout;



    public ViewHolderMyTask(@NonNull View itemView) {
        super(itemView);

        myTaskCompanyName = itemView.findViewById(R.id.myTaskCompanyName);
        myTaskCompanyName2 = itemView.findViewById(R.id.myTaskCompanyName2);
        myTaskrecCreateDate = itemView.findViewById(R.id.myTaskrecCreateDate);
        myTasklinearlayout = itemView.findViewById(R.id.myTasklinearlayout);
        myTaskCompleteFlag = itemView.findViewById(R.id.myTaskCompleteFlag);



    }

    public void onBind(MyCompanyReviewVO data, int position, SparseBooleanArray selectedItems){
        myTaskCompanyName.setText(data.getCompanyName());
        myTaskCompanyName2.setText(data.getCompanyName());
        myTaskrecCreateDate.setText(data.getRecCreateDate());
        myTaskCompleteFlag.setText(data.getCompleteFlag());
        if(data.getCompleteFlag().equals("0")){
            myTaskCompleteFlag.setText("待機");
        }else if(data.getCompleteFlag().equals("1")){
            myTaskCompleteFlag.setText("承認完了");
            myTaskCompleteFlag.setTextColor(Color.parseColor("#00ff00"));
        }else if(data.getCompleteFlag().equals("2")){
            myTaskCompleteFlag.setText("却下");
            myTaskCompleteFlag.setTextColor(Color.parseColor("#ff2222"));
        }else{
            myTaskCompleteFlag.setText("error");
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