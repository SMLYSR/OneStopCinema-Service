package org.joker.oscp.cinema.controller;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.cinema.vo.CinemaConditionResponseVO;
import org.joker.oscp.cinema.vo.CinemaFieldResponseVO;
import org.joker.oscp.cinema.vo.CinemaFieldsResponseVO;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.system.api.cinema.CinemaServiceApi;
import org.joker.oscp.system.api.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 影院服务对外接口
 * @author JOKER
 */
@Slf4j
@RestController
public class CinemaController {

    private CinemaServiceApi cinemaServiceApi;

    @Autowired
    public CinemaController(CinemaServiceApi cinemaServiceApi) {
        this.cinemaServiceApi = cinemaServiceApi;
    }

    @RequestMapping(value = "/getCinemas", method = RequestMethod.GET)
    public CommonResult getCinemas(CinemaQueryVO cinemaQueryVO) {
        try {
            Page<CinemaVO> cinemas = cinemaServiceApi.getCinemas(cinemaQueryVO);
            if (cinemas.getRecords() == null || cinemas.getRecords().size() == 0) {
                return CommonResult.success("没有影院可查");
            } else {
                return CommonResult.success(cinemas.getCurrent(),(int)cinemas.getPages(),cinemas.getRecords());
            }
        } catch (Exception e) {
            log.error("获取影院列表异常",e);
            return CommonResult.failed("查询影院列表失败");
        }
    }

    @RequestMapping(value = "/getCondition", method = RequestMethod.GET)
    public CommonResult getCondition(CinemaQueryVO cinemaQueryVO) {
        try {
            //获取三个集合，组合成一个对象
            List<BrandVO> brands = cinemaServiceApi.getBrands(cinemaQueryVO.getBrandId().longValue());
            List<AreaVO> areas = cinemaServiceApi.getAreas(cinemaQueryVO.getDistrictId().longValue());
            List<HallTypeVO> hallTypes = cinemaServiceApi.getHallTypes(cinemaQueryVO.getHallType().longValue());

            CinemaConditionResponseVO cinemaConditionResponseVO = new CinemaConditionResponseVO();
            cinemaConditionResponseVO.setAreaList(areas);
            cinemaConditionResponseVO.setBrandList(brands);
            cinemaConditionResponseVO.setHallTypeList(hallTypes);
            return CommonResult.success(cinemaConditionResponseVO);
        } catch (Exception e) {
            log.error("获取条件列表失败", e);
            return CommonResult.failed("获取影院查询条件失败");
        }
    }

    @RequestMapping(value = "/getFields", method = RequestMethod.GET)
    public CommonResult getFields(Long cinemaId) {
        try {
            CinemaInfoVO cinemaInfoById = cinemaServiceApi.getCinemaInfoById(cinemaId);
            List<FilmInfoVO> filmInfoByCinemaId = cinemaServiceApi.getFilmInfoByCinemaId(cinemaId);
            CinemaFieldsResponseVO cinemaFieldsResponseVO = new CinemaFieldsResponseVO();
            cinemaFieldsResponseVO.setCinemaInfo(cinemaInfoById);
            cinemaFieldsResponseVO.setFilmList(filmInfoByCinemaId);
            return CommonResult.success(cinemaFieldsResponseVO);
        } catch (Exception e) {
            log.error("获取播放场次失败",e);
            return CommonResult.failed("获取播放场次失败");
        }
    }

    @RequestMapping(value = "/getFieldInfo", method = RequestMethod.POST)
    public CommonResult getFieldInfo(Long cinemaId, Long fieldId) {
        try {
            CinemaInfoVO cinemaInfoById = cinemaServiceApi.getCinemaInfoById(cinemaId);
            FilmInfoVO filmInfoByFieldId = cinemaServiceApi.getFilmInfoByFieldId(fieldId);
            HallInfoVO filmFieldInfo = cinemaServiceApi.getFilmFieldInfo(fieldId);

            // TODO: 2020/2/18 暂时制造订单数据，后期接入订单中心
            filmFieldInfo.setSoldSeats("1,2,3");
            CinemaFieldResponseVO cinemaFieldResponseVO = new CinemaFieldResponseVO();
            cinemaFieldResponseVO.setCinemaInfo(cinemaInfoById);
            cinemaFieldResponseVO.setFilmInfo(filmInfoByFieldId);
            cinemaFieldResponseVO.setHallInfo(filmFieldInfo);

            return CommonResult.success(cinemaFieldResponseVO);
        } catch (Exception e) {
            log.error("获取选座信息失败",e);
            return CommonResult.failed("获取选座信息失败");
        }
    }
}
