package org.joker.oscp.system.api.cinema.vo.enums;


import java.io.Serializable;

/**
 * 影院查询枚举信息
 * @author JOKER
 */
public enum CinemaQueryEnum implements Serializable {

    BRAND_ID(99, "全部品牌"),
    DIRSTRICT_ID(99,"全部描述信息"),
    HALL_TYPE(99,"全部影院信息"),
    AREA_ID(99,"全部地区"),
    PAGE_SIZE(12, "分页大小"),
    NOW_PAGE(1, "当前页");

    private Integer value;
    private String msg;

    CinemaQueryEnum(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return this.value;
    }
    public String getMsg() {
        return this.msg;
    }
}
