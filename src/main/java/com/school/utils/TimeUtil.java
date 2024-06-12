package com.school.utils;

public class TimeUtil {
    public static String getTimeReduce(Long start,Long end){
        long leftTime = end-start;
        long d = leftTime/1000/60/60/24;
        long h = leftTime/1000/60/60%24;
        long m = leftTime/1000/60%60;
        long s = leftTime/1000%60;
        String str = d + "天" + h + "小时" + m + "分" + s + "秒";
        return str;
    }

    public static Long getTimeCountDown(Long start,Long end){
        if (start > end){
            return 0l;
        }else {
            return (end-start)/1000;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis() + 5*60*1000;
        String timeReduce = getTimeReduce(start, end);
        System.out.println(timeReduce);
    }
}
