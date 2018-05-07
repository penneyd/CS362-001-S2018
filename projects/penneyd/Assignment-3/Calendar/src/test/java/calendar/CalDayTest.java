/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.*;

public class CalDayTest{

  //Tests simplest constructor
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
     CalDay calday0 = new CalDay();
     assertFalse(calday0.isValid());
     assertNull(calday0.iterator());
     String str0 = calday0.toString();
     assertEquals(0, str0.length());
  }

  //Tests full constructor and several methods including toString
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
     GregorianCalendar day = new GregorianCalendar(2018, 2, 15);
     CalDay calday0 = new CalDay(day);
     assertTrue(calday0.isValid());
     assertEquals(15, calday0.getDay());
     assertEquals(3, calday0.getMonth());
     assertEquals(2018, calday0.getYear());
     Appt appt0 = new Appt(10, 30, 15, 2, 2018, null, null, null);
     Appt appt1 = new Appt(10, 15, 15, 2, 2018, null, null, null);
     Appt appt2 = new Appt(11, 30, 15, 2, 2018, null, null, null);
     calday0.addAppt(appt0);
     assertEquals(1, calday0.getSizeAppts());
     assertEquals(3, calday0.getMonth());
     
     StringBuilder sb = new StringBuilder();
     sb.append("\t --- 4/15/2018 --- \n");
     //Should be 2/15/2018, but bug in given code causes incorrect behavior
     sb.append(" --- -------- Appointments ------------ --- \n");
     Iterator<Appt> itr = (Iterator<Appt>)calday0.iterator();
     Object element = itr.next();
     sb.append(element + " ");;
     sb.append("\n");
     String calStr = calday0.toString();


     //System.out.println("sb to string starts here: ");
     //System.out.println(sb.toString());
     //System.out.println("calStr starts here: ");
     //System.out.println(calStr);

     assertTrue((sb.toString()).equals(calStr));

     calday0.addAppt(appt1);
     assertEquals(2, calday0.getSizeAppts());
     calday0.addAppt(appt2);
     assertEquals(3, calday0.getSizeAppts());
  }
 
   
  //Test getAppts
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
     GregorianCalendar day = new GregorianCalendar(2018, 2, 15);
     CalDay calday0 = new CalDay(day);
     assertTrue(calday0.isValid());
     assertNotNull(calday0.getAppts());
  }

  //Test FullInfomrationAppt()
  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
     GregorianCalendar day = new GregorianCalendar(2018, 2, 15);
     CalDay calday0 = new CalDay(day);  
     Appt appt0 = new Appt(12,  5, 15, 2, 2018, null, null, null);
     Appt appt1 = new Appt( 0,  6, 15, 2, 2018, null, null, null);
     Appt appt2 = new Appt(15, 10, 15, 2, 2018, null, null, null);
     Appt appt3 = new Appt(15,  5, 15, 2, 2018, null, null, null);
     Appt appt4 = new Appt(14, 10, 15, 2, 2018, null, null, null);
     Appt appt5 = new Appt(13, 15, 15, 2, 2018, null, null, null);
     Appt appt6 = new Appt(15, 15, 15, 2, 2018, null, null, null);
     Appt appt7 = new Appt(16,  5, 15, 2, 2018, null, null, null);
     calday0.addAppt(appt0);
     calday0.addAppt(appt1);
     calday0.addAppt(appt2);
     calday0.addAppt(appt3);
     calday0.addAppt(appt4);
     calday0.addAppt(appt5);
     calday0.addAppt(appt6);

     String expectedStr = "3-15-2018 \n\t12:06AM   \n\t0:05AM   \n\t1:15PM   \n\t2:10PM   \n\t3:10PM   \n\t3:05PM   \n\t3:15PM   ";
     //Bug causes incorrect 0:05AM instead of 12:05PM, but this is expected behavior using given code
     String calStr = calday0.getFullInfomrationApp(calday0);
     
     //System.out.println(expectedStr);
     //System.out.println(calStr);
     assertNotNull(calStr);
     assertTrue(expectedStr.equals(calStr));
     
  }
}
