package com.ksinfo.tomodomo.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public final class GlideImageGetter implements Html.ImageGetter {
    private final Context context;
    private final TextView textView;

    public GlideImageGetter(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    @Override
    public Drawable getDrawable(String url) {
        RequestOptions requestOptions = new RequestOptions().centerInside();
        BitmapDrawablePlaceholder bitmapDrawablePlaceholder =
			new BitmapDrawablePlaceholder(context.getResources(), textView);

        Glide.with(context)
			 .asBitmap()
			 .load(url)
			 .apply(requestOptions)
			 .into(bitmapDrawablePlaceholder);

        return bitmapDrawablePlaceholder;
    }
}