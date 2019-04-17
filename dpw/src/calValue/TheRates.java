package calValue;

public class TheRates {

	private int ww_total_Period = 0;

	private double ww_first_z_PI_day = 1;
	private double ww_sum_z_PI_day = 0;
	
	private double ww_first_z_PI_per = 1;
	private double ww_sum_z_PI_per = 0;

	private double [] ww_data_z_R_day; // 当前期的息率 = 年率 /360* 本期天数
	private double [] ww_data_z_PI_day; // <1+当前期的息率>连乘积
	private double    ww_data_z_R_per; // 按整期的利率
	private double [] ww_data_z_PI_per; // <1+整期的息率>连乘积
	// private $data_z_ByDay; // 实际是按天算利息

	
	public TheRates(int pp_num)
    {
    	initMe(pp_num);
    }
	
	public void initMe(int pp_num) {
		// $this->data_start_date = date_create();

		ww_data_z_R_day = new double[pp_num+2];
		ww_data_z_PI_day = new double[pp_num+2];
		ww_data_z_PI_per = new double[pp_num+2];

		ww_total_Period = pp_num;

		ww_first_z_PI_day = 1;
		ww_sum_z_PI_day = 0;
		ww_first_z_PI_per = 1;
		ww_sum_z_PI_per = 0;
		
		ww_data_z_R_per = 0;

		for (int i = 0; i <= ww_total_Period + 1; i++) {
			ww_data_z_R_day[i] = 0;
			ww_data_z_PI_day[i] = 1;
			ww_data_z_PI_per[i] = 1;
			// $this->data_z_ByDay[$i] = false;
		}

	}

	public void releaseMe() {
		// echo 'Destroying: ';
		// , $this->name, PHP_EOL;
		/*
		 * for (int i = d3_total_Period + 1; i >= 0; i --) { if
		 * (is_array($this->data_z_R) && isset($this->data_z_R[$i]))
		 * unset($this->data_z_R[$i]); if (is_array($this->data_z_pai) &&
		 * isset($this->data_z_pai[$i])) unset($this->data_z_pai[$i]); // if
		 * (is_array($this->data_z_ByDay) && isset($this->data_z_ByDay[$i])) //
		 * unset($this->data_z_ByDay[$i]); }
		 */
		ww_data_z_R_day = null;
		ww_data_z_PI_day = null;
		ww_data_z_PI_per = null;
		// $this->data_z_ByDay = null;
	}

	private void set_z_R_per(double pp_real_day_rate, int pp_len) {

		if (pp_len > 0) {
			ww_data_z_R_per = pp_len * pp_real_day_rate;
		}
		if (pp_len == 0) {
			ww_data_z_R_per = 15 * pp_real_day_rate;
		}
		if (pp_len < 0 & pp_len > -25 ) {
			ww_data_z_R_per = 30 * (-pp_len) * pp_real_day_rate;
		}
		
		if (pp_len <= -25 ) {
			ww_data_z_R_per = 0;
		}
	}

	private double get_z_R_per() {
		return ww_data_z_R_per;
	}
	
	private void set_z_R_day(int pp_num, double pp_z_R) { // 获取???
		if (pp_num < 0 || pp_num > ww_total_Period + 1)
			return;
		if (ww_data_z_R_day != null && pp_num < ww_data_z_R_day.length) {
			ww_data_z_R_day[pp_num] = pp_z_R;
		}
		return;
	}

	private double get_z_R_day(int pp_num) { // 获取???
		if (pp_num < 0 || pp_num > ww_total_Period + 1)
			return 0;
		if (ww_data_z_R_day != null && pp_num < ww_data_z_R_day.length) {
			return ww_data_z_R_day[pp_num];
		}
		return 0;
	}
	
	
	private double set_z_PI_day(int pp_num, double pp_mult_pai) { // = false)) // 获取???
		double r = 0;
		if (pp_num < 0 || pp_num > ww_total_Period + 1)
			return 1;
 
		r = get_z_R_day(pp_num);
				
		if (ww_data_z_PI_day != null && pp_num < ww_data_z_PI_day.length) {
			ww_data_z_PI_day[pp_num] = (r + 1) * pp_mult_pai;
		}
		return ww_data_z_PI_day[pp_num]; // 不用return 数字的 ????
	}

	

