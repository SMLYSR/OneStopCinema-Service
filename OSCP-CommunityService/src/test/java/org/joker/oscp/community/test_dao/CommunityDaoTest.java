package org.joker.oscp.community.test_dao;


import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.community.dao.ActivityReplyTMapper;
import org.joker.oscp.community.dao.ActivityTMapper;
import org.joker.oscp.community.entity.ActivityReplyT;
import org.joker.oscp.community.entity.ActivityT;
import org.joker.oscp.system.api.community.CommunityServiceApi;
import org.joker.oscp.system.api.community.vo.ActivityReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityDaoTest {

    @Autowired
    private CommunityServiceApi communityServiceApi;

    @Autowired
    private ActivityReplyTMapper activityReplyTMapper;

    @Autowired
    private ActivityTMapper activityTMapper;

    @Test
    public void insertActivity(){
        ActivityT activityT = new ActivityT();

        activityT.setTitle("哪些电影角色让你“背脊一凉”？");
        activityT.setContentDetail("是否有某些电影角色会让你看到时全身冒冷汗，在初秋的渐凉中“凉上加凉”？快来说一说，限量电影周边等你拿呦！");
        activityT.setActiveImg("http://img5.mtime.cn/mg/2019/10/08/150944.80311103.jpg");

        int insert = activityTMapper.insert(activityT);

        log.info("插入状态：{}", insert);
    }

    @Test
    public void insertActivityReply() {
        Long userId = 1222901482101440514L;
        Long activityId = 1236501501874569217L;
        String content="<h2>奥斯卡大热一镜到底《1917》美到窒息却<wbr />酷到难想象</h2>\n" +
                "<p><img src=\"http://p1.pstatp.com/large/pgc-image/7416c05dab0944b4a828e07cdf78de01\" alt=\"\" /></p>\n" +
                "<p>意外摘得金球奖最佳影片，还成为2020年奥斯卡最佳大热，都让一战题材巨制《1917》备受关注。全片以两位士兵在旅途中经历各种惊险坎坷为视角，</p>\n" +
                "<p>利用场景暗部或数字技术的衔接与处理，给人以&ldquo;一镜到底&rdquo;沉浸式超酷观影感受，所以我认为本片不亚于《西线无战事》《现代启示录》等战争片经典。</p>\n" +
                "<p><img src=\"http://p1.pstatp.com/large/pgc-image/173ce481febe45c38040dc834b78cf4f\" alt=\"\" /></p>\n" +
                "<p>119分钟的时长，却像一个公路片叙事方式，英军得知敌方在前线设下陷阱之后，两个士兵于仓促间受命去穿过战壕通风报喜，</p>\n" +
                "<p>就为避免自己超过25万人中计死亡，谁都知晓子弹横飞、炮火轰鸣的战场上是恐怖的，命运多坎的哥两何去何从？从一开始就悬念勾人。</p>\n" +
                "<p><img src=\"http://p1.pstatp.com/large/pgc-image/3a17c22c80de4d88aa995666d599dd47\" alt=\"\" /></p>";
        ActivityReplyT activityReplyT = new ActivityReplyT();
        activityReplyT.setUserId(userId);
        activityReplyT.setContent(content);
        int reply = activityReplyTMapper.insertAndGetId(activityReplyT);
        log.info("Id{}",activityReplyT.getUuid());
        if (reply > 0) {
            int i = activityReplyTMapper.insertActivityUser(activityId, activityReplyT.getUuid());
            if(i > 0) {
                log.info("插入成功");
            } else {
                log.info("插入失败");
            }
        } else {
            log.info("reply插入失败");
        }
    }

    @Test
    public void selectAllReply() {
        Long activityId = 1236501501874569217L;
        List<ActivityReplyVO> activityReplyVOS = activityReplyTMapper.selectAllActivityReply(activityId);
        activityReplyVOS.stream().forEach(a -> {
            log.info("内容为： {}",a);
        });
    }

}
