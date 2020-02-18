package org.joker.oscp.system.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 影院信息VO
 * @author JOKER
 */
@Data
public class CinemaVO implements Serializable {

    private Long uuid;
    private String cinemaName;
    private String address;
    private String minimumPrice;

}
