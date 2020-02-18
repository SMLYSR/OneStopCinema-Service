package org.joker.oscp.film.dao;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.film.entity.*;
import org.joker.oscp.system.api.film.vo.FilmDetailVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmDaoTest {
    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private NewTrailerMapper newTrailerMapper;

    @Test
    public void insertActor() {
        Actor actor = new Actor();
        actor.setActorName("徐峥");
        actor.setActorImg("actors/2b98c9d2e6d23a7eff25dcac8b584b0136045.jpg");
        actorMapper.insert(actor);
    }

    @Test
    public void insertFilm() {
        String str = "2019-12-13";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(str, fmt);

        Film film = new Film();
        film.setFilmName("误杀");
        film.setFilmSource(1L);
        film.setFilmType(0);
        film.setFilmCats("#4#6#9");
        film.setFilmArea(1L);
        film.setFilmDate(14L);
        film.setFilmTime(date);
        film.setFilmPresalenum(331112491);
        film.setFilmBoxOffice(129600);
        film.setFilmScore("7.7");
        film.setFilmStatus(1);
        film.setImgAddress("http://img5.mtime.cn/mg/2019/12/06/151725.72056401_270X405X4.jpg");

        filmMapper.insert(film);
    }

    @Test
    public void insertFilmInfo() {
        FilmInfo filmInfo = new FilmInfo();
        filmInfo.setFilmId(1227113293607243777L);
        filmInfo.setFilmEnName("Sheep Without a Shepherd");
        filmInfo.setFilmScore("7.7");
        filmInfo.setFilmScoreNum(2100);
        filmInfo.setFilmLength(127);
        filmInfo.setDirectorId(1L);
        filmInfo.setFilmImgs("http://img5.mtime.cn/pi/2019/10/31/103551.19027921_235X235.jpg,http://img5.mtime.cn/pi/2019/11/21/155316.57053042_235X235.jpg,films/4633dd44c51ff15fc7e939679d7cdb67561602.jpg,films/df2d30b1a3bd58fb1d38b978662ae844648169.jpg,films/c845f6b04aa49059951fd55e6b0eddac454036.jpg");
        filmInfo.setBiography("李维杰与妻子阿玉打拼多年，膝下育有两个女儿。一个雨夜，一场意外，打破了这个家庭的宁静。而李维杰作为一个父亲，为了保全自己的家人，他不顾一切地决定瞒天过海……");

        filmInfoMapper.insert(filmInfo);
    }

    @Test
    public void insertBanner() {
        Banner banner = new Banner();
        banner.setBannerAddress("http://img5.mtime.cn/mg/2019/12/05/094700.35622697.jpg");
        banner.setBannerUrl("www.meetingshop.cn");
        bannerMapper.insert(banner);
    }

    @Test
    public void inserTrailer() {
        NewTrailer newTrailer = new NewTrailer();
        newTrailer.setTrailerName("黑寡妇");
        newTrailer.setTrailerContext("《黑寡妇》首款中文预告");
        newTrailer.setTrailerTime("2020中国上映");
        newTrailer.setTrailerImg("http://img5.mtime.cn/mg/2019/12/06/112918.19537827.jpg");
        newTrailer.setTrailerVideo("http://img5.mtime.cn/mg/2019/12/06/112918.19537827.jpg");
        newTrailer.setTrailerUrl("www.video.com");

        newTrailerMapper.insert(newTrailer);
    }

    @Test
    public void testFilmMapperByName() {
        FilmDetailVO filmDetailVOByName = filmMapper.getFilmDetailByName("我不是药神");
        log.info("rs: {}",filmDetailVOByName);
    }

    @Test
    public void testFilmMapperById() {
        FilmDetailVO filmDetailVOById = filmMapper.getFilmDetailById(1227069975103971329L);
        log.info("rs: {}",filmDetailVOById);
    }
}
