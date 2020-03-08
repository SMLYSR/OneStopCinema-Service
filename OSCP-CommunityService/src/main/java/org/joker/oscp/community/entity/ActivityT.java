package org.joker.oscp.community.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

/**
 * 社区活动
 * @author JOKER
 */
@Data
@TableName("cy_activity")
public class ActivityT {

    @TableId
    private Long uuid;

    private String title;

    private String contentDetail;

    private String activeImg;

    private LocalDate createTime;


}
