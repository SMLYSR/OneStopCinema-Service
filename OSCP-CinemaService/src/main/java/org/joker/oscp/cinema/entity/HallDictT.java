package org.joker.oscp.cinema.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;


/**
 * 地域信息表
 * @author JOKER
 */
@Data
@TableName("cs_hall_dict")
public class HallDictT {
    /**
     * 主键编号
     */
    @TableId
    private Long uuid;
    /**
     * 显示名称
     */
    @TableField("show_name")
    private String showName;
    /**
     * 座位文件存放地址
     */
    @TableField("seat_address")
    private String seatAddress;
}
