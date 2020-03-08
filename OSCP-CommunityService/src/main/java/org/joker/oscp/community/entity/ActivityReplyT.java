package org.joker.oscp.community.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 参与活动的回复
 * @author JOKER
 */
@Data
@TableName("cy_activity_reply")
public class ActivityReplyT {

    @TableId
    private Long uuid;

    private Long userId;

    private String content;

}
