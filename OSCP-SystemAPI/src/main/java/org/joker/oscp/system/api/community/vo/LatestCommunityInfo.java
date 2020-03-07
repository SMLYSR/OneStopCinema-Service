package org.joker.oscp.system.api.community.vo;

import lombok.Data;
import org.joker.oscp.system.api.film.vo.ActorVO;

import java.util.List;

/**
 * 最新社区信息
 * @author JOKER
 */
@Data
public class LatestCommunityInfo {

    private List<FilmReviewVO> filmReviewVOList;
    private ActivityVO activityVO;
}
