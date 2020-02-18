package org.joker.oscp.system.api.film;

import org.joker.oscp.system.api.film.vo.*;

import java.util.List;

/**
 * 影片服务Api接口
 * @author JOKER
 */
public interface FilmServiceApi {

    /**
     * 获取banners
     * @return {@link BannerVO}列表
     */
    List<BannerVO> getBanners();

    /**
     * 获取热映影片
     * @param isLimit 是否是首页标记
     * @param nums 分页条数
     * @param nowPage 当前页
     * @param sortId 排序查询标记值
     * @param sourceId 区域查询标记值
     * @param yearId 按年代查询标记值
     * @param catId 类型查询标记值
     * @return {@link FilmVO}
     */
    FilmVO getHotFilms(boolean isLimit, int nums,int nowPage,int sortId,Long sourceId,Long yearId,Long catId);


    /**
     * 获取即将上映的影片
     * @param nums 分页条数
     * @param nowPage 当前页
     * @param sortId 排序查询标记值
     * @param sourceId 区域查询标记值
     * @param yearId 按年代查询标记值
     * @param catId 类型查询标记值
     * @return {@link FilmVO}
     */
    FilmVO getSoonFilms(int nums, int nowPage, int sortId, Long sourceId, Long yearId, Long catId);

    /**
     * 获取经典影片
     * @param nums 分页条数
     * @param nowPage 当前页
     * @param sortId 排序查询标记值
     * @param sourceId 区域查询标记值
     * @param yearId 按年代查询标记值
     * @param catId 类型查询标记值
     * @return {@link FilmVO}
     */
    FilmVO getClassicFilms(int nums,int nowPage,int sortId,Long sourceId,Long yearId,Long catId);

    /**
     * 获取新片预告
     * @return {@link NewTrailerVO}
     */
    List<NewTrailerVO> getNewTrailer();

    // 获取影片条件接口

    /**
     * 获取影片类型列表
     * @return {@link CatVO}列表
     */
    List<CatVO> getCats();

    /**
     * 获取区域信息列表
     * @return {@link SourceVO}列表
     */
    List<SourceVO> getSources();

    /**
     * 获取年代信息列表
     * @return {@link YearVO}列表
     */
    List<YearVO> getYears();

    /**
     * 获取影片描述信息
     * @param filmId
     * @return {@link FilmDescVO}
     */
    FilmDescVO getFilmDesc(Long filmId);

    /**
     * 根据影片ID或名称获取信息
     * @param searchType 类型
     * @param searchParam 信息
     * @return
     */
    FilmDetailVO getFilmDetail(int searchType, String searchParam);

    /**
     * 获取图片信息
     * @param filmId
     * @return {@link ImgVO}列表
     */
    ImgVO getImgs(Long filmId);

    /**
     * 获取导演信息
     * @param filmId
     * @return {@link ActorVO}
     */
    ActorVO getDectInfo(Long filmId);

    /**
     * 获取演员信息
     * @param filmId
     * @return {@link ActorVO}列表
     */
    List<ActorVO> getActors(Long filmId);

    /**
     * 【封装业务】 获取影片类型列表
     * @param catId 类型
     * @param sourceId 片源
     * @param yearId 年代
     * @return {@link FilmConditionVO}
     */
    FilmConditionVO getFilmConditionService(Long catId, Long sourceId, Long yearId);
}
