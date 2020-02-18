package org.joker.oscp.system.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 区域信息
 * @author JOKER
 */
@Data
public class SourceVO implements Serializable {

    private Long sourceId;
    private String sourceName;
    private boolean isActive;

}
