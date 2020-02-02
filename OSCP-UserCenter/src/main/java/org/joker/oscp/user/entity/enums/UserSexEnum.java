package org.joker.oscp.user.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 性别枚举类
 * @author JOKER
 */
public enum UserSexEnum implements IEnum{

    MALE(1, "male","男"),
    FEMALE(0, "female", "女");

    @EnumValue
    @JsonValue
    private int value;
    private String code;
    private String desc;

    UserSexEnum(int value, String code, String desc) {
        this.value = value;
        this.code = code;
        this.desc = desc;
    }

    public int value() {
        return this.value;
    }

    public String desc() {
        return this.desc;
    }

    public String getCode() {
        return this.code;
    }

    public static UserSexEnum getcode(String code) {
        UserSexEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            UserSexEnum e = var1[var3];
            if (Objects.equals(code, e.getCode())) {
                return e;
            }
        }

        return null;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }
}
