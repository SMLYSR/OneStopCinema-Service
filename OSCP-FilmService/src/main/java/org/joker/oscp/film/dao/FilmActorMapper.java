package org.joker.oscp.film.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.joker.oscp.film.entity.FilmActor;
import org.springframework.stereotype.Repository;

/**
 * 影片与演员映射表 Mapper 接口
 * @author JOKER
 */
@Repository
public interface FilmActorMapper extends BaseMapper<FilmActor> {
}
