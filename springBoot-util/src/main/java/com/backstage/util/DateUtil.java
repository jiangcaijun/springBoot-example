package com.backstage.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 处理日期工具类
 *
 */
public class DateUtil {

	private static final Logger LOG = Logger.getLogger(DateUtil.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static Calendar c = Calendar.getInstance();

	/**
	 * 毫秒转日期
	 * @param millis
	 * @param format
	 * @return
	 */
	public static String millisToDate(long millis,String format){
//		logger.info(millis + "毫秒转日期字符串开始...");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
//		logger.info(millis + "毫秒转日期字符串结束...");
		synchronized(sdf) {
			return sdf.format(cal.getTime());
		}
	}
	
	public static Date formatToDate(String date) throws ParseException{
		if(StringUtils.isNotBlank(date)){
			//		logger.info(date + " 转日期开始...");
			synchronized(sdf) {
				return sdf.parse(date);
			}
		}else{
			return null;
		}

	}
	
	public static Date millsToDate(long mills){
//		logger.info(mills + "毫秒转日期开始...");
		return new Date(mills);
	}
	
	/**
	 * 将时间转化为mills
	 * @param Date
	 * @return
	 * @throws ParseException
	 */
	public static Long getMills(String Date) throws ParseException{
//		logger.info(Date + " 日期字符串转毫秒");
		return formatToDate(Date).getTime();
	}
	
	/**
	 * 将时间转化为second
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Long getSecond(Date date) throws ParseException{
//		logger.info(date + " 转秒");
		return date.getTime()/1000;
	}

	/**
	 * 将时间转化为mills
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Long getmills(Date date) throws ParseException{
//		logger.info(date + " 转毫秒");
		return date.getTime();
	}

	/**
	 * 获取日期字符串，eg:2017-08-29 11:16:53
	 */
	public static String getTime(){
		Date date = new Date();
		synchronized(sdf) {
			return sdf.format(date);
		}
	}
	public static String getTime4yyyyMMdd(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		synchronized(sdf) {
			return sdf.format(date);
		}
	}

	public static List<Date> getDates(Date dBegin, Date dEnd)
	{
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime()))
		{
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

	/**
	 * 根据开始、结束时间获取时间列表
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getDateStr(String startTime,String endTime){
		List<String> strList = new ArrayList<String>();
		try {
			Calendar cal = Calendar.getInstance();
			String start = startTime;
			String end = endTime;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dBegin = sdf.parse(start);
			Date dEnd = sdf.parse(end);
			List<Date> lDate = getDates(dBegin, dEnd);
			for (Date date : lDate) {
				strList.add(sdf.format(date));
			}
			return strList;
		}catch (Exception e){

		}
		return strList;
	}

	/**
	 * 得到近一周的日期开始字符串
	 * @return
	 */
	public static String getAgoOneWeek(Date date){
		//过去七天
		c.setTime(date);
		c.add(Calendar.DATE, - 7);
		Date d = c.getTime();
		String day = format.format(d);
		return day;
	}

	/**
	 * 得到近一月的日期开始字符串
	 * @return
	 */
	public static String getAgoOneMonth(Date date){
		//过去一月
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		return mon;
	}

	/**
	 * 得到近三个月的日期开始字符串
	 * @return
	 */
	public static String getAgoThreeMonth(Date date){
		//过去三个月
		c.setTime(date);
		c.add(Calendar.MONTH, -3);
		Date m3 = c.getTime();
		String mon3 = format.format(m3);
		return  mon3;
	}
	/**
	 * 得到近一年的日开始字符串
	 * @return
	 */
	public static String getAgoOneYear(Date date){
		//过去一年
		c.setTime(date);
		c.add(Calendar.YEAR, -1);
		Date y = c.getTime();
		String year = format.format(y);
		return  year;
	}

	public static String[] getTimeByType(String timeType,String formatStr){
		SimpleDateFormat sdFormat = new SimpleDateFormat(formatStr);
		String[] resultArray = new String[2];
		if(StringUtils.isEmpty(timeType)){
			Date date = new Date();
			String startTime = getAgoOneMonth(date);
			resultArray[0]=startTime;
			String endTime = sdFormat.format(date);
			resultArray[1]=endTime;
		}
		return  resultArray;
	}
	/**
	 * 将long类型转化为Date
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static String longToDare(Long str) throws ParseException{
		return format.format(new Date(str));
	}

	/**
	 * 首页-时间参数
	 * @param params
	 */
	public static void getParam(Map<String, String> params){
		String time = params.get("time");//获取时间
		Map<String,Object> timeMap  = (Map<String,Object>) JSONObject.parse(time);
		String startTime = String.valueOf(timeMap.get("start"));
		String endTime = String.valueOf(timeMap.get("end"));
		try {
			startTime = DateUtil.longToDare(Long.parseLong(startTime));
			endTime = DateUtil.longToDare(Long.parseLong(endTime));
			//若转换之后得到的日期为1970年，默认重新生成一个月的时间查询，这里是前台时间传的不准确造成的
			if(StringUtils.isNotEmpty(startTime) &&startTime.contains("1970")){
				startTime = DateUtil.getAgoOneMonth(new Date());
				endTime = DateUtil.getTimeByFormat(new Date(),"yyyy-MM-dd");
			}
			params.put("startTime",startTime+" 00:00:00");
			params.put("endTime",endTime+" 23:59:59");
		}catch (Exception e){
			LOG.error("首页-时间参数处理出现异常",e);
		}
	}

	/**
	 * 根据时间及
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String getTimeByFormat(Date date,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	public static void main(String[] args) throws Exception {
//		String week = getAgoOneWeek();
//		List<String> list = getDateStr(week,format.format(new Date()));
//		for (String str :list)
//			System.out.println(str);
//		String month = getAgoOneMonth();
//		String threeMonth = getAgoThreeMonth();
//		String oneYear = getAgoOneYear();
		System.out.println(longToDare(1477386005l));
	}


}