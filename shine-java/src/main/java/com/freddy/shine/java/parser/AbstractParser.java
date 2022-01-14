package com.freddy.shine.java.parser;

import com.google.gson.Gson;

/**
 * @author: FreddyChen
 * @date : 2022/01/14 10:43
 * @email : freddychencsc@gmail.com
 */
public abstract class AbstractParser implements IParser {

    protected Gson gson = new Gson();
}
