package org.joker.oscp.common.enums;


import java.io.Serializable;

/**
 * 性别枚举类
 * @author JOKER
 */
public enum UserSexEnum {

    MALE(1, "male"),
    FEMALE(0, "female");
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


}
