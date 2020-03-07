package org.joker.oscp.system.api.community.vo;

import lombok.Data;

/**
 * 发表影评VO
 * @author JOKER
 */
@Data
public class PublishArticleVO {

    private Long userId;
    private String title;
    private String contentDetail;
    private String content;
    private String reviewCover;

}
