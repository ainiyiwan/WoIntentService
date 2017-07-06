package com.zhimadai.cctvmall.wointentservice.entity;

import cn.bmob.v3.BmobObject;

/**
 * Author ： zhangyang
 * Date   ： 2017/7/6
 * Email  :  18610942105@163.com
 * Description  :
 */

public class SMS extends BmobObject {
    public String number;
    public String content;

    public SMS(String number, String content) {
        this.number = number;
        this.content = content;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getcontent() {
        return content;
    }

    public void setcontent(String content) {
        this.content = content;
    }
}
