package org.joker.oscp.film.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 轮播实体
 * @author JOKER
 */
@Data
@TableName("fs_banner")
public class Banner {
    /**
     * 主键编号
     */
    @TableId
    private Long uuid;
    /**
     * banner图存放路径
     */
    @TableField("banner_address")
    private String bannerAddress;
    /**
     * banner点击跳转url
     */
    @TableField("banner_url")
    private String bannerUrl;
    /**
     * 是否弃用 0-失效,1-失效
     */
    @TableField("is_valid")
    private Integer isValid;
}
