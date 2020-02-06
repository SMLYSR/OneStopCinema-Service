package org.joker.oscp.film.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 影片详细信息实体
 * @author JOKER
 */
@Data
@TableName("fs_film_info")
public class FilmInfo {

    /**
     * 主键编号
     */
    @TableId
    private Long uuid;
    /**
     * 影片编号
     */
    @TableField("film_id")
    private Long filmId;
    /**
     * 影片英文名称
     */
    @TableField("film_en_name")
    private String filmEnName;
    /**
     * 影片评分
     */
    @TableField("film_score")
    private String filmScore;
    /**
     * 评分人数,以万为单位
     */
    @TableField("film_score_num")
    private Integer filmScoreNum;
    /**
     * 播放时长，以分钟为单位，不足取整
     */
    @TableField("film_length")
    private Integer filmLength;
    /**
     * 影片介绍
     */
    private String biography;
    /**
     * 导演编号
     */
    @TableField("director_id")
    private Long directorId;
    /**
     * 影片图片集地址,多个图片以逗号分隔
     */
    @TableField("film_imgs")
    private String filmImgs;
}
