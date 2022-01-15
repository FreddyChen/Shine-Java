package com.freddy.shine.java.cipher;

/**
 * 默认的加解密器
 * <p>
 * 示例实现<br />
 * 在{@link #encrypt(String)}中实现加密逻辑<br />
 * 在{@link #decrypt(String)}中实现解密逻辑<br />
 * {@link #getParamName()}配置与服务端协商好的加密字段key<br />
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:16
 * @email : freddychencsc@gmail.com
 */
public class DefaultCipher extends AbstractCipher {

    @Override
    public String encrypt(String original) {
        return original;
    }

    @Override
    public String decrypt(String original) {
        return original;
    }

    @Override
    public String getParamName() {
        return "params";
    }
}
