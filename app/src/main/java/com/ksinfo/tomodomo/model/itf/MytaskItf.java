package com.ksinfo.tomodomo.model.itf;



import com.ksinfo.tomodomo.model.vo.mypage.ModifyProfileVO;
import com.ksinfo.tomodomo.model.vo.mypage.MyCompanyReviewVO;
import com.ksinfo.tomodomo.model.vo.mypage.MyTaskReportVO;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MytaskItf {
	@GET("member/task/mytaskApi")
	Call<List<MyCompanyReviewVO>> getMyCompanyReview(@Query ("userId") String userId);

	@GET("member/task/mytaskReportApi")
	Call<List<MyTaskReportVO>> getMyTaskReport(@Query ("userId") String userId);


}