package org.joker.oscp.cinema.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.joker.oscp.cinema.entity.FieldT;
import org.joker.oscp.system.api.cinema.vo.FilmInfoVO;
import org.joker.oscp.system.api.cinema.vo.HallInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 放映场次表 Mapper 接口
 * @author JOKER
 */
@Repository
public interface FieldTMapper extends BaseMapper<FieldT> {

    /**
     * 根据影院ID获取当前影院播放影片详情
     * @param cinemaId 影院ID
     * @return {@link FilmInfoVO} list
     */
    List<FilmInfoVO> getFilmInfos(@Param("cinemaId") Long cinemaId);

    /**
     * 根据场次ID获取院厅信息
     * @param fieldId 场次ID
     * @return {@link HallInfoVO}
     */
    HallInfoVO getHallInfo(@Param("fieldId") Long fieldId);

    /**
     * 根据场次ID获取影信息
     * @param fieldId 影院ID
     * @return {@link FilmInfoVO}
     */
    FilmInfoVO getFilmInfoById(@Param("fieldId") Long fieldId);

}
