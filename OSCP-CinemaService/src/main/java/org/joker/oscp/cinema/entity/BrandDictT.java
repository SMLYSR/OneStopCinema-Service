package org.joker.oscp.cinema.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;


/**
 * 品牌信息表
 * @author Joker
 */
@Data
@TableName("cs_brand_dict")
public class BrandDictT  {

    /**
     * 主键编号
     */
    @TableId
    private Long uuid;
    /**
     * 显示名称
     */
    @TableField("show_name")
    private String showName;

}
