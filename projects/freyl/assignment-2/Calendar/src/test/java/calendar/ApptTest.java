package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void testConstructor()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
		                      startYear, title, description);
	// assertions
		 assertTrue(appt.getValid());
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());      
	 }

		@Test
		public void testInvalid() throws Throwable {
			 int startHour=0;
			 int startMinute=0;
			 int startDay=1;
			 int startMonth=1;
			 int startYear=2018;
			 String val_appt="Invalid Appt";
			 String description="This is a invalid appt.";
			Appt invalid_appt_1 = new Appt(startHour-1, startMinute, startDay, startMonth,
			            		 		 startYear, val_appt, description);
			assertEquals(false, invalid_appt_1.getValid());

			Appt invalid_appt_2 = new Appt(startHour+24, startMinute, startDay, startMonth,
			            		 		 startYear, val_appt, description);
			assertEquals(false, invalid_appt_2.getValid());

			Appt invalid_appt_3 = new Appt(startHour, startMinute-1, startDay, startMonth,
			            		 		 startYear, val_appt, description);
			assertEquals(false, invalid_appt_3.getValid());

			Appt invalid_appt_4 = new Appt(startHour, startMinute+60, startDay, startMonth,
			            		 		 startYear, val_appt, description);
			assertEquals(false, invalid_appt_4.getValid());

			Appt invalid_appt_5 = new Appt(startHour, startMinute, startDay-1, startMonth,
			            		 		 startYear, val_appt, description);
			assertEquals(false, invalid_appt_5.getValid());

			Appt invalid_appt_6 = new Appt(startHour, startMinute, startDay+31, startMonth,
			            		 		 startYear, val_appt, description);
			assertEquals(false, invalid_appt_6.getValid());

			//Appt invalid_appt_7 = new Appt(startHour, startMinute, startDay, startMonth-1, 
			//							   startYear, val_appt, description);
			//assertEquals(false, invalid_appt_7.getValid());

			Appt invalid_appt_8 = new Appt(startHour, startMinute, startDay, startMonth+11,
			            		 		 startYear, val_appt, description);
			//assertEquals(false, invalid_appt_8.getValid());
		}
	 
	 @Test
	  public void testSetMethods()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="test set method";
		 String description="This is a test";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
		                      startYear, title, description);
		 
		 
		 assertEquals(30, appt.getStartMinute());
		 appt.setStartMinute(45);
		 assertEquals(45, appt.getStartMinute());
		 
		 assertEquals(21, appt.getStartHour());
		 appt.setStartHour(16);
		 assertEquals(16, appt.getStartHour());
		 
		 assertEquals(15, appt.getStartDay());
		 appt.setStartDay(10);
		 assertEquals(10, appt.getStartDay());
		 
		 assertEquals(01, appt.getStartMonth());
		 appt.setStartMonth(11);
		 assertEquals(11, appt.getStartMonth());
		 
		 assertEquals(2018, appt.getStartYear());
		 appt.setStartYear(2019);
		 assertEquals(2019, appt.getStartYear());
		 
		 assertEquals("test set method", appt.getTitle());
		 appt.setTitle("Changed title");
		 assertEquals("Changed title", appt.getTitle());
		 appt.setTitle(null);
		 assertEquals("", appt.getTitle());
		 
		 assertEquals("This is a test", appt.getDescription());
		 appt.setDescription("Changed description");
		 assertEquals("Changed description", appt.getDescription());
		 appt.setDescription(null);
		 assertEquals("", appt.getDescription());
	 }
	 
	 @Test
	  public void testRecurMethods()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="test set method";
		 String description="This is a test";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
		                      startYear, title, description);
		 
		 assertEquals(false, appt.isRecurring());
		 
		 int recurBy = 1;
		 int recurIncrement = 2;
		 int recurNumber = 3;
		 appt.setRecurrence(null, recurBy, recurIncrement, recurNumber);
		 assertEquals(true, appt.isRecurring());
		 assertEquals(0, appt.getRecurDays().length);
		 assertEquals(1, appt.getRecurBy());
		 assertEquals(2, appt.getRecurIncrement());
		 assertEquals(3, appt.getRecurNumber());
		 
		 
		 int[] recurDays = {0, 1, 2};
		 appt.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
		 assertEquals(3, appt.getRecurDays().length);
	 }
	 
	 @Test
	  public void testToString()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=1;
		 int startYear=2018;
		 String title="test set method";
		 String description="This is a test";
		 
		 Appt appt = new Appt(-1, startMinute, startDay, startMonth,
                 startYear, title, description);
		 assertEquals(null, appt.toString());
		 
		 //Construct a new Appointment object with the initial data	 
		 appt = new Appt(startHour, startMinute, startDay, startMonth,
                 startYear, title, description);
		 String apt_str = appt.toString();
		 
		 //assertEquals("\t1/15/2018 at 9:30pm ,test set method, This is a test", apt_str);
		 
		 appt = new Appt(11, startMinute, startDay, startMonth,
                 startYear, title, description);
		 apt_str = appt.toString();
		//assertEquals("\t1/15/2018 at 11:30am ,test set method, This is a test", apt_str);
		 
		 appt = new Appt(11, startMinute, startDay, startMonth,
                 startYear, title, description);
		 apt_str = appt.toString();
		//assertEquals("\t1/15/2018 at 11:30am ,test set method, This is a test", apt_str);
		 
		 
		 appt = new Appt(0, startMinute, startDay, startMonth,
                 startYear, title, description);
		 apt_str = appt.toString();
		//assertEquals("\t1/15/2018 at 12:30am ,test set method, This is a test", apt_str);
	 }
	 
	 @Test
	  public void testCompareTo()  throws Throwable  {
		 int startHour=0;
		 int startMinute=0;
		 int startDay=1;
		 int startMonth=1;
		 int startYear=2018;
		 String title="test set method";
		 String description="This is a test";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
		                      startYear, title, description);
		 assertEquals(0, appt.compareTo(appt));
		 
		 Appt appt1 = new Appt(startHour+1, startMinute+3, startDay+7, startMonth+11,
                startYear+13, title, description);
		 assertEquals(-35, appt.compareTo(appt1));
		 assertEquals(35, appt1.compareTo(appt));
	 }
//add more unit tests as you needed
	
}
