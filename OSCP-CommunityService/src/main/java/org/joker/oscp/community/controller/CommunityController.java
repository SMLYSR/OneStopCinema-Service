package org.joker.oscp.community.controller;

import org.joker.oscp.common.CommonResult;
import org.joker.oscp.system.api.community.vo.LatestCommunityInfo;
import org.joker.oscp.system.api.community.vo.PublishArticleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 社区前端控制器
 * @author JOKER
 */
@RestController
public class CommunityController {


    /**
     * 发表影评
     * @param publishArticleVO 文章详细参数
     * @return
     */
    @PostMapping(value = "/publishArticle")
    public CommonResult publishArticle(PublishArticleVO publishArticleVO){
        return null;
    }

    /**
     * 删除影评
     * @param uuid 影评Id
     * @return
     */
    @PostMapping(value = "/deleteArticle")
    public CommonResult deleteArticle(Long uuid) {
        return null;
    }


    /**
     * 获取最新社区活动
     * @return
     */
    @GetMapping(value = "/getLatestActivity")
    public CommonResult getLatestActivity() {
        return null;
    }

    /**
     * 获取最新社区活动
     * @return
     */
    @GetMapping(value = "/getCommunityInfoByIndex")
    public LatestCommunityInfo getCommunityInfoByIndex() {
        return null;
    }

    /**
     * 参与发表社区活动
     * @return
     */
    @PostMapping(value = "/createActivity")
    public CommonResult createActivity(){
        return null;
    }

    /**
     * 获取社区首页全部的活动回答
     * @return
     */
    @GetMapping(value = "/getAllActivityReply")
    public CommonResult getAllActivityReply() {
        return null;
    }
}
