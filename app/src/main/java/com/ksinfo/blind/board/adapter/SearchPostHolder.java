package com.ksinfo.blind.board.adapter;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ksinfo.blind.R;

public final class SearchPostHolder extends RecyclerView.ViewHolder {
	private final TextView boardName;
	private final TextView postTitle;
	private final ImageView postImage;
	private final TextView postContents;

	public SearchPostHolder(@NonNull View itemView) {
		super(itemView);
		boardName = itemView.findViewById(R.id.searchBoardName);
		postTitle = itemView.findViewById(R.id.searchPostTitle);
		postImage = itemView.findViewById(R.id.searchPostImage);
		postContents = itemView.findViewById(R.id.searchPostContents);
	}

	public void setBoardName(String boardName) {
		this.boardName.setText(boardName);
	}

	public void setPostTitle(String postTitle) {
		this.postTitle.setText(postTitle);
	}

	public void setPostImage(String postFileUrl) {
		if (postFileUrl != null) {
			Glide.with(itemView).load(postFileUrl).into(postImage);
		}
	}

	public void setPostContents(String postContents) {
		if (postContents != null) {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
				this.postContents.setText(Html.fromHtml(postContents));
			} else {
				this.postContents.setText(Html.fromHtml(postContents, Html.FROM_HTML_MODE_LEGACY));
			}
		}
	}
}