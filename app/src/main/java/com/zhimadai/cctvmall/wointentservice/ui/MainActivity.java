package com.zhimadai.cctvmall.wointentservice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;

import com.zhimadai.cctvmall.wointentservice.MyApp;
import com.zhimadai.cctvmall.wointentservice.R;
import com.zhimadai.cctvmall.wointentservice.service.MyIntentService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MyIntentService";
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.jump)
    Button jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MyApp.getInstance().addActivity(this);
    }

    @OnClick(R.id.btn)
    public void onBtnClicked() {
        MyIntentService.startActionFoo(this,"张胜男","188-1334-156");
        Log.e(TAG,"已启动通讯录Service");
    }

    @OnClick(R.id.jump)
    public void onJumpClicked() {
        startActivity(new Intent(MainActivity.this,SMSAct.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //捕获返回键按下的事件
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.e(TAG,"Activity终结了");
            MyApp.getInstance().exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}


