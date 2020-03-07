package org.joker.oscp.system.api.community.vo;

import lombok.Data;

@Data
public class FilmReviewVO {
    private Long userId;

    private String title;

    private String contentDetail;

    private String content;

    private String reviewCover;
}
