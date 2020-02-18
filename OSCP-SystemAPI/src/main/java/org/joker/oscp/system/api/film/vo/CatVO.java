package org.joker.oscp.system.api.film.vo;

import lombok.Data;

/**
 * 影片类型
 * @author JOKER
 */
@Data
public class CatVO {

    private Long catId;
    private String catName;
    private boolean isActive;
}
