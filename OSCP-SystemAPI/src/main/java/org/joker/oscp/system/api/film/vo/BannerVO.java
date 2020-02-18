package org.joker.oscp.system.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 轮播图VO
 * @author JOKER
 */
@Data
public class BannerVO implements Serializable {

    private Long bannerId;
    private String bannerUrl;
    private String bannerUri;
}
