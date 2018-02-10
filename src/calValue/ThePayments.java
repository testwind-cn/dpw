package calValue;

public class ThePayments {

	// 1-1098按设置固定天（ 多期、一期），
	// 0按半月，-1按月，-2双月，-3三月，-4四月，-5五月，-6六月、-7七月、-8八月、-9九月、-10十月、-11十月、-12一年、（ 多期、一期）
	// -13 .. -24两年，（ 多期、一期）
	// -25按指定天,（ 多期、一期）

	private long d1_all_loan = 0;
	private int d3_total_Period = 0;


	private double d6_Fixed_Payment = 0.0;
	private long d6_Fixed_Payment_Round = 0;

	private long [] d_Principal;
	private long [] d_DuePrincipal;
	private long [] d_DueInterest;
	
	
	public final static int ROUND_UP =           java.math.BigDecimal.ROUND_UP;
	public final static int ROUND_DOWN =         java.math.BigDecimal.ROUND_DOWN;
	public final static int ROUND_CEILING =      java.math.BigDecimal.ROUND_CEILING;
	public final static int ROUND_FLOOR =        java.math.BigDecimal.ROUND_FLOOR;
	public final static int ROUND_HALF_UP =      java.math.BigDecimal.ROUND_HALF_UP;
	public final static int ROUND_HALF_DOWN =    java.math.BigDecimal.ROUND_HALF_DOWN;
	public final static int ROUND_HALF_EVEN =    java.math.BigDecimal.ROUND_HALF_EVEN;
	
	private int d_pmt_roundingMode = ROUND_HALF_UP;
	private int d_I_roundingMode = ROUND_HALF_UP;


	
	public ThePayments(int num)
    {
    	initMe(num);
    }
	


	private void initMe(int num)
	{
		//        $this->data_start_date = date_create();
		
		if (num <= 0) {
			d3_total_Period = 0;
			return;			
		}
		d3_total_Period = num;

		d_Principal = new long[num+2];
		d_DuePrincipal = new long[num+2];
		d_DueInterest = new long[num+2];

		for (int i=0 ; i<=num+1;i++){
			d_Principal[i] = 0;
			d_DuePrincipal[i] = 0;
			d_DueInterest[i] = 0;
			//            $this->data_z_ByDay[$i] = false;
		}


	}


	public void releaseMe()
	{
		//        echo 'Destroying: ';
		// , $this->name, PHP_EOL;
		/*   	
        for ( int i = d3_total_Period + 1; i >= 0; i --) {
            if (is_array($this->d_Amounts) && isset($this->d_Amounts[$i]))
                unset($this->d_Amounts[$i]);
            if (is_array($this->d_DueAmounts) && isset($this->d_DueAmounts[$i]))
                unset($this->d_DueAmounts[$i]);
            if (is_array($this->d_DueInterests) && isset($this->d_DueInterests[$i]))
                unset($this->d_DueInterests[$i]);
            // if (is_array($this->data_z_ByDay) && isset($this->data_z_ByDay[$i]))
            // unset($this->data_z_ByDay[$i]);
        }
		 */
		d_Principal = null;
		d_DuePrincipal = null;
		d_DueInterest = null;
		// $this->data_z_ByDay = null;
	}

	
	public int getCount()
	{ // ???
		return d3_total_Period;
	}
	
	public void setCount(int num)
	{ // ???
		if ( num != d3_total_Period ) {
			releaseMe();
			initMe(num);
		}
	}

	
	public void setRoundingMode( int f_pmt_mode, int interest_mode ) {
		d_pmt_roundingMode = f_pmt_mode;
		d_I_roundingMode = interest_mode;
	}
	public long set_Fixed_Payment( double f_pmt) {
		d6_Fixed_Payment = f_pmt;
		d6_Fixed_Payment_Round = ( long ) com.wj.fin.wjutil.TheTools.round_mode( f_pmt, 0, d_pmt_roundingMode );
		return d6_Fixed_Payment_Round;
	}
	
	public long get_Fixed_Payment() {
		return d6_Fixed_Payment_Round;
	}
	
	
	
	public void setAllPrincipal( long Principal) {
		d1_all_loan = Principal;				
//				( long ) com.wj.fin.wjutil.TheTools.round_half_up( all_loan*100, 0 );
	}
	
	public void setPrincipal(int num, long Principal) {
		if ( num <0 || num > getCount() ) return;
		d_Principal[num] = Principal;
	}
	
	public void setDuePrincipal(int num, long duePrincipal) {
		if ( num <0 || num > getCount() ) return;
		d_DuePrincipal[num] = duePrincipal;
	}
	
	public void setDueInterest(int num, double DueInterest) {
		if ( num <0 || num > getCount() ) return;
		d_DueInterest[num] = ( long ) com.wj.fin.wjutil.TheTools.round_mode( DueInterest, 0, d_I_roundingMode );
	}
	
