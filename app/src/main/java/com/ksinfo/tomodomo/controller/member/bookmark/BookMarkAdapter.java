package com.ksinfo.tomodomo.controller.member.bookmark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.model.vo.member.BookmarkPostDto;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BookMarkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    // adapter에 들어갈 list 입니다.
    private ArrayList<BookmarkPostDto> listData = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.bm_recycleview_items, parent, false);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bm_recycleview_items, parent, false);
        return new BookMarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        BookMarkViewHolder bookMarkViewHolder = (BookMarkViewHolder)holder;
        bookMarkViewHolder.onBind(listData.get(position),position);
    }

    @Override
    public int getItemCount() {
        //return listData.size();
        if (listData != null) {
            return listData.size();
        }
        return 0;
    }

    public void addItem(BookmarkPostDto data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }
}