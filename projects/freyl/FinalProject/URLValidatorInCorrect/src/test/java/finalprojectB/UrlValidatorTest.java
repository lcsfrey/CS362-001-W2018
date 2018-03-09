
package finalprojectB;

import junit.framework.TestCase;
import junit.*;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!


public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   public void testManualTest() {
	  //You can use this function to implement your manual testing	   
	  UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	  //assertFalse(urlval.isValid(null));
	  //System.out.println(true);
	  String url = "https://www.google.com";
	  //System.out.println(urlval.isValid(url));
	  try {
		  System.out.println(urlval.isValidAuthority("://"));
		//  System.out.println(urlval.isValid(url));
	  } catch (Exception e) {
		  System.out.println(e);
	  }
		  assertTrue(true);
   }
   
   
   public void testYourFirstPartition() {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition() {
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid() {
	   //You can use this function for programming based testing

   }
   
//   public static void main() {
//	   UrlValidatorTest test = new UrlValidatorTest("manual");
//	   test.testManualTest();
//   }

}
