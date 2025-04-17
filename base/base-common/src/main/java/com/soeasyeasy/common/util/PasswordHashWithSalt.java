package com.soeasyeasy.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 使用 salt 密码哈希
 *
 * @author hc
 * @date 2025/04/17
 */
public class PasswordHashWithSalt {
    /**
     * 获取盐
     *
     * @return {@link byte[] }
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    /**
     * 使用 salt 对密码进行哈希处理
     *
     * @param password 密码
     * @param salt     盐
     * @return {@link String }
     * @throws NoSuchAlgorithmException 没有这样算法例外
     */
    public static String hashPasswordWithSalt(String password, byte[] salt) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPassword) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}