package org.joker.oscp.system.api.community;

import org.joker.oscp.system.api.community.vo.ActivityReplyVO;
import org.joker.oscp.system.api.community.vo.ActivityVO;
import org.joker.oscp.system.api.community.vo.FilmReviewVO;

import java.util.List;

/**
 * 社区服务Api接口
 * @author JOKER
 */

public interface CommunityServiceApi {


    /**
     * 创建影评
     * @param filmReviewVO
     * @return 创建成功返回true
     */
    boolean createFilmReview(FilmReviewVO filmReviewVO);

    /**
     * 删除影评
     * @param uuid 影评编号
     * @return 删除成功返回true
     */
    boolean deleteFilmReview(Long uuid);


    /**
     * 获取最新社区活动
     * @return {@link ActivityVO}
     */
    ActivityVO getLatestActivityForIndex();


    /**
     * 获取活动先关所有的回复
     * @return {@link ActivityReplyVO}
     */
    List<ActivityReplyVO> getAllActivityReply(Long uuid);

    /**
     * 参与发布社区活动
     * @return
     */
    boolean publishActivity(Long uuid);

}
