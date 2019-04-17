package calValue;

public class ThePayments {

	// 1-1098按设置固定天（ 多期、一期），
	// 0按半月，-1按月，-2双月，-3三月，-4四月，-5五月，-6六月、-7七月、-8八月、-9九月、-10十月、-11十月、-12一年、（ 多期、一期）
	// -13 .. -24两年，（ 多期、一期）
	// -25按指定天,（ 多期、一期）

	private long ww_all_loan = 0;
	private int ww_total_Period = 0;


	private double ww_Fixed_Payment = 0.0;
	private long ww_Fixed_Payment_Round = 0;

	private long [] ww_Principal;
	private long [] ww_DuePrincipal;
	private long [] ww_DueInterest;
	
	
	public final static int ROUND_UP =           java.math.BigDecimal.ROUND_UP;
	public final static int ROUND_DOWN =         java.math.BigDecimal.ROUND_DOWN;
	public final static int ROUND_CEILING =      java.math.BigDecimal.ROUND_CEILING;
	public final static int ROUND_FLOOR =        java.math.BigDecimal.ROUND_FLOOR;
	public final static int ROUND_HALF_UP =      java.math.BigDecimal.ROUND_HALF_UP;
	public final static int ROUND_HALF_DOWN =    java.math.BigDecimal.ROUND_HALF_DOWN;
	public final static int ROUND_HALF_EVEN =    java.math.BigDecimal.ROUND_HALF_EVEN;
	
	private int ww_pmt_roundingMode = ROUND_HALF_UP;
	private int ww_I_roundingMode = ROUND_HALF_UP;


	
	public ThePayments(int pp_num)
    {
    	initMe(pp_num);
    }
	


	private void initMe(int pp_num)
	{
		//        $this->data_start_date = date_create();
		
		if (pp_num <= 0) {
			ww_total_Period = 0;
			return;			
		}
		ww_total_Period = pp_num;

		ww_Principal = new long[pp_num+2];
		ww_DuePrincipal = new long[pp_num+2];
		ww_DueInterest = new long[pp_num+2];

		for (int i=0 ; i<=pp_num+1;i++){
			ww_Principal[i] = 0;
			ww_DuePrincipal[i] = 0;
			ww_DueInterest[i] = 0;
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
		ww_Principal = null;
		ww_DuePrincipal = null;
		ww_DueInterest = null;
		// $this->data_z_ByDay = null;
	}

	
	public int getCount()
	{ // ???
		return ww_total_Period;
	}
	
	public void setCount(int pp_num)
	{ // ???
		if ( pp_num != ww_total_Period ) {
			releaseMe();
			initMe(pp_num);
		}
	}

	
	public void setRoundingMode( int pp_f_pmt_mode, int pp_interest_mode ) {
		ww_pmt_roundingMode = pp_f_pmt_mode;
		ww_I_roundingMode = pp_interest_mode;
	}
	public long set_Fixed_Payment( double pp_f_pmt) {
		ww_Fixed_Payment = pp_f_pmt;
		ww_Fixed_Payment_Round = ( long ) com.wj.fin.wjutil.TheTools.round_mode( pp_f_pmt, 0, ww_pmt_roundingMode );
		return ww_Fixed_Payment_Round;
	}
	
	public long get_Fixed_Payment() {
		return ww_Fixed_Payment_Round;
	}
	
	
	
	public void setAllPrincipal( long pp_Principal) {
		ww_all_loan = pp_Principal;				
//				( long ) com.wj.fin.wjutil.TheTools.round_half_up( all_loan*100, 0 );
	}
	
	public void setPrincipal(int pp_num, long pp_Principal) {
		if ( pp_num <0 || pp_num > getCount() ) return;
		ww_Principal[pp_num] = pp_Principal;
	}
	
	public void setDuePrincipal(int pp_num, long pp_duePrincipal) {
		if ( pp_num <0 || pp_num > getCount() ) return;
		ww_DuePrincipal[pp_num] = pp_duePrincipal;
	}
	
	public void setDueInterest(int pp_num, double pp_DueInterest) {
		if ( pp_num <0 || pp_num > getCount() ) return;
		ww_DueInterest[pp_num] = ( long ) com.wj.fin.wjutil.TheTools.round_mode( pp_DueInterest, 0, ww_I_roundingMode );
	}
	
	public long getAllPrincipal() {
		return ww_all_loan;				
	}
	
	public long getPrincipal(int pp_num) {
		if ( pp_num <0 || pp_num > getCount() ) return 0;
		return ww_Principal[pp_num];
	}
	
	public long getDuePrincipal(int pp_num) {
		if ( pp_num <0 || pp_num > getCount() ) return 0;
		return ww_DuePrincipal[pp_num];
	}
	
	public long getDueInterest(int pp_num) {
		if ( pp_num <0 || pp_num > getCount() ) return 0;
		return ww_DueInterest[pp_num];
	}
	
	public long [] getPrincipals() {
		int ll_num = getCount();
		if ( ll_num <= 0 ) return null;
		
		long [] a_array = new long[ll_num];
		for (int i=0;i<ll_num;i++) {
			a_array[i] = ww_Principal[i+1];
		}
		return a_array;
	}
	
	public long [] getDuePrincipals() {
		int num = getCount();
		if ( num <= 0 ) return null;
		
		long [] a_array = new long[num];
		for (int i=0;i<num;i++) {
			a_array[i] = ww_DuePrincipal[i+1];
		}
		return a_array;
	}
	
	public long [] getDueInterests() {
		int ll_num = getCount();
		if ( ll_num <= 0 ) return null;
		
		long [] a_array = new long[ll_num];
		for (int i=0;i<ll_num;i++) {
			a_array[i] = ww_DueInterest[i+1];
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
	public String echoData( boolean pp_need_table ) //=true )
	{
		String ll_echoStr = null;
		if ( pp_need_table ) {
			//echo date_default_timezone_get();
			            
            ll_echoStr = "<table border=1 cellspacing=0 cellpadding=0>\n";
            for (int x=0; x <= ww_total_Period+1; x++) {
                ll_echoStr = ll_echoStr+"    <tr>\n";


                ll_echoStr =ll_echoStr+"        <td>"+x+"</td>\n";
                //            $echoStr = $echoStr."        <td>".date_format($this->data_start_date,"Y-m-d")."</td>\n";
                ll_echoStr = ll_echoStr+"        <td>"+ww_Principal[x]/100.0+"</td>\n";
                ll_echoStr = ll_echoStr+"        <td>"+ww_DuePrincipal[x]/100.0+"</td>\n";
                ll_echoStr = ll_echoStr+"        <td>"+ww_DueInterest[x]/100.0+"</td>\n";
                ll_echoStr = ll_echoStr+"        <td>"+(ww_DuePrincipal[x]+ww_DueInterest[x])/100.0+"</td>\n";

                ll_echoStr = ll_echoStr+"\n";


                ll_echoStr = ll_echoStr+"    </tr>\n";
            }
            ll_echoStr = ll_echoStr+"</table>\n";

             
//            echo $echoStr;
                 
		}
		return ll_echoStr;

	}



}
