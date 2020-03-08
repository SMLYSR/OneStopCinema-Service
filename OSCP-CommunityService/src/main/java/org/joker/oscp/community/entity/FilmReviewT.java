package org.joker.oscp.community.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 影评
 * @author JOKER
 */
@Data
@TableName("cy_film_review")
public class FilmReviewT {

    @TableId
    private Long uuid;

    private Long userId;

    private String title;

    private String contentDetail;

    private String content;

    private String reviewCover;

}
