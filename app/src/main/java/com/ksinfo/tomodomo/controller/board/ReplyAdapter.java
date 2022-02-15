package com.ksinfo.tomodomo.controller.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.model.itf.BoardInterface;
import com.ksinfo.tomodomo.model.vo.board.ReplyVO;

import java.util.List;

public final class ReplyAdapter extends RecyclerView.Adapter<ReplyHolder> {
    private final BoardInterface boardInterface;
    private List<ReplyVO> replyList;

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