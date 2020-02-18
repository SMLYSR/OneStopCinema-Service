package org.joker.oscp.film.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.system.api.film.FilmServiceApi;
import org.joker.oscp.system.api.film.vo.BannerVO;
import org.joker.oscp.system.api.film.vo.CatVO;
import org.joker.oscp.system.api.film.vo.FilmVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmServiceImplTest {

    @Autowired
    private FilmServiceApi filmServiceApi;


    @Test
    public void contextLoads() {
        log.info("FilmServiceApi: {}", this.filmServiceApi);
    }

    @Test
    public void testGetHotFilms() {
        boolean isLimit = true;
        int nums = 2;
        int nowPage = 1;
        int sortId = 1;
        Long sourceId = 1L;
        Long yearId = 1L;
        Long carId = 2L;
        FilmVO filmVO = filmServiceApi.getHotFilms(isLimit, nums, nowPage, sortId, sourceId, yearId, carId);
        log.info("filmVo: {}", filmVO);
    }

    @Test
    public void testGetBanners() {
        List<BannerVO> list = filmServiceApi.getBanners();
        list.stream().forEach(b -> {
            log.info("banner: {}", b);
        });
    }

    @Test
    public void testGetCatList() {
        List<CatVO> catVOList = filmServiceApi.getCats();
        catVOList.forEach(c -> {
            log.info("catList: {}",c);
        });
    }

}