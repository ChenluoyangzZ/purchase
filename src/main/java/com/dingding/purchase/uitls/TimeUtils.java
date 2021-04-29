package com.dingding.purchase.uitls;

import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.TimeZones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static Date unixTimestampToDate() {
        return new Date(System.currentTimeMillis());
    }

    @SneakyThrows
    public static Date stirngToDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-SS");
        long time = simpleDateFormat.parse(date).getTime()+374399999;
        Date date1 = new Date();
        date1.setTime(time);
        return date1;
    }

    public static String format(long date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(long date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }
}
