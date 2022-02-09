package com.ksinfo.blind.mypage.util;

import android.animation.ValueAnimator;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.blind.mypage.util.OnViewHolderItemClickListener;
import com.ksinfo.blind.mypage.vo.NoticeVO;
import com.ksinfo.blind.R;

public class ViewHolderNotice extends RecyclerView.ViewHolder {

    //notice_recycleview_items
    TextView noticeTitle;
    TextView noticeCreateDate;
    TextView noticeContents;
    LinearLayout linearlayout;

    OnViewHolderItemClickListener onViewHolderItemClickListener;


    public ViewHolderNotice(@NonNull View itemView) {
        super(itemView);

        noticeTitle = itemView.findViewById(R.id.notice_title);
        noticeCreateDate = itemView.findViewById(R.id.notice_create_date);
        noticeContents = itemView.findViewById(R.id.notice_contents);
        linearlayout = itemView.findViewById(R.id.linearlayout);

        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });
    }

    public void onBind(NoticeVO data, int position, SparseBooleanArray selectedItems){
        noticeTitle.setText(data.getNoticeTitle());
        noticeCreateDate.setText(data.getNoticeCreateDate());
        noticeContents.setText(data.getNoticeContents());
        changeVisibility(selectedItems.get(position));
    }

    private void changeVisibility(final boolean isExpanded) {
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 600) : ValueAnimator.ofInt(600, 0);
        // Animation이 실행되는 시간, n/1000초
        va.setDuration(100);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // imageView의 높이 변경
                noticeContents.getLayoutParams().height = (int) animation.getAnimatedValue();
                noticeContents.requestLayout();
                // imageView가 실제로 사라지게하는 부분
                noticeContents.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            }
        });
        // Animation start
        va.start();
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }
}