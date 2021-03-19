# KakaoLogin_baseJAVA

## Introduction
-*
<!--  App클래스 application name 에 등록-->
<!--    https://developers.kakao.com/ 에서 네이티브키 프로젝트에 등록 -->
<!--    릴리즈 해시키 = > 카카오디벨로퍼에 등록 -->
<!--    meta-data 추가-->
<!--    코틀린으로 마이그레이션 진행해야함 문서참고-->
*


```java
            <meta-data
            android:name="com.kakao.sdk.AppKey"
            android:value="@string/kakao_app_key" />
    
```

```java
    repositories {
        mavenCentral()
        maven { url 'http://devrepo.kakao.com:8088/nexus/content/groups/public/' }
    }
    
```


```java
    implementation 'com.kakao.sdk:usermgmt:1.30.0'
    implementation group: 'com.kakao.sdk', name: 'plusfriend', version: project.KAKAO_SDK_PLUS_VERSION
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    implementation 'com.kakao.sdk:plusfriend:'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
    
```
