package com.zhimadai.cctvmall.wointentservice.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.zhimadai.cctvmall.wointentservice.MyApp;
import com.zhimadai.cctvmall.wointentservice.R;
import com.zhimadai.cctvmall.wointentservice.service.MyIntentService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SMSAct extends AppCompatActivity {
    public static final String TAG = "MyIntentService";
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
        MyApp.getInstance().addActivity(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        MyIntentService.startActionBaz(this,"133-6654-652","重大新闻：大雨来临，");
        Log.e(TAG,"已启动短信Service");
    }
}
