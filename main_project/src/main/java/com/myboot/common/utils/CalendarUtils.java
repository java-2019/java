package com.myboot.common.utils;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarUtils {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_PATTERN);
        }
    };

    private static ThreadLocal<DateFormat> datetimeFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATETIME_PATTERN);
        }
    };

    /**
     * get the datetime start from 00:00:00
     *
     * @param date
     * @return
     */
    public static Date dayBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date dayBegin(String date) {
        Date d = parseDate(date);
        return dayBegin(d);
    }

    public static final Date parseDate(String date) {
        try {
            return dateFormat.get().parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String dayBeginStr(Date date) {

        return format(dayBegin(date));
    }

    public static String dayBeginStr(String date) {
        Date d = parseDate(date);
        return format(dayBegin(d));
    }

    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return getDateTimeFormat().format(date);
    }

    public static DateFormat getDateTimeFormat() {
        return datetimeFormat.get();
    }

    public static final String simpleFormat(Date date) {
        try {
            return dateFormat.get().format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param format
     * @return String
     * @throws
     * @author wmj911
     * @Description: (获取相应格式的当前机器时间)
     */
    public static String getDateTime(String format) {
        Calendar cale = Calendar.getInstance();
        DateFormatter dateFormatter = new DateFormatter(format);
        return dateFormatter.print(cale.getTime(), Locale.getDefault());
    }

    /**
     * @return String
     * @throws
     * @author wmj911
     * @Description: (获取默认格式(yyyy-MM-dd HH:mm:ss)的当前机器时间)
     */
    public static String getDateTimeWithLine() {
        return getDateTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @return yyyy-MM-dd String
     * @throws
     * @author wmj911
     * @Description: (返回年月日)
     */
    public static String getDateYMD() {
        return getDateTimeWithLine().substring(0, 10);
    }

    /**
     * @param date1 格式:yyyy-MM-dd
     * @param date2 格式:yyyy-MM-dd
     * @return String
     * @throws
     * @author wmj911
     * @Description: (返回date1距离date2的天数)
     */
    public static int getDiffDateDay(String date1, String date2) {
        try {
            if (StringUtils.isEmpty(date1)) {
                date1 = getDateYMD();
            }
            if (StringUtils.isEmpty(date2)) {
                date2 = getDateYMD();
            }
            if (!date1.equalsIgnoreCase(date2)) {
                date1 = date1.trim();
                date2 = date2.trim();
                String[] date1Str = {date1.substring(0, 4),
                        date1.substring(5, 7), date1.substring(8, 10)};
                String[] date2Str = {date2.substring(0, 4),
                        date2.substring(5, 7), date2.substring(8, 10)};
                Calendar cNow = Calendar.getInstance();
                Calendar cReturnDate = Calendar.getInstance();
                cNow.set(Calendar.YEAR, Integer.valueOf(date1Str[0]));
                cNow.set(Calendar.MONTH, Integer.valueOf(date1Str[1]) - 1);
                cNow.set(Calendar.DAY_OF_MONTH, Integer.valueOf(date1Str[2]));
                cNow.set(Calendar.HOUR_OF_DAY, 0);
                cNow.set(Calendar.MINUTE, 0);
                cNow.set(Calendar.SECOND, 0);

                cReturnDate.set(Calendar.YEAR, Integer.valueOf(date2Str[0]));
                cReturnDate.set(Calendar.MONTH, Integer.valueOf(date2Str[1]) - 1);
                cReturnDate.set(Calendar.DAY_OF_MONTH,
                        Integer.valueOf(date2Str[2]));
                cReturnDate.set(Calendar.HOUR_OF_DAY, 0);
                cReturnDate.set(Calendar.MINUTE, 0);
                cReturnDate.set(Calendar.SECOND, 0);

                long todayMs = cNow.getTimeInMillis();
                long returnMs = cReturnDate.getTimeInMillis();
                long intervalMs = returnMs - todayMs;
                return (int) (intervalMs / (1000 * 86400));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * @param date
     * @param field
     * @param amount
     * @return 设定文件 Date 返回类型
     * @throws
     * @author penbin
     * @Description: (时间加减)
     */
    public static Date dateAddOrSub(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();

    }
}
