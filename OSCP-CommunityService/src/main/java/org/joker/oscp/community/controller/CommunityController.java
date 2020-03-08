package org.joker.oscp.community.controller;

import org.joker.oscp.common.CommonResult;
import org.joker.oscp.system.api.community.CommunityServiceApi;
import org.joker.oscp.system.api.community.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 社区前端控制器
 *
 * @author JOKER
 */
@RestController
public class CommunityController {

    private CommunityServiceApi communityServiceApi;

    @Autowired
    public CommunityController(CommunityServiceApi communityServiceApi) {
        this.communityServiceApi = communityServiceApi;
    }


    /**
     * 发表影评
     *
     * @param filmReviewVO 文章详细参数
     * @return
     */
    @PostMapping(value = "/publishArticle")
    public CommonResult publishArticle(FilmReviewVO filmReviewVO) {
        if (filmReviewVO != null) {
            boolean filmReview = communityServiceApi.createFilmReview(filmReviewVO);
            if (filmReview) {
                return CommonResult.success("影评创建成功！");
            } else {
                return CommonResult.serviceFailed("影评创建失败，请稍后重试！");
            }
        }
        return CommonResult.failed("入参错误");
    }

    /**
     * 删除影评
     *
     * @param filmReviewId 影评Id
     * @return
     */
    @PostMapping(value = "/deleteArticle")
    public CommonResult deleteArticle(Long filmReviewId) {
        if (filmReviewId != null) {
            boolean b = communityServiceApi.deleteFilmReview(filmReviewId);
            if (b) {
                return CommonResult.success("影评删除成功！");
            } else {
                return CommonResult.serviceFailed("影评删除失败！");
            }
        }
        return CommonResult.failed("入参错误");
    }


    /**
     * 获取最新社区活动
     *
     * @return
     */
    @GetMapping(value = "/getLatestActivity")
    public CommonResult getLatestActivity() {
        ActivityVO activityForIndex = communityServiceApi.getLatestActivityForIndex();
        return CommonResult.success(activityForIndex);
    }

    /**
     * 获取最新社区活动和最新影评
     *
     * @return
     */
    @GetMapping(value = "/getCommunityInfoByIndex")
    public LatestCommunityInfo getCommunityInfoByIndex() {
        LatestCommunityInfo latestCommunityInfo = new LatestCommunityInfo();
        latestCommunityInfo.setFilmReviewVOList(communityServiceApi.selectLatestFilmReview());
        latestCommunityInfo.setActivityVO(communityServiceApi.getLatestActivityForIndex());
        return latestCommunityInfo;
    }

    /**
     * 参与发表社区活动
     *
     * @return
     */
    @PostMapping(value = "/createActivity")
    public CommonResult createActivity(@RequestBody ActivityReplyVO activityReplyVO) {
        boolean b = communityServiceApi.publishActivity(activityReplyVO);
        if (b) {
            return CommonResult.success("发布成功！");
        }
        return CommonResult.failed("发布失败");
    }

    /**
     * 获取社区首页全部的活动回答
     *
     * @return
     */
    @GetMapping(value = "/getAllActivityReply")
    public CommonResult getAllActivityReply(@RequestParam Long activityId) {
        List<ActivityReplyVO> allActivityReply = communityServiceApi.getAllActivityReply(activityId);
        return CommonResult.success(allActivityReply);
    }
}
