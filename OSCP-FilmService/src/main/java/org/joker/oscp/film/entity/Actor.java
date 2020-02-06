package org.joker.oscp.film.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 演员实体
 * @author JOKER
 */
@Data
@TableName("fs_actor")
public class Actor {
    /**
     * 主键编号
     */
    @TableId
    private Long uuid;
    /**
     * 演员名称
     */
    @TableField("actor_name")
    private String actorName;
    /**
     * 演员图片位置
     */
    @TableField("actor_img")
    private String actorImg;
}
