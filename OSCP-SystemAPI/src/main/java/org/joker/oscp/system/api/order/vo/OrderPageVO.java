package org.joker.oscp.system.api.order.vo;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

@Data
public class OrderPageVO {
    private Long userId;
    private Page page;
}
