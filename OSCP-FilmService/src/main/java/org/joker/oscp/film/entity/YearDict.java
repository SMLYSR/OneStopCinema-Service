package org.joker.oscp.film.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 年代信息字典
 * @author JOKER
 */
@Data
@TableName("fs_year_dict")
public class YearDict {
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
