package az.com.newazhong.utilsclass.base;


import az.com.newazhong.communityprofile.bean.Community;
import az.com.newazhong.noticeandannouncement.bean.Notice;
import az.com.newazhong.propagandwindow.bean.ActivityPhoto;
import az.com.newazhong.propagandwindow.bean.PropagandWindow;
import az.com.newazhong.street12345.activity.bean.Street;
import az.com.newazhong.utilsclass.bean.AddFind;
import az.com.newazhong.utilsclass.bean.Banner;
import az.com.newazhong.utilsclass.bean.Login;
import az.com.newazhong.utilsclass.bean.Register;
import az.com.newazhong.utilsclass.bean.Version;
import az.com.newazhong.workguide.bean.WorkGuide;
import az.com.newazhong.workguide.bean.WorkGuideList;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public interface RetrofitService {
    /**
     * 获取banner图片
     * @return
     */
    @GET("carousel")
    Observable<Banner> getBannerImage();

    /**
     * 街道简介
     * @return
     */
    @GET("department?page=0&size=1000")
    Observable<Community> getMDeptData();

    /**
     * 注册
     * @param phone
     * @param loginName
     * @param pwd
     * @return
     */
    @GET("reg")
    Observable<Register> getRegisterData(@Query("phone") String phone,
                                         @Query("loginName") String loginName, @Query("pwd") String pwd);

    /**
     * 登陆
     * @param phone
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("login?mobile=true")
    Observable<Login> getLoginData(@Field("username") String phone, @Field("password") String pwd);
//    b31b4179560f411eb84b9176f89ad9f0

    /**
     * 办事指南大项
     * @return
     */
    @GET("guideCategory?sort=sort")
    Observable<WorkGuide> getWorkGuideData();

    /**
     * 获取办事列表
     * @param typeId
     * @return
     */
    @GET("getEnchiridionInfo")
    Observable<WorkGuideList> getWorkGuideListData(@Query("typeId") String typeId);


    /**
     * 通知公告
     * @param type
     * @return
     */
    @GET("policy")
    Observable<Notice> getNoticetData(@Query("type") String type);
    /**
     * 跑马灯
     * @return
     */
    @GET("findAppNotifyInfo")
    Observable<Notice> getAllNoticetData();

    /**
     * 通知公告下拉刷新测试
     * @param type
     * @return
     */
    @GET("findAppNotifyInfo")
    Observable<Notice> getNoticetTestData(@Query("type") String type, @Query("pageNo") String pageNo, @Query("pageSize") String pageSize);


    /**
     * 工作动态
     * @return
     */
    @GET("workdynamics?page=0&size=1000")
    Observable<PropagandWindow> getPropagandWindowData();

    /**
     * 获取活动图片
     * @return
     */
    @GET("mien?mienType=PICTURE&page=0&size=1500")
    Observable<ActivityPhoto> getActivityPhoto();

    /**
     * 获取12345
     * @return
     */
    @GET("matter")
    Observable<Street> getStreet();

    /**
     * 上传12345
     * @return
     */
   // @FormUrlEncoded
    @GET("matter")
    Observable<AddFind> addFind(@Query("title") String title,
                                @Query("content") String content);


    /**
     * 获取版本号
     * @return
     */
    // @FormUrlEncoded
    @GET("version")
    Observable<Version> getVersion(@Query("type") String title);
}
