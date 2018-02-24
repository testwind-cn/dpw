package calValue;

public class TheMethod {
	public final static int METHOD_FIXED_PRINCIPAL_INTEREST		= 1;
	public final static int METHOD_FIXED_PRINCIPAL				= 2;
	public final static int METHOD_FIXED_INTEREST				= 3;
	public final static int METHOD_HEAD_INTEREST					= 4;
	private boolean ww_HR_forceByPeriod = false;
	
	public void ForceHeadRearByPeriod(boolean pp_isFore ) {
		ww_HR_forceByPeriod = pp_isFore;
	}
	
	public ThePayments getPayments(TheRates pp_r, long pp_amount,int pp_method) {
		ThePayments aa = null;
		return aa;
	}
	
	private ThePayments getPayments_Fixed_P_I( TheRates pp_r, long pp_amount) { // METHOD_FIXED_PRINCIPAL_INTEREST
		ThePayments aa = null;
		return aa;
	}
	
	private ThePayments getPayments_Fixed_P(TheRates pp_r, long pp_amount) { // METHOD_FIXED_PRINCIPAL_INTEREST
		ThePayments aa = null;
		return aa;
	}
	
	private ThePayments getPayments_Fixed_E( TheRates pp_r, long pp_amount) { // METHOD_FIXED_PRINCIPAL_INTEREST
		ThePayments aa = null;
		return aa;
	}
	
	private ThePayments getPayments_Head_I(TheRates pp_r, long pp_amount) { // METHOD_FIXED_PRINCIPAL_INTEREST
		ThePayments aa = null;
		return aa;
	}
	
	
	public void cal_Payments( TheRates pp_theRates, ThePayments pp_thePMT , boolean pp_useDay )
	{
		long ll_all_loan;

		if ( pp_theRates == null || pp_thePMT == null )  {
			return;
		}


		int ll_num1 = pp_theRates.getCount();
		int ll_num2 = pp_thePMT.getCount();
		if ( ll_num1 <=0 ) return;
		
		if ( ll_num1 != ll_num2 ) {
			pp_thePMT.setCount(ll_num1);
			ll_num2 = pp_thePMT.getCount();
		}
		
		ll_all_loan = pp_thePMT.getAllPrincipal();

		if ( ll_all_loan < 0 ) { ll_all_loan = 0; }

//		d1_all_loan = ( long ) com.wj.fin.wjutil.TheTools.round_half_up( all_loan*100, 0 );

		// d3_total_Period = num; // 总期数不能小于1

		double ll_Fixed_Payment = pp_theRates.cal_Average_Payment( ll_all_loan,pp_useDay );
		long ll_Fixed_Payment_Round = pp_thePMT.set_Fixed_Payment(ll_Fixed_Payment);
		//;   round( $this->d6_period_amount, 2, PHP_ROUND_HALF_UP ); // 求四舍五入到分月供


		// $this->d6_period_amount_round = round( ceil($this->d6_period_amount *100) / 100, 2, PHP_ROUND_HALF_UP ); // 求向上取整到分月供

		long ll_Principal;
		long ll_DuePrincipal = 0;
		long ll_DueInterest = 0;
		
		ll_Principal = ll_all_loan;
		
		for ( int x=1; x <= ll_num1; x++) {
			ll_Principal = ll_Principal-ll_DuePrincipal;
			pp_thePMT.setPrincipal(x, ll_Principal);
			ll_DueInterest = pp_theRates.cal_Period_Interest(x, ll_Principal,pp_useDay );
			pp_thePMT.setDueInterest(x, ll_DueInterest);
			ll_DuePrincipal = ll_Fixed_Payment_Round - ll_DueInterest;
			pp_thePMT.setDuePrincipal(x, ll_DuePrincipal);			
		}

//		cal_last_period_due_principal();
		
		
		// 修正最后一期应还本金，如果没还完本金，全部归还。
		/*
				if ( d_DuePrincipal[num] != d_Principal[num] )
				{
					d_DuePrincipal[num] = d_Principal[num];
				}
		*/
		if ( ll_DuePrincipal != ll_Principal )
		{
			pp_thePMT.setDuePrincipal(ll_num1, ll_Principal);
		}

		ll_Principal = pp_thePMT.getPrincipal(1);
		ll_DueInterest = pp_theRates.cal_Period_Interest(1, ll_Principal,true );
		pp_thePMT.setDueInterest(1, ll_DueInterest);
		

		ll_Principal = pp_thePMT.getPrincipal(ll_num1);
		ll_DueInterest = pp_theRates.cal_Period_Interest( ll_num1 , ll_Principal,true );
		pp_thePMT.setDueInterest(ll_num1, ll_DueInterest);
		
		
		return;

	}

/*
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
}
