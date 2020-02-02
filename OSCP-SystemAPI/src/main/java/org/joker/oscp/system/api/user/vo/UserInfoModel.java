package org.joker.oscp.system.api.user.vo;

import lombok.Data;
import org.joker.oscp.common.enums.UserSexEnum;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 用户详细信息VO
 * @author JOKER
 */
@Data
public class UserInfoModel implements Serializable {

    private Long UUID;

    private String username;

    private String nickname;

    private String email;

    private String phone;

    private int sex;

    private LocalDate birthday;

    private String address;

    private String headAddress;
}
