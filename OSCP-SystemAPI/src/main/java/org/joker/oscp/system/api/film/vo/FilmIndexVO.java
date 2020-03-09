package org.joker.oscp.system.api.film.vo;

import lombok.Data;
import org.joker.oscp.system.api.community.vo.ActivityVO;
import org.joker.oscp.system.api.community.vo.FilmReviewVO;

import java.util.List;

/**
 * 首页影片VO
 * @author JOKER
 */
@Data
public class FilmIndexVO {
    private List<BannerVO> banners;
    private FilmVO hotFilms;
    private List<NewTrailerVO> newTrailers;
    private List<FilmReviewVO> filmReviewVOList;
    private ActivityVO activityVO;
}
