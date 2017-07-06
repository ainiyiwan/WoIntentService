package com.zhimadai.cctvmall.wointentservice.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zhimadai.cctvmall.wointentservice.entity.Contact;
import com.zhimadai.cctvmall.wointentservice.entity.SMS;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;

public class MyIntentService extends IntentService {
    public static final String TAG = "MyIntentService";
    private static final String ACTION_FOO = "com.zhimadai.cctvmall.wointentservice.service.action.FOO";
    private static final String ACTION_BAZ = "com.zhimadai.cctvmall.wointentservice.service.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.zhimadai.cctvmall.wointentservice.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.zhimadai.cctvmall.wointentservice.service.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            setIntentRedelivery(true);
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    private void handleActionFoo(String param1, String param2) {
        List<BmobObject> contactList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Contact contact = new Contact(param1 + i, param2 + i);
            Log.i(TAG, "已存" + i + "条通讯录");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (contactList.size() < 50) {
                contactList.add(contact);
            }
        }

        new BmobBatch().insertBatch(contactList).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> o, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < o.size(); i++) {
                        BatchResult result = o.get(i);
                        BmobException ex = result.getError();
                        if (ex == null) {
                            Log.i(TAG, "第" + i + "个数据批量添加成功：" + result.getCreatedAt() + "," +
                                    result.getObjectId() + "," + result.getUpdatedAt());
                        } else {
                            Log.i(TAG, "第" + i + "个数据批量添加失败：" + ex.getMessage() + "," + ex
                                    .getErrorCode());
                        }
                    }
                } else {
                    Log.i(TAG, "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    private void handleActionBaz(String param1, String param2) {
        List<BmobObject> smsList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SMS sms = new SMS(param1 + i, param2 + i);
            Log.i(TAG, "已存" + i + "条短信");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (smsList.size() < 50) {
                smsList.add(sms);
            }
        }

        new BmobBatch().insertBatch(smsList).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> o, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < o.size(); i++) {
                        BatchResult result = o.get(i);
                        BmobException ex = result.getError();
                        if (ex == null) {
                            Log.i(TAG, "第" + i + "个数据批量添加成功：" + result.getCreatedAt() + "," +
                                    result.getObjectId() + "," + result.getUpdatedAt());
                        } else {
                            Log.i(TAG, "第" + i + "个数据批量添加失败：" + ex.getMessage() + "," + ex
                                    .getErrorCode());
                        }
                    }
                } else {
                    Log.i(TAG, "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
    }
}
