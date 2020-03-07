package org.joker.oscp.system.api.community.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * 活动回复内容VO
 * @author JOKER
 */
@Data
public class ActivityReplyVO {

    private Long UUID;

    private Long userId;

    private String content;

    private String nickName;

    private String headUrl;

    private LocalDate createTime;

}
