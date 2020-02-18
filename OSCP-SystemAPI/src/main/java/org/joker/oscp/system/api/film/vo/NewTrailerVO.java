package org.joker.oscp.system.api.film.vo;

import lombok.Data;

/**
 * 新片预告VO
 * @author JOKER
 */
@Data
public class NewTrailerVO {

    private Long uuid;

    private String trailerName;

    private String trailerContext;

    private String trailerImg;

    private String trailerVideo;

    private String trailerTime;

    private String trailerUrl;
}
