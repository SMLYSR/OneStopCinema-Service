package org.joker.oscp.system.api.film.vo;

import lombok.Data;

/**
 * 根据影片类型查询
 * @author JOKER
 */
@Data
public class FilmRequestTypeVo {
    private String searchParam;
    private int searchType;
}
