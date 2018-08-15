package com.hanlet.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author xm
 * 2018年6月1日
 */
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
