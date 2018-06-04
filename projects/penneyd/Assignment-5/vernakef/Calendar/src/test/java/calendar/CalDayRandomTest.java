package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import calendar.Appt;
import java.util.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {

   private static final long TestTimeout = 60 * 50 * 1; /* Timeout at 3 seconds */
   private static final int NUM_TESTS=100;

   /**
    * Return a randomly selected method to be tests !.
    */
   public static String RandomSelectMethod(Random random){
      String[] methodArray = new String[] {"addAppt"};// The list of the of methods to be tested in the Appt class

      int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

      return methodArray[n] ; // return the method name 
   }

   /**
    * Generate Random Tests that tests CalDay Class.
    */
   @Test
   public void radnomtest()  throws Throwable  {

      long startTime = Calendar.getInstance().getTimeInMillis();
      long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


      System.out.println("Start testing...");

      try{ 
	 for(int iteration = 0; elapsed < TestTimeout; iteration++) {
	    long randomseed =System.currentTimeMillis(); //10
	    //			System.out.println(" Seed:"+randomseed );
	    Random random = new Random(randomseed);

	    //Generate gregorian calendar day
	    int gregDay=ValuesGenerator.getRandomIntBetween(random, -1, 32);
	    int gregMonth=ValuesGenerator.getRandomIntBetween(random, -1, 13);
	    int gregYear=ValuesGenerator.getRandomIntBetween(random, -20, 2018);
	    GregorianCalendar day = new GregorianCalendar(gregYear, gregMonth, gregDay);

	    //Construct new CalDay object
	    CalDay calday0 = new CalDay(day);

	    for(int i = 0; i < NUM_TESTS; i++) {
	       String methodName = CalDayRandomTest.RandomSelectMethod(random);
	       if(methodName.equals("addAppt")){
		  //Generate values for appointment object and construct appt object
		  int apptHour=ValuesGenerator.getRandomIntBetween(random, -2, 25);
		  int apptMinute=ValuesGenerator.getRandomIntBetween(random, -5, 65);
		  int apptDay=ValuesGenerator.getRandomIntBetween(random, -1, 32);
		  int apptMonth=ValuesGenerator.getRandomIntBetween(random, -1, 13);
		  int apptYear=ValuesGenerator.getRandomIntBetween(random, -20, 2018);

		  Appt appt = new Appt(
			apptHour,
			apptMinute,
			apptDay,
			apptMonth,
			apptYear,
			null,
			null,
			null);
		  //appt.setValid();

		  //Add appointment
		  calday0.addAppt(appt);
	       }
	    }		  

	    elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
	    if((iteration%10000)==0 && iteration!=0 )
	       System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
	 }
      }
      catch(NullPointerException e){

      }
      System.out.println("Done testing...");
   }
}


