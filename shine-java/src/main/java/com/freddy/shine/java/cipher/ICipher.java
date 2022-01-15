package com.freddy.shine.java.cipher;

/**
 * 加解密器抽象接口
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/13 18:02
 * @email : freddychencsc@gmail.com
 * @see DefaultCipher
 */
public interface ICipher {

    /**
     * 加密数据
     *
     * @param original
     * @return
     */
    String encrypt(String original);

    /**
     * 解密数据
     *
     * @param original
     * @return
     */
    String decrypt(String original);

    /**
     * 获取加解密字段名称
     *
     * @return
     */
    String getParamName();
}
