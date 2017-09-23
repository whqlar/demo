package com.dianping.study.biz.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wu on 16/11/14.
 */
public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    public static String formatDate(Date date, String format) {
        if (StringUtils.isBlank(format)) {
            format = DEFAULT_FORMAT;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_FORMAT);
    }

    public static Date parseDate(String date, String format) {
        if (StringUtils.isBlank(format)) {
            format = DEFAULT_FORMAT;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String date) {
        return parseDate(date, DEFAULT_FORMAT);
    }

    private static String loadNextMonday() {
        DateTime now = new DateTime();
        int dayOfWeek = now.getDayOfWeek();
        DateTime nextMonday = now.plusDays(7 - dayOfWeek + 1);
        return nextMonday.toString("yyyy-MM-dd");
    }

    /**
     * 时刻是否匹配
     */
    private static boolean isInTime(String startTime, String endTime, Calendar now) {
        int nowHour = now.get(Calendar.HOUR_OF_DAY);
        int startHour;
        int endHour;
        try {
            startHour = Integer.parseInt(startTime);
            endHour = Integer.parseInt(endTime);
        } catch (Exception e) {
            LOGGER.error("startTime or endTime parse error, please check lion config", e);
            return false;
        }

        if (endHour > startHour) {
            return (nowHour >= startHour) && (nowHour + 1 <= endHour);
        } else { //跨天，持续到第二天
            return (nowHour >= startHour) || (nowHour + 1 <= endHour);
        }
    }

    /**
     * 周几是否匹配
     * @param dayOfWeek
     * @param now
     * @return
     */
    private static boolean isInWeek(int dayOfWeek, Calendar now) {
        return now.get(Calendar.DAY_OF_WEEK) == (dayOfWeek + 1); //calendar一周的第一天是周日
    }
}
