package calValue;

public class TheRates {

	private int d3_total_Period = 0;

	private double first_z_pai = 1;
	private double sum_z_pai = 0;

	private double [] data_z_R; // 当前期的本息率 = 1 + 年率 /360* 本期天数
	private double [] data_z_pai; // <当前期的息率>连乘积
	private double data_z_R_per; // 按整期的利率
	// private $data_z_ByDay; // 实际是按天算利息

	
	public TheRates(int num)
    {
    	initMe(num);
    }
	
	public void initMe(int num) {
		// $this->data_start_date = date_create();

		data_z_R = new double[num+2];
		data_z_pai = new double[num+2];

		d3_total_Period = num;

		first_z_pai = 1;
		sum_z_pai = 0;
		data_z_R_per = 0;

		for (int i = 0; i <= d3_total_Period + 1; i++) {
			data_z_R[i] = 0;
			data_z_pai[i] = 1;
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
		data_z_R = null;
		data_z_pai = null;
		// $this->data_z_ByDay = null;
	}

	private void set_z_R_per(double real_day_rate, int len) {

		if (len > 0) {
			data_z_R_per = len * real_day_rate;
		}
		if (len == 0) {
			data_z_R_per = 15 * real_day_rate;
		}
		if (len < 0) {
			data_z_R_per = 30 * (-len) * real_day_rate;
		}
	}

	private double get_z_R_per() {
		return data_z_R_per;
	}

	private double get_z_R(int num) { // 获取???
		if (num < 0 || num > d3_total_Period + 1)
			return 0;
		if (data_z_R != null && num < data_z_R.length) {
			return data_z_R[num];
		}
		return 0;
	}

	private void set_z_R(int num, double z1b) { // 获取???
		if (num < 0 || num > d3_total_Period + 1)
			return;
		if (data_z_R != null && num < data_z_R.length) {
			data_z_R[num] = z1b;
		}
		return;
	}

	private double get_z_pai(int num) { // 获取???
		if (num < 0 || num > d3_total_Period + 1)
			return 1;
		if (data_z_pai != null && num < data_z_pai.length) {
			return data_z_pai[num];
		}
		return 1;
	}

	private double set_z_pai(int num, double mult_pai, boolean useSelfDay) { // = false)) // 获取???
		double r = 0;
		if (num < 0 || num > d3_total_Period + 1)
			return 1;
 
		if ( useSelfDay ) {
			r = get_z_R(num);
		}else{
			r = get_z_R_per();
		}
		
		if (data_z_pai != null && num < data_z_pai.length) {
			data_z_pai[num] = (r + 1) * mult_pai;
		}
		return data_z_pai[num]; // 不用return 数字的 ????
	}

	public int getCount() { // ???
		return d3_total_Period;
	}

	private void fix_z_R(int x, double real_day_rate, int days, int len) {// = false)
		fix_z_R(x, real_day_rate, days, len, false);
	}

	private void fix_z_R(int x, double real_day_rate, int days, int len, boolean useSelfDay)// = false)
	{
		if (useSelfDay) {
			set_z_R(x, days * real_day_rate); // real_rate / 360.0;
			return;
		}

		if (x == 1 || x == d3_total_Period) {
			set_z_R(x, days * real_day_rate); // real_rate / 360.0;
			return;
		}

		if (len > 0) {
			set_z_R(x, len * real_day_rate);
		}
		if (len == 0) {
			set_z_R(x, 15 * real_day_rate);
		}
		if (len < 0) {
			set_z_R(x, 30 * (-len) * real_day_rate);
		}
	}

	private void cal_PerRate() { // 算月还,是按期,还是按天
		cal_PerRate(false);
	}

	private void cal_PerRate(boolean useSelfDay) { // 算月还,是按期,还是按天

		int num = getCount();

		double mult_pai=1; // 从 2 到 第 25 个 z_pai 求和
		
		sum_z_pai = 0;

		if (useSelfDay) {

			for (int x = num; x >= 1; x--) {
				mult_pai = get_z_pai(x + 1);
				set_z_pai(x, mult_pai,useSelfDay);
				sum_z_pai = sum_z_pai + mult_pai; // 从 2 到 第 25 个 z_pai 求和
			}

			first_z_pai = get_z_pai(1); // 第1 个 z_pai

		} else {
/*
			mult_pai = 1;
			for (int x = num; x >= 1; x--) {
				sum_z_pai = sum_z_pai + mult_pai; // 从 2 到 第 25 个 z_pai 求和
				mult_pai = (get_z_R_per() + 1) * mult_pai;
			}
			first_z_pai = mult_pai; // 第1 个 z_pai
*/			
			for (int x = num; x >= 1; x--) {
				mult_pai = get_z_pai(x + 1);
				set_z_pai(x, mult_pai,useSelfDay);
				sum_z_pai = sum_z_pai + mult_pai; // 从 2 到 第 25 个 z_pai 求和
			}
			
			first_z_pai = get_z_pai(1); // 第1 个 z_pai

			

		}

	}

	public double cal_Period_Amount(long total) {
		double amt = total * first_z_pai / sum_z_pai; // 求精确月供
		// $amt = round( $amt, 2, PHP_ROUND_HALF_UP ); // 求四舍五入到分月供
		// $this->d6_period_amount_round = round( ceil($this->d6_period_amount *100) /
		// 100, 2, PHP_ROUND_HALF_UP ); // 求向上取整到分月供
		return amt;
	}

	public long cal_Period_Interest(int num, long total_amt ) { // 算月还,是按期,还是按天
		return cal_Period_Interest(num, total_amt, false);
	}

	public long cal_Period_Interest( int num, long total_amt,  boolean useSelfDay){ // 算月还,是按期,还是按天
		double intD=0;
		long intL=0;
		if ( num < 1 || num > d3_total_Period )
			return intL;
		
		if ( useSelfDay ) { // 按天
			intD = total_amt * get_z_R(num);
		} else {
			intD = total_amt * get_z_R_per(  );
		}
	//$amt = round( $amt, 2, PHP_ROUND_HALF_UP ); // 求四舍五入到分月供
	// $this->d6_period_amount_round = round( ceil($this->d6_period_amount *100) / 100, 2, PHP_ROUND_HALF_UP ); // 求向上取整到分月供
		intL = ( long ) com.wj.fin.wjutil.TheTools.round_half_up( intD, 0 ); // 求四舍五入到分月供
		return intL;
	}

	public void cal_theRates(  calValue.TheDates theDates, double rate, boolean useDay ) {
		if ( theDates == null ) {
			return;
		}
		
		int len = theDates.getPeriod_Len();
		int num = theDates.getCount();
		
		if ( num <=0 ) return;
		if ( getCount() != num  ) {
			releaseMe();;
			initMe(num);
		}
		
		double real_day_rate = rate / 360.0;
		
		
		for (int x=1; x <= num; x++)
		{
			int days = theDates.getDueDays( x );
			
			fix_z_R( x, real_day_rate,days, len,useDay ); // $this->d2_real_day_rate, false 按期，true 按天
		}
		
		set_z_R_per( real_day_rate, len );
		cal_PerRate(useDay);


		
//       $this->d6_period_amount = $this->d1_all_loan * $first_z_pai / $sum_z_pai; // 求精确月供
	        //$this->d6_period_amount_round = round( $this->d6_period_amount, 2, PHP_ROUND_HALF_UP ); // 求四舍五入到分月供
	        // $this->d6_period_amount_round = round( ceil($this->d6_period_amount *100) / 100, 2, PHP_ROUND_HALF_UP ); // 求向上取整到分月供

		
		
	}

	public String echoData( boolean need_table ) { //$need_table=true )
		String echoStr = null;
		if ( need_table ) {
			//echo date_default_timezone_get();

			echoStr = "<table border=1 cellspacing=0 cellpadding=0>\n";
			for (int x=0; x <= d3_total_Period+1; x++) {
				echoStr.concat("    <tr>\n" );


				echoStr = echoStr + "        <td>"+x+"</td>\n";

				echoStr = echoStr + "        <td>"+data_z_pai[x]+"</td>\n";
				echoStr = echoStr+"        <td>"+data_z_R[x]+"</td>\n";

				echoStr = echoStr+"\n";


				echoStr = echoStr+"    </tr>\n";
			}
			echoStr = echoStr+"</table>\n";
			echoStr = echoStr+"_"+first_z_pai+"_"+sum_z_pai+"<br>\n";


//			echo $echoStr;

		}
		return echoStr;

	}
           
    
}