package org.joker.oscp.cinema.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.cinema.service.fegined.OrderCenterFeigned;
import org.joker.oscp.cinema.vo.CinemaConditionResponseVO;
import org.joker.oscp.cinema.vo.CinemaFieldResponseVO;
import org.joker.oscp.cinema.vo.CinemaFieldsResponseVO;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.common.util.ResultDataConvertValue;
import org.joker.oscp.system.api.cinema.CinemaServiceApi;
import org.joker.oscp.system.api.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 影院服务对外接口
 * @author JOKER
 */
@Slf4j
@RestController
public class CinemaController {
    private final int SUCCESS_FLAG = 200;

    private CinemaServiceApi cinemaServiceApi;
    private OrderCenterFeigned orderCenterFeigned;

    @Autowired
    public CinemaController(CinemaServiceApi cinemaServiceApi, OrderCenterFeigned orderCenterFeigned) {
        this.cinemaServiceApi = cinemaServiceApi;
        this.orderCenterFeigned = orderCenterFeigned;
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

            // 接入订单中心接口
            CommonResult soldCR = orderCenterFeigned.getSoldSeatsByFieldId(fieldId);
            if (soldCR.getCode() == SUCCESS_FLAG) {
                ResultDataConvertValue<String> resultDataConvertValue = new ResultDataConvertValue();
                String soldSeats = resultDataConvertValue.obResultDataConvert(soldCR, new TypeReference<String>() {});
                if (!soldSeats.isEmpty()) {
                    filmFieldInfo.setSoldSeats(soldSeats);
                    CinemaFieldResponseVO cinemaFieldResponseVO = new CinemaFieldResponseVO();
                    cinemaFieldResponseVO.setCinemaInfo(cinemaInfoById);
                    cinemaFieldResponseVO.setFilmInfo(filmInfoByFieldId);
                    cinemaFieldResponseVO.setHallInfo(filmFieldInfo);
                    return CommonResult.success(cinemaFieldResponseVO);
                } else {
                    log.error("查询失败！！！{}",fieldId);
                    return CommonResult.failed("查询失败！");
                }
            }
            // TODO: 2020/2/20 暂时向前端返回错误信息，后期加入熔断降级
            return CommonResult.failed("请求订单中心失败，服务降级！！！");
        } catch (Exception e) {
            log.error("获取选座信息失败",e);
            return CommonResult.failed("获取选座信息失败");
        }
    }

    /**
     * <p>远程调用暴露接口</p>
     */
    @RequestMapping(value = "/getFilmInfoByFieldId")
    public CommonResult getFilmInfoByFieldId(@RequestParam(value = "fieldId")Long fieldId) {
        FilmInfoVO filmInfoVO = cinemaServiceApi.getFilmInfoByFieldId(fieldId);
        return CommonResult.success(filmInfoVO);
    }

    @RequestMapping(value = "/getOrderNeeds")
    public CommonResult getOrderNeeds(@RequestParam(value = "fieldId")Long fieldId) {
        OrderQueryVO orderNeeds = cinemaServiceApi.getOrderNeeds(fieldId);
        return CommonResult.success(orderNeeds);
    }
}
