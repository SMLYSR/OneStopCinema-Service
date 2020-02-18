package org.joker.oscp.film.dao;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.joker.oscp.film.entity.Actor;
import org.joker.oscp.system.api.film.vo.ActorVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 演员表 Mapper 接口
 * @author JOKER
 */
@Repository
public interface ActorMapper extends BaseMapper<Actor> {

    List<ActorVO> getActors(@Param("filmId") Long filmId);

}
