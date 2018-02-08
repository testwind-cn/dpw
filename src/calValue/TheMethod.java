package calValue;

public class TheMethod {
	public final static int METHOD_FIXED_PRINCIPAL_INTEREST		= 1;
	public final static int METHOD_FIXED_PRINCIPAL				= 2;
	public final static int METHOD_FIXED_INTEREST				= 3;
	public final static int METHOD_HEAD_INTEREST					= 4;
	private boolean m_HR_forceByPeriod = false;
	
	public void ForceHeadRearByPeriod(boolean isFore ) {
		m_HR_forceByPeriod = isFore;
	}
	
	public ThePayments getPayments(TheRates r, long amount,int method) {
		ThePayments aa = null;
		return aa;
	}
	
	private ThePayments getPayments_Fixed_P_I( TheRates r, long amount) { // METHOD_FIXED_PRINCIPAL_INTEREST
		ThePayments aa = null;
		return aa;
	}
	
	private ThePayments getPayments_Fixed_P(TheRates r, long amount) { // METHOD_FIXED_PRINCIPAL_INTEREST
		ThePayments aa = null;
		return aa;
	}
	
	private ThePayments getPayments_Fixed_E( TheRates r, long amount) { // METHOD_FIXED_PRINCIPAL_INTEREST
		ThePayments aa = null;
		return aa;
	}
	
	private ThePayments getPayments_Head_I(TheRates r, long amount) { // METHOD_FIXED_PRINCIPAL_INTEREST
		ThePayments aa = null;
		return aa;
	}
	
	
	public void cal_Payments( TheRates theRates, ThePayments thePMT , boolean useDay )
	{
		long m_all_loan;

		if ( theRates == null || thePMT == null )  {
			return;
		}


		int num1 = theRates.getCount();
		int num2 = thePMT.getCount();
		if ( num1 <=0 ) return;
		
		if ( num1 != num2 ) {
			thePMT.setCount(num1);
			num2 = thePMT.getCount();
		}
		
		m_all_loan = thePMT.getAllPrincipal();

		if ( m_all_loan < 0 ) { m_all_loan = 0; }

//		d1_all_loan = ( long ) com.wj.fin.wjutil.TheTools.round_half_up( all_loan*100, 0 );

		// d3_total_Period = num; // 总期数不能小于1

		double Fixed_Payment = theRates.cal_Average_Payment( m_all_loan,useDay );
		long Fixed_Payment_Round = thePMT.set_Fixed_Payment(Fixed_Payment);
		//;   round( $this->d6_period_amount, 2, PHP_ROUND_HALF_UP ); // 求四舍五入到分月供


		// $this->d6_period_amount_round = round( ceil($this->d6_period_amount *100) / 100, 2, PHP_ROUND_HALF_UP ); // 求向上取整到分月供

		long m_Principal;
		long m_DuePrincipal = 0;
		long m_DueInterest = 0;
		
		m_Principal = m_all_loan;
		
		for ( int x=1; x <= num1; x++) {
			m_Principal = m_Principal-m_DuePrincipal;
			thePMT.setPrincipal(x, m_Principal);
			m_DueInterest = theRates.cal_Period_Interest(x, m_Principal,useDay );
			thePMT.setDueInterest(x, m_DueInterest);
			m_DuePrincipal = Fixed_Payment_Round - m_DueInterest;
			thePMT.setDuePrincipal(x, m_DuePrincipal);			
		}

//		cal_last_period_due_principal();
		
		
		// 修正最后一期应还本金，如果没还完本金，全部归还。
		/*
				if ( d_DuePrincipal[num] != d_Principal[num] )
				{
					d_DuePrincipal[num] = d_Principal[num];
				}
		*/
		if ( m_DuePrincipal != m_Principal )
		{
			thePMT.setDuePrincipal(num1, m_Principal);
		}

		m_Principal = thePMT.getPrincipal(1);
		m_DueInterest = theRates.cal_Period_Interest(1, m_Principal,true );
		thePMT.setDueInterest(1, m_DueInterest);
		

		m_Principal = thePMT.getPrincipal(num1);
		m_DueInterest = theRates.cal_Period_Interest( num1 , m_Principal,true );
		thePMT.setDueInterest(num1, m_DueInterest);
		
		
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
