package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;
import java.util.*;
import java.io.*;


/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {

   private static final long TestTimeout = 60 * 250 * 1; /* Timeout at 15 seconds */
   private static final int NUM_TESTS=25;

   /**
    * Return a randomly selected method to be tests !.
    */
   public static String RandomSelectMethod(Random random){
      String[] methodArray = new String[] {"saveAppt", "deleteAppt", "getApptRange"};// The list of the of methods to be tested in the Appt class

      int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

      return methodArray[n] ; // return the method name 
   }

   /**
    * Generate Random Tests that tests DataHandler Class.
    */
   @Test
   public void radnomtest()  throws Throwable  {

      long startTime = Calendar.getInstance().getTimeInMillis();
      long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


      System.out.println("Start testing...");


      //Generate 2 appts (one valid, one invalid) consistent through NUM_TESTS (to test save/delete)
      int appt1Hour=15;
      int appt2Hour=30;
      int apptMinute=30;
      int apptDay=15;
      int apptMonth=5;
      int apptYear=2018;
      Appt appt1 = new Appt(
	    appt1Hour,
	    apptMinute,
	    apptDay,
	    apptMonth,
	    apptYear,
	    null,
	    null,
	    null);
      appt1.setValid();
      Appt appt2 = new Appt(
	    appt2Hour,
	    apptMinute,
	    apptDay,
	    apptMonth,
	    apptYear,
	    null,
	    null,
	    null);
      appt2.setValid();

      try{ 
	 for(int iteration = 0; elapsed < TestTimeout; iteration++) {
	    long randomseed =System.currentTimeMillis(); //10
	    //			System.out.println(" Seed:"+randomseed );
	    Random random = new Random(randomseed);

	    //Construct linked list of caldays
	    LinkedList<CalDay> calDays = new LinkedList<CalDay>();


	    //Generate autosave value for DataHandler object
	    int autoSaveInt = ValuesGenerator.getRandomIntBetween(random, 0, 1);
	    boolean autosave;
	    if(autoSaveInt==1)
	       autosave = true;
	    else
	       autosave = false;

	    //Generate DataHandler object
	    DataHandler dh = new DataHandler("test.txt", autosave);

	    //Generate new random valid appt (to test getApptRange)
	    int randApptHour=ValuesGenerator.getRandomIntBetween(random, 1, 23);
	    int randApptMinute=ValuesGenerator.getRandomIntBetween(random, 0, 59);
	    int randApptDay=ValuesGenerator.getRandomIntBetween(random, 1, 28);
	    int randApptMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
	    int randApptYear=ValuesGenerator.getRandomIntBetween(random, 1, 2018);

	    Appt randAppt = new Appt(
		  randApptHour,
		  randApptMinute,
		  randApptDay,
		  randApptMonth,
		  randApptYear,
		  null,
		  null,
		  null);
	    randAppt.setValid();
	    int[] recurDays = {1};
	    randAppt.setRecurrence(recurDays, 3, 1, 1000);
	    dh.saveAppt(randAppt);

	    for(int i = 0; i < NUM_TESTS; i++) {
	       String methodName = DataHandlerRandomTest.RandomSelectMethod(random);

	       if(methodName.equals("saveAppt")){
		  //Save consistent appointment
		  dh.saveAppt(appt1);
		  dh.saveAppt(appt2);
	       }
	       else if(methodName.equals("deleteAppt")){
		  dh.deleteAppt(appt1);
		  dh.deleteAppt(appt2);
	       }
	       else if(methodName.equals("getApptRange")){
		  //Generate 2 gregorian calendar days
		  int gregDay1=ValuesGenerator.getRandomIntBetween(random, 1, 31);
		  int gregMonth1=ValuesGenerator.getRandomIntBetween(random, 1, 12);
		  int gregYear1=ValuesGenerator.getRandomIntBetween(random, -20, 2018);
		  GregorianCalendar day1 = new GregorianCalendar(gregYear1, gregMonth1, gregDay1);

		  int gregDay2=ValuesGenerator.getRandomIntBetween(random, -1, 32);
		  int gregMonth2=ValuesGenerator.getRandomIntBetween(random, -1, 13);
		  int gregYear2=ValuesGenerator.getRandomIntBetween(random, -20, 2018);
		  GregorianCalendar day2 = new GregorianCalendar(gregYear2, gregMonth2, gregDay2);

		  try{
		     calDays = (LinkedList<CalDay>) dh.getApptRange(day1, day2);
		  }catch(DateOutOfRangeException e){
		     System.err.println(e.toString());
		  }

	       }
	    }
	    elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
	    if((iteration%10000)==0 && iteration!=0 )
	       System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);

	    //Delete test file (xml tree save info) after each run
	    File file = new File("test.txt");
	    file.delete();
	 }
      }catch(NullPointerException e){

      }
      System.out.println("Done testing...");
   }
}

