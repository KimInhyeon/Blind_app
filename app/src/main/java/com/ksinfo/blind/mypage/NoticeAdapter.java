package com.ksinfo.blind.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.blind.R;
import com.ksinfo.blind.VPAdapter;
import com.ksinfo.blind.ViewPagerItem;
import com.ksinfo.blind.mypage.vo.NoticeVO;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    //[메모] 단계1.변수생성 - 출력할 자료형에 맞추어 변수생성.
    ArrayList<NoticeVO> noticeList;

    //[메모] 단계2.변수초기화 - 단계1의 변수를 초기화.
    public NoticeAdapter(ArrayList<NoticeVO> noticeList) {
        this.noticeList = noticeList;
    }

    //[메모] 단계3.뷰홀더설정? - 레이아웃 시스템등에 연결하는 과정.
    //                       view : css처럼 사용할 포맷을 갖추는 레이아웃을 선택.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.notice_recycler_item,parent,false);
        return new NoticeAdapter.ViewHolder(view);
    }

    //[메모] 단계4.변수에 있는 데이터를 holder에 분할배치.
    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.ViewHolder holder, int position) {
        NoticeVO notice = noticeList.get(position);
        holder.textNoticeTitle.setText(notice.getNoticeTitle());
    }

    //[메모] 단계5.배열 갯수 카운트
    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    //[메모] 단계6.
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textNoticeTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // textNoticeTitle = itemView.findViewById(R.id.text_notice_title);
        }
    }

}
