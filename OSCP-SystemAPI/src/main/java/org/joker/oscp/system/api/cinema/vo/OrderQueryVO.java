package org.joker.oscp.system.api.cinema.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 提供远程调用
 * @author JOKER
 */
@Data
public class OrderQueryVO {
    private Long cinemaId;
    private BigDecimal filmPrice;
}
