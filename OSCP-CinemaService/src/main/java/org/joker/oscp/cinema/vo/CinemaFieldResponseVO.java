package org.joker.oscp.cinema.vo;

import lombok.Data;
import org.joker.oscp.system.api.cinema.vo.CinemaInfoVO;
import org.joker.oscp.system.api.cinema.vo.FilmInfoVO;
import org.joker.oscp.system.api.cinema.vo.HallInfoVO;



/**
 * 【组合类】
 *
 * @author JOKER
 */
@Data
public class CinemaFieldResponseVO {

    private CinemaInfoVO cinemaInfo;
    private FilmInfoVO filmInfo;
    private HallInfoVO hallInfo;
}
