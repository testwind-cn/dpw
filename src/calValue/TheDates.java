package calValue;

import java.util.Calendar;
import java.util.Date;

public class TheDates {
	
	private int d3_total_Period = 0;
    private int d4_period_len;
    private Calendar d5_start_date;
    
    //    private $data_start_date;// = date_create();        // 贷款首期借款日期
    private Calendar [] data_last_date;// = date_create();         // 贷款上期还款日期
    private Calendar [] data_period_date;// = date_create();       // 贷款本期还款日期
    private int [] data_due_days;                         // 本期借款天数
    private int [] period_days_array;
    
    
    
    public TheDates(int num)
    {
    	initMe(num);
    }
    
    private void initMe(int num)
    {    	
        //        $this->data_start_date = date_create();
        
        data_period_date = new Calendar[num+2];
        data_last_date = new Calendar[num+2];
        data_due_days = new int[num+2];
        
        d3_total_Period = num;
        
        
        
        for (int i=0 ; i<=d3_total_Period+1;i++){
            data_period_date[i] = com.wj.fin.wjutil.TheTools.getDateShort();// new Date();
            data_last_date[i] =  com.wj.fin.wjutil.TheTools.getDateShort();// new Date();
            data_due_days[i] =  0;
        }
    	
    }
    
    
    private void releaseMe()
    {
//        echo 'Destroying: ';
        //, $this->name, PHP_EOL;
    
        for (int i = d3_total_Period + 1; i >= 0; i --) {
        	
            if ( data_last_date != null && i < data_last_date.length  && data_last_date[i] != null )
            	data_last_date[i] = null;
            
            if ( data_period_date != null &&  i < data_period_date.length  && data_period_date[i] != null )
            	data_period_date[i] = null;
            
            if ( data_due_days != null &&  i < data_due_days.length  && data_due_days[i] != 0 )
            	data_due_days[i] = 0;
        }
        data_last_date = null;
        data_period_date = null;
        data_due_days = null;
    }
    
    
    private Calendar getLastDate(int num)
    { // 获取上期还款日的一个副本
        if (num < 0 || num > d3_total_Period + 1)
            return null;
        if ( data_last_date != null && num < data_last_date.length  && data_last_date[num] != null ) {
            return data_last_date[num];
        }
        return null;
    }
    
    
    private Calendar getThisDate(int num)
    { // 获取本期还款日的一个副本
        if (num < 0 || num > d3_total_Period + 1)
            return null;
        if ( data_period_date != null && num < data_period_date.length  && data_period_date[num] != null ) {
            return data_period_date[num];
        }
        return null;
    }
    
    
    
    public int getDueDays(int num)
    { // 获取上期还款日的一个副本
        if ( num < 0 || num > d3_total_Period + 1)
            return 0;
        if ( data_due_days != null && num < data_due_days.length  ) {
            return data_due_days[num];
        }
        return 0;
    }
    
    public int getCount()
    { // ???
        return d3_total_Period;
    }
    
    public int getPeriod_Len()
    { // ???
        return d4_period_len;
    }
    
    
    
    
    private Calendar getShiftSameDay( Calendar start_date) // shift 前后挪期， $is_month=false半月，true月
    {    	
    	return getShiftSameDay( start_date,0,true);    	
    }
    
    private Calendar getShiftSameDay( Calendar start_date,int shift) // shift 前后挪期， $is_month=false半月，true月
    {    	
    	return getShiftSameDay( start_date,shift,true);    	
    }
    
