package calendar;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.*;
import org.junit.Ignore;

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
		 
		 calday = new CalDay();
		 assertFalse(calday.isValid());
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
	 
	 @Ignore @Test
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
		 
		 Appt appt1 = new Appt(startHour-1, startMinute, startDay, startMonth,
                 startYear, title, description);

		 calday.addAppt(appt1);
		 assertEquals(2, calday.getSizeAppts());
		 
		 Appt appt2 = new Appt(startHour+1, startMinute, startDay, startMonth,
                 startYear, title, description);

		 calday.addAppt(appt2);
		 assertEquals(3, calday.getSizeAppts());
		 
		 Appt appt3 = new Appt(startHour, startMinute+1, startDay, startMonth,
                 startYear, title, description);
		 calday.addAppt(appt3);
		 
		 Appt invalid_appt = new Appt(-1, startMinute, startDay, startMonth,
                 startYear, title, description);

		 calday.addAppt(invalid_appt);
		 assertEquals(4, calday.getSizeAppts());
		 
		 Iterator<Appt> it = (Iterator<Appt>) calday.iterator();
		 assertEquals(appt1, it.next());
		 assertEquals(appt, it.next());
		 assertEquals(appt3, it.next());
		 assertEquals(appt2, it.next());
		 
	 }
	 
	 @Test
	 public void testToString()  throws Throwable  {
		 CalDay calday  = new CalDay();
		 assertEquals("", calday.toString());
		 
		 GregorianCalendar gregCal = new GregorianCalendar(2018, 11, 25);
		 calday = new CalDay(gregCal);
		 
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
	 
	 @Test
	 public void testIterator()  throws Throwable  {
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
		 
		 Iterator<Appt> it = (Iterator<Appt>) calday.iterator();
		 assertNotNull(it);
		 
		 calday = new CalDay();
		 it = (Iterator<Appt>) calday.iterator();
		 assertNull(it);
	 }

}
