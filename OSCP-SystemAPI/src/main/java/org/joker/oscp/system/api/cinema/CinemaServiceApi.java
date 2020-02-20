package org.joker.oscp.system.api.cinema;

import com.baomidou.mybatisplus.plugins.Page;
import org.joker.oscp.system.api.cinema.vo.*;

import java.util.List;

/**
 * 影院服务Api接口
 * @author JOKER
 */
public interface CinemaServiceApi {

    /**
     * 根据所选类型查询影院列表
     * @param cinemaQueryVO
     * @return {@link Page<CinemaVO>}
     */
    Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO);

    /**
     * 获取品牌列表
     * @param brandId 品牌ID
     * @return {@link BrandVO} 列表
     */
    List<BrandVO> getBrands(Long brandId);

    /**
     * 获取行政区域列表
     * @param areaId
     * @return {@link AreaVO} 列表
     */
    List<AreaVO> getAreas(Long areaId);

    /**
     * 获取影厅类型列表
     * @param hallType 影厅类型
     * @return {@link HallTypeVO} 列表
     */
    List<HallTypeVO> getHallTypes(Long hallType);

    /**
     * 根据影院编号，获取影院信息
     * @param cinemaId  影院ID
     * @return {@link CinemaInfoVO}
     */
    CinemaInfoVO getCinemaInfoById(Long cinemaId);

    /**
     * 获取所有电影的信息和对应的放映场次信息，根据影院编号
     * @param cinemaId 影院ID
     * @return {@link FilmInfoVO}
     */
    List<FilmInfoVO> getFilmInfoByCinemaId(Long cinemaId);

    /**
     * 根据放映场次ID获取放映信息
     * @param fieldId 场次ID
     * @return {@link HallInfoVO}
     */
    HallInfoVO getFilmFieldInfo(Long fieldId);

    /**
     * 根据放映场次查询播放的电影编号，然后根据电影编号获取对应的电影信息
     * @param fieldId 场次ID
     * @return {@link FilmInfoVO}
     */
    FilmInfoVO getFilmInfoByFieldId(Long fieldId);

    /**
     * <p>为订单中心提供服务</p>
     * @param FieldId 影院Id
     * @return {@link OrderQueryVO}
     */
    OrderQueryVO getOrderNeeds(Long FieldId);
}
