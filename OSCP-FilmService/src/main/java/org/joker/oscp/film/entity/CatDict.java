package org.joker.oscp.film.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 类型信息表
 * @author JOKER
 */
@Data
@TableName("fs_cat_dict")
public class CatDict {
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
