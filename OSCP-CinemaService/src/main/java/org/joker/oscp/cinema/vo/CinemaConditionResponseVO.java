package org.joker.oscp.cinema.vo;

import lombok.Data;
import org.joker.oscp.system.api.cinema.vo.AreaVO;
import org.joker.oscp.system.api.cinema.vo.BrandVO;
import org.joker.oscp.system.api.cinema.vo.HallTypeVO;

import java.util.List;

/**
 * 【组合类】
 * @author JOKER
 */
@Data
public class CinemaConditionResponseVO {
    private List<BrandVO> brandList;
    private List<AreaVO> areaList;
    private List<HallTypeVO> hallTypeList;
}
