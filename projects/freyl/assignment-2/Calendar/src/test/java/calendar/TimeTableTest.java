package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=03;
		 int startYear=2018;
		 
		 LinkedList<Appt> listAppts = new LinkedList<Appt>();
		 
		 // add 30 appointments to linked list with years ranging from 2019 to 2049
		 for (int i = 1; i <= 30; i++) {
			 startYear++;
			 String title = "appointment " + Integer.toString(i) + " title";
			 String description = "appointment " + Integer.toString(i) + " description";
			 Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
                     startYear, title, description);
			 listAppts.add(appt);
		 }
		 
		 
		 TimeTable table = new TimeTable();
		 GregorianCalendar firstDay = new GregorianCalendar(2018, 2, 15);
		 GregorianCalendar lastDay = new GregorianCalendar(2027, 4, 15);
		 
		 // get the first ten appointments from time table
		 LinkedList<CalDay> apptRange = table.getApptRange(listAppts, firstDay, lastDay);
		 
		 assertEquals(10, apptRange.size());
		 
	 }
	 @Test
	  public void test02()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=03;
		 int startYear=2018;
		 String title = "appointment 1 title";
		 String description = "appointment 1 description";
		 Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
                              startYear, title, description);
		 
		 LinkedList<Appt> listAppts = new LinkedList<Appt>();
		 
		 TimeTable table = new TimeTable();
		 
		 // can't remove appt from null list
		 assertNull(table.deleteAppt(null, appt));
		 listAppts.add(appt);
		 assertEquals(1, listAppts.size());
		 
		 Appt appt2 = new Appt(startHour+1, startMinute, startDay, startMonth,
                              startYear, title, description);
		 
		 // can't remove an appt that isn't there.
		 assertNull(table.deleteAppt(listAppts, appt2));
		 
		 // can remove appt that is there.
		 assertEquals(appt, table.deleteAppt(listAppts, appt)); 
	 }
	 
	 @Test
	  public void testPermute()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=03;
		 int startYear=2018;
		 String title = "appointment 1 title";
		 String description = "appointment 1 description";
		 Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
                             startYear, title, description);

		 String title2 = "appointment 2 title";
		 String description2 = "appointment 2 description";
		 Appt appt2 = new Appt(startHour, startMinute, startDay, startMonth,
                             startYear, title2, description2);
		 
		 LinkedList<Appt> listAppts = new LinkedList<Appt>();
         
		 listAppts.add(appt);
		 listAppts.add(appt2);
		 
		 int[] pv = {1 ,1};
		 
		 TimeTable table = new TimeTable();
		 
		 //should swap appt at index 0 with appt at index 1	 
		 LinkedList<Appt> listApptsPermuted = table.permute(listAppts, pv);
		 
		 assertEquals(appt2, listApptsPermuted.get(0));

	 }
//add more unit tests as you needed
}
