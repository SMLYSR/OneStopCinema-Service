package org.joker.oscp.film.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * 影片实体
 * @author JOKER
 */
@Data
@TableName("fs_film")
public class Film {

    /**
     * 主键编号
     */
    @TableId
    private Long uuid;
    /**
     * 影片名称
     */
    @TableField("film_name")
    private String filmName;
    /**
     * 片源类型: 0-2D,1-3D,2-3DIMAX,4-无
     */
    @TableField("film_type")
    private Integer filmType;
    /**
     * 影片主图地址
     */
    @TableField("img_address")
    private String imgAddress;
    /**
     * 影片评分
     */
    @TableField("film_score")
    private String filmScore;
    /**
     * 影片预售数量
     */
    @TableField("film_preSaleNum")
    private Integer filmPresalenum;
    /**
     * 影片票房：每日更新，以万为单位
     */
    @TableField("film_box_office")
    private Integer filmBoxOffice;
    /**
     * 影片片源，参照片源字典表
     */
    @TableField("film_source")
    private Long filmSource;
    /**
     * 影片分类，参照分类表,多个分类以,分割
     */
    @TableField("film_cats")
    private Long filmCats;
    /**
     * 影片区域，参照区域表
     */
    @TableField("film_area")
    private Long filmArea;
    /**
     * 影片上映年代，参照年代表
     */
    @TableField("film_date")
    private Long filmDate;
    /**
     * 影片上映时间
     */
    @TableField("film_time")
    private LocalDate filmTime;
    /**
     * 影片状态,1-正在热映，2-即将上映，3-经典影片
     */
    @TableField("film_status")
    private Integer filmStatus;


}
