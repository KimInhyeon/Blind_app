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
import com.ksinfo.tomodomo.controller.member.bookmark.BookMarkActivity;
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

    //메모 필수2 해당Activity의 oncreate() 내에 아래의 2줄의 코드를 기입해야 한다
    //        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
    //        tomodomoApplication.getApplicationComponent().inject(this);
    //메모 아래의 코드는 위의 2줄의 코드가 작동하도록 하기 위해 선언하는 코드이다.
    void inject(BookMarkActivity bookMarkActivity);
}