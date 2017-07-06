package com.zhimadai.cctvmall.wointentservice.entity;

import cn.bmob.v3.BmobObject;

/**
 * Author ： zhangyang
 * Date   ： 2017/7/6
 * Email  :  18610942105@163.com
 * Description  :
 */

public class Contact extends BmobObject {
    public String contactNumber;
    public String contactName;

    public Contact(String contactNumber, String contactName) {
        this.contactNumber = contactNumber;
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
