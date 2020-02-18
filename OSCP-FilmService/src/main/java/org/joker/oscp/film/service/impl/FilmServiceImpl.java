package org.joker.oscp.film.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.joker.oscp.film.dao.*;
import org.joker.oscp.film.entity.*;
import org.joker.oscp.film.entity.FilmInfo;
import org.joker.oscp.system.api.film.FilmServiceApi;
import org.joker.oscp.system.api.film.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>FilmServiceApi实现类</p>
 *
 * @author JOKER
 */
@Component
public class FilmServiceImpl implements FilmServiceApi {

    private BannerMapper bannerMapper;
    private FilmMapper filmMapper;
    private CatDictMapper catDictMapper;
    private YearDictMapper yearDictMapper;
    private SourceDictMapper sourceDictMapper;
    private FilmInfoMapper filmInfoMapper;
    private ActorMapper actorMapper;
    private NewTrailerMapper newTrailerMapper;

    @Autowired
    public FilmServiceImpl(FilmMapper filmMapper, BannerMapper bannerMapper,
                           CatDictMapper catDictMapper, YearDictMapper yearDictMapper,
                           SourceDictMapper sourceDictMapper, FilmInfoMapper filmInfoMapper,
                           ActorMapper actorMapper, NewTrailerMapper newTrailerMapper) {
        this.bannerMapper = bannerMapper;
        this.filmMapper = filmMapper;
        this.catDictMapper = catDictMapper;
        this.yearDictMapper = yearDictMapper;
        this.sourceDictMapper = sourceDictMapper;
        this.filmInfoMapper = filmInfoMapper;
        this.actorMapper = actorMapper;
        this.newTrailerMapper = newTrailerMapper;
    }

    @Override
    public List<BannerVO> getBanners() {
        List<BannerVO> bannerVOList = new ArrayList<>();
        List<Banner> banners = bannerMapper.selectList(null);

        banners.stream().forEach(banner -> {
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(banner.getUuid());
            bannerVO.setBannerUrl(banner.getBannerAddress());
            bannerVO.setBannerUri(banner.getBannerUrl());
            bannerVOList.add(bannerVO);
        });
        return bannerVOList;
    }

    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums, int nowPage, int sortId, Long sourceId, Long yearId, Long catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfoVO> filmInfos = new ArrayList<>();

        // 热映影片的限制条件
        EntityWrapper<Film> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status", "1");
        // 判断是否是首页需要的内容
        if (isLimit) {
            // 限制条数，限制内容为热映影片
            Page<Film> page = new Page<>(1, nums);
            List<Film> films = filmMapper.selectPage(page, entityWrapper);
            filmInfos = getFilmInfosByList(films);
            filmVO.setFilmNum(films.size());
            filmVO.setFilmInfoVO(filmInfos);
        } else {
            // 如果不是，则是列表页，同样需要限制内容为热映影片
            Page<Film> page = null;
            // 根据sortId的不同，来组织不同的Page对象
            // 1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId) {
                case 1:
                    page = new Page<>(nowPage, nums, "film_box_office");
                    break;
                case 2:
                    page = new Page<>(nowPage, nums, "film_time");
                    break;
                case 3:
                    page = new Page<>(nowPage, nums, "film_score");
                    break;
                default:
                    page = new Page<>(nowPage, nums, "film_box_office");
                    break;
            }
            if (!sourceId.equals(99L)) {
                entityWrapper.eq("film_source", sourceId);
            }
            if (!yearId.equals(99L)) {
                entityWrapper.eq("film_date", yearId);
            }
            if (!catId.equals(99L)) {
                String catStr = "%#" + catId + "#%";
                entityWrapper.like("film_cats", catStr);
            }

            List<Film> films = filmMapper.selectPage(page, entityWrapper);
            // 组织filmInfos
            filmInfos = getFilmInfosByList(films);
            filmVO.setFilmNum(films.size());

            int totalCounts = filmMapper.selectCount(entityWrapper);
            int totalPages = (totalCounts / nums) + 1;

