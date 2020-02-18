package org.joker.oscp.system.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 区域信息VO
 * @author JOKER
 */
@Data
public class AreaVO implements Serializable {

    private String areaId;
    private String areaName;
    private boolean isActive;


}
