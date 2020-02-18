package org.joker.oscp.system.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 影片信息VO
 * @author JOKER
 */
@Data
public class FilmInfoVO implements Serializable {

    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmType;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private List<FilmFieldVO> filmFields;

}
