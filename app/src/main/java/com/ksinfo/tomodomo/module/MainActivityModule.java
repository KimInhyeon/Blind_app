package com.ksinfo.tomodomo.module;

import android.app.Application;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.controller.common.VPAdapter;
import com.ksinfo.tomodomo.controller.common.ViewPagerItem;

import java.util.ArrayList;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private final Application application;

    public MainActivityModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public int[] provideImages() {
        return new int[]{
            R.drawable.cm_p_start1
          , R.drawable.cm_p_start2
          , R.drawable.cm_p_start3
          , R.drawable.cm_p_start4
          , R.drawable.cm_p_start5
        };
    }

    @Provides
    @Singleton
    @Named("heading")
    public String[] provideHeading() {
        return new String[]{
            "アイデア"
          , "自由な会話"
          , "コネクト"
          , "趣味"
          , "楽な会話"
        };
    }

    @Provides
    @Singleton
    @Named("desc")
    public String[] provideDesc() {
        return new String[]{
            application.getString(R.string.a_desc)
          , application.getString(R.string.b_desc)
          , application.getString(R.string.c_desc)
          , application.getString(R.string.d_desc)
          , application.getString(R.string.e_desc)
        };
    }

    @Provides
    @Singleton
    public VPAdapter provideVpAdapter(
        int[] images, @Named("heading") String[] heading, @Named("desc") String[] desc
    ) {
        ArrayList<ViewPagerItem> viewPagerItemArrayList = new ArrayList<>();
        for (int i = 0, imagesLength = images.length; i < imagesLength; i++) {
            ViewPagerItem viewPagerItem = new ViewPagerItem(images[i], heading[i], desc[i]);
            viewPagerItemArrayList.add(viewPagerItem);
        }

        return new VPAdapter(viewPagerItemArrayList);
    }
}