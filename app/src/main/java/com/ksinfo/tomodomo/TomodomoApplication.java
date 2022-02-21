package com.ksinfo.tomodomo;

import android.app.Application;

import com.ksinfo.tomodomo.module.BoardModule;
import com.ksinfo.tomodomo.module.MainActivityModule;
import com.ksinfo.tomodomo.module.NetworkModule;
import com.ksinfo.tomodomo.util.ApplicationComponent;
import com.ksinfo.tomodomo.util.DaggerApplicationComponent;

public final class TomodomoApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        NetworkModule networkModule = new NetworkModule(getString(R.string.base_url));
        MainActivityModule mainActivityModule = new MainActivityModule(this);
        BoardModule boardModule = new BoardModule(this);
        applicationComponent = DaggerApplicationComponent.builder()
                                                         .networkModule(networkModule)
                                                         .mainActivityModule(mainActivityModule)
                                                         .boardModule(boardModule)
                                                         .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}