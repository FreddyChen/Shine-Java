package com.freddy.shine.java.cipher;

/**
 * 抽象的加解密器
 * <p>
 * 如果需要自定义加解密器，继承此{@link AbstractCipher}，实现{@link ICipher#encrypt(String)}及{@link ICipher#decrypt(String)}方法即可<br />
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:12
 * @email : freddychencsc@gmail.com
 */
public abstract class AbstractCipher implements ICipher {
}
