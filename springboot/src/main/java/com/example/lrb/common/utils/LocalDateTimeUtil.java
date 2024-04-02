package com.example.lrb.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class LocalDateTimeUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    public static LocalDate stringToLocalDate(String date) {
        LocalDate localDate = null;
        if (date == null) {
            return null;
        }
        try {
            if (date.contains("-")) {
                localDate = LocalDate.parse(date, formatter);
            } else if (date.contains("/")) {
                localDate = LocalDate.parse(date, formatter2);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("---  时间转换失败  ---");
        }
        return localDate;
    }

    public static LocalDateTime stringToLocalDateTime(String date) {
        LocalDateTime localDateTime = null;
        if (date == null) {
            return null;
        }
        try {
            localDateTime = LocalDateTime.parse(date, formatter3);
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("---  时间转换失败  ---");
        }
        return localDateTime;
    }

    public static String toString(LocalDateTime l) {
        String s = null;
        if (l == null) {
            return null;
        }
        try {
            s = l.format(formatter3);
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("---  时间转换失败  ---");
        }
        return s;
    }


    //将java.util.Date 转换为java8 的java.time.LocalDateTime,默认时区为东8区
    public static LocalDateTime dateConvertToLocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }


    //将java8 的 java.time.LocalDateTime 转换为 java.util.Date，默认时区为东8区
    public static Date localDateTimeConvertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }


    public static String localDateToString(Date date) {
        return format.format(date);
    }


}
