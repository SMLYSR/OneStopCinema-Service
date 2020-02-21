package org.joker.oscp.common.util;


/**
 * 保存登录的用户信息
 * @author JOKER
 */
public class CurrentUser {
    /**
     * 线程绑定的存储空间
     */
    private static final  InheritableThreadLocal<Long> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void saveUserId(Long userId) {
        THREAD_LOCAL.set(userId);
    }

    public static Long getCurrentUser() {
        return THREAD_LOCAL.get();
    }

    public static void removeUserId() {
        THREAD_LOCAL.remove();
    }
}
