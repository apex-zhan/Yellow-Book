package utils;

import java.util.Random;

/**
 * 验证码生成器
 */
public class VerificationCodeGenerator {
    public static String generateVerificationCode() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10); // 生成0到9的随机数
            sb.append(digit);
        }
        return sb.toString();
    }
}
