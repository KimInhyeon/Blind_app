package com.ksinfo.tomodomo.controller.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.model.itf.BoardInterface;
import com.ksinfo.tomodomo.model.vo.board.ReplyVO;

import java.util.List;

public final class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyHolder> {
    private final BoardInterface boardInterface;
    private List<ReplyVO> replyList;

    static final class ReplyHolder extends RecyclerView.ViewHolder {
        private final TextView companyName;
        private final TextView userNickname;
        private final ImageView replyImage;
        private final TextView replyContents;
        private final TextView recCreateDate;
        private final TextView replyRecommendCount;
        private final TextView nestedCount;

        public ReplyHolder(@NonNull View itemView) {
            super(itemView);
            companyName = itemView.findViewById(R.id.bd_reply_list_company_name);
            userNickname = itemView.findViewById(R.id.bd_reply_list_user_name);
            replyImage = itemView.findViewById(R.id.bd_reply_list_image);
            replyContents = itemView.findViewById(R.id.bd_reply_list_contents);
            recCreateDate = itemView.findViewById(R.id.bd_reply_list_create_date);
            replyRecommendCount = itemView.findViewById(R.id.bd_reply_list_recommend_count);
            nestedCount = itemView.findViewById(R.id.bd_reply_list_nested_count);
        }

        public void setCompanyName(String companyName) {
            this.companyName.setText(companyName);
        }

        public void setUserNickname(String userNickname) {
            this.userNickname.setText(userNickname);
        }

        public void setReplyImage(String replyFileUrl) {
            if (replyFileUrl != null) {
                Glide.with(itemView).load(replyFileUrl).into(replyImage);
            }
        }

        public void setReplyContents(String replyContents) {
            this.replyContents.setText(replyContents);
        }

        public void setRecCreateDate(String recCreateDate) {
            this.recCreateDate.setText(recCreateDate);
        }

        public void setReplyRecommendCount(int replyRecommendCount) {
            this.replyRecommendCount.setText(String.valueOf(replyRecommendCount));
        }

        public void setNestedCount(int nestedCount) {
            this.nestedCount.setText(String.valueOf(nestedCount));
        }
    }

    public ReplyAdapter(BoardInterface boardInterface, List<ReplyVO> replyList) {
        this.boardInterface = boardInterface;
        this.replyList = replyList;
    }

    @NonNull
    @Override
    public ReplyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.bd_reply_list, parent, false);

        return new ReplyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyHolder holder, int position) {
        ReplyVO reply = replyList.get(position);

        holder.setCompanyName(reply.getCompanyName());
        holder.setUserNickname(reply.getUserNickname());
        holder.setReplyImage(reply.getReplyFileUrl());
        holder.setReplyContents(reply.getReplyContents());
        holder.setRecCreateDate(reply.getRecCreateDate());
        holder.setReplyRecommendCount(reply.getReplyRecommendCount());
        holder.setNestedCount(reply.getNestedCount());
    }

    @Override
    public int getItemCount() {
        return replyList.size();
    }

    public void setReplyList(List<ReplyVO> replyList) {
        this.replyList = replyList;
        notifyDataSetChanged();
    }

    public void addReplyList(List<ReplyVO> replyList) {
        int position = this.replyList.size();
        this.replyList.addAll(replyList);
        notifyItemInserted(position);
    }
}