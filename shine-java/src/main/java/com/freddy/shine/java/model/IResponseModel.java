package com.freddy.shine.java.model;

/**
 * ResponseModel接口，自定义ResponseModel需继承此接口并实现{@link #isSuccessful}方法
 * <p>
 * @author: FreddyChen
 * @date : 2022/01/15 00:53
 * @email : freddychencsc@gmail.com
 */
public interface IResponseModel {

    boolean isSuccessful();
}
