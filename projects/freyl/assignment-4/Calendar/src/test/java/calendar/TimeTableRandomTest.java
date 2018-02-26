package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tested.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"getApptRange", "deleteAppt"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
     }
    
    public Appt getRandomAppt(Random random, int month, int year) {
    	int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 24);
		int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 61);
		int startDay=ValuesGenerator.getRandomIntBetween(random, -1, 32);
	
		Appt appt = new Appt(startHour, startMinute, startDay, month, year , "test", "description");
		
		if (ValuesGenerator.getRandomIntBetween(random, 0, 10) != 0) {
			int[] recurDays;
			int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
			if (sizeArray == 0) {
				recurDays = null;
			} else {
				recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
			}
			int recur=ApptRandomTest.RandomSelectRecur(random);
			int recurIncrement = ValuesGenerator.RandInt(random);
			int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
			appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
		}
		
		return appt;
    }
    
    public LinkedList<Appt> getRandomApptList(Random random, int month, int year) {
		int numAppts=ValuesGenerator.getRandomIntBetween(random, 0, 30);
		LinkedList<Appt> list = new LinkedList<Appt>();
		for (int j = 0; j < numAppts; j++) {
			list.add(getRandomAppt(random, month, year));
		}
		return list;
    }

   /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	  public void testRandom()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		 
		 System.out.println("Start testing...");
		 
		 long randomseed =System.currentTimeMillis(); //10
		 Random random = new Random(randomseed); 
		 
		 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
		 int startYear=ValuesGenerator.RandInt(random);
		 int NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);
		 int startDay=ValuesGenerator.getRandomIntBetween(random, 1, NumDaysInMonth);
		 String title="Birthday Party";
		 String description="This is my birthday party.";

		 GregorianCalendar cal = new GregorianCalendar(startYear, startMonth, startDay);
		 CalDay calday = new CalDay(cal);
		 LinkedList<Appt> list = new LinkedList<Appt>();
		 Appt appt = null;
		 
		 TimeTable table = new TimeTable();
		 
		 try{ 
			 for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);
				startDay=ValuesGenerator.getRandomIntBetween(random, 1, NumDaysInMonth);
				startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
				startYear=ValuesGenerator.RandInt(random);
				cal = new GregorianCalendar(startYear, startMonth, startDay);
				calday = new CalDay(cal);

				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = TimeTableRandomTest.RandomSelectMethod(random);
					
					if (methodName.equals("getApptRange")) {
						
						list = getRandomApptList(random, startMonth, startYear);
						
						int firstMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
						NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);
						int firstDay=ValuesGenerator.getRandomIntBetween(random, 1, NumDaysInMonth);
						GregorianCalendar firstCalDay = new GregorianCalendar(startYear, firstMonth, firstDay);
						
						int lastMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
						NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);
						int lastDay=ValuesGenerator.getRandomIntBetween(random, 1, NumDaysInMonth);
						GregorianCalendar lastCalDay = new GregorianCalendar(startYear, lastMonth, lastDay);

						try {
							LinkedList<CalDay> caldays = table.getApptRange(list,  firstCalDay, lastCalDay);
							continue;
						} catch (DateOutOfRangeException e) {
							//System.out.println("Exception caught: " + e);
						}
					} else if (methodName.equals("deleteAppt")) {

						int choice=ValuesGenerator.getRandomIntBetween(random, 0, 10);
						LinkedList<Appt> listAppts = null;
						Appt apptChoice = null;
						if (choice != 0) {
							listAppts = getRandomApptList(random, startMonth, startYear);
						}
					
						if (listAppts == null) {
							table.deleteAppt(listAppts, apptChoice);
						} else {
							for (int j = 0; j < listAppts.size()/2; j++) {
								choice=ValuesGenerator.getRandomIntBetween(random, 0, 20);
								if (choice == 0) {
									apptChoice = null;
								} else if (choice < 18) {
									int apptChoiceIndex =ValuesGenerator.getRandomIntBetween(random, 0, listAppts.size()-1);
									apptChoice = listAppts.get(apptChoiceIndex);
								} else {
									apptChoice = getRandomAppt(random, startMonth, startYear);
								}

								table.deleteAppt(listAppts, apptChoice);
								assertFalse(listAppts.contains(apptChoice));
							}
						}
						
					} else {
						System.out.println("ERROR: " + methodName + " is a typo!\n");
					}
				}
				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%1000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			        
			}
		}catch(NullPointerException e){
			System.out.println("Caught exception: " + e);
		}
	 
		 System.out.println("Done testing...");
	 }
}
