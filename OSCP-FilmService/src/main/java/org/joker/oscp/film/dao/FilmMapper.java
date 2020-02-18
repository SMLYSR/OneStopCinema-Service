package org.joker.oscp.film.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.joker.oscp.film.entity.Film;
import org.joker.oscp.system.api.film.vo.FilmDetailVO;
import org.springframework.stereotype.Repository;

/**
 * 影片主表 Mapper 接口
 * @author JOKER
 */
@Repository
public interface FilmMapper extends BaseMapper<Film> {

    FilmDetailVO getFilmDetailByName(@Param("filmName") String filmName);

    FilmDetailVO getFilmDetailById(@Param("uuid") Long uuid);

}
