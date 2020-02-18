package org.joker.oscp.system.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌VO
 * @author JOKER
 */
@Data
public class BrandVO implements Serializable {

    private Long brandId;
    private String brandName;
    private boolean isActive;


}
