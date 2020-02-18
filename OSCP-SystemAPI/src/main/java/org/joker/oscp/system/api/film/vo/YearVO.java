package org.joker.oscp.system.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 年代信息
 * @author JOKER
 */
@Data
public class YearVO implements Serializable {

    private Long yearId;
    private String yearName;
    private boolean isActive;

}
