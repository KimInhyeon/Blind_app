package com.ksinfo.tomodomo.util;

import com.ksinfo.tomodomo.controller.annualincome.CalculatorActivity;
import com.ksinfo.tomodomo.controller.annualincome.ShowRankActivity;
import com.ksinfo.tomodomo.controller.board.PostActivity;
import com.ksinfo.tomodomo.controller.board.ReplyAdapter;
import com.ksinfo.tomodomo.controller.board.SearchPostActivity;
import com.ksinfo.tomodomo.controller.board.SearchPostAdapter;
import com.ksinfo.tomodomo.controller.common.MainActivity;
import com.ksinfo.tomodomo.controller.company.CompanyReviewActivity;
import com.ksinfo.tomodomo.controller.company.CompanySearchActivity;
import com.ksinfo.tomodomo.controller.member.LoginActivity;
import com.ksinfo.tomodomo.controller.member.MemberJoinActivity;
import com.ksinfo.tomodomo.controller.notice.NoticeActivity;
import com.ksinfo.tomodomo.module.MainActivityModule;
import com.ksinfo.tomodomo.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    NetworkModule.class,
    MainActivityModule.class
})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(MemberJoinActivity memberJoinActivity);

    void inject(CalculatorActivity calculatorActivity);

    void inject(ShowRankActivity showRankActivity);

    void inject(SearchPostActivity searchPostActivity);

    void inject(PostActivity postActivity);

    void inject(CompanySearchActivity companySearchActivity);

    void inject(CompanyReviewActivity companyReviewActivity);

    void inject(NoticeActivity noticeActivity);
}