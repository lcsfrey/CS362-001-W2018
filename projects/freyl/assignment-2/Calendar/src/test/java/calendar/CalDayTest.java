package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	 @Test
	 public void testInvalidCalDay()  throws Throwable  {
		 CalDay calday = new CalDay();
		 assertFalse(calday.isValid());
	 }
	 
	 @Test
	 public void testValidCalDay()  throws Throwable  {
		 GregorianCalendar gregCal = new GregorianCalendar(2018, 11, 25);
		 CalDay calday = new CalDay(gregCal);
		 assertTrue(calday.isValid());
	 }
	 
	 @Test
	 public void testGetMethods()  throws Throwable  {
		 GregorianCalendar gregCal = new GregorianCalendar(2018, 11, 25);
		 CalDay calday = new CalDay(gregCal);
		 
		 assertEquals(25, calday.getDay());
		 assertEquals(11, calday.getMonth());
		 assertEquals(2018, calday.getYear());
		 
		 assertEquals(0, calday.getAppts().size());
		 assertEquals(0, calday.getSizeAppts());
	 }
	 
	 @Test
	 public void testAddAppt()  throws Throwable  {
		 GregorianCalendar gregCal = new GregorianCalendar(2018, 11, 25);
		 CalDay calday = new CalDay(gregCal);
		 
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="test CalDay Add Appt";
		 String description="This is a test";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
		                      startYear, title, description);
		 
		 calday.addAppt(appt);
		 assertEquals(1, calday.getSizeAppts());	 
	 }
	 
	 @Test
	 public void testToString()  throws Throwable  {
		 GregorianCalendar gregCal = new GregorianCalendar(2018, 11, 25);
		 CalDay calday = new CalDay(gregCal);
		 
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="test CalDay Add Appt";
		 String description="This is a test";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
		                      startYear, title, description);
		 
		 calday.addAppt(appt);
		 assertEquals("\t --- 11/25/2018 --- \n"
		 		    + " --- -------- Appointments ------------ --- \n" 
				    + appt.toString() + " \n" , calday.toString());	 
	 }
//add more unit tests as you needed	
}
