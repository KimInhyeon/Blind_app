package com.ksinfo.blind.board.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.blind.R;
import com.ksinfo.blind.board.api.BoardApi;
import com.ksinfo.blind.board.vo.ReplyVO;

import java.util.List;

public final class ReplyAdapter extends RecyclerView.Adapter<ReplyHolder> {
    private final BoardApi boardApi;
    private List<ReplyVO> replyList;

    public ReplyAdapter(BoardApi boardApi, List<ReplyVO> replyList) {
        this.boardApi = boardApi;
        this.replyList = replyList;
    }

    @NonNull
    @Override
    public ReplyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.reply_list, parent, false);

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