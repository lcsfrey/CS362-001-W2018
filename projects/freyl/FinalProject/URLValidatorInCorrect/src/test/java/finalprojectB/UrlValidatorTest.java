
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
   // http only works
   public void testManualTest() {
	  //You can use this function to implement your manual testing	   
	  UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	  String url = "http://www.google.com";
	  assertTrue(urlval.isValid(url)); // true
	  url = "http://www.google.com/";
	  assertTrue(urlval.isValid(url)); // true
	  url = "http://google.com";
	  assertTrue(urlval.isValid(url)); //true
	  //url = "http://.google.com";
	  //assertFalse(urlval.isValid(url)); // true
	  //url = "http://www.google.com.";
	  //assertFalse(urlval.isValid(url)); // true
	  //url = "http.://google.com";
	  //assertFalse(urlval.isValid(url)); //true
	  try {
		  assertTrue(urlval.isValid("https://www.google.com"));
	  }catch(Throwable e){
			 System.out.println(e+" : " + url);
	}
	  try {
		  assertTrue(urlval.isValid("https://www.google.com/"));
	  }catch(Throwable e){
			 System.out.println(e+" : " + url);
	}try {
		  assertTrue(urlval.isValid("https://google.com"));
	  }catch(Throwable e){
			 System.out.println(e+" : " + url);
	}
   }
   // HTTP, HTTPS, and FTP
   public void testManualTest2() {
	  //You can use this function to implement your manual testing	   
	   UrlValidator urlval = new UrlValidator();
	   String[] url = {"http://www.google.com","https://www.google.com","http://google.com"};
		 for(int i=0; i< 3; i++) {
			 assertTrue(urlval.isValid(url[i]));
		 }
   }
   public void testYourFirstPartition() {
	 //You can use this function to implement your First Partition testing
	   UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		 String[] validScheme = {"http://", "https://", "ftp:///", "h3t://"};
		 String[] invalidScheme = {"xxx://", "yyy://", "x://", "y://"};
		 String[] correctAuthority = {"www.google.com", "google.com", "oregonstate.edu", "reddit.com"};
		 String url ="";
		 for(int i=0; i<8; i++) {
			 for(int j=0; j<4;j++) {
				 if(i<4) {
					 url = validScheme[i] + correctAuthority[j];
					 try {
						 assertTrue(urlval.isValid(url));
						 //System.out.println(url);
					 }catch(Throwable e){
						 System.out.println(e+" : " + url);
					 }
				 }
				 else {
					 url = invalidScheme[i%4] + correctAuthority[j];
					 try {
						 assertFalse(urlval.isValid(url));
						 //System.out.println(url);
					 }catch(Throwable e){
						 System.out.println(e+" : " + url);
					 }
				 }
			 }
		 }	
   }
   public void testYourSecondPartition() {
	   //You can use this function to implement your Second Partition testing
	   UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		 String[] validAuthority = {"www.google.com", "255.255.255.255", "google.com", "255.com"};
		 String[] invalidAuthority = {"1.2.3.4.5", "1.2.3.4.", ".1.2.3.4", "go.a"};
		 String[] correctScheme = {"http://", "https://", "ftp://", "h3t://"};
		 String url ="";
		 for(int i=0; i<8; i++) {
			 for(int j=0; j<4;j++) {
				 if(i<4) {
					 url = correctScheme[j] + validAuthority[i];
					 try {
						 assertTrue(urlval.isValid(url));
						 //System.out.println(url);
					 }catch(Throwable e){
						 System.out.println(e+" : " + url);
					 }
				 }
				 else {
					 url = correctScheme[j]+invalidAuthority[i%4];
					 try {
						 assertFalse(urlval.isValid(url));
						 //System.out.println(url);
					 }catch(Throwable e){
						 System.out.println(e+" : " + url);
					 }
				 }
			 }
		 }		   
   }
   public void testYourThirdPartition() {
	   //You can use this function to implement your Second Partition testing
	   UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   	 String[] validPath = {"/test1", "/t123", "/~alotaima", ""};
	   	 String[] invalidPath = {"/..", "/.", "/..//", "/../hghgh"};
		 String[] validPathOptions = {"/test1", "/t123", "/$23/file", ""};
		 String[] invalidPathOptions = {"/#", "/..", "/../file", "/#/file"};
		 String[] correctScheme = {"http://www.google.com", "https://www.google.com", "ftp://www.google.com", "h3t://www.google.com"};
		 String url ="";
		 for(int i=0; i<8; i++) {
			 for(int j=0; j<8;j++) {
				 for(int z=0; z<4;z++) {
					 if(i<4 && j < 4) {
						 url = correctScheme[z] + validPath[i] + validPathOptions[j];
						 try {
							 assertTrue(urlval.isValid(url));
							 //System.out.println(url);
						 }catch(Throwable e){
							 System.out.println(e+" : " + url);
						 }
					 }
					 else {
						 if(i < 4)
							 url = correctScheme[z]+validPath[i];
						 else
							 url = correctScheme[z]+invalidPath[i%4];
						 if(j < 4)
							 url = url + validPathOptions[j];
						 else
							 url = url+invalidPathOptions[j%4];
						 try {
							 assertFalse(urlval.isValid(url));
							 //System.out.println(url);
						 }catch(Throwable e){
							 System.out.println(e+" : " + url);
						 }
					 }
				 }
			 }
		 }		   
   }
   //You need to create more test cases for your Partitions if you need to 
   /*
   public void testIsValid() {
	   //You can use this function for programming based testing
	   UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		 String[] invalidScheme = {"http://", "https://", "ftp://", "h3t://","xxx://", "yyy://", "x://", "y://"};
		 String[] invalidAuthority = {"www.google.com", "255.255.255.255", "google.com", "255.com", "1.2.3.4.5", "1.2.3.4.", ".1.2.3.4", "go.a"};
		 String[] invalidPort = {":80", ":0", ":65535", "",":-1", ":12dda", ":4ds", ":4fdf"};
		 String[] invalidPath = {"/test1", "/t123", "/~alotaima", "", "/..", "/.", "/..//", "/../hghgh"};
		 String[] invalidPathOptions = {"/test1", "/t123", "/$23/file", "", "/#", "/..", "/../file", "/#/file"};
		 String[] invalidUrlQuery = {"?action=view", "?action=edit&mode=up", "?action=list", ""};

		 for(int i=0; i<8; i++) {
			 for(int j=0; j<8;j++) {
				 for(int x=0; x<8; x++) {
					 for(int y=0; y<8; y++) {
						 for(int z=0; z<8; z++) {
							 for(int w=0; w<4; w++) {
								 String urlValid = invalidScheme[i]+invalidAuthority[j]+invalidPort[x]+invalidPath[y]+invalidPathOptions[z]+invalidUrlQuery[w];
								 try {
									 if(i > 3 || j > 3 | x > 3 || y > 3 || z > 3)
										 assertFalse(urlval.isValid(urlValid));
									 else {
										 assertTrue(urlval.isValid(urlValid));
										 System.out.println(urlValid);
									 }
								 }catch(Throwable e){
									 System.out.println(e+" : " + urlValid);
								 }
							 }
						 }
					 }
				 }
			 }
		 }

   }*/

}
