package org.joker.oscp.cinema.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 影厅电影信息表
 * @author JOKER
 */
@Data
@TableName("cs_hall_film_info")
public class HallFilmInfoT {
    /**
     * 主键编号
     */
    @TableId
    private Long uuid;
    /**
     * 电影编号
     */
    @TableField("film_id")
    private Long filmId;
    /**
     * 电影名称
     */
    @TableField("film_name")
    private String filmName;
    /**
     * 电影时长
     */
    @TableField("film_length")
    private String filmLength;
    /**
     * 电影类型
     */
    @TableField("film_cats")
    private String filmCats;
    /**
     * 电影语言
     */
    @TableField("film_language")
    private String filmLanguage;
    /**
     * 演员列表
     */
    private String actors;
    /**
     * 图片地址
     */
    @TableField("img_address")
    private String imgAddress;

}
