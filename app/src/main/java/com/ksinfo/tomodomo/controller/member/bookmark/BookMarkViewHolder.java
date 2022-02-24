package com.ksinfo.tomodomo.controller.member.bookmark;

import android.animation.ValueAnimator;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.controller.notice.OnViewHolderItemClickListener;
import com.ksinfo.tomodomo.model.vo.board.PostBlock;
import com.ksinfo.tomodomo.model.vo.member.BookmarkPostVO;
import com.ksinfo.tomodomo.model.vo.notice.NoticeVO;

import java.util.List;

public class BookMarkViewHolder extends RecyclerView.ViewHolder {

    TextView boardTopicName;        //bookmark_boardTopicName;
    TextView postTitle;             //bookmark_postTitle;
    TextView postContents;          //bookmark_postContents;
    TextView companyName;           //bookmark_companyName;
    TextView userNickName;          //bookmark_userNickName;
    TextView postCount;             //bookmark_postCount;
    TextView recommended;           //bookmark_recommended;
    TextView postRecommendCount;    //bookmark_postRecommendCount;
    TextView replyCount;            //bookmark_replyCount;
    TextView createDate;            //bookmark_createDate;

    LinearLayout linearlayoutBookMark;

    public BookMarkViewHolder(@NonNull View itemView) {
        super(itemView);

//        boardTopicName = itemView.findViewById(R.id.bookmark_boardTopicName);
        postTitle = itemView.findViewById(R.id.bookmark_postTitle);
        postContents = itemView.findViewById(R.id.bookmark_postContents);
//       companyName  = itemView.findViewById(R.id.bookmark_companyName);
//        userNickName = itemView.findViewById(R.id.bookmark_userNickName);
//        postCount = itemView.findViewById(R.id.bookmark_postCount);
//        recommended = itemView.findViewById(R.id.bookmark_recommended);
//        postRecommendCount = itemView.findViewById(R.id.bookmark_postRecommendCount);
//        replyCount = itemView.findViewById(R.id.bookmark_replyCount);
        createDate = itemView.findViewById(R.id.bookmark_createDate);

        linearlayoutBookMark = itemView.findViewById(R.id.linearlayoutBookMark);

        linearlayoutBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // 메모 터치(클릭)한 북마크 토픽을 보여주는 상세포스트페이지로 이동하도록 연결예정.
            //    onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });
    }

    public void onBind(BookmarkPostVO data, int position){
//        boardTopicName = itemView.findViewById(R.id.bookmark_boardTopicName);
        postTitle = itemView.findViewById(R.id.bookmark_postTitle);
        postContents = itemView.findViewById(R.id.bookmark_postContents);
//        companyName  = itemView.findViewById(R.id.bookmark_companyName);
//        userNickName = itemView.findViewById(R.id.bookmark_userNickName);
//        postCount = itemView.findViewById(R.id.bookmark_postCount);
//        recommended = itemView.findViewById(R.id.bookmark_recommended);
//        postRecommendCount = itemView.findViewById(R.id.bookmark_postRecommendCount);
//        replyCount = itemView.findViewById(R.id.bookmark_replyCount);
        createDate = itemView.findViewById(R.id.bookmark_createDate);
    }

}