package com.ksinfo.blind.util;

import com.ksinfo.blind.board.api.BoardApi;
import com.ksinfo.blind.member.api.MemberApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public final class NetworkModule {
    private final String baseURL;

    public NetworkModule(String baseURL) {
        this.baseURL = baseURL;
    }
/*
    @Provides
    @Singleton
    public ScalarsConverterFactory provideScalarsConverterFactory() {
        return ScalarsConverterFactory.create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(ScalarsConverterFactory scalarsConverterFactory) {
        return new Retrofit.Builder()
                           .baseUrl(baseURL)
                           .addConverterFactory(scalarsConverterFactory)
                           .build();
    }
*/
    @Provides
    @Singleton
    public JacksonConverterFactory provideJacksonConverterFactory() {
        return JacksonConverterFactory.create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(JacksonConverterFactory jacksonConverterFactory) {
        return new Retrofit.Builder()
                           .baseUrl(baseURL)
                           .addConverterFactory(jacksonConverterFactory)
                           .build();
    }

    @Provides
    @Singleton
    public MemberApi provideMemberApi(Retrofit retrofit) {
        return retrofit.create(MemberApi.class);
    }

    @Provides
    @Singleton
    public BoardApi provideBoardApi(Retrofit retrofit) {
        return retrofit.create(BoardApi.class);
    }
}