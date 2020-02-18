package org.joker.oscp.cinema.vo;

import lombok.Data;
import org.joker.oscp.system.api.cinema.vo.CinemaInfoVO;
import org.joker.oscp.system.api.cinema.vo.FilmInfoVO;

import java.util.List;


/**
 * 【组合类】
 *
 * @author JOKER
 */
@Data
public class CinemaFieldsResponseVO {

    private CinemaInfoVO cinemaInfo;
    private List<FilmInfoVO> filmList;
}
