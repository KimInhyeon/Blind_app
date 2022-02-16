package com.ksinfo.tomodomo.util;

import com.ksinfo.tomodomo.controller.annualincome.AnnualIncomeRankCalculatorActivity;
import com.ksinfo.tomodomo.controller.board.PostActivity;
import com.ksinfo.tomodomo.controller.board.ReplyAdapter;
import com.ksinfo.tomodomo.controller.board.SearchPostActivity;
import com.ksinfo.tomodomo.controller.board.SearchPostAdapter;
import com.ksinfo.tomodomo.controller.common.MainActivity;
import com.ksinfo.tomodomo.controller.company.CompanyReviewActivity;
import com.ksinfo.tomodomo.controller.company.CompanySearchActivity;
import com.ksinfo.tomodomo.controller.member.LoginActivity;
import com.ksinfo.tomodomo.controller.member.MemberJoinActivity;
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

    void inject(SearchPostActivity searchPostActivity);

    void inject(SearchPostAdapter searchPostAdapter);

    void inject(PostActivity postActivity);

    void inject(ReplyAdapter replyAdapter);

    void inject(AnnualIncomeRankCalculatorActivity annualIncomeRankCalculatorActivity);

    void inject(CompanySearchActivity companySearchActivity);

    void inject(CompanyReviewActivity companyReviewActivity);
}