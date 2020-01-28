package org.joker.oscp.user.entity;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * 性别枚举类
 * @author JOKER
 */
public enum UserSexEnum implements IEnum {

    MALE(1, "男性"),
    FEMALE(0, "女性");
    private int value;
    private String code;

    UserSexEnum(int value, String code) {
        this.value = value;
        this.code = code;
    }

    public int value() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public Serializable getValue() {
        return null;
    }

}
