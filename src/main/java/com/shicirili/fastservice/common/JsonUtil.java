package com.shicirili.fastservice.common;

import com.google.gson.Gson;

public class JsonUtil {

    static Gson gson = new Gson();


    public static String toJson(Object t){
        return gson.toJson(t);
    }

}
