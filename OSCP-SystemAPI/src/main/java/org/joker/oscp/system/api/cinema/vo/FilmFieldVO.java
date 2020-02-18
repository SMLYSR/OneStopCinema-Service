package org.joker.oscp.system.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 场次信息VO
 * @author JOKER
 */
@Data
public class FilmFieldVO implements Serializable {

    private String fieldId;
    private String beginTime;
    private String endTime;
    private String language;
    private String hallName;
    private String price;


}
