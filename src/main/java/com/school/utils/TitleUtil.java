package com.school.utils;

public class TitleUtil {
    public static String getTitle(String source){
        if (source.equals("")||source == null){
            return null;
        }
        if (source.length() <= 10){
            return source;
        }else{
            String[] split = source.split(" ");
            if (split.length > 2){
                return split[0] + " " + split[1];
            }
            return source;
        }
    }
}
