package org.joker.oscp.pay.util;

import java.util.UUID;

/**
 * 手动维护UUID工具
 * @author JOKER
 */
public class UUIDUtil {

    public static  String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
