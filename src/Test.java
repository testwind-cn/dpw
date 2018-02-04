import calValue.TheDates;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TheDates aa = new TheDates(10);
		aa.prt();
		
		
		
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");  
	    try{  
	    	java.util.Date d = sdf.parse("2016-2-15");  
	        java.util.Calendar cld = java.util.Calendar.getInstance();  
	        cld.setTime(d);  
	        cld.add(java.util.Calendar.DATE, 15);  
	        java.util.Date d2 = cld.getTime();  
	        System.out.println(sdf.format(d)+"加一月："+sdf.format(d2));  
	          
	        //闰年的情况  
	        d = sdf.parse("2016-01-30");  
	        cld = java.util.Calendar.getInstance();  
	        cld.setTime(d);  
	        cld.add(java.util.Calendar.MONTH, 13);  
	        d2 = cld.getTime();  
	        System.out.println(sdf.format(d)+"加一月："+sdf.format(d2));  
	          
	    }catch(Exception e){  
	        e.printStackTrace();  
	    }  

	}

}
