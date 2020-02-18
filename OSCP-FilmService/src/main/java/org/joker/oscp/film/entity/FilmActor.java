package org.joker.oscp.film.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 影片与演员映射
 * @author JOKER
 */
@Data
@TableName("fs_film_actor")
public class FilmActor {
    /**
     * 主键编号
     */
    @TableId
    private Long uuid;
    /**
     * 影片编号,对应mooc_film_t
     */
    @TableField("film_id")
    private Long filmId;
    /**
     * 演员编号,对应mooc_actor_t
     */
    @TableField("actor_id")
    private Long actorId;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

}
