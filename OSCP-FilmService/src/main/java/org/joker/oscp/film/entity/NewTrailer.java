package org.joker.oscp.film.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 新片预告
 * @author JOKER
 */
@Data
@TableName("fs_new_trailer")
public class NewTrailer {
    /**
     * 主键编号
     */
    @TableId
    private Long uuid;

    /**
     * 新片名称
     */
    private String trailerName;

    /**
     * 新片文本介绍
     */
    private String trailerContext;

    /**
     * 预告封面图片
     */
    private String trailerImg;

    /**
     * 预告视频地址
     */
    private String trailerVideo;

    /**
     * 新片上映时间
     */
    private String trailerTime;

    /**
     * 预告视访问页面
     */
    private String trailerUrl;

}
