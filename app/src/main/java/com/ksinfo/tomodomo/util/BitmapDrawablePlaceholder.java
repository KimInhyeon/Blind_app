package com.ksinfo.tomodomo.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

public final class BitmapDrawablePlaceholder extends BitmapDrawable implements Target<Bitmap> {
	private final Resources resources;
	private final TextView textView;
	private Drawable drawable;

	public BitmapDrawablePlaceholder(Resources resources, TextView textView) {
		super(resources, Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888));
		this.resources = resources;
		this.textView = textView;
	}

	@Override
	public void draw(Canvas canvas) {
		if (drawable != null) {
			drawable.draw(canvas);
		}
	}

	private void setDrawable(Drawable drawable) {
		this.drawable = drawable;

		int intrinsicWidth = drawable.getIntrinsicWidth();
		int intrinsicHeight = drawable.getIntrinsicHeight();
		int measuredWidth = textView.getMeasuredWidth();
		if (intrinsicWidth > measuredWidth) {
			intrinsicHeight = measuredWidth * intrinsicHeight / intrinsicWidth;
			intrinsicWidth = measuredWidth;
		}

		drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
		setBounds(0, 0, intrinsicWidth, intrinsicHeight);

		textView.setText(textView.getText());
	}

	@Override
	public void onLoadStarted(@Nullable Drawable placeholderDrawable) {
		if (placeholderDrawable != null) {
			setDrawable(placeholderDrawable);
		}
	}

	@Override
	public void onLoadFailed(@Nullable Drawable errorDrawable) {
		if (errorDrawable != null) {
			setDrawable(errorDrawable);
		}
	}

	@Override
	public void onResourceReady(
		@NonNull Bitmap bitmap,
		@Nullable Transition<? super Bitmap> transition
	) {
		Drawable drawable = new BitmapDrawable(resources, bitmap);
		setDrawable(drawable);
	}

	@Override
	public void onLoadCleared(@Nullable Drawable placeholderDrawable) {
		if (placeholderDrawable != null) {
			setDrawable(placeholderDrawable);
		}
	}

	@Override
	public void getSize(@NonNull SizeReadyCallback cb) {
		textView.post(new Runnable() {
			@Override
			public void run() {
				cb.onSizeReady(textView.getWidth(), textView.getHeight());
			}
		});
	}

	@Override
	public void removeCallback(@NonNull SizeReadyCallback cb) {}

	@Override
	public void setRequest(@Nullable Request request) {}

	@Nullable
	@Override
	public Request getRequest() {
		return null;
	}

	@Override
	public void onStart() {}

	@Override
	public void onStop() {}

	@Override
	public void onDestroy() {}
}