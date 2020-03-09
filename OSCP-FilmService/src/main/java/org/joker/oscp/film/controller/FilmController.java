package org.joker.oscp.film.controller;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.film.service.CommunityFeignedApi;
import org.joker.oscp.system.api.community.vo.LatestCommunityInfo;
import org.joker.oscp.system.api.film.FilmServiceApi;
import org.joker.oscp.system.api.film.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 影片服务
 *
 * @author JOKER
 */
@Slf4j
@RestController
@RequestMapping
public class FilmController {

    private FilmServiceApi filmServiceApi;
    private CommunityFeignedApi communityFeignedApi;

    @Autowired
    public FilmController(FilmServiceApi filmServiceApi, CommunityFeignedApi communityFeignedApi) {
        this.filmServiceApi = filmServiceApi;
        this.communityFeignedApi = communityFeignedApi;
    }

    /**
     * 返回首页所需信息
     *
     * @return {@link FilmIndexVO}
     */
    @RequestMapping(value = "/getIndex", method = RequestMethod.GET)
    public CommonResult getIndex() {

        FilmIndexVO filmIndexVO = new FilmIndexVO();
        // 获取轮播
        filmIndexVO.setBanners(filmServiceApi.getBanners());
        // 获取热映影片
        filmIndexVO.setHotFilms(filmServiceApi.getHotFilms(true, 4, 1, 1, 99L, 99L, 99L));
        // 获取新片预告
        filmIndexVO.setNewTrailers(filmServiceApi.getNewTrailer());
        // TODO: 2020/2/15  获取社区信息
        // 待社区模块开发完成，远程调用
        LatestCommunityInfo communityInfoByIndex = communityFeignedApi.getCommunityInfoByIndex();
        filmIndexVO.setFilmReviewVOList(communityInfoByIndex.getFilmReviewVOList());
        filmIndexVO.setActivityVO(communityInfoByIndex.getActivityVO());
        return CommonResult.success(filmIndexVO);
    }

    /**
     * 返回电影筛选列表
     *
     * @param catId    类型
     * @param sourceId 片源
     * @param yearId   年代
     * @return {@link FilmConditionVO}
     */
    @RequestMapping(value = "/getConditionList", method = RequestMethod.GET)
    public CommonResult getConditionList(@RequestParam(name = "catId", required = false, defaultValue = "99") Long catId,
                                         @RequestParam(name = "sourceId", required = false, defaultValue = "99") Long sourceId,
                                         @RequestParam(name = "yearId", required = false, defaultValue = "99") Long yearId) {
        FilmConditionVO filmConditionVO = filmServiceApi.getFilmConditionService(catId, sourceId, yearId);
        return CommonResult.success(filmConditionVO);
    }

    @RequestMapping(value = "/getFilms", method = RequestMethod.GET)
    public CommonResult getFilms(FilmRequestVO filmRequestVO) {
        FilmVO filmVO = null;
        // 根据showType判断影片查询的类型
        switch (filmRequestVO.getShowType()) {
            case 1:
                filmVO = filmServiceApi.getHotFilms(
                        false, filmRequestVO.getPageSize(), filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(), filmRequestVO.getSourceId(), filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
            case 2:
                filmVO = filmServiceApi.getSoonFilms(
                        filmRequestVO.getPageSize(), filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(), filmRequestVO.getSourceId(), filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
            case 3:
                filmVO = filmServiceApi.getClassicFilms(
                        filmRequestVO.getPageSize(), filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(), filmRequestVO.getSourceId(),
                        filmRequestVO.getYearId(), filmRequestVO.getCatId());
                break;
            default:
                filmVO = filmServiceApi.getHotFilms(
                        false, filmRequestVO.getPageSize(), filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(), filmRequestVO.getSourceId(), filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
        }
        return CommonResult.success(filmVO.getNowPage(), filmVO.getTotalPage(), filmVO.getFilmInfoVO());
    }

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public CommonResult films(FilmRequestTypeVo filmRequestTypeVo) {

        // 根据searchType，判断查询类型
        FilmDetailVO filmDetail = filmServiceApi.getFilmDetail(filmRequestTypeVo.getSearchType(),
                                                                filmRequestTypeVo.getSearchParam());
        // 不同的查询类型，传入的条件不同
        Long filmId = filmDetail.getFilmId();

        FilmDescVO filmDescVO = filmServiceApi.getFilmDesc(filmId);

        ImgVO imgVO = filmServiceApi.getImgs(filmId);

        ActorVO directorVO = filmServiceApi.getDectInfo(filmId);

        List<ActorVO> actorVOList = filmServiceApi.getActors(filmId);
        // 组织info对象
        InfoRequstVO infoRequstVO = new InfoRequstVO();

        ActorRequestVO actorRequestVO = new ActorRequestVO();
        actorRequestVO.setActors(actorVOList);
        actorRequestVO.setDirector(directorVO);

        infoRequstVO.setActors(actorRequestVO);
        infoRequstVO.setBiography(filmDescVO.getBiography());
        infoRequstVO.setFilmId(filmId);
        infoRequstVO.setImgs(imgVO);

        filmDetail.setInfo04(infoRequstVO);

        return CommonResult.success(filmDetail);
    }
}
