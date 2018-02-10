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
		 
		 //assertEquals(10, apptRange.size());
		 
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
		 
		 Appt appt2 = new Appt(startHour+1, startMinute, startDay, startMonth,
                 startYear, title, description);
		 
		 Appt appt3 = new Appt(startHour+2, startMinute, startDay, startMonth,
                 startYear, title, description);
		 
		 Appt appt4 = new Appt(startHour+5, startMinute, startDay, startMonth,
                 startYear, title, description);
		 
		 LinkedList<Appt> listAppts = new LinkedList<Appt>();
		 
		 TimeTable table = new TimeTable();
		 
		 // can't remove appt from null list
		 assertNull(table.deleteAppt(null, appt));
		 // can't remove null appt from list
		 assertNull(table.deleteAppt(listAppts, null));
		 
		 // can't remove appt from empty list
		 assertNull(table.deleteAppt(listAppts, appt));
		 
		 listAppts.add(appt);
		 listAppts.add(appt2);
		 listAppts.add(appt3);
		 assertEquals(3, listAppts.size());
		 
		 // can't remove an appt that isn't there.
		 assertNull(table.deleteAppt(listAppts, appt4));
		 

		 LinkedList<Appt> newTable = table.deleteAppt(listAppts, appt2);
		 // can remove appt that is there.
		 assertEquals(2, newTable.size());
		 assertTrue(newTable.contains(appt)); 
		 assertTrue(newTable.contains(appt3));
		 assertFalse(newTable.contains(appt2));
		 
		 Appt invalid_appt = new Appt(-1, startMinute, startDay, startMonth,
                 startYear, title, description);
		 assertNull(table.deleteAppt(listAppts, invalid_appt));
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
		 
		  //assertEquals(appt2, listApptsPermuted.get(0));

	 }
//add more unit tests as you needed
}
