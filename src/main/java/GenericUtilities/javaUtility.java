package GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class javaUtility
{
	public int generateRandomNo()
	{
		Random r=new Random();
		int randomint = r.nextInt(1000);
		return randomint;
	}
	
	public String fetchTodaysDate()
	{
		Date dobj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(dobj);
		return date;
	}
	
	public String fetchDateAfterGivenDays(int days)
	{
		Date dobj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		sim.format(dobj);
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String date = sim.format(cal.getTime());
		return date;
		
	}
}
