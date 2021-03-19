package com.example.cankakaologin;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

public class App extends Application {

    private static volatile App instance = null;

    private static class KakaoSDKAdapter extends KakaoAdapter {


        public ISessionConfig getSessionConfig() {

            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[]{AuthType.KAKAO_LOGIN_ALL};
                    /*로그인을 하는 방식을 지정하는 부분. AuthType로는 다음 네 가지 방식이 있다.
                    KAKAO_TALK: 카카오톡으로 로그인,
                    KAKAO_STORY: 카카오스토리로 로그인,
                    KAKAO_ACCOUNT: 웹뷰를 통한 로그인,
                    KAKAO_TALK_EXCLUDE_NATIVE_LOGIN: 카카오톡으로만 로그인+계정 없으면 계정생성 버튼 제공
                    KAKAO_LOGIN_ALL: 모든 로그인방식 사용 가능. 정확히는, 카카오톡이나 카카오스토리가 있으면 그 쪽으로 로그인 기능을 제공하고, 둘 다 없으면 웹뷰를 통한 로그인을 제공한다.
                     */

                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Nullable
                @Override
                public ApprovalType getApprovalType() {
                    return null;
                }

                @Override
                public boolean isSaveFormData() {
                    return false;
                }

            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig(){

                @Override
                public Context getApplicationContext() {
                    return App.getGlobalApplicationContext();
                }
            };
        }
    }

    private static App getGlobalApplicationContext() {
        if(instance == null) {
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