	private double get_z_PI_day(int pp_num) { // 获取???
		if (pp_num < 0 || pp_num > ww_total_Period + 1)
			return 1;
		if (ww_data_z_PI_day != null && pp_num < ww_data_z_PI_day.length) {
			return ww_data_z_PI_day[pp_num];
		}
		return 1;
	}
	
	
	private double set_z_PI_per(int pp_num, double pp_mult_pai) { // = false)) // 获取???
		double r = 0;
		if (pp_num < 0 || pp_num > ww_total_Period + 1)
			return 1;
 
		r = get_z_R_per();
		
		
		if (ww_data_z_PI_per != null && pp_num < ww_data_z_PI_per.length) {
			ww_data_z_PI_per[pp_num] = (r + 1) * pp_mult_pai;
		}
		return ww_data_z_PI_per[pp_num]; // 不用return 数字的 ????
	}
	
	private double get_z_PI_per(int pp_num) { // 获取???
		if (pp_num < 0 || pp_num > ww_total_Period + 1)
			return 1;
		if (ww_data_z_PI_per != null && pp_num < ww_data_z_PI_per.length) {
			return ww_data_z_PI_per[pp_num];
		}
		return 1;
	}

	

	public int getCount() { // ???
		return ww_total_Period;
	}

	private void fix_z_R(int x, double pp_real_day_rate, int pp_days, int pp_len) {// = false)
		fix_z_R(x, pp_real_day_rate, pp_days, pp_len, false);
	}

	private void fix_z_R(int x, double pp_real_day_rate, int pp_days, int pp_len, boolean pp_useSelfDay)// = false)
	{
		if (pp_useSelfDay) {
			set_z_R_day(x, pp_days * pp_real_day_rate); // real_rate / 360.0;
			return;
		}

		if (x == 1 || x == ww_total_Period) {
			set_z_R_day(x, pp_days * pp_real_day_rate); // real_rate / 360.0;
			return;
		}

		if (pp_len > 0) {
			set_z_R_day(x, pp_len * pp_real_day_rate);
		}
		if (pp_len == 0) {
			set_z_R_day(x, 15 * pp_real_day_rate);
		}
		if (pp_len < 0) {
			set_z_R_day(x, 30 * (-pp_len) * pp_real_day_rate);
		}
	}

	
	private void cal_Rate_PI() { // 算月还,是按期,还是按天

		int ll_num = getCount();

		double ll_mult_pai=1; // 从 2 到 第 25 个 z_pai 求和
		
		
		


		ww_sum_z_PI_day = 0;
		for (int x = ll_num; x >= 1; x--) {
			ll_mult_pai = get_z_PI_day(x + 1);
			set_z_PI_day(x, ll_mult_pai);
			ww_sum_z_PI_day = ww_sum_z_PI_day + ll_mult_pai; // 从 2 到 第 25 个 z_pai 求和
		}

		ww_first_z_PI_day = get_z_PI_day(1); // 第1 个 z_pai


		
		
/*
			mult_pai = 1;
			for (int x = num; x >= 1; x--) {
				sum_z_pai = sum_z_pai + mult_pai; // 从 2 到 第 25 个 z_pai 求和
				mult_pai = (get_z_R_per() + 1) * mult_pai;
			}
			first_z_pai = mult_pai; // 第1 个 z_pai
*/		
		ww_sum_z_PI_per = 0;
		for (int x = ll_num; x >= 1; x--) {
			ll_mult_pai = get_z_PI_per(x + 1);
			set_z_PI_per(x, ll_mult_pai);
			ww_sum_z_PI_per = ww_sum_z_PI_per + ll_mult_pai; // 从 2 到 第 25 个 z_pai 求和
		}
			
		ww_first_z_PI_per = get_z_PI_per(1); // 第1 个 z_pai

	}

