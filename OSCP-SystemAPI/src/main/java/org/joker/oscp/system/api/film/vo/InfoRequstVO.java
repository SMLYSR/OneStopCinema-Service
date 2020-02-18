package org.joker.oscp.system.api.film.vo;

import lombok.Data;

@Data
public class InfoRequstVO {

    private String biography;
    private ActorRequestVO actors;
    private ImgVO imgs;
    private Long filmId;

}
