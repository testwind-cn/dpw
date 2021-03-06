package calValue;



/**
 * @startuml
 *  
 * digraph G {
    aize ="4,4";
    main [shape=box];
    main -> parse [weight=8];
    parse -> execute;
    main -> init [style=dotted];
    main -> cleanup;
    execute -> { make_string; printf}
    init -> make_string;
    edge [color=red];
    main -> printf [style=bold,label="100 times"];
    make_string [label="make a string"];
    node [shape=box,style=filled,color=".7 .3 1.0"];
    execute -> compare;
  }
 * @enduml
 * 
 */



public class TheTotal {

    // 1-1098按设置固定天（ 多期、一期），
    // 0按半月，-1按月，-2双月，-3三月，-4四月，-5五月，-6六月、-7七月、-8八月、-9九月、-10十月、-11十月、-12一年、（ 多期、一期）
    // -13 .. -24两年，（ 多期、一期）
    // -25按指定天,（ 多期、一期）
    
    private double d1_all_loan = 12000;
    private double d2_real_rate = 0.18;
    private double d2_real_day_rate = 0.0005;
    private int d3_total_Period = 6;
    private double d4_period_days = 0;  // 0=按自然月还， 1-365=按天数周期还，-1=按后面的还款天表
    private java.util.Calendar d5_start_date;
    private double d6_period_amount = 0.0;
    private double d6_period_amount_round = 0;
    private int [] period_days_array=null;
//    private $periodAmounts = array();
    
    
    
    
    public void initMe()
    {
        //        $this->data_start_date = date_create();

    }
    
    
    public void releaseMe() {

    }
    
    
    public String cal_theTotals( double all_loan, double rate,int total,int len, String sdate, int spec_day, boolean spec_mode,boolean useDay){ // useDay 一般默认false，表示按期

        
//      $a->calPeriodAmount(90000,0.059,16,-1,"2018-1-29",0,true);
  	String ss;
  	TheDates theDates = new TheDates(total);
      theDates.cal_theDates( total, len, sdate , spec_day , spec_mode,null);
      ss =  theDates.echoData(true) ;
      
      
      TheRates theRates = new TheRates(total);
      theRates.cal_theRates( theDates, rate, useDay );
      ss += theRates.echoData(true);
      
      ThePayments thePayments = new ThePayments(total);
      
//      theAmounts.cal_theAmounts( theRates, all_loan, useDay  );
      thePayments.setAllPrincipal((long)(all_loan*100));
      
      TheMethod theMtd = new TheMethod();
      theMtd.cal_Payments(theRates, thePayments, useDay);
      
      
      ss += thePayments.echoData(true);
      
      return ss;
  }
    
    
    
    
    
    
        
}