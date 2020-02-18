package org.joker.oscp.system.api.film.vo;

import lombok.Data;

import java.util.List;

/**
 * 影片列表集合VO
 * @author JOKER
 */
@Data
public class FilmConditionVO {
    private List<CatVO> catInfo;
    private List<SourceVO> sourceInfo;
    private List<YearVO> yearInfo;
}
