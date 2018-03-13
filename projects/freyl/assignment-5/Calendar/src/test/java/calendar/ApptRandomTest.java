package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import calendar.ValuesGenerator;

/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {
	private static final long TestTimeout = 10 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */

	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY, 4};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
        }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
        }	
   /**
     * Generate Random Tests that tests Appt Class.
     */
	  @Test
	  public void testRandom()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
				Appt appt = null;
				int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
				int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
			    int recur=ApptRandomTest.RandomSelectRecur(random);
			    int recurIncrement = ValuesGenerator.RandInt(random);
			    int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
				
				
				for (int i = 0; i < NUM_TESTS; i++) {
					do {
						int startHour=ValuesGenerator.getRandomIntBetween(random, -10, 30);
						int startMinute=ValuesGenerator.getRandomIntBetween(random, -10, 70);
						int startDay=ValuesGenerator.getRandomIntBetween(random, -10, 35);
						int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
						int startYear=ValuesGenerator.RandInt(random);
						String title="Birthday Party";
						String description="This is my birthday party.";
						
						//Construct a new Appointment object with the initial data	 
						appt = new Appt(startHour, startMinute, startDay, startMonth, 
										startYear , title, description);
					 
					} while (!appt.getValid());
					
					sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
					if (sizeArray == 0) {
						recurDays = null;
					} else {
						recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
					}
					
					recur=ApptRandomTest.RandomSelectRecur(random);
					recurIncrement = ValuesGenerator.RandInt(random);
					recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
					
					appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			    if((iteration%10000)==0 && iteration!=0 )
			    	System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			}
		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }
}
