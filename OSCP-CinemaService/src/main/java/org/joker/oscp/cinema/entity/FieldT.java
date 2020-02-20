package org.joker.oscp.cinema.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 放映场次表
 * @author JOKER
 */
@Data
@TableName("cs_field")
public class FieldT  {
    /**
     * 主键编号
     */
    @TableId
    private Long uuid;
    /**
     * 影院编号
     */
    @TableField("cinema_id")
    private Long cinemaId;
    /**
     * 电影编号
     */
    @TableField("film_id")
    private Long filmId;
    /**
     * 开始时间
     */
    @TableField("begin_time")
    private String beginTime;
    /**
     * 结束时间
     */
    @TableField("end_time")
    private String endTime;
    /**
     * 放映厅类型编号
     */
    @TableField("hall_id")
    private Long hallId;
    /**
     * 放映厅名称
     */
    @TableField("hall_name")
    private String hallName;
    /**
     * 票价
     */
    private BigDecimal price;
}
