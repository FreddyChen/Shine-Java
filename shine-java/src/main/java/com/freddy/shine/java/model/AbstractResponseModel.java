package com.freddy.shine.java.model;

/**
 * 抽象的ResponseModel，所有ResponseModel都应继承此类，并重写{@link #isSuccessful()}方法
 * <p>
 * 参考{@link DefaultResponseModel}实现
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:50
 * @email : freddychencsc@gmail.com
 */
public abstract class AbstractResponseModel {

    abstract boolean isSuccessful();
}
