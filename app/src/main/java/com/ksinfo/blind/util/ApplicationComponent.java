package com.ksinfo.blind.util;

import com.ksinfo.blind.MainActivity;
import com.ksinfo.blind.board.activity.PostActivity;
import com.ksinfo.blind.board.activity.SearchPostActivity;
import com.ksinfo.blind.board.adapter.ReplyAdapter;
import com.ksinfo.blind.board.adapter.SearchPostAdapter;
import com.ksinfo.blind.member.LoginActivity;
import com.ksinfo.blind.member.MemberJoinActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    MainActivityModule.class,
    NetworkModule.class
})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(MemberJoinActivity memberJoinActivity);

    void inject(SearchPostActivity searchPostActivity);

    void inject(SearchPostAdapter searchPostAdapter);

    void inject(PostActivity postActivity);

    void inject(ReplyAdapter replyAdapter);
}