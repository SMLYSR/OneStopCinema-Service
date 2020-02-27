package org.joker.oscp.pay.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author JOKER
 */
@Data
@TableName("oc_order")
public class OrderT {
    /**
     * 主键编号
     */
    @TableField("UUID")
    private String uuid;
    /**
     * 影院编号
     */
    @TableField("cinema_id")
    private Long cinemaId;
    /**
     * 放映场次编号
     */
    @TableField("field_id")
    private Long fieldId;
    /**
     * 电影编号
     */
    @TableField("film_id")
    private Long filmId;
    /**
     * 已售座位编号
     */
    @TableField("seats_ids")
    private String seatsIds;
    /**
     * 已售座位名称
     */
    @TableField("seats_name")
    private String seatsName;
    /**
     * 影片售价
     */
    @TableField("film_price")
    private BigDecimal filmPrice;
    /**
     * 订单总金额
     */
    @TableField("order_price")
    private BigDecimal orderPrice;
    /**
     * 下单时间
     */
    @TableField("order_time")
    private Date orderTime;
    /**
     * 下单人
     */
    @TableField("order_user")
    private Long orderUser;
    /**
     * 0-待支付,1-已支付,2-已关闭
     */
    @TableField("order_status")
    private Integer orderStatus;
}