	public double cal_Average_Payment(long pp_total,  boolean pp_useSelfDay) {
		double ll_amt=0;
		if ( pp_useSelfDay ) { // 按天
			ll_amt = pp_total * ww_first_z_PI_day / ww_sum_z_PI_day; // 求精确月供
		} else {
			ll_amt = pp_total * ww_first_z_PI_per / ww_sum_z_PI_per; // 求精确月供			
		}
		// $amt = round( $amt, 2, PHP_ROUND_HALF_UP ); // 求四舍五入到分月供
		// $this->d6_period_amount_round = round( ceil($this->d6_period_amount *100) /
		// 100, 2, PHP_ROUND_HALF_UP ); // 求向上取整到分月供
		return ll_amt;
	}

	public long cal_Period_Interest(int pp_num, long pp_total_amt ) { // 算月还,是按期,还是按天
		return cal_Period_Interest(pp_num, pp_total_amt, false);
	}

	public long cal_Period_Interest( int pp_num, long pp_total_amt,  boolean pp_useSelfDay){ // 算月还,是按期,还是按天
		double ll_intD=0;
		long ll_intL=0;
		if ( pp_num < 1 || pp_num > ww_total_Period )
			return ll_intL;
		
		if ( pp_useSelfDay ) { // 按天
			ll_intD = pp_total_amt * get_z_R_day(pp_num);
		} else {
			ll_intD = pp_total_amt * get_z_R_per(  );
		}
	//$amt = round( $amt, 2, PHP_ROUND_HALF_UP ); // 求四舍五入到分月供
	// $this->d6_period_amount_round = round( ceil($this->d6_period_amount *100) / 100, 2, PHP_ROUND_HALF_UP ); // 求向上取整到分月供
		ll_intL = ( long ) com.wj.fin.wjutil.TheTools.round_half_up( ll_intD, 0 ); // 求四舍五入到分月供
		return ll_intL;
	}

	public void cal_theRates(  calValue.TheDates pp_theDates, double pp_rate, boolean pp_useDay ) {
		if ( pp_theDates == null ) {
			return;
		}
		
		int ll_len = pp_theDates.getPeriod_Len();
		int ll_num = pp_theDates.getCount();
		
		if ( ll_num <=0 ) return;
		if ( getCount() != ll_num  ) {
			releaseMe();;
			initMe(ll_num);
		}
		
		double ll_real_day_rate = pp_rate / 360.0;
		
		
		for (int x=1; x <= ll_num; x++)
		{
			int ll_days = pp_theDates.getDueDays( x );
			
			// fix_z_R( x, real_day_rate,days, len,useDay ); // $this->d2_real_day_rate, false 按期，true 按天
			
			set_z_R_day(x, ll_days * ll_real_day_rate); 
		}		
		set_z_R_per( ll_real_day_rate, ll_len );
		
		cal_Rate_PI();


		
//       $this->d6_period_amount = $this->d1_all_loan * $first_z_pai / $sum_z_pai; // 求精确月供
	        //$this->d6_period_amount_round = round( $this->d6_period_amount, 2, PHP_ROUND_HALF_UP ); // 求四舍五入到分月供
	        // $this->d6_period_amount_round = round( ceil($this->d6_period_amount *100) / 100, 2, PHP_ROUND_HALF_UP ); // 求向上取整到分月供

		
		
	}

	public String echoData( boolean pp_need_table ) { //$need_table=true )
		String ll_echoStr = null;
		if ( pp_need_table ) {
			//echo date_default_timezone_get();

			ll_echoStr = "<table border=1 cellspacing=0 cellpadding=0>\n";
			for (int x=0; x <= ww_total_Period+1; x++) {
				ll_echoStr.concat("    <tr>\n" );


				ll_echoStr = ll_echoStr + "        <td>"+x+"</td>\n";

				ll_echoStr = ll_echoStr + "        <td>"+ww_data_z_PI_day[x]+"</td>\n";
				ll_echoStr = ll_echoStr+"        <td>"+ww_data_z_R_day[x]+"</td>\n";

				ll_echoStr = ll_echoStr+"\n";


				ll_echoStr = ll_echoStr+"    </tr>\n";
			}
			ll_echoStr = ll_echoStr+"</table>\n";
			ll_echoStr = ll_echoStr+"_"+ww_first_z_PI_day+"_"+ww_sum_z_PI_day+"<br>\n";


//			echo $echoStr;

		}
		return ll_echoStr;

	}
           
    
}