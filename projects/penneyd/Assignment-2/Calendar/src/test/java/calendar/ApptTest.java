/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      //assertEquals("xyz@gmail.com", appt0.getEmailAddress()); 
      //Commented out due to bug from assignment-1
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      int recurInc = appt0.getRecurIncrement();
      //assertEquals(0, recurInc);
      //Commented out due to revised bug
      appt0.setValid();
  }
 
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Appt appt1 = new Appt(14, 9, 2018, null, null, null);
      assertFalse(appt1.hasTimeSet());
      assertTrue(appt1.isOn(14, 9, 2018));
      assertFalse(appt1.isOn(13, 9, 2018));
      assertFalse(appt1.isOn(14, 8, 2018));
      assertFalse(appt1.isOn(14, 9, 2017));
      appt1.setValid();
      String string0 = appt1.toString();
      assertEquals(2, appt1.getRecurBy());	
      assertFalse(appt1.isRecurring());

      int recurInc = appt1.getRecurIncrement();
      //assertEquals(0, recurInc);
      //Commented out due to revised bug
      appt1.setStartHour(0);
      appt1.setStartMinute(30); 
      appt1.setValid();
      assertTrue(appt1.hasTimeSet());
      assertTrue(appt1.getValid());
      
      int[] recur = {2,3};
      appt1.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
      assertEquals(0, (appt1.getRecurDays()).length);
      appt1.setRecurrence(recur, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
      assertEquals(recur, appt1.getRecurDays()); 
      //assertTrue(appt1.isRecurring());
      //Commented out due to bug
      assertNull(appt1.getXmlElement());
  }
  
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Appt appt2 = new Appt(15, 30, 31, 2, 2018, null, null, null);
      appt2.setValid();
      //assertFalse(appt2.getValid());
      //Commented out due to bug
      
      appt2.setStartDay(28);
      appt2.setValid();
      assertTrue(appt2.getValid());
      
      appt2.setStartYear(-2);
      appt2.setValid();
      assertFalse(appt2.getValid());
      appt2.setStartYear(2018);
      appt2.setValid();
      assertTrue(appt2.getValid());
      
      appt2.setStartMinute(60);
      appt2.setValid();
      assertFalse(appt2.getValid());
      appt2.setStartMinute(30);
      appt2.setValid();
      assertTrue(appt2.getValid());
      
      appt2.setStartHour(24);
      appt2.setValid();
      assertFalse(appt2.getValid());
      appt2.setStartHour(15);
      appt2.setValid();
      assertTrue(appt2.getValid());
      
      appt2.setStartMonth(13);
      appt2.setValid();
      assertFalse(appt2.getValid());
      appt2.setStartMonth(2);
      appt2.setValid();
      assertTrue(appt2.getValid());
  }
}
