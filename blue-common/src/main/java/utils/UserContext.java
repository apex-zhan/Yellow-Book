package utils;

/**
 * 用户上下文工具类
 * 用于存储当前登录用户的信息
 *
 * @author MECHREVO
 */
public class UserContext {
    private static final ThreadLocal<Long> tl = new ThreadLocal<>();

    /**
     * 保存当前登录用户信息到ThreadLocal
     *
     * @param userId 用户id
     */
    public static void setUser(Long userId) {
        tl.set(userId);
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 用户id
     */
    public static Long getUser() {
        return tl.get();
    }

    /**
     * 移除当前登录用户信息
     */
    public static void removeUser() {
        tl.remove();
    }
}