            filmVO.setFilmInfoVO(filmInfos);
            filmVO.setTotalPage(totalPages);
            filmVO.setNowPage(nowPage);
        }
        return filmVO;
    }

    @Override
    public FilmVO getSoonFilms(int nums, int nowPage, int sortId, Long sourceId, Long yearId, Long catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfoVO> filmInfos = new ArrayList<>();
        // 热映影片的限制条件
        EntityWrapper<Film> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status", "2");
        // 如果不是，则是列表页，同样需要限制内容为热映影片
        Page<Film> page = null;
        // 根据sortId的不同，来组织不同的Page对象
        // 1-按热门搜索，2-按时间搜索，3-按评价搜索
        switch (sortId) {
            case 1:
                page = new Page<>(nowPage, nums, "film_preSaleNum");
                break;
            case 2:
                page = new Page<>(nowPage, nums, "film_time");
                break;
            case 3:
                page = new Page<>(nowPage, nums, "film_preSaleNum");
                break;
            default:
                page = new Page<>(nowPage, nums, "film_preSaleNum");
                break;
        }
        if (!sourceId.equals(99L)) {
            entityWrapper.eq("fiml_source", sourceId);
        }
        if (!yearId.equals(99L)) {
            entityWrapper.eq("film_date", yearId);
        }
        if (!catId.equals(99L)) {
            // #2#4#22#
            String catStr = "%#" + catId + "#%";
            entityWrapper.like("film_cats", catStr);
        }

        List<Film> films = filmMapper.selectPage(page, entityWrapper);
        // 组织filmInfos
        filmInfos = getFilmInfosByList(films);
        filmVO.setFilmNum(films.size());

        // 需要总页数 totalCounts/nums -> 0 + 1 = 1
        // 每页10条，我现在有6条 -> 1
        int totalCounts = filmMapper.selectCount(entityWrapper);
        int totalPages = (totalCounts / nums) + 1;

        filmVO.setFilmInfoVO(filmInfos);
        filmVO.setTotalPage(totalPages);
        filmVO.setNowPage(nowPage);
        return filmVO;
    }

    @Override
    public FilmVO getClassicFilms(int nums, int nowPage, int sortId, Long sourceId, Long yearId, Long catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfoVO> filmInfos = new ArrayList<>();
        EntityWrapper<Film> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","3");
        Page<Film> page = null;
        // 根据sortId的不同，来组织不同的Page对象
        // 1-按热门搜索，2-按时间搜索，3-按评价搜索
        switch (sortId){
            case 1 :
                page = new Page<>(nowPage,nums,"film_box_office");
                break;
            case 2 :
                page = new Page<>(nowPage,nums,"film_time");
                break;
            case 3 :
                page = new Page<>(nowPage,nums,"film_score");
                break;
            default:
                page = new Page<>(nowPage,nums,"film_box_office");
                break;
        }

        // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
        if(sourceId != 99){
            entityWrapper.eq("film_source",sourceId);
        }
        if(yearId != 99){
            entityWrapper.eq("film_date",yearId);
        }
        if(catId != 99){
            // #2#4#22#
            String catStr = "%#"+catId+"#%";
            entityWrapper.like("film_cats",catStr);
        }

        List<Film> films = filmMapper.selectPage(page, entityWrapper);
        // 组织filmInfos
        filmInfos = getFilmInfosByList(films);
        filmVO.setFilmNum(films.size());

        // 需要总页数 totalCounts/nums -> 0 + 1 = 1
        // 每页10条，我现在有6条 -> 1
        int totalCounts = filmMapper.selectCount(entityWrapper);
        int totalPages = (totalCounts/nums)+1;

        filmVO.setFilmInfoVO(filmInfos);
        filmVO.setTotalPage(totalPages);
        filmVO.setNowPage(nowPage);

        return filmVO;
    }


    @Override
    public List<NewTrailerVO> getNewTrailer() {
        List<NewTrailer> newTrailerList = newTrailerMapper.selectList(null);
        List<NewTrailerVO> newTrailerVOList = new ArrayList<>();

        newTrailerList.stream().forEach(ntl -> {
            NewTrailerVO newTrailerVO = new NewTrailerVO();
            newTrailerVO.setTrailerContext(ntl.getTrailerContext());
            newTrailerVO.setTrailerImg(ntl.getTrailerImg());
            newTrailerVO.setTrailerVideo(ntl.getTrailerVideo());
            newTrailerVO.setTrailerTime(ntl.getTrailerTime());
            newTrailerVO.setTrailerUrl(ntl.getTrailerUrl());
            newTrailerVOList.add(newTrailerVO);
        });
        return newTrailerVOList;
    }

    @Override
    public List<CatVO> getCats() {
        List<CatVO> cats = new ArrayList<>();
        List<CatDict> catDicts = catDictMapper.selectList(null);

        catDicts.stream().forEach(catDict -> {
            CatVO catVO = new CatVO();
            catVO.setCatId(catDict.getUuid());
            catVO.setCatName(catDict.getShowName());
            cats.add(catVO);
        });
        return cats;
    }

    @Override
    public List<SourceVO> getSources() {
        List<SourceVO> sources = new ArrayList<>();
        List<SourceDict> sourceDicts = sourceDictMapper.selectList(null);

        sourceDicts.stream().forEach(sourceDict -> {
            SourceVO sourceVO = new SourceVO();
            sourceVO.setSourceId(sourceDict.getUuid());
            sourceVO.setSourceName(sourceDict.getShowName());
            sources.add(sourceVO);
        });
        return sources;
    }

    @Override
    public List<YearVO> getYears() {
        List<YearVO> years = new ArrayList<>();
        List<YearDict> yearDicts = yearDictMapper.selectList(null);

        yearDicts.stream().forEach(yearDict -> {
            YearVO yearVO = new YearVO();
            yearVO.setYearId(yearDict.getUuid());
            yearVO.setYearName(yearDict.getShowName());
            years.add(yearVO);
        });
        return years;
    }

    @Override
    public FilmDescVO getFilmDesc(Long filmId) {
        FilmInfo filmInfo = getFilmInfosById(filmId);

        FilmDescVO filmDescVO = new FilmDescVO();
        filmDescVO.setBiography(filmInfo.getBiography());
        filmDescVO.setFilmId(filmId);

        return filmDescVO;
    }

    @Override
    public FilmDetailVO getFilmDetail(int searchType, String searchParam) {
        FilmDetailVO filmDetailVO = null;
        if (searchType == 1) {
            // 按名称查找
            filmDetailVO = filmMapper.getFilmDetailByName("%"+searchParam+"%");
        } else {
            filmDetailVO = filmMapper.getFilmDetailById(Long.parseLong(searchParam));
        }
        return filmDetailVO;
    }

    private FilmInfo getFilmInfo(Long filmId) {
        FilmInfo filmInfo = new FilmInfo();
        filmInfo.setFilmId(filmId);
        filmInfo = filmInfoMapper.selectOne(filmInfo);
        return filmInfo;
    }

    @Override
    public ImgVO getImgs(Long filmId) {

        FilmInfo filmInfo = getFilmInfosById(filmId);
        String filmImgStr = filmInfo.getFilmImgs();
        String[] filmImgs = filmImgStr.split(",");

        ImgVO imgVO = new ImgVO();
        imgVO.setMainImg(filmImgs[0]);
        imgVO.setImg01(filmImgs[1]);
        imgVO.setImg02(filmImgs[2]);
        imgVO.setImg03(filmImgs[3]);
        imgVO.setImg04(filmImgs[4]);

        return imgVO;
    }

    @Override
    public ActorVO getDectInfo(Long filmId) {

        FilmInfo filmInfo = getFilmInfosById(filmId);
        Long directId = filmInfo.getDirectorId();
        Actor actor = actorMapper.selectById(directId);
        ActorVO actorVO = new ActorVO();
        actorVO.setImgAddress(actor.getActorImg());
        actorVO.setDirectorName(actor.getActorName());

        return actorVO;
    }

    @Override
    public List<ActorVO> getActors(Long filmId) {
        List<ActorVO> actorVOS = actorMapper.getActors(filmId);
        return actorVOS;
    }

    private List<FilmInfoVO> getFilmInfosByList(List<Film> films) {
        List<FilmInfoVO> filmInfos = new ArrayList<>();
        films.stream().forEach(film -> {
            FilmInfoVO filmInfo = new FilmInfoVO();
            filmInfo.setScore(film.getFilmScore());
            filmInfo.setImgAddress(film.getImgAddress());
            filmInfo.setFilmType(film.getFilmType());
            filmInfo.setFilmScore(film.getFilmScore());
            filmInfo.setFilmName(film.getFilmName());
            filmInfo.setFilmId(film.getUuid());
            filmInfo.setExpectNum(film.getFilmPresalenum());
            filmInfo.setBoxNum(film.getFilmBoxOffice());
            filmInfo.setShowTime(film.getFilmTime());
            filmInfos.add(filmInfo);
        });
        return filmInfos;
    }

    private FilmInfo getFilmInfosById(Long filmId) {
        FilmInfo filmInfo = new FilmInfo();
        filmInfo.setFilmId(filmId);
        filmInfo = filmInfoMapper.selectOne(filmInfo);
        return filmInfo;
    }

    /**
     * 【封装业务】 获取影片类型列表
     *
     * @param catId    类型
     * @param sourceId 片源
     * @param yearId   年代
     * @return {@link FilmConditionVO}
     */
    @Override
    public FilmConditionVO getFilmConditionService(Long catId, Long sourceId, Long yearId) {
        FilmConditionVO filmConditionVO = new FilmConditionVO();
        //全局标识
        boolean actFlag = false;
        // 获取类型集合
        List<CatVO> cats = getCats();
        List<CatVO> catResult = new ArrayList<>();
        CatVO cat = new CatVO();
        for (CatVO catvos : cats) {
            // 判断集合中是否存在catId，存在则激活状态
            if (catvos.getCatId().equals(99L)) {
                cat = catvos;
                continue;
            }
            if (catvos.getCatId().equals(catId)) {
                actFlag = true;
                catvos.setActive(true);
            } else {
                catvos.setActive(false);
            }
            catResult.add(catvos);
        }
        // 如果不存在，则全部状态为false
        if (!actFlag) {
            cat.setActive(true);
            catResult.add(cat);
        } else {
            cat.setActive(false);
            catResult.add(cat);
        }
        // 获取片源集合
        actFlag = false;
        List<SourceVO> sources = getSources();
        List<SourceVO> sourceResult = new ArrayList<>();
        SourceVO sourceVO = new SourceVO();
        for (SourceVO source : sources) {
            if (source.getSourceId().equals(99L)) {
                sourceVO = source;
                continue;
            }
            if (source.getSourceId().equals(sourceId)) {
                actFlag = true;
                source.setActive(true);
            } else {
                source.setActive(false);
            }
            sourceResult.add(source);
        }
        if (!actFlag) {
            sourceVO.setActive(true);
            sourceResult.add(sourceVO);
        } else {
            sourceVO.setActive(false);
            sourceResult.add(sourceVO);
        }
        // 获取年代集合
        actFlag = false;
        List<YearVO> years = getYears();
        List<YearVO> yearResult = new ArrayList<>();
        YearVO yearVO = new YearVO();
        for (YearVO year : years) {
            if (year.getYearId().equals(99L)) {
                yearVO = year;
                continue;
            }
            if (year.getYearId().equals(yearId)) {
                actFlag = true;
                year.setActive(true);
            } else {
                year.setActive(false);
            }
            yearResult.add(year);
        }
        if (!actFlag) {
            yearVO.setActive(true);
            yearResult.add(yearVO);
        } else {
            yearVO.setActive(false);
            yearResult.add(yearVO);
        }
        // 装载对象
        filmConditionVO.setCatInfo(catResult);
        filmConditionVO.setYearInfo(yearResult);
        filmConditionVO.setSourceInfo(sourceResult);
        return filmConditionVO;
    }
}
