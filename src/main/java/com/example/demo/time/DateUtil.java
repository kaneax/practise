package com.example.demo.time;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/3/15
 */
public class DateUtil {

    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat SDF_YYYY_MM_DD_MM_SS = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private final static String START_TIME = " 00:00:00";
    private final static String END_TIME = " 23:59:59";
    private final static int TWO = 2;

    /**
     * 获取当前月第一天最小时间
     * @param month
     * @return
     * @throws ParseException
     */
    public static Date getFirstDayOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 获取某月最小天数
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        calendar.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期
        String firstDayString = SDF.format(calendar.getTime())+START_TIME;
        try{
            return SDF.parse(firstDayString);
        }catch (Exception e){
            return null;
        }

    }

    /**
     * 获取当前月最后一天的最大时间
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay=0;
        //2月的平年瑞年天数
        if(month== TWO) {
            lastDay = calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
        }else {
            lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // 设置日历中月份的最大天数
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        String lastDayString = SDF.format(calendar.getTime())+END_TIME;
        try{
            return   SDF.parse(lastDayString);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 根据时间字符串转成date类型
     * @param dateString
     * @return
     */
    public static Date getDateByString(String dateString) throws ParseException {

        return  SDF.parse(dateString);
    }

    /**
     * 获取传入Date时间，当天的最大时间；
     * @param date
     * @return
     */
    public static Date getDateStartTime(Date date) throws ParseException {
        String format = SDF.format(date)+START_TIME;
        return  SDF_YYYY_MM_DD_MM_SS.parse(format);
    }
    /**
     * 获取传入Date时间，当天的最小时间；
     * @param date
     * @return
     */
    public static Date getDateEndTime(Date date) throws ParseException {
        String format = SDF.format(date)+END_TIME;
        return  SDF_YYYY_MM_DD_MM_SS.parse(format);
    }

    public static void main(String[] args) {
        System.out.println(getFirstDayOfMonth(2));
    }
}
