package com.freddy.shine.java.parser;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * 抽象的数据解析器
 * <p>
 * 如果需要自定义解析器，继承此{@link AbstractParser}，实现{@link AbstractParser#parse(String, String, Type)}方法即可<br />
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:43
 * @email : freddychencsc@gmail.com
 */
public abstract class AbstractParser implements IParser {

    protected Gson gson = new Gson();
}
