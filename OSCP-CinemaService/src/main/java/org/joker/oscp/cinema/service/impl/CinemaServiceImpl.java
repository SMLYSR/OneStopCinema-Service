package org.joker.oscp.cinema.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.joker.oscp.cinema.dao.*;
import org.joker.oscp.cinema.entity.AreaDictT;
import org.joker.oscp.cinema.entity.BrandDictT;
import org.joker.oscp.cinema.entity.CinemaT;
import org.joker.oscp.cinema.entity.HallDictT;
import org.joker.oscp.system.api.cinema.CinemaServiceApi;
import org.joker.oscp.system.api.cinema.vo.*;
import org.joker.oscp.system.api.cinema.vo.enums.CinemaQueryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>CinemaServiceApi实现类</p>
 *
 * @author JOKER
 */
@Component
public class CinemaServiceImpl implements CinemaServiceApi {

    private CinemaTMapper cinemaTMapper;
    private AreaDictTMapper areaDictTMapper;
    private BrandDictTMapper brandDictTMapper;
    private HallDictTMapper hallDictTMapper;
    private HallFilmInfoTMapper hallFilmInfoTMapper;
    private FieldTMapper fieldTMapper;

    @Autowired
    public CinemaServiceImpl(CinemaTMapper cinemaTMapper, AreaDictTMapper areaDictTMapper,
                             BrandDictTMapper brandDictTMapper, HallDictTMapper hallDictTMapper,
                             HallFilmInfoTMapper hallFilmInfoTMapper, FieldTMapper fieldTMapper) {
        this.cinemaTMapper = cinemaTMapper;
        this.areaDictTMapper = areaDictTMapper;
        this.brandDictTMapper = brandDictTMapper;
        this.hallDictTMapper = hallDictTMapper;
        this.hallFilmInfoTMapper = hallFilmInfoTMapper;
        this.fieldTMapper = fieldTMapper;

    }

    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO) {
        List<CinemaVO> cinemaQueryVOS = new ArrayList<>();
        Page<CinemaT> page = new Page<>(cinemaQueryVO.getNowPage(), cinemaQueryVO.getPageSize());
        EntityWrapper<CinemaT> entityWrapper = new EntityWrapper<>();

        if (cinemaQueryVO.getBrandId() != CinemaQueryEnum.BRAND_ID.getValue()) {
            entityWrapper.eq("brand_id", cinemaQueryVO.getBrandId());
        }
        if (cinemaQueryVO.getDistrictId() != CinemaQueryEnum.DIRSTRICT_ID.getValue()) {
            entityWrapper.eq("area_id", cinemaQueryVO.getDistrictId());
        }
        if (cinemaQueryVO.getHallType() != CinemaQueryEnum.HALL_TYPE.getValue()) {
            entityWrapper.like("hall_ids", "%#+" + cinemaQueryVO.getHallType() + "+#%");
        }

        List<CinemaT> cinemaTS = cinemaTMapper.selectPage(page, entityWrapper);
        cinemaTS.stream().forEach(cts -> {
            CinemaVO cinemaVO = new CinemaVO();
            cinemaVO.setUuid(cts.getUuid());
            cinemaVO.setMinimumPrice(cts.getMinimumPrice() + "");
            cinemaVO.setAddress(cts.getCinemaAddress());
            cinemaVO.setCinemaName(cts.getCinemaName());
            cinemaQueryVOS.add(cinemaVO);
        });
        long counts = cinemaTMapper.selectCount(entityWrapper);
        Page<CinemaVO> rs = new Page<>();
        rs.setRecords(cinemaQueryVOS);
        rs.setSize(cinemaQueryVO.getPageSize());
        rs.setTotal(counts);
        return rs;
    }

    @Override
    public List<BrandVO> getBrands(Long brandId) {
        boolean flag = false;
        List<BrandVO> brandVOS = new ArrayList<>();
        BrandDictT brandDictT = brandDictTMapper.selectById(brandId);

        if (brandId == CinemaQueryEnum.BRAND_ID.getValue() || brandDictT == null || brandDictT.getUuid() == null) {
            flag = true;
        }
        // 查询所有列表
        List<BrandDictT> brandDictTS = brandDictTMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for (BrandDictT brand : brandDictTS) {
            BrandVO brandVO = new BrandVO();
            brandVO.setBrandName(brand.getShowName());
            brandVO.setBrandId(brand.getUuid());
            // 如果flag为true，则需要99，如为false，则匹配上的内容为active
            if (flag) {
                if (brand.getUuid() == CinemaQueryEnum.HALL_TYPE.getValue()) {
                    brandVO.setActive(true);
                }
            } else {
                if (brand.getUuid().equals(brandId)) {
                    brandVO.setActive(true);
                }
            }
            brandVOS.add(brandVO);
        }
        return brandVOS;
    }

    @Override
    public List<AreaVO> getAreas(Long areaId) {
        boolean flag = false;
        List<AreaVO> areaVOS = new ArrayList<>();
        // 判断brandId是否存在
        AreaDictT AreaDictT = areaDictTMapper.selectById(areaId);
        // 判断brandId 是否等于 99
        if(areaId == CinemaQueryEnum.AREA_ID.getValue() || AreaDictT==null || AreaDictT.getUuid() == null){
            flag = true;
        }
        // 查询所有列表
        List<AreaDictT> areaDictTS = areaDictTMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for(AreaDictT area : areaDictTS){
            AreaVO areaVO = new AreaVO();
            areaVO.setAreaName(area.getShowName());
            areaVO.setAreaId(area.getUuid()+"");
            // 如果flag为true，则需要99，如为false，则匹配上的内容为active
            if(flag){
                if(area.getUuid() == 99){
                    areaVO.setActive(true);
                }
            }else{
                if(area.getUuid().equals(areaId)){
                    areaVO.setActive(true);
                }
            }
            areaVOS.add(areaVO);
        }
        return areaVOS;
    }

    @Override
    public List<HallTypeVO> getHallTypes(Long hallType) {
        boolean flag = false;
        List<HallTypeVO> hallTypeVOS = new ArrayList<>();
        // 判断brandId是否存在
        HallDictT moocHallDictT = hallDictTMapper.selectById(hallType);
        // 判断brandId 是否等于 99
        if(hallType == 99 || moocHallDictT==null || moocHallDictT.getUuid() == null){
            flag = true;
        }
        // 查询所有列表
        List<HallDictT> hallDictTS = hallDictTMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for(HallDictT hall : hallDictTS){
            HallTypeVO hallTypeVO = new HallTypeVO();
            hallTypeVO.setHalltypeName(hall.getShowName());
            hallTypeVO.setHalltypeId(hall.getUuid()+"");
            // 如果flag为true，则需要99，如为false，则匹配上的内容为active
            if(flag){
                if(hall.getUuid() == 99){
                    hallTypeVO.setActive(true);
                }
            }else{
                if(hall.getUuid().equals(hallType)){
                    hallTypeVO.setActive(true);
                }
            }
            hallTypeVOS.add(hallTypeVO);
        }
        return hallTypeVOS;
    }

    @Override
    public CinemaInfoVO getCinemaInfoById(Long cinemaId) {
        CinemaT cinemaT = cinemaTMapper.selectById(cinemaId);
        if (cinemaT == null) {
            return new CinemaInfoVO();
        }
        CinemaInfoVO cinemaInfoVO = new CinemaInfoVO();
        cinemaInfoVO.setImgUrl(cinemaT.getImgAddress());
        cinemaInfoVO.setCinemaPhone(cinemaT.getCinemaPhone());
        cinemaInfoVO.setCinemaName(cinemaT.getCinemaName());
        cinemaInfoVO.setCinemaId(cinemaT.getUuid());
        return cinemaInfoVO;
    }

    @Override
    public List<FilmInfoVO> getFilmInfoByCinemaId(Long cinemaId) {
        List<FilmInfoVO> filmInfoVOS = fieldTMapper.getFilmInfos(cinemaId);
        return filmInfoVOS;
    }

    @Override
    public HallInfoVO getFilmFieldInfo(Long fieldId) {
        HallInfoVO hallInfoVO = fieldTMapper.getHallInfo(fieldId);
        return hallInfoVO;
    }

    @Override
    public FilmInfoVO getFilmInfoByFieldId(Long fieldId) {
        FilmInfoVO filmInfoVO = fieldTMapper.getFilmInfoById(fieldId);
        return filmInfoVO;
    }
}
