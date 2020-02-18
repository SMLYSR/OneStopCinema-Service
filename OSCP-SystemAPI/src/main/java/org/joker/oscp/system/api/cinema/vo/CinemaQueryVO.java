package org.joker.oscp.system.api.cinema.vo;

import lombok.Data;
import org.joker.oscp.system.api.cinema.vo.enums.CinemaQueryEnum;

import java.io.Serializable;

/**
 * 影院查询条件VO
 * @author JOKER
 */
@Data
public class CinemaQueryVO implements Serializable {
    /**
     * 品牌ID
     */
    private Integer brandId = CinemaQueryEnum.BRAND_ID.getValue();
    /**
     * 区域ID
     */
    private Integer districtId = CinemaQueryEnum.DIRSTRICT_ID.getValue();
    /**
     * 影厅ID
     */
    private Integer hallType = CinemaQueryEnum.HALL_TYPE.getValue();
    private Integer pageSize = CinemaQueryEnum.PAGE_SIZE.getValue();
    private Integer nowPage = CinemaQueryEnum.NOW_PAGE.getValue();
}