	public long getAllPrincipal() {
		return d1_all_loan;				
	}
	
	public long getPrincipal(int num) {
		if ( num <0 || num > getCount() ) return 0;
		return d_Principal[num];
	}
	
	public long getDuePrincipal(int num) {
		if ( num <0 || num > getCount() ) return 0;
		return d_DuePrincipal[num];
	}
	
	public long getDueInterest(int num) {
		if ( num <0 || num > getCount() ) return 0;
		return d_DueInterest[num];
	}
	
	public long [] getPrincipals() {
		int num = getCount();
		if ( num <= 0 ) return null;
		
		long [] a_array = new long[num];
		for (int i=0;i<num;i++) {
			a_array[i] = d_Principal[i+1];
		}
		return a_array;
	}
	
	public long [] getDuePrincipals() {
		int num = getCount();
		if ( num <= 0 ) return null;
		
		long [] a_array = new long[num];
		for (int i=0;i<num;i++) {
			a_array[i] = d_DuePrincipal[i+1];
		}
		return a_array;
	}
	
	public long [] getDueInterests() {
		int num = getCount();
		if ( num <= 0 ) return null;
		
		long [] a_array = new long[num];
		for (int i=0;i<num;i++) {
			a_array[i] = d_DueInterest[i+1];
		}
		return a_array;
	}
	
	
	
	
/*	

	public void  cal_thePayments( TheRates theRates, double all_loan , boolean useDay )
	{

		if ( theRates == null)  {
			return;
		}

		int num = theRates.getCount();
		if ( num <=0 ) return;
		if ( getCount() != num  ) {
			releaseMe();
			initMe(num);
		}


		if ( all_loan < 0 ) { all_loan = 0; }

		d1_all_loan = ( long ) com.wj.fin.wjutil.TheTools.round_half_up( all_loan*100, 0 );

		d3_total_Period = num; // 总期数不能小于1



		long amt;

		d_Principal[0] = d1_all_loan;
		d_DuePrincipal[0] = 0;


		d6_Fixed_Payment = theRates.cal_Average_Payment( d1_all_loan,useDay );
		d6_Fixed_Payment_Round = ( long ) com.wj.fin.wjutil.TheTools.round_half_up(d6_Fixed_Payment, 0);
		//;   round( $this->d6_period_amount, 2, PHP_ROUND_HALF_UP ); // 求四舍五入到分月供


		// $this->d6_period_amount_round = round( ceil($this->d6_period_amount *100) / 100, 2, PHP_ROUND_HALF_UP ); // 求向上取整到分月供


		for ( int x=1; x <= num; x++) {
			amt = d_Principal[x-1]-d_DuePrincipal[x-1];
			d_Principal[x] =  amt;
			d_DueInterest[x] = theRates.cal_Period_Interest(x, amt,useDay );
			d_DuePrincipal[x] = d6_Fixed_Payment_Round - d_DueInterest[x];
		}

		cal_last_period_due_principal();

		amt = d_Principal[1];
		d_DueInterest[1] = theRates.cal_Period_Interest(1, amt,true );

		amt = d_Principal[  num ];
		d_DueInterest[ num ] = theRates.cal_Period_Interest( num , amt,true );

	}


	private void cal_last_period_due_principal()
	{ // 修正最后一期应还本金，如果没还完本金，全部归还。
		int num = getCount();
		if ( d_DuePrincipal[num] != d_Principal[num] )
		{
			d_DuePrincipal[num] = d_Principal[num];
		}
		//    $this->data_due_amount = $this->data_due_principal + $this->data_due_interest;
	}
*/
	public String echoData( boolean need_table ) //=true )
	{
		String echoStr = null;
		if ( need_table ) {
			//echo date_default_timezone_get();
			            
            echoStr = "<table border=1 cellspacing=0 cellpadding=0>\n";
            for (int x=0; x <= d3_total_Period+1; x++) {
                echoStr = echoStr+"    <tr>\n";


                echoStr =echoStr+"        <td>"+x+"</td>\n";
                //            $echoStr = $echoStr."        <td>".date_format($this->data_start_date,"Y-m-d")."</td>\n";
                echoStr = echoStr+"        <td>"+d_Principal[x]/100.0+"</td>\n";
                echoStr = echoStr+"        <td>"+d_DuePrincipal[x]/100.0+"</td>\n";
                echoStr = echoStr+"        <td>"+d_DueInterest[x]/100.0+"</td>\n";
                echoStr = echoStr+"        <td>"+(d_DuePrincipal[x]+d_DueInterest[x])/100.0+"</td>\n";

                echoStr = echoStr+"\n";


                echoStr = echoStr+"    </tr>\n";
            }
            echoStr = echoStr+"</table>\n";

             
//            echo $echoStr;
                 
		}
		return echoStr;

	}



}
