/**
 * 
 */
package com.wj.fin.wjutil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author DEV
 *
 */
public class TheTools {
	public static double round_half_up(double data, int digit) {
		BigDecimal bg = new BigDecimal(data);
        double new_round = bg.setScale(digit, BigDecimal.ROUND_HALF_UP).doubleValue();
		return new_round;
		
		
		// 方式一：BigDecimal方式
		// http://blog.csdn.net/w1014074794/article/details/53640731
		// https://www.cnblogs.com/huangwenjie/p/6596833.html
		/*
		double f = 3.1315;
		BigDecimal b = new BigDecimal(new Double(f).toString);
		double f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		注意：这里一定不要直接使用new BigDecimal(double)的构造方法，
		而要使用new BigDecimal(new Double(1.1315).toString())的方式，不然会出现精确问题.
		*/
		
		/*
		 * 在上面简单地介绍了银行家舍入法，目前Java支持7中舍入法：
		1、 ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。
		2、 ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。
		3、 ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。
		4、 ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP。
		5、 HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。
		6、 HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。
		7、 HAIL_EVEN：银行家舍入法。 
		 */
	}
	public static double round_up(double data, int digit) {
		BigDecimal bg = new BigDecimal(data);
        double new_round = bg.setScale(digit, BigDecimal.ROUND_UP).doubleValue();
		return new_round;
	}
	
	public static int date_diff(java.util.Calendar cal1, java.util.Calendar cal2) {
		
		if ( cal1 == null || cal2 == null )
			return 0;
		double datediff =   cal1.getTimeInMillis() - cal2.getTimeInMillis();
    	datediff = datediff / (1000*3600*24);
    	datediff = com.wj.fin.wjutil.TheTools.round_up(datediff, 0);
    	int m_diff = (int) datediff;
    	
    	return m_diff;
	}
	
	
	public static String printDateShort(Calendar cal)
	{
		if ( cal == null )
			return null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = null;
		
		java.util.TimeZone timeZone = cal.getTimeZone();
		formatter.setTimeZone(timeZone);
		
		dateString = formatter.format(cal.getTime());
		
		return dateString;
	}
	/**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Calendar getDateShort() 
    {
    	return getDateShort(null);    	
    }

    public static Calendar getDateShort(String s)
	{
    	Date resultDate = null;
		Date currentTime = new Date();
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd Z");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = null;
		
//		java.util.TimeZone timeZone1 = java.util.TimeZone.getDefault();
//		java.util.TimeZone timeZone2 = java.util.TimeZone.getTimeZone("Asia/Shanghai");
//		java.util.TimeZone timeZone3 = java.util.TimeZone.getTimeZone("Europe/Athens");
		
		java.util.TimeZone timeZone = java.util.TimeZone.getTimeZone("Asia/Shanghai");
		
//		java.util.TimeZone timeZone4 =  formatter.getTimeZone();
		formatter.setTimeZone(timeZone);
		
		/*
		YEAR
		MONTH
		DAY_OF_MONTH
		HOUR_OF_DAY
		MINUTE
		SECOND
		MILLISECOND
		
		
		try {
			resultDate = formatter.parse("2018-1-28");
			Calendar calendar4 = Calendar.getInstance(timeZone3, java.util.Locale.ENGLISH);
			calendar4.setTime( resultDate );
			System.out.println(calendar4.toString());
			Date aa = calendar4.getTime();
			System.out.println(calendar4.getTime());
			System.out.println(calendar4.get(Calendar.YEAR));
			System.out.println(calendar4.get(Calendar.MONTH));
			System.out.println(calendar4.get(Calendar.DAY_OF_MONTH));
			System.out.println(calendar4.get(Calendar.HOUR_OF_DAY));
			System.out.println(calendar4.get(Calendar.MINUTE));
			System.out.println(calendar4.get(Calendar.SECOND));
			System.out.println(calendar4.get(Calendar.MILLISECOND));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		

		Calendar calendar1 = Calendar.getInstance(timeZone3, java.util.Locale.ENGLISH);  
		Calendar calendar2 = Calendar.getInstance(timeZone3, java.util.Locale.ENGLISH);  

        calendar1.setTime(new Date());
        calendar2.set(2017,10,28,0,0,0);
        
        calendar2.set(Calendar.MILLISECOND, 0); 
        
        Date a1 = calendar1.getTime();
        Date a2 = calendar2.getTime();
        
        */
        
		Calendar calendar = Calendar.getInstance(timeZone, java.util.Locale.ENGLISH);

		if (s == null || s.length() <= 0) {
			dateString = formatter.format(currentTime);
		} else
			dateString = s;// +" +0800";
		
		// ParsePosition pos = new ParsePosition(8);
		
		try {
			resultDate = formatter.parse(dateString); //+" +0800");
			calendar.setTime( resultDate );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dateString = formatter.format(currentTime);
			try {
				resultDate = formatter.parse(dateString);
				calendar.setTime( resultDate );
			}
			catch (ParseException e2) {
				calendar.setTime( currentTime );
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0); 
				calendar.set(Calendar.SECOND, 0); 
				calendar.set(Calendar.MILLISECOND, 0); 
			}
		}
		
		
/*		
		
		System.out.println(calendar.getTime());
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH));
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println(calendar.get(Calendar.MINUTE));
		System.out.println(calendar.get(Calendar.SECOND));
		System.out.println(calendar.get(Calendar.MILLISECOND));
*/		
		return calendar;
		
		/*
		Date and Time Pattern 

		Result 
		
		"yyyy-MM-dd HH:mm:ss Z"			 2018-02-01 10:06:46 +0200
		
		

		"yyyy.MM.dd G 'at' HH:mm:ss z"  2001.07.04 AD at 12:08:56 PDT  
		"EEE, MMM d, ''yy"  Wed, Jul 4, '01  
		"h:mm a"  12:08 PM  
		"hh 'o''clock' a, zzzz"  12 o'clock PM, Pacific Daylight Time  
		"K:mm a, z"  0:08 PM, PDT  
		"yyyyy.MMMMM.dd GGG hh:mm aaa"  02001.July.04 AD 12:08 PM  
		"EEE, d MMM yyyy HH:mm:ss Z"  Wed, 4 Jul 2001 12:08:56 -0700  
		"yyMMddHHmmssZ"  010704120856-0700  
		"yyyy-MM-dd'T'HH:mm:ss.SSSZ"  2001-07-04T12:08:56.235-0700  
		"yyyy-MM-dd'T'HH:mm:ss.SSSXXX"  2001-07-04T12:08:56.235-07:00  
		"YYYY-'W'ww-u"  2001-W27-3  
		*/
	}


}
