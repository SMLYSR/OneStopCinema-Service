package org.joker.oscp.system.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 电影详细信息VO
 * @author JOKER
 */
@Data
public class CinemaInfoVO implements Serializable {

    private Long cinemaId;
    private String imgUrl;
    private String cinemaName;
    private String cinemaAdress;
    private String cinemaPhone;

}
