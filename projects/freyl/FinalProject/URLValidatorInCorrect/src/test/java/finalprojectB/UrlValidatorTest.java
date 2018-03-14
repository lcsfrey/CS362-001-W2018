
package finalprojectB;

import junit.framework.TestCase;
import junit.*;
import java.lang.Object;
//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!


public class UrlValidatorTest extends TestCase {
   public UrlValidatorTest(String testName) {
      super(testName);
   }
   public void testManualTest() {
	  // test1: https issue
	  // test2: sub-dir issue
	  // test3: 
	  //You can use this function to implement your manual testing	   
	  UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	  //assertFalse(urlval.isValid(null));
	  //System.out.println(true);
	  String url = "http://www.google.com";
	  System.out.println(urlval.isValid(url)); // true
	  url = "http://www.google.com/";
	  System.out.println(urlval.isValid(url)); // true
	  url = "http://www.google.com/alotaima";
	  System.out.println(urlval.isValid(url)); // true
	  url = "http://www.google.com/~alotaima";
	  System.out.println(urlval.isValid(url)); // false
	  url = "http://www.google.com:alotaima/";
	  System.out.println(urlval.isValid(url)); // false
	  url = "http://google.com";
	  System.out.println(urlval.isValid(url)); //true
	  try {
		  System.out.println(urlval.isValid("https://www.google.com/alotaima/"));
	  }catch(Throwable e){
	}
   }
   public void testYourFirstPartition() {
	 //You can use this function to implement your First Partition testing	   
	 UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	 String[] validScheme = {"http://", "https://", "ftp://", "h3t://"};
	 String[] validAuthority = {"www.google.com", "255.255.255.255", "google.com", "255.com"};
	 String[] validPort = {":80", ":0", ":65535", ""};
	 String[] validPath = {"/test1", "/t123", "/$23", ""};
	 String[] validPathOptions = {"/test1", "/t123", "/$23/file", ""};
	 String[] validUrlQuery = {"?action=view", "?action=edit&mode=up", "?action=list", ""};
	 
	 for(int i=0; i<4; i++) {
		 for(int j=0; j<4;j++) {
			 for(int x=0; x<4; x++) {
				 for(int y=0; y<4; y++) {
					 for(int z=0; z<4; z++) {
						 for(int w=0; w<4; w++) {
							 String urlValid = validScheme[i]+validAuthority[j]+validPort[x]+validPath[y]+validPathOptions[z]+validUrlQuery[w];
							 try {
								 assertTrue(urlval.isValid(urlValid));
								 System.out.println(urlValid);
							 }catch(Throwable e){
							 }
						 }
					 }
				 }
			 }
		 }
	 }
	 
	 
   }
   public void testYourSecondPartition() {
	   //You can use this function to implement your Second Partition testing	   
		 UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		 String[] invalidScheme = {"xxx://", "yyy://", "x://", "y://"};
		 String[] invalidAuthority = {"1.2.3.4.5", "1.2.3.4.", ".1.2.3.4", "go.a"};
		 String[] invalidPort = {":-1", ":12dda", ":4ds", ":4fdf"};
		 String[] invalidPath = {"/..", "/.", "/..//", "/../hghgh"};
		 String[] invalidPathOptions = {"/#", "/..", "/../file", "/#/file"};
		 String[] invalidUrlQuery = {"?action=view", "?action=edit&mode=up", "?action=list", ""};
		 
		 for(int i=0; i<4; i++) {
			 for(int j=0; j<4;j++) {
				 for(int x=0; x<4; x++) {
					 for(int y=0; y<4; y++) {
						 for(int z=0; z<4; z++) {
							 for(int w=0; w<4; w++) {
								 String urlValid = invalidScheme[i]+invalidAuthority[j]+invalidPort[x]+invalidPath[y]+invalidPathOptions[z]+invalidUrlQuery[w];
								 try {
									 assertTrue(urlval.isValid(urlValid));
									 System.out.println(urlValid);
								 }catch(Throwable e){
									 System.out.print(".");
								 }
							 }
						 }
					 }
				 }
			 }
		 }	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid() {
	   //You can use this function for programming based testing

   }

}
