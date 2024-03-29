package sg.edu.nus.imovin.System;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import sg.edu.nus.imovin.Retrofit.Object.PlanData;
import sg.edu.nus.imovin.Retrofit.Object.UserData;

public class ImovinApplication extends Application {
    private static ImovinApplication instance;
    private static UserData userData;
    private static PlanData planData;
    private static Boolean showWarning = false;
    private static ImageLoader imageLoader;
    private static OkHttpClient.Builder httpClient;

    private static boolean NeedRefreshForum = false;
    private static boolean NeedRefreshSocialFeed = false;
    private static boolean NeedRefreshPlanGoal = false;
    private static boolean NeedRefreshPlanMonitor = false;

    public static ImovinApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(instance));
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.d("fcm_test", "getInstanceId failed", task.getException());
                    return;
                }

                // Get new Instance ID token
                String token = task.getResult().getToken();

                Log.d("fcm_test", token);
            }
        });
    }

    public static UserData getUserData() {
        return userData;
    }

    public static void setUserData(UserData userData) {
        ImovinApplication.userData = userData;
    }

    public static PlanData getPlanData() {
        return planData;
    }

    public static void setPlanData(PlanData planData) {
        ImovinApplication.planData = planData;
    }

    public static Boolean getShowWarning() {
        return showWarning;
    }

    public static void setShowWarning(Boolean showWarning) {
        ImovinApplication.showWarning = showWarning;
    }

    public static ImageLoader getImageLoader() {
        return imageLoader;
    }

    public static OkHttpClient.Builder getHttpClient() {
        if(httpClient == null){
            initHttpClient();
        }
        return httpClient;
    }

    private static void initHttpClient(){
        if(userData != null){
            httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + ImovinApplication.getUserData().getToken()).build();
                    return chain.proceed(request);
                }
            });
        }else{
            httpClient = null;
        }
    }

    public static boolean isNeedRefreshForum() {
        return NeedRefreshForum;
    }

    public static void setNeedRefreshForum(boolean needRefreshForum) {
        NeedRefreshForum = needRefreshForum;
    }

    public static boolean isNeedRefreshSocialFeed(){
        return NeedRefreshSocialFeed;
    }

    public static void setNeedRefreshSocialNeed(boolean needRefreshSocialFeed){
        NeedRefreshSocialFeed = needRefreshSocialFeed;
    }

    public static boolean isNeedRefreshPlanGoal() {
        return NeedRefreshPlanGoal;
    }

    public static void setNeedRefreshPlanGoal(boolean needRefreshPlanGoal) {
        NeedRefreshPlanGoal = needRefreshPlanGoal;
    }

    public static boolean isNeedRefreshPlanMonitor() {
        return NeedRefreshPlanMonitor;
    }

    public static void setNeedRefreshPlanMonitor(boolean needRefreshPlanMonitor) {
        NeedRefreshPlanMonitor = needRefreshPlanMonitor;
    }

    public static void setNeedRefreshPlan(boolean needRefreshPlan){
        NeedRefreshPlanGoal = needRefreshPlan;
        NeedRefreshPlanMonitor = needRefreshPlan;
    }
}
