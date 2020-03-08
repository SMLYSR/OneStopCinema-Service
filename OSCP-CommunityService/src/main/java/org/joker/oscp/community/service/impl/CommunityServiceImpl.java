package org.joker.oscp.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.community.dao.ActivityReplyTMapper;
import org.joker.oscp.community.dao.ActivityTMapper;
import org.joker.oscp.community.dao.FilmReviewTMapper;
import org.joker.oscp.community.entity.ActivityReplyT;
import org.joker.oscp.community.entity.ActivityT;
import org.joker.oscp.community.entity.FilmReviewT;
import org.joker.oscp.system.api.community.CommunityServiceApi;
import org.joker.oscp.system.api.community.vo.ActivityReplyVO;
import org.joker.oscp.system.api.community.vo.ActivityVO;
import org.joker.oscp.system.api.community.vo.FilmReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 社区服务API实现类 </p>
 * @author JOKER
 */
@Slf4j
@Component
public class CommunityServiceImpl implements CommunityServiceApi {

    private FilmReviewTMapper filmReviewTMapper;
    private ActivityTMapper activityTMapper;
    private ActivityReplyTMapper activityReplyTMapper;

    @Autowired
    public CommunityServiceImpl(FilmReviewTMapper filmReviewTMapper, ActivityTMapper activityTMapper,
                                ActivityReplyTMapper activityReplyTMapper) {
        this.filmReviewTMapper = filmReviewTMapper;
        this.activityTMapper = activityTMapper;
        this.activityReplyTMapper = activityReplyTMapper;
    }

    @Override
    public boolean createFilmReview(FilmReviewVO filmReviewVO) {
        if (filmReviewVO != null) {
            FilmReviewT filmReviewT = new FilmReviewT();
            filmReviewT.setUserId(filmReviewVO.getUserId());
            filmReviewT.setTitle(filmReviewVO.getTitle());
            filmReviewT.setContentDetail(filmReviewVO.getContentDetail());
            filmReviewT.setContent(filmReviewVO.getContent());
            filmReviewT.setReviewCover(filmReviewVO.getReviewCover());

            int insert = filmReviewTMapper.insert(filmReviewT);

            if (insert > 0) {
                return true;
            } else {
                return false;
            }
        }
        log.info("入参错误");
        return false;
    }

    @Override
    public boolean deleteFilmReview(Long uuid) {
        int i = filmReviewTMapper.deleteById(uuid);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<FilmReviewVO> selectLatestFilmReview() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        List<FilmReviewVO> list = filmReviewTMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public ActivityVO getLatestActivityForIndex() {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        ActivityT activityT = activityTMapper.selectOne(queryWrapper);
        if (activityT != null) {
            ActivityVO activityVO = new ActivityVO();
            activityVO.setTitle(activityT.getTitle());
            activityVO.setContentDetail(activityT.getContentDetail());
            activityVO.setActiveImg(activityT.getActiveImg());
            activityVO.setDateTime(String.valueOf(activityT.getCreateTime()));

            return activityVO;
        }
        log.info("未存在最新活动！！！");
        return null;
    }

    @Override
    public List<ActivityReplyVO> getAllActivityReply(Long activityId) {
        List<ActivityReplyVO> activityReplyVOList = activityReplyTMapper.selectAllActivityReply(activityId);
        return activityReplyVOList;
    }

    @Override
    public boolean publishActivity(ActivityReplyVO activityReplyVO) {
        ActivityReplyT activityReplyT = new ActivityReplyT();
        activityReplyT.setUserId(activityReplyVO.getUserId());
        activityReplyT.setContent(activityReplyVO.getContent());

        int i = activityReplyTMapper.insertAndGetId(activityReplyT);
        if (i > 0) {
            int i1 = activityReplyTMapper.insertActivityUser(activityReplyVO.getActivityId(), activityReplyT.getUuid());
            if(i1 > 0) {
                log.info("参与回复发表成功");
                return true;
            } else {
                log.info("参与回复失败，映射表服务同步！");
                return false;
            }
        } else {
            log.info("参与回复失败！");
            return false;
        }
    }
}
