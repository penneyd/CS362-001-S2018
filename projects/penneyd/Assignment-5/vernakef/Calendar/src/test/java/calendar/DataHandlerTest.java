
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.*;
import java.io.*;

public class DataHandlerTest{

  //Test simplest constructor & appt save/delete w/ null fields
  @Test(timeout = 10000)
  public void test00()  throws Throwable  {
     DataHandler dh0 = new DataHandler();

     Appt appt0 = new Appt(15, 30, 14, 9, 2018, null, null, null);
     appt0.setValid();
     Appt appt1 = new Appt(24, 45, 14, 9, 2018, null, null, null);
     appt1.setValid();		//should be invalid

     assertFalse(dh0.saveAppt(appt1));
     assertTrue(dh0.saveAppt(appt0));
     
     assertFalse(dh0.deleteAppt(appt1));
     assertTrue(dh0.deleteAppt(appt0));
  
  }
  //Test more complicated constructor & appt save/delete w/ null fields 
  @Test(timeout = 10000)
  public void test01()  throws Throwable  {
     String name = "testName0";
     DataHandler dh0 = new DataHandler(name);
     
     Appt appt0 = new Appt(15, 30, 14, 9, 2018, null, null, null);
     appt0.setValid();
     Appt appt1 = new Appt(24, 45, 14, 9, 2018, null, null, null);
     appt1.setValid();		//should be invalid

     assertFalse(dh0.saveAppt(appt1));
     assertTrue(dh0.saveAppt(appt0));
     
     assertFalse(dh0.deleteAppt(appt1));
     assertTrue(dh0.deleteAppt(appt0));

  }
  //Test full constructor with multiple DataHandler objects & appt save/delete with/without autosave
  @Test(timeout = 10000)
  public void test02()  throws Throwable  {

     String name = "testName1";
     String title = "Test Event";
     String description = "Description for test event";
     String emailAddress = "email@test.com";

     DataHandler dh0 = new DataHandler(name, false);
     DataHandler dh1 = new DataHandler(name, true);

     Appt appt0 = new Appt(15, 30, 14, 9, 2018, title, description, emailAddress);
     appt0.setValid();

     assertTrue(dh0.saveAppt(appt0));
     assertTrue(dh0.deleteAppt(appt0)); //return true since not autosave

     assertTrue(dh1.saveAppt(appt0));
     assertTrue(dh1.deleteAppt(appt0)); //autosave should return true

     assertTrue(dh1.saveAppt(appt0));
     appt0.setXmlElement(null);		//try setting to null
     assertFalse(dh1.deleteAppt(appt0)); //should return false as Xml location missing

  }
  //Test getApptRange
  @Test(timeout = 10000)
  public void test03()  throws Throwable  {

     String name = "testName2";
     String title = "Test Event";
     String title2 = "Test Event 2";
     String title3 = "Test Event 3";
     String title4 = "Test Event 4";
     String title5 = "Test Event 5";
     String title6 = "Test Event 6";
     String description = "Description for test event";
     String emailAddress = "email@test.com";

     DataHandler dh0 = new DataHandler(name, true);

     Appt appt0 = new Appt(15, 30, 14, 9, 2018, title, description, emailAddress);
     int[] recurDays = {3};
     appt0.setRecurrence(recurDays, Appt.RECUR_BY_WEEKLY, 1, 2);
     appt0.setValid();
     assertTrue(dh0.saveAppt(appt0));
     
     Appt appt1 = new Appt(15, 45, 14, 9, 2018, title2, description, emailAddress);
     int[] recurDays2 = new int[0];
     appt1.setRecurrence(recurDays2, Appt.RECUR_BY_WEEKLY, 1, 2);
     appt1.setValid();
     assertTrue(dh0.saveAppt(appt1));
     
     Appt appt2 = new Appt(8, 45, 14, 10, 2018, title3, description, emailAddress);
     int[] recurDays3 = {4};
     appt2.setRecurrence(recurDays2, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
     appt2.setValid();
     assertTrue(dh0.saveAppt(appt2));
     
     Appt appt3 = new Appt(8, 45, 14, 10, 2018, title4, description, emailAddress);
     appt3.setRecurrence(recurDays3, Appt.RECUR_BY_YEARLY, 1, Appt.RECUR_NUMBER_FOREVER);
     appt3.setValid();
     assertTrue(dh0.saveAppt(appt3));

     Appt appt4 = new Appt(8, 45, 15, 10, 2018, title5, description, emailAddress);
     appt4.setValid();
     assertTrue(dh0.saveAppt(appt4));
     
     Appt appt5 = new Appt(8, 45, 15, 10, 2020, title6, description, emailAddress);
     appt3.setRecurrence(recurDays3, 5, 1, Appt.RECUR_NUMBER_FOREVER);
     assertTrue(dh0.saveAppt(appt4));

     GregorianCalendar day1 = new GregorianCalendar(2018, 8, 14);
     GregorianCalendar day2 = new GregorianCalendar(2019, 9, 24);

     LinkedList<CalDay> calDays = new LinkedList<CalDay>();
     //calDays = (LinkedList<CalDay>) dh0.getApptRange(day1, day2);

     //Check returned CalDays
     //String[] strArr = new String[5];
     String expectedStr1 = "9-14-2018 \n\t3:30PM Test Event Description for test event \n\t3:45PM Test Event 2 Description for test event ";
     String expectedStr2 = "10-14-2019 \n\t8:45AM Test Event 3 Description for test event \n\t8:45AM Test Event 4 Description for test event "; 
     
     /*
     int count = 0;
     int i = 0;
     for (i = 0; i < calDays.size(); i++){
	if ((i == 0) || (i == 4) || (i == 7) || (i == 30) || (i == 395)) {
	  CalDay calday = calDays.get(i);
	  strArr[count] = calday.getFullInfomrationApp(calday);
	  //System.out.println(strArr[count]);
	  //System.out.println(expectedStr1);
	  count++;
	}
     }*/

     //CalDay calday = calDays.get(0);
     //String strArr = calday.getFullInfomrationApp(calday);

     System.out.println("\nLength of strArr: ");
     //System.out.println(strArr.length());
     System.out.println("\nLength of expectedStr1: ");
     System.out.println(expectedStr1.length());
     System.out.println("\n");

     //System.out.println("strArr: \n");
     //System.out.println(strArr);
     //System.out.println("expectedStr1: \n");
     //System.out.println(expectedStr1);
     
     //assertTrue(expectedStr1.equals(strArr));
	
  }
  
  //Tests exception for CalDay linked list
  @Test(timeout = 10000)
  public void test04()  throws Throwable  {
     
     String name = "testName3";
     
     DataHandler dh0 = new DataHandler(name, true);
     
     GregorianCalendar day1 = new GregorianCalendar(2018, 8, 14);
     GregorianCalendar day2 = new GregorianCalendar(2019, 9, 24);

     LinkedList<CalDay> calDays = new LinkedList<CalDay>();

     //day2 before day1 so should throw exception
     try {
	calDays = (LinkedList<CalDay>) dh0.getApptRange(day2, day1);
	fail("Day2 before day1 so should throw DateOutOfRangeException");
     
     } catch (DateOutOfRangeException e) {
	System.err.println(e.toString());
     }
  
  }
  
  //Test construction with filename already existing (non xml) 
  @Test(timeout = 10000)
  public void test05()  throws Throwable  {

    String name = "testName4";
    File file = new File(name);
    FileWriter fileWriter = new FileWriter(file);
    fileWriter.write("This is a test line");
    fileWriter.flush();
    fileWriter.close();

    try {
       DataHandler dh0 = new DataHandler(name);
       fail("Should have IOException");

    } catch (IOException e) {
       System.err.println(e.toString());
    }
  }
  
     
}
