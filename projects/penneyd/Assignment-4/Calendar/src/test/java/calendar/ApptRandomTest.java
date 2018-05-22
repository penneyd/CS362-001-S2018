package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {

   private static final long TestTimeout = 60 * 50 * 1; /* Timeout at 3 seconds */
   private static final int NUM_TESTS=100;

   /**
    * Return a randomly selected method to be tests !.
    */
   public static String RandomSelectMethod(Random random){
      String[] methodArray = new String[] {"setValid","setRecurrence","isOn"};// The list of the of methods to be tested in the Appt class

      int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

      return methodArray[n] ; // return the method name 
   }
   /**
    * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
    */
   
      public static int RandomSelectRecur(Random random){
      int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

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
   public void radnomtest()  throws Throwable  {

      long startTime = Calendar.getInstance().getTimeInMillis();
      long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


      System.out.println("Start testing...");

      try{ 
	 for(int iteration = 0; elapsed < TestTimeout; iteration++) {
	    long randomseed =System.currentTimeMillis(); //10
	    //			System.out.println(" Seed:"+randomseed );
	    Random random = new Random(randomseed);

	    int startHour=ValuesGenerator.getRandomIntBetween(random, -2, 25);
	    int startMinute=ValuesGenerator.getRandomIntBetween(random, -5, 65);
	    int startDay=ValuesGenerator.getRandomIntBetween(random, -1, 32);
	    int startMonth=ValuesGenerator.getRandomIntBetween(random, -1, 13);
	    int startYear=ValuesGenerator.getRandomIntBetween(random, -20, 2018);
	    String title="Test Event";
	    String description="This is a test event.";
	    String emailAddress="xyz@gmail.com";

	    //Construct a new Appointment object with the initial data	 
	    Appt appt = new Appt(startHour,
		  startMinute ,
		  startDay ,
		  startMonth ,
		  startYear ,
		  title,
		  description,
		  emailAddress);

	    for(int i = 0; i < NUM_TESTS; i++) {
	       String methodName = ApptRandomTest.RandomSelectMethod(random);
	       if(methodName.equals("setValid")){
		  appt.setValid();


	       }
	       else {
		  if(!appt.getValid())break;
		  else if(methodName.equals("setRecurrence")){
		     //Generate random number to determine if use null for recur days
		     int useNull = ValuesGenerator.getRandomIntBetween(random, 0, 1);
		     int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
		     int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
		     int recur=ApptRandomTest.RandomSelectRecur(random);
		     int recurIncrement = ValuesGenerator.RandInt(random);
		     int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
		     
		     if(useNull==0)
		     {
			appt.setRecurrence(null, recur, recurIncrement, recurNumber);
		     }
		     else
		     {
			appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
		     }

		  }
		  else if(methodName.equals("isOn")){
		     boolean result;
		     //Use additional random number to determine whether to use generated date
		     int realDay = ValuesGenerator.getRandomIntBetween(random, 0, 1);
		     int realMonth = ValuesGenerator.getRandomIntBetween(random, 0, 1);
		     int realYear = ValuesGenerator.getRandomIntBetween(random, 0, 1);
		     int genDay = ValuesGenerator.getRandomIntBetween(random, -1, 32);
		     int genMonth = ValuesGenerator.getRandomIntBetween(random, -1, 13);
		     int genYear = ValuesGenerator.getRandomIntBetween(random, -20, 2018);

		     if((realDay==1) && (realMonth==1) && (realYear==1))
			result = appt.isOn(startDay, startMonth, startYear);
		     else if((realDay==1) && (realMonth==1) && (realYear==0))
			result = appt.isOn(startDay, startMonth, genYear);
		     else if((realDay==1) && (realMonth==0) && (realYear==1))
			result = appt.isOn(startDay, genMonth, startYear);
		     else if((realDay==0) && (realMonth==1) && (realYear==1))
			result = appt.isOn(genDay, startMonth, startYear);
		     else if((realDay==1) && (realMonth==0) && (realYear==0))
			result = appt.isOn(startDay, genMonth, genYear);
		     else if((realDay==0) && (realMonth==1) && (realYear==0))
			result = appt.isOn(genDay, startMonth, genYear);
		     else if((realDay==0) && (realMonth==0) && (realYear==1))
			result = appt.isOn(genDay, genMonth, startYear);


		  }
	       }		  
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

