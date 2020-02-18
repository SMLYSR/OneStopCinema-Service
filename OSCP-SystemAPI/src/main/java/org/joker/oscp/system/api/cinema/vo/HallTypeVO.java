package org.joker.oscp.system.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 院厅类型VO
 * @author JOKER
 */
@Data
public class HallTypeVO implements Serializable {

    private String halltypeId;
    private String halltypeName;
    private boolean isActive;


}
