package org.joker.oscp.system.api.film.vo;

import lombok.Data;

/**
 * 查询影片接口VO
 * @author JOKER
 */
@Data
public class FilmRequestVO {
    private Integer showType=1;
    private Integer sortId=1;
    private Long sourceId=99L;
    private Long catId=99L;
    private Long yearId=99L;
    private Integer nowPage=1;
    private Integer pageSize=18;
}
