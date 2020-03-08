package org.joker.oscp.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.joker.oscp.community.entity.ActivityReplyT;
import org.joker.oscp.system.api.community.vo.ActivityReplyVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 活动回复内容
 * @author JOKER
 */
@Repository
public interface ActivityReplyTMapper extends BaseMapper<ActivityReplyT> {

    /**
     * 查询所有活动回复
     * @param  uuid 活动主题Id
     * @return
     */
    List<ActivityReplyVO> selectAllActivityReply(@Param("uuid") Long uuid);

    /**
     * 插入ActivityUser映射记录
     * @param activityId 活动Id
     * @param replyId 活动Id
     * @return
     */
    int insertActivityUser(Long activityId, Long replyId);

    /**
     * 插入活动回复后获得Id
     * @param activityReplyT
     * @return
     */
    int insertAndGetId(ActivityReplyT activityReplyT);

}