    private Calendar getShiftSameDay( Calendar start_date, int shift, boolean is_month) // shift 前后挪期， $is_month=false半月，true月
    {
    	//( Calendar start_date, int shift=0, boolean is_month=true) // shift 前后挪期， $is_month=false半月，true月        
        // ?? 检查 $start_date 是合法日期 !!!!!
    	
//    	Calendar theday1 = start_date;

    	int m_year = start_date.get(Calendar.YEAR);
        int m_month = start_date.get(Calendar.MONTH) + 1;
        int m_day = start_date.get(Calendar.DAY_OF_MONTH);
        
        if ( m_day > 28 ) m_day = 28; //只会是 1-28
        
        if ( is_month == false ) { // 半月
            if ( m_day == 15 ) m_day = 16;
            if ( m_day == 14 ) m_day = 13;
            
            if ( shift % 2 != 0 ) { // 单数半月
                if ( m_day < 15 ){
                    m_day = m_day + 15;
                    shift = shift - 1; //去掉一个半月，人工挪后半月
                } else {
                    m_day = m_day - 15;
                    shift = shift + 1; //去掉一个半月，人工前移半月
                }
            }
            shift = shift / 2; // floor( shift / 2 );
        }
        
        
        // 月 和 半月 相同模式
        //$add_year = floor( ( $m_month + $shift - 1 ) / 12 );
        int add_year = ( m_month + shift - 1 ) / 12 ;
        m_month = ( ( m_month + shift - 1 ) % 12 ) + 1;
        if ( m_month <= 0 ) m_month = m_month + 12;
        m_year = m_year + add_year;
        
        
        
        Calendar calendar = Calendar.getInstance( start_date.getTimeZone(), java.util.Locale.ENGLISH );
        calendar.set( m_year, m_month - 1 ,  m_day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0); 
        
//        $date = date_create_from_format("Y-m-d H:i:s", $m_year."-".$m_month."-".$m_day." 00:00:00");
        
        return calendar;
        
    }
    
    
    private Calendar getFakeStartDate( Calendar start_date, int days_len, int spec_mday,boolean spec_mode ) {
        
        // ?? 检查 $start_date 是合法日期 !!!!!
    	Calendar m_fake_start = null;
        
    	Calendar calendar = (Calendar) start_date.clone(); // Calendar.getInstance( start_date.getTimeZone(), java.util.Locale.ENGLISH );
//    	calendar.setTime(start_date.clone());
  //      calendar.set( m_year, m_month - 1 ,  m_day, 0, 0, 0);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0); 
    	
//        $date=date_create_from_format("Y-m-d H:i:s", date_format( $start_date, "Y-m-d 00:00:00"));
        
        if ( days_len > 0 || spec_mday <= 0 || spec_mday > 28 ) { // 按日模式不修改，只改半月和月, 不指定日期的，也返回
            
            m_fake_start = calendar;
            return m_fake_start;
        }
        
        
//        $theday1 = getdate( $date->getTimestamp() );
        int m_year = calendar.get(Calendar.YEAR); //.$theday1['year'];
        int m_month = calendar.get(Calendar.MONTH) + 1; // $theday1['mon'];
        int m_day = calendar.get(Calendar.DAY_OF_MONTH); //$theday1['mday'];
        
        
        
        //////////////// 2.修正指定日期
        /*
        if ( spec_mday < 0 ) {
            spec_mday = m_day;
        }
        if ( spec_mday > 28 ) spec_mday = 28; // spec_mode 按实际传入的
        */
        
        if (  days_len == 0 ) { ///半月模式
            if ( spec_mday !=0 ) { //注意半月不指定日期模式！！！ 不是0任意天模式，
                if ( spec_mday == 15 ) spec_mday = 16;
                if ( spec_mday == 14 ) spec_mday = 13;
            }
            
            if ( m_day + 15 <= spec_mday ) spec_mday -= 15; //** 半月模式的，借款日的mday + 15 <=指定日mday， 指定日mday-=15
            if ( spec_mday + 15 <= m_day ) spec_mday += 15; //** 半月模式的，指定日mday + 15 <= 借款日的mday， 指定日mday+=15
            
        }
        
        
        if ( spec_mday > 0 ) {
            if ( m_day <= spec_mday ) {
                
            	
            	calendar.set(m_year, m_month,spec_mday,0,0,0);
				calendar.set(Calendar.MILLISECOND, 0); 
				
//                $date=date_create_from_format("Y-m-d H:i:s", date_format($start_date,"Y-m-d 00:00:00"));
//                $date->setDate ( $m_year , $m_month , $spec_mday );
                m_fake_start = calendar;
                
                int m_diff = com.wj.fin.wjutil.TheTools.date_diff(m_fake_start, start_date);
            	
                
//                $m_diff = (int) date_diff( $m_fake_start ,$start_date)->format("%a");
                
                if ( spec_mode == false ) {
                    
                    m_fake_start = calendar;//$this->getShiftSameDay($date);         // 不用
                } else {
                    if ( m_diff >15 || ( m_diff > 7 & days_len==0)  ) {
                        m_fake_start = getShiftSameDay( calendar, -1, days_len!=0 );
                    } else { // 指定日mday - 借款日的mday <= 15
                        // 月：用指定日生成本月  --做假借款日
                        // 半月：用指定日生成本期  --做假借款日
                    	m_fake_start = calendar; // 不用
                    }
                    
                }
                
                
            }else { //指定日mday<借款日的mday
                
//                $date=date_create_from_format("Y-m-d H:i:s", date_format($start_date,"Y-m-d 00:00:00"));
//                $date->setDate ( $m_year , $m_month , $spec_mday );
//                $m_fake_start = $this->getShiftSameDay($date,1,$days_len!=0);
            	calendar.set(m_year, m_month,spec_mday,0,0,0);
				calendar.set(Calendar.MILLISECOND, 0); 
				m_fake_start = getShiftSameDay( calendar, 1, days_len!=0 );
                
//                $m_diff = (int) date_diff( $m_fake_start ,$start_date)->format("%a");
                int m_diff = com.wj.fin.wjutil.TheTools.date_diff(m_fake_start, start_date);
                
                if ( spec_mode == false ) {
//                    m_fake_start = $this->getShiftSameDay($date,1,$days_len!=0); // 不用
                	m_fake_start = getShiftSameDay( calendar, 1, days_len!=0 );
                } else {
                    if ( m_diff >15 || (m_diff > 7 & days_len==0)  ) {
                        m_fake_start = calendar;
                    } else {
                        m_fake_start = getShiftSameDay(calendar,1,days_len!=0); // 不用
                    }
                    
                }
                
            }
            
        } else { // 任意天的，支持 29,30,31的
            //            $m_fake_start = date_create_from_format("Y-m-d H:i:s", date_format($this->d5_start_date,"Y-m-d 00:00:00"));
        }
        
        return m_fake_start;
        
    }
    
    
    
//    public cal_theDates( total_Period, days_len=-1, start_date=null, spec_mday=0, spec_mode=false,days_array=null)
    public void cal_theDates( int total_Period, int days_len, String start_date, int spec_mday, boolean spec_mode,int [] days_array)
    {
        Calendar m_fake_start = null;
        
        int num = getCount();
        if ( total_Period <=0 ) return;
        if ( total_Period != num  ) {
        	releaseMe();
        	initMe(total_Period);
        }
        
        
        d3_total_Period = total_Period; // 总期数不能小于1
        d4_period_len = days_len; // -1=按自然月还， 1-365=按天数周期还，-25=按后面的还款天表

        period_days_array = null;
        if ( days_array != null && days_array.length > 0) {
        	period_days_array = new int[days_array.length];
        	System.arraycopy(days_array, 0, period_days_array, 0, days_array.length);
        }
        	
        
        
        
        
        
        
        //////////////// 1.设定开始日期
        /*
        date_default_timezone_set("Asia/Shanghai");
        // $date=date_create(date("Y-m-d")); // "Y-m-d 2017-01-09 Y n j 2017-1-9" // date_date_set($date,2020,10,15);
        
        $date=date_create_from_format("Y-m-d",$start_date);
        if ( $date == false )
        { // 如果没有传递开始日期，或者错误的开始日期，则设置当前日期为开始日期
            $date=date_create(date("Y-m-d")); // "Y-m-d 2017-01-09 Y n j 2017-1-9" // date_date_set($date,2020,10,15);
        }
        $this->d5_start_date= date_create_from_format("Y-m-d H:i:s",date_format($date,"Y-m-d 00:00:00"));
        */
        
        
        Calendar date = com.wj.fin.wjutil.TheTools.getDateShort(start_date);// new Date();
        d5_start_date = date;
        
        //////////////// 1.设定开始日期
        

        
        
//        $m_fake_start = $this->getFakeStartDate($this->d5_start_date,$days_len,$spec_mday,$spec_mode);
        m_fake_start = getFakeStartDate( d5_start_date, days_len, spec_mday, spec_mode);
        
        
        
        
/*        
        
//        unset($this->periodAmounts);
//        $this->periodAmounts =array();
//        $this->periodAmounts[0] = new PeriodAmount();
        
        $this->setPeriodDate($this->d5_start_date,$this->d5_start_date,0,$this->d4_period_len,$this->period_days_array);
//        $this->setPeriodPrincipal($this->d1_all_loan);
        
        for ($x=1; $x <= $this->d3_total_Period; $x++)
        {
            $last_date = $this->getThisDate($x-1);
//            $this->periodAmounts[$x] = new PeriodAmount();
            $this->setPeriodDate($m_fake_start,$last_date,$x, $this->d4_period_len, $this->period_days_array);       // 赋值借款日和本期还款日、本期期数
            //不需要了            $this->perDates[$x]->fixDueDays($this->periodAmounts[$x-1]->getPeriodDate()); // 修正本期天数
//需要再实现            $this->periodAmounts[$x]->fix_z_1_B($this->d2_real_day_rate,$this->d4_period_len,false); // $this->d2_real_day_rate, false 按期，true 按天
        }
        
        $this->setPeriodDate($this->d5_start_date,$last_date,$this->d3_total_Period,$this->d4_period_len,  $this->period_days_array); // 修正末期
        
//        $this->periodAmounts[$this->d3_total_Period+1] = new PeriodAmount(); // 多生成一个，data_due_z_1_B = 1；
  */      
        
     


        setPeriodDate(d5_start_date,d5_start_date,0,d4_period_len,period_days_array);
        
        Calendar last_date=null;
        for (int x=1; x <= d3_total_Period; x++)
        {
            last_date = getThisDate(x-1);
            setPeriodDate( m_fake_start, last_date, x, d4_period_len, period_days_array);       // 赋值借款日和本期还款日、本期期数
            //不需要了            $this->perDates[$x]->fixDueDays($this->periodAmounts[$x-1]->getPeriodDate()); // 修正本期天数
//需要再实现            $this->periodAmounts[$x]->fix_z_1_B($this->d2_real_day_rate,$this->d4_period_len,false); // $this->d2_real_day_rate, false 按期，true 按天
        }
        
        setPeriodDate(d5_start_date,last_date,d3_total_Period,d4_period_len,  period_days_array); // 修正末期

    
        
    }
    
    
    
    
//    private void setPeriodDate( Calendar start_date, Calendar last_date, int x, int period_days=0, int [] period_days_array=null)
    private void setPeriodDate( Calendar start_date, Calendar last_date, int x, int period_days, int [] period_days_array)
    {
        int xd=0;
        
        if ( x < 0 || x > d3_total_Period + 1) {
            return;
        }
        
//        $this->data_period_num = $x; //设置这是第几期的编号
        //        $this->data_start_date  = date_create_from_format("Y-m-d H:i:s",date_format($start_date,"Y-m-d 00:00:00"));
//        data_last_date[x] = date_create_from_format("Y-m-d H:i:s",date_format($last_date,"Y-m-d 00:00:00"));
        data_last_date[x] = (Calendar) last_date.clone();
//        data_last_date[x].set( last_date.get(field), last_date.get(field), last_date.get(field), last_date.get(field)
        data_period_date[x] = (Calendar) start_date.clone();
        
//        data_period_date[x] = date_create_from_format("Y-m-d H:i:s",date_format($start_date,"Y-m-d 00:00:00"));
        
        
        if ( x <= 0 ) {
            return;
        }
        
        if (period_days > 0) // 如果是大于0.则是按天
        {
            xd = x * period_days;
            
//            int per_Int = new DateInterval("P" . $xd . "D");
            
//            date_add($this->data_period_date[$x], $per_Int);
            data_period_date[x].add( Calendar.DATE , xd );
            
            
        } else { // 下面是月还，和半月还。如果是0，则是按半月还，间隔为x个半月.
            if (  0 >= period_days && period_days >= - 24 ) // 如果是-1..-24，则是按月还，间隔为-period_days月.
            {
//                $theday1 = getdate($start_date->getTimestamp());
                int mday = start_date.get(Calendar.DAY_OF_MONTH);
                
                
                
                ////////////// 1) 借款日31号的，首月之后调整到1号
                // 2： 按月的，借款日31号的，往后挪一天到1号，之后按月加，不用调整；首月多借了一天
                if ( mday == 31 && (period_days<0 || (x % 2==0) ) )  // 按月，按半月x是双数的
                {
                    if (x > 0) // 第2月开始平移到1号
                        //date_add($this->data_period_date[$x], new DateInterval("P1D"));
                    	data_period_date[x].add( Calendar.DATE , 1 );
                }
                ////////////// 1)
                
                
                
                ////////////// 2) 加 x 或者 (x+1)/2 、 (x-1)/2 个月
                if (period_days < 0)
                    xd = x * (- period_days); // 月还, 从首月，增加 x * per 个月
                else { // 半月还, 从首月，增加 x /2 个月
                	if (x % 2 == 0) // 半月，双数
                		xd = x / 2;
                	if (x % 2 == 1) // 半月，单数
                	{
                		if (mday >= 16) // 半月，单数，16号以后的
                			xd = (x + 1) / 2;
                		else // 半月，单数，15号以前的
                			xd = (x - 1) / 2;
                	}
                }

                
//                    $per_Int = new DateInterval("P" . $xd . "M");
//                    date_add($this->data_period_date[$x], $per_Int);
                data_period_date[x].add( Calendar.MONTH , xd );
                    
                ////////////// 2)
                
                
                int fixNum1 = mday; // 首期的开始日
                ////////////// 3) 半月，单数，15号以前，增加15天                
                if ( period_days == 0 && ( x % 2==1) && ( mday <= 15 ) ) { // 半月，单数，15号以前的，<=15号的，就加（x-1）/2个月，再加15天，再修正29,30
//                        date_add($this->data_period_date[$x],  new DateInterval("P15D"));                    
                	data_period_date[x].add( Calendar.DATE , 15 );
                	fixNum1 = fixNum1 + 15;
                }
                ////////////// 3)



                ////////////// 4) N月还；或者：半月，双数；或者：半月，单数，15号以前。修正 29,30号起始日的
                //// 半月 单数  14,15需要修正；  
                //// 半月 单数  29,30 不需要修正！！！！
                
                if ( period_days < 0 ||( x % 2==0) || ( mday <= 15 ) )
                {

                	if (  fixNum1 == 29 || fixNum1 == 30)
                	{ // !!!!!! PHP PHP : PHP 是 (fixNum1 == 29 || fixNum1 == 30) , JAVA 只要处理30号
                		Calendar aa = fix29_30( data_period_date[x], fixNum1 );
                		data_period_date[x] = aa;
                		
                	}
                }
                ////////////// 4)
                
                ////////////// 5) 半月，单数，>=16号的，就加（x+1）/2个月，再减15天，修正到day-15
                if ( period_days == 0 && ( (x % 2==1) && ( mday >= 16 ) ) )
                {
//                            date_sub($this->data_period_date[$x],  new DateInterval("P15D"));
                	data_period_date[x].add( Calendar.DATE , -15 );
                	
//                        	Calendar theday2 = (Calendar) data_period_date[x].clone();
//                            data_period_date[x] = date_create_from_format("Y-m-d H:i:s",$theday2['year']."-".$theday2['mon']."-".($theday1['mday']-15)." 00:00:00");
                	data_period_date[x].set(Calendar.DAY_OF_MONTH, mday -15);
                }
                ////////////// 5)
                
                
            } else if (period_days < - 24) // 需要采用后面的实际天数数组
            {
                int sumDays = 0;
                // int t_period_days_array[] = (int[]) period_days_array;
                if ( period_days_array != null )
                    for (int i = 0; i < x && i < period_days_array.length ; i ++) {
//                        if ( period_days_array[i] ))
                            sumDays += period_days_array[i];
                    }
//                per_Int = new DateInterval("P" . $sumDays . "D"); // 日期加sumDays个天
            }
        }
        
        //    echo date_format($date,"Y/m/d")."<br>";
        // $this->data_due_days = (int) date_diff($this->data_period_date,$this->data_last_date)->format("%a");  //这个没用了，后面需要重新 FixDueDays
        fixDueDays( x, last_date);
        
        
        return;
    }
    
    
    
    private Calendar fix29_30( Calendar thedate, int num )
    {
        //$thedate = date_create_from_format("Y-m-d H:i:s",date_format($thedate,"Y-m-d 00:00:00"));
        //$theday2 = getdate( $thedate->getTimestamp() );
    	Calendar aDate = (Calendar) thedate.clone();
        
        // 1：  借款日29号的，按月加，如果结果日不等于29（就是1），就减一天
        if ( num == 29 )
        {  // JAVA： 1月29日 + 一月 = 2月28日，不用处理；  PHP：1月29日 + 一月 = 3月1日，减一天；
        	
        	/* JAVA： 2017-1-29 + 一月 = 2017-2-28，不用处理；
             * JAVA： 2016-1-29 + 一月 = 2016-2-29，不用处理；
             * PHP：2017-1-29 + 一月 = 2017-3-1：减一天，成2017-2-28；
             * PHP：2016-1-29 + 一月 = 2016-2-29，不用处理；
             *
             */
        	
            if ( aDate.get(Calendar.DAY_OF_MONTH) != 29 && aDate.get(Calendar.DAY_OF_MONTH) != 28)
//                $thedate = date_sub( $thedate,new DateInterval("P1D"));
            	aDate.add(Calendar.DATE, -1);
        }
        // 3：  借款日30号的，day=30时, 先按月加，如果结果日不等于30，回到1号，如果是上月是闰月29，再减一天到2月29
        if ( num == 30 )
        {  	
        	/* JAVA： 2017-1-30 + 一月 = 2017-2-28，改成2017-3-1；  
        	 * JAVA： 2016-1-30 + 一月 = 2016-2-29，不用处理；        	
        	 * PHP：2017-1-30 + 一月 = 2017-3-2，改成2017-3-1；
        	 * PHP：2016-1-30 + 一月 = 2016-3-1，改成2017-3-1，再减一天，改成2016-2-29；
        	 * 
        	 */
        	
            if ( aDate.get(Calendar.DAY_OF_MONTH) < 5 )
            {  // PHP 的模式：只会是 3月日1,3月2日，等
            	
            	// PHP：1月30日 + 一月 = 3月2日，调整到1日；
            	aDate.set(Calendar.DAY_OF_MONTH, 1);
            	
            	Calendar aDate2 = (Calendar) aDate.clone();
                
            	// PHP：1月30日 + 一月 = 3月2日，调整到1日，如果再减1日是2月29日，就用2月29日； 	
            	aDate2.add(Calendar.DATE, -1);
            
                if ( aDate2.get(Calendar.DAY_OF_MONTH) == 29 )
                	aDate = aDate2;
                    // 3：  借款日30号的，day=30时, 先按月加，如果结果日不等于30，回到1号，如果是上月是闰月29，再减一天到2月29
            } else {
            	// JAVA 的模式
            	if ( aDate.get(Calendar.DAY_OF_MONTH) == 28 ) {        		
            		aDate.add(Calendar.DATE, 1);        		
            	}
            }
        }
        return aDate;
    }
    
    
    
    
    private void  fixDueDays( int num, Calendar date)
    { // 计算本期还款日和某日期（上期还款日）间隔的天数
        if ( num < 0 || num > d3_total_Period + 1)
            return;
        if ( data_due_days != null  && num < data_due_days.length  )
            data_due_days[num] = (int) com.wj.fin.wjutil.TheTools.date_diff(data_period_date[num], date);
    }
    
    
    
    public String echoData( boolean need_table) //=true )
    {
    	String echoStr=null;
    	
        if ( need_table ) {
            //echo date_default_timezone_get();

        	echoStr = "<table border=1 cellspacing=0 cellpadding=0>\n";
            for (int x=0; x <= d3_total_Period+1; x++) {
                echoStr.concat("    <tr>\n");
                
                
                echoStr = echoStr+"        <td>"+x+"</td>\n";
                //            $echoStr = $echoStr."        <td>".date_format($this->data_start_date,"Y-m-d")."</td>\n";
                echoStr = echoStr+"        <td>"+com.wj.fin.wjutil.TheTools.printDateShort(data_last_date[x])+"</td>\n";
                echoStr = echoStr+"        <td>"+com.wj.fin.wjutil.TheTools.printDateShort(data_period_date[x])+"</td>\n";
                echoStr = echoStr+"        <td>"+data_due_days[x]+"</td>\n";

                echoStr = echoStr+"\n";
                
                
                echoStr = echoStr+"    </tr>\n";
            }
            echoStr = echoStr+"</table>\n";
            
            
            return echoStr;
           
        }
        
        return echoStr;
        
    }
    
    
    
    
    public void prt() 
    {
    	System.out.println("dssd");
    	
    }


}




    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
