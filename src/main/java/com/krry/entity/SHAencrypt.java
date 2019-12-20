package com.krry.entity;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;

public class SHAencrypt {
    private final static String KEY_SHA = "SHA";
    public SHAencrypt() {

    }
    public static String encryptSHA(String data) throws Exception {
        // 验证传入的字符串
        if (data == null || data.equals("")) {
            return "";
        }
        // 创建具有指定算法名称的信息摘要
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        // 使用指定的字节数组对摘要进行最后更新
        sha.update(data.getBytes());
        // 完成摘要计算
        byte[] bytes = sha.digest();
        // 将得到的字节数组变成字符串返回
        return Base64.encodeBase64String(bytes);
    }
}
