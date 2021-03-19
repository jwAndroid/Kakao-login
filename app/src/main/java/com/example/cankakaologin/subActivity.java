package com.example.cankakaologin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.network.ApiErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.plusfriend.PlusFriendService;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.util.exception.KakaoException;

public class subActivity extends AppCompatActivity {

    String strNickname, strProfile;
    ImageView iv_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        TextView tvNickname = findViewById(R.id.tvNickname);
        TextView tvProfile = findViewById(R.id.tvProfile);
        iv_profile = findViewById(R.id.iv_profile);

        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnSignout = findViewById(R.id.btnSignout);
        Button kakaoChat = findViewById(R.id.kakaoChat);
        Button kakaoChatStart = findViewById(R.id.kakaoChatStart);

        Intent intent = getIntent();
        strNickname = intent.getStringExtra("name");
        strProfile = intent.getStringExtra("profile");

        tvNickname.setText(strNickname);
        tvProfile.setText(strProfile);

        Glide.with(this).load(strProfile).override(300,300).into(iv_profile);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_SHORT).show();

                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Intent intent = new Intent(subActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        });


        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(subActivity.this)
                        .setMessage("탈퇴하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {


                                    @Override
                                    public void onFailure(ErrorResult errorResult) {
                                        //회원탈퇴 실패 시 동작
                                        super.onFailure(errorResult);

                                        int result = errorResult.getErrorCode();


                                        if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
                                            Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "회원탈퇴에 실패했습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onSessionClosed(ErrorResult errorResult) {
                                        //세션이 닫혔을 시 동작.
                                        Toast.makeText(getApplicationContext(), "로그인 세션이 닫혔습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(subActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }

                                    @Override
                                    public void onNotSignedUp() {

                                        //가입되지 않은 계정이 회원탈퇴를 요구할 경우 동작.
                                        Toast.makeText(getApplicationContext(), "가입되지않은 계정입니다.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(subActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }

                                    @Override
                                    public void onSuccess(Long result) {

                                        //회원탈퇴 성공 시 동작.
                                        Toast.makeText(getApplicationContext(), "탈퇴성공.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(subActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                });

                                dialog.dismiss();
                            }

                        }).setNegativeButton("아니요", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });


//        //    implementation 'com.kakao.sdk:plusfriend:'
//        // 플러스친구 1:1대화 add , start
//        kakaoChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    PlusFriendService.getInstance().addFriend(subActivity.this,"_xixdVqK");
//                    Log.d("ee","ee");
//
//                } catch (KakaoException e) {
//                    Log.d("ee","ee");
//                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//
//        kakaoChatStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    PlusFriendService.getInstance().chat(subActivity.this, "_xixdVqK");
//                } catch (KakaoException e) {
//
//                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });



    }//..onCreate
}