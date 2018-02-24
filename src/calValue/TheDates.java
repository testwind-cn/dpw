package calValue;

import java.util.Calendar;
import java.util.Date;

public class TheDates {
	
	private int ww_total_Period = 0;
    private int ww_period_len;
    private Calendar ww_start_date;
    
    //    private $data_start_date;// = date_create();        // 贷款首期借款日期
    private Calendar [] ww_last_date;// = date_create();         // 贷款上期还款日期
    private Calendar [] ww_period_date;// = date_create();       // 贷款本期还款日期
    private int [] ww_due_days;                         // 本期借款天数
    private int [] ww_period_days_array;
    
    private boolean ww_data_is_HeadRear_Period = false; // 这是通过日期比对,看实际头尾是否是正月,是就 true, 不是就 false
    
    
    
    public TheDates(int pp_num)
    {
    	initMe(pp_num);
    }
    
    private void initMe(int pp_num)
    {    	
        //        $this->data_start_date = date_create();
        
        ww_period_date = new Calendar[pp_num+2];
        ww_last_date = new Calendar[pp_num+2];
        ww_due_days = new int[pp_num+2];
        
        ww_total_Period = pp_num;
        
        
        
        for (int i=0 ; i<=ww_total_Period+1;i++){
            ww_period_date[i] = com.wj.fin.wjutil.TheTools.getDateShort();// new Date();
            ww_last_date[i] =  com.wj.fin.wjutil.TheTools.getDateShort();// new Date();
            ww_due_days[i] =  0;
        }
    	
    }
    
    
    private void releaseMe()
    {
//        echo 'Destroying: ';
        //, $this->name, PHP_EOL;
    
        for (int i = ww_total_Period + 1; i >= 0; i --) {
        	
            if ( ww_last_date != null && i < ww_last_date.length  && ww_last_date[i] != null )
            	ww_last_date[i] = null;
            
            if ( ww_period_date != null &&  i < ww_period_date.length  && ww_period_date[i] != null )
            	ww_period_date[i] = null;
            
            if ( ww_due_days != null &&  i < ww_due_days.length  && ww_due_days[i] != 0 )
            	ww_due_days[i] = 0;
        }
        ww_last_date = null;
        ww_period_date = null;
        ww_due_days = null;
    }
    
    
    private Calendar getLastDate(int pp_num)
    { // 获取上期还款日的一个副本
        if (pp_num < 0 || pp_num > ww_total_Period + 1)
            return null;
        if ( ww_last_date != null && pp_num < ww_last_date.length  && ww_last_date[pp_num] != null ) {
            return ww_last_date[pp_num];
        }
        return null;
    }
    
    
    private Calendar getThisDate(int num)
    { // 获取本期还款日的一个副本
        if (num < 0 || num > ww_total_Period + 1)
            return null;
        if ( ww_period_date != null && num < ww_period_date.length  && ww_period_date[num] != null ) {
            return ww_period_date[num];
        }
        return null;
    }
    
    
    
    public int getDueDays(int pp_num)
    { // 获取上期还款日的一个副本
        if ( pp_num < 0 || pp_num > ww_total_Period + 1)
            return 0;
        if ( ww_due_days != null && pp_num < ww_due_days.length  ) {
            return ww_due_days[pp_num];
        }
        return 0;
    }
    
    public int getCount()
    { // ???
        return ww_total_Period;
    }
    
    public int getPeriod_Len()
    { // ???
        return ww_period_len;
    }
    
    
    public boolean is_HeadRear_Period() {
    	return ww_data_is_HeadRear_Period;
    }
    
    private Calendar getShiftSameDay( Calendar pp_start_date) // shift 前后挪期， $is_month=false半月，true月
    {    	
    	return getShiftSameDay( pp_start_date,0,true);    	
    }
    
    private Calendar getShiftSameDay( Calendar pp_start_date,int pp_shift) // shift 前后挪期， $is_month=false半月，true月
    {    	
    	return getShiftSameDay( pp_start_date,pp_shift,true);    	
    }
    
    private Calendar getShiftSameDay( Calendar pp_start_date, int pp_shift, boolean pp_is_month) // shift 前后挪期， $is_month=false半月，true月
    {
    	//( Calendar start_date, int shift=0, boolean is_month=true) // shift 前后挪期， $is_month=false半月，true月        
        // ?? 检查 $start_date 是合法日期 !!!!!
    	
//    	Calendar theday1 = start_date;

    	int ll_year = pp_start_date.get(Calendar.YEAR);
        int ll_month = pp_start_date.get(Calendar.MONTH) + 1;
        int ll_day = pp_start_date.get(Calendar.DAY_OF_MONTH);
        
        if ( ll_day > 28 ) ll_day = 28; //只会是 1-28
        
        if ( pp_is_month == false ) { // 半月
            if ( ll_day == 15 ) ll_day = 16;
            if ( ll_day == 14 ) ll_day = 13;
            
            if ( pp_shift % 2 != 0 ) { // 单数半月
                if ( ll_day < 15 ){
                    ll_day = ll_day + 15;
                    pp_shift = pp_shift - 1; //去掉一个半月，人工挪后半月
                } else {
                    ll_day = ll_day - 15;
                    pp_shift = pp_shift + 1; //去掉一个半月，人工前移半月
                }
            }
            pp_shift = pp_shift / 2; // floor( shift / 2 );
        }
        
        
        // 月 和 半月 相同模式
        //$add_year = floor( ( $m_month + $shift - 1 ) / 12 );
        int ll_add_year = ( ll_month + pp_shift - 1 ) / 12 ;
        ll_month = ( ( ll_month + pp_shift - 1 ) % 12 ) + 1;
        if ( ll_month <= 0 ) ll_month = ll_month + 12;
        ll_year = ll_year + ll_add_year;
        
        
        
        Calendar ll_date = Calendar.getInstance( pp_start_date.getTimeZone(), java.util.Locale.ENGLISH );
        ll_date.set( ll_year, ll_month - 1 ,  ll_day, 0, 0, 0);
        ll_date.set(Calendar.MILLISECOND, 0); 
        
//        $date = date_create_from_format("Y-m-d H:i:s", $m_year."-".$m_month."-".$m_day." 00:00:00");
        
        return ll_date;
        
    }
    
    
    private Calendar getFakeStartDate( Calendar pp_start_date, int pp_days_len, int pp_spec_mday,boolean pp_spec_mode ) {
        
        // ?? 检查 $start_date 是合法日期 !!!!!
    	Calendar ll_fake_start = null;
        
    	Calendar ll_Date = (Calendar) pp_start_date.clone(); // Calendar.getInstance( start_date.getTimeZone(), java.util.Locale.ENGLISH );
//    	calendar.setTime(start_date.clone());
  //      calendar.set( m_year, m_month - 1 ,  m_day, 0, 0, 0);
    	ll_Date.set(Calendar.HOUR_OF_DAY, 0);
    	ll_Date.set(Calendar.MINUTE, 0);
    	ll_Date.set(Calendar.SECOND, 0);
        ll_Date.set(Calendar.MILLISECOND, 0); 
    	
//        $date=date_create_from_format("Y-m-d H:i:s", date_format( $start_date, "Y-m-d 00:00:00"));
        
        if ( pp_days_len > 0 || pp_spec_mday <= 0 || pp_spec_mday > 28 ) { // 按日模式不修改，只改半月和月, 不指定日期的，也返回
            
            ll_fake_start = ll_Date;
            return ll_fake_start;
        }
        
        
//        $theday1 = getdate( $date->getTimestamp() );
        int ll_year = ll_Date.get(Calendar.YEAR); //.$theday1['year'];
        int ll_month = ll_Date.get(Calendar.MONTH) + 1; // $theday1['mon'];
        int ll_day = ll_Date.get(Calendar.DAY_OF_MONTH); //$theday1['mday'];
        
        
        
        //////////////// 2.修正指定日期
        /*
        if ( spec_mday < 0 ) {
            spec_mday = m_day;
        }
        if ( spec_mday > 28 ) spec_mday = 28; // spec_mode 按实际传入的
        */
        
        if (  pp_days_len == 0 ) { ///半月模式
            if ( pp_spec_mday !=0 ) { //注意半月不指定日期模式！！！ 不是0任意天模式，
                if ( pp_spec_mday == 15 ) pp_spec_mday = 16;
                if ( pp_spec_mday == 14 ) pp_spec_mday = 13;
            }
            
            if ( ll_day + 15 <= pp_spec_mday ) pp_spec_mday -= 15; //** 半月模式的，借款日的mday + 15 <=指定日mday， 指定日mday-=15
            if ( pp_spec_mday + 15 <= ll_day ) pp_spec_mday += 15; //** 半月模式的，指定日mday + 15 <= 借款日的mday， 指定日mday+=15
            
        }
        
        
        if ( pp_spec_mday > 0 ) {
            if ( ll_day <= pp_spec_mday ) {
                
            	
            	ll_Date.set(ll_year, ll_month,pp_spec_mday,0,0,0);
				ll_Date.set(Calendar.MILLISECOND, 0); 
				
//                $date=date_create_from_format("Y-m-d H:i:s", date_format($start_date,"Y-m-d 00:00:00"));
//                $date->setDate ( $m_year , $m_month , $spec_mday );
                ll_fake_start = ll_Date;
                
                int ll_diff = com.wj.fin.wjutil.TheTools.date_diff(ll_fake_start, pp_start_date);
            	
                
//                $m_diff = (int) date_diff( $m_fake_start ,$start_date)->format("%a");
                
                if ( pp_spec_mode == false ) {
                    
                    ll_fake_start = ll_Date;//$this->getShiftSameDay($date);         // 不用
                } else {
                    if ( ll_diff >15 || ( ll_diff > 7 & pp_days_len==0)  ) {
                        ll_fake_start = getShiftSameDay( ll_Date, -1, pp_days_len!=0 );
                    } else { // 指定日mday - 借款日的mday <= 15
                        // 月：用指定日生成本月  --做假借款日
                        // 半月：用指定日生成本期  --做假借款日
                    	ll_fake_start = ll_Date; // 不用
                    }
                    
                }
                
                
            }else { //指定日mday<借款日的mday
                
//                $date=date_create_from_format("Y-m-d H:i:s", date_format($start_date,"Y-m-d 00:00:00"));
//                $date->setDate ( $m_year , $m_month , $spec_mday );
//                $m_fake_start = $this->getShiftSameDay($date,1,$days_len!=0);
            	ll_Date.set(ll_year, ll_month,pp_spec_mday,0,0,0);
				ll_Date.set(Calendar.MILLISECOND, 0); 
				ll_fake_start = getShiftSameDay( ll_Date, 1, pp_days_len!=0 );
                
//                $m_diff = (int) date_diff( $m_fake_start ,$start_date)->format("%a");
                int ll_diff = com.wj.fin.wjutil.TheTools.date_diff(ll_fake_start, pp_start_date);
                
                if ( pp_spec_mode == false ) {
//                    m_fake_start = $this->getShiftSameDay($date,1,$days_len!=0); // 不用
                	ll_fake_start = getShiftSameDay( ll_Date, 1, pp_days_len!=0 );
                } else {
                    if ( ll_diff >15 || (ll_diff > 7 & pp_days_len==0)  ) {
                        ll_fake_start = ll_Date;
                    } else {
                        ll_fake_start = getShiftSameDay(ll_Date,1,pp_days_len!=0); // 不用
                    }
                    
                }
                
            }
            
        } else { // 任意天的，支持 29,30,31的
            //            $m_fake_start = date_create_from_format("Y-m-d H:i:s", date_format($this->d5_start_date,"Y-m-d 00:00:00"));
        }
        
        return ll_fake_start;
        
    }
    
    
    
//    public cal_theDates( total_Period, days_len=-1, start_date=null, spec_mday=0, spec_mode=false,days_array=null)
    public void cal_theDates( int pp_total_Period, int pp_days_len, String pp_start_date, int pp_spec_mday, boolean pp_spec_mode,int [] pp_days_array)
    {
        Calendar ll_fake_start = null;
        
        int ll_num = getCount();
        if ( pp_total_Period <=0 ) return;
        if ( pp_total_Period != ll_num  ) {
        	releaseMe();
        	initMe(pp_total_Period);
        }
        
        
        ww_total_Period = pp_total_Period; // 总期数不能小于1
        ww_period_len = pp_days_len; // -1=按自然月还， 1-365=按天数周期还，-25=按后面的还款天表

        ww_period_days_array = null;
        if ( pp_days_array != null && pp_days_array.length > 0) {
        	ww_period_days_array = new int[pp_days_array.length];
        	System.arraycopy(pp_days_array, 0, ww_period_days_array, 0, pp_days_array.length);
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
        
        
        Calendar ll_date = com.wj.fin.wjutil.TheTools.getDateShort(pp_start_date);// new Date();
        ww_start_date = ll_date;
        
        //////////////// 1.设定开始日期
        

        
        
//        $m_fake_start = $this->getFakeStartDate($this->d5_start_date,$days_len,$spec_mday,$spec_mode);
        ll_fake_start = getFakeStartDate( ww_start_date, pp_days_len, pp_spec_mday, pp_spec_mode);
        
        
        
        
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
        
     


        setPeriodDate(ww_start_date,ww_start_date,0,ww_period_len,ww_period_days_array);
        
        Calendar ll_last_date=null;
        for (int x=1; x <= ww_total_Period; x++)
        {
            ll_last_date = getThisDate(x-1);
            setPeriodDate( ll_fake_start, ll_last_date, x, ww_period_len, ww_period_days_array);       // 赋值借款日和本期还款日、本期期数
            //不需要了            $this->perDates[$x]->fixDueDays($this->periodAmounts[$x-1]->getPeriodDate()); // 修正本期天数
//需要再实现            $this->periodAmounts[$x]->fix_z_1_B($this->d2_real_day_rate,$this->d4_period_len,false); // $this->d2_real_day_rate, false 按期，true 按天
        }
        
        setPeriodDate(ww_start_date,ll_last_date,ww_total_Period,ww_period_len,  ww_period_days_array); // 修正末期

    
        
    }
    
    
    
    
//    private void setPeriodDate( Calendar start_date, Calendar last_date, int x, int period_days=0, int [] period_days_array=null)
    private void setPeriodDate( Calendar pp_start_date, Calendar pp_last_date, int x, int pp_period_days, int [] pp_period_days_array)
    {
        int xd=0;
        
        if ( x < 0 || x > ww_total_Period + 1) {
            return;
        }
        
//        $this->data_period_num = $x; //设置这是第几期的编号
        //        $this->data_start_date  = date_create_from_format("Y-m-d H:i:s",date_format($start_date,"Y-m-d 00:00:00"));
//        data_last_date[x] = date_create_from_format("Y-m-d H:i:s",date_format($last_date,"Y-m-d 00:00:00"));
        ww_last_date[x] = (Calendar) pp_last_date.clone();
//        data_last_date[x].set( last_date.get(field), last_date.get(field), last_date.get(field), last_date.get(field)
        ww_period_date[x] = (Calendar) pp_start_date.clone();
        
//        data_period_date[x] = date_create_from_format("Y-m-d H:i:s",date_format($start_date,"Y-m-d 00:00:00"));
        
        
        if ( x <= 0 ) {
            return;
        }
        
        if (pp_period_days > 0) // 如果是大于0.则是按天
        {
            xd = x * pp_period_days;
            
//            int per_Int = new DateInterval("P" . $xd . "D");
            
//            date_add($this->data_period_date[$x], $per_Int);
            ww_period_date[x].add( Calendar.DATE , xd );
            
            
        } else { // 下面是月还，和半月还。如果是0，则是按半月还，间隔为x个半月.
            if (  0 >= pp_period_days && pp_period_days >= - 24 ) // 如果是-1..-24，则是按月还，间隔为-period_days月.
            {
//                $theday1 = getdate($start_date->getTimestamp());
                int ll_mday = pp_start_date.get(Calendar.DAY_OF_MONTH);
                
                
                
                ////////////// 1) 借款日31号的，首月之后调整到1号
                // 2： 按月的，借款日31号的，往后挪一天到1号，之后按月加，不用调整；首月多借了一天
                if ( ll_mday == 31 && (pp_period_days<0 || (x % 2==0) ) )  // 按月，按半月x是双数的
                {
                    if (x > 0) // 第2月开始平移到1号
                        //date_add($this->data_period_date[$x], new DateInterval("P1D"));
                    	ww_period_date[x].add( Calendar.DATE , 1 );
                }
                ////////////// 1)
                
                
                
                ////////////// 2) 加 x 或者 (x+1)/2 、 (x-1)/2 个月
                if (pp_period_days < 0)
                    xd = x * (- pp_period_days); // 月还, 从首月，增加 x * per 个月
                else { // 半月还, 从首月，增加 x /2 个月
                	if (x % 2 == 0) // 半月，双数
                		xd = x / 2;
                	if (x % 2 == 1) // 半月，单数
                	{
                		if (ll_mday >= 16) // 半月，单数，16号以后的
                			xd = (x + 1) / 2;
                		else // 半月，单数，15号以前的
                			xd = (x - 1) / 2;
                	}
                }

                
//                    $per_Int = new DateInterval("P" . $xd . "M");
//                    date_add($this->data_period_date[$x], $per_Int);
                ww_period_date[x].add( Calendar.MONTH , xd );
                    
                ////////////// 2)
                
                
                int ll_fixNum1 = ll_mday; // 首期的开始日
                ////////////// 3) 半月，单数，15号以前，增加15天                
                if ( pp_period_days == 0 && ( x % 2==1) && ( ll_mday <= 15 ) ) { // 半月，单数，15号以前的，<=15号的，就加（x-1）/2个月，再加15天，再修正29,30
//                        date_add($this->data_period_date[$x],  new DateInterval("P15D"));                    
                	ww_period_date[x].add( Calendar.DATE , 15 );
                	ll_fixNum1 = ll_fixNum1 + 15;
                }
                ////////////// 3)



                ////////////// 4) N月还；或者：半月，双数；或者：半月，单数，15号以前。修正 29,30号起始日的
                //// 半月 单数  14,15需要修正；  
                //// 半月 单数  29,30 不需要修正！！！！
                
                if ( pp_period_days < 0 ||( x % 2==0) || ( ll_mday <= 15 ) )
                {

                	if (  ll_fixNum1 == 29 || ll_fixNum1 == 30 )
                	{ // !!!!!! PHP PHP : PHP 是 (fixNum1 == 29 || fixNum1 == 30) , JAVA 只要处理30号
                		Calendar aa = fix29_30( ww_period_date[x], ll_fixNum1 );
                		ww_period_date[x] = aa;
                		
                	}
                }
                ////////////// 4)
                
                ////////////// 5) 半月，单数，>=16号的，就加（x+1）/2个月，再减15天，修正到day-15
                if ( pp_period_days == 0 && ( (x % 2==1) && ( ll_mday >= 16 ) ) )
                {
//                            date_sub($this->data_period_date[$x],  new DateInterval("P15D"));
                	ww_period_date[x].add( Calendar.DATE , -15 );
                	
//                        	Calendar theday2 = (Calendar) data_period_date[x].clone();
//                            data_period_date[x] = date_create_from_format("Y-m-d H:i:s",$theday2['year']."-".$theday2['mon']."-".($theday1['mday']-15)." 00:00:00");
                	ww_period_date[x].set(Calendar.DAY_OF_MONTH, ll_mday -15);
                }
                ////////////// 5)
                
                
            } else if (pp_period_days < - 24) // 需要采用后面的实际天数数组
            {
                int ll_sumDays = 0;
                // int t_period_days_array[] = (int[]) period_days_array;
                if ( pp_period_days_array != null )
                    for (int i = 0; i < x && i < pp_period_days_array.length ; i ++) {
//                        if ( period_days_array[i] ))
                            ll_sumDays += pp_period_days_array[i];
                    }
//                per_Int = new DateInterval("P" . $sumDays . "D"); // 日期加sumDays个天
            }
        }
        
        //    echo date_format($date,"Y/m/d")."<br>";
        // $this->data_due_days = (int) date_diff($this->data_period_date,$this->data_last_date)->format("%a");  //这个没用了，后面需要重新 FixDueDays
        fixDueDays( x, pp_last_date);
        
        
        return;
    }
    
    
    
    private Calendar fix29_30( Calendar pp_thedate, int pp_num )
    {
        //$thedate = date_create_from_format("Y-m-d H:i:s",date_format($thedate,"Y-m-d 00:00:00"));
        //$theday2 = getdate( $thedate->getTimestamp() );
    	Calendar ll_Date = (Calendar) pp_thedate.clone();
    	int ll_mday2, ll_mday1 = ll_Date.get(Calendar.DAY_OF_MONTH);
        
        // 1：  借款日29号的，按月加，如果结果日不等于29（就是1），就减一天
        if ( pp_num == 29 )
        {  // JAVA： 1月29日 + 一月 = 2月28日，不用处理；  PHP：1月29日 + 一月 = 3月1日，减一天；
        	
        	/* JAVA： 2017-1-29 + 一月 = 2017-2-28，不用处理；
             * JAVA： 2016-1-29 + 一月 = 2016-2-29，不用处理；
             * PHP：2017-1-29 + 一月 = 2017-3-1：减一天，成2017-2-28；
             * PHP：2016-1-29 + 一月 = 2016-2-29，不用处理；
             *
             */
        	
            if ( ll_mday1 != 29 && ll_mday1 != 28)
//                $thedate = date_sub( $thedate,new DateInterval("P1D"));
            	ll_Date.add(Calendar.DATE, -1);
        }
        // 3：  借款日30号的，day=30时, 先按月加，如果结果日不等于30，回到1号，如果是上月是闰月29，再减一天到2月29
        if ( pp_num == 30 )
        {  	
        	/* JAVA： 2017-1-30 + 一月 = 2017-2-28，改成2017-3-1；  
        	 * JAVA： 2016-1-30 + 一月 = 2016-2-29，不用处理；        	
        	 * PHP：2017-1-30 + 一月 = 2017-3-2，改成2017-3-1；
        	 * PHP：2016-1-30 + 一月 = 2016-3-1，改成2017-3-1，再减一天，改成2016-2-29；
        	 * 
        	 */
        	
            if ( ll_mday1 < 5 )
            {  // PHP 的模式：只会是 3月日1,3月2日，等
            	
            	// PHP：1月30日 + 一月 = 3月2日，调整到1日；
            	ll_Date.set(Calendar.DAY_OF_MONTH, 1);
            	
            	Calendar ll_aDate2 = (Calendar) ll_Date.clone();
                
            	// PHP：1月30日 + 一月 = 3月2日，调整到1日，如果再减1日是2月29日，就用2月29日； 	
            	ll_aDate2.add(Calendar.DATE, -1);
            	
            	ll_mday2 = ll_aDate2.get(Calendar.DAY_OF_MONTH);
            
                if ( ll_mday2 == 29 )
                	ll_Date = ll_aDate2;
                    // 3：  借款日30号的，day=30时, 先按月加，如果结果日不等于30，回到1号，如果是上月是闰月29，再减一天到2月29
            } else {
            	// JAVA 的模式
            	if ( ll_mday1 == 28 ) {        		
            		ll_Date.add(Calendar.DATE, 1);        		
            	}
            }
        }
        return ll_Date;
    }
    
    
    
    
    private void  fixDueDays( int pp_num, Calendar pp_aDate)
    { // 计算本期还款日和某日期（上期还款日）间隔的天数
        if ( pp_num < 0 || pp_num > ww_total_Period + 1)
            return;
        if ( ww_due_days != null  && pp_num < ww_due_days.length  )
            ww_due_days[pp_num] = (int) com.wj.fin.wjutil.TheTools.date_diff(ww_period_date[pp_num], pp_aDate);
    }
    
    
    
    public String echoData( boolean pp_need_table) //=true )
    {
    	String ll_echoStr=null;
    	
        if ( pp_need_table ) {
            //echo date_default_timezone_get();

        	ll_echoStr = "<table border=1 cellspacing=0 cellpadding=0>\n";
            for (int x=0; x <= ww_total_Period+1; x++) {
                ll_echoStr.concat("    <tr>\n");
                
                
                ll_echoStr = ll_echoStr+"        <td>"+x+"</td>\n";
                //            $echoStr = $echoStr."        <td>".date_format($this->data_start_date,"Y-m-d")."</td>\n";
                ll_echoStr = ll_echoStr+"        <td>"+com.wj.fin.wjutil.TheTools.printDateShort(ww_last_date[x])+"</td>\n";
                ll_echoStr = ll_echoStr+"        <td>"+com.wj.fin.wjutil.TheTools.printDateShort(ww_period_date[x])+"</td>\n";
                ll_echoStr = ll_echoStr+"        <td>"+ww_due_days[x]+"</td>\n";

                ll_echoStr = ll_echoStr+"\n";
                
                
                ll_echoStr = ll_echoStr+"    </tr>\n";
            }
            ll_echoStr = ll_echoStr+"</table>\n";
            
            
            return ll_echoStr;
           
        }
        
        return ll_echoStr;
        
    }
    
    
    
    
    public void prt() 
    {
    	System.out.println("dssd");
    	
    }


}




    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
