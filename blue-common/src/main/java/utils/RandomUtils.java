package utils;

import java.util.Random;

public class RandomUtils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int USERNAME_LENGTH = 8; // 用户名长度
    private static final int PASSWORD_LENGTH = 10; // 密码长度

    // 生成随机用户名
    public static String generateRandomUsername() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < USERNAME_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    // 生成随机密码
    public static String generateRandomPassword() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

}
