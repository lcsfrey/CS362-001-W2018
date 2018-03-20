
package finalprojectB;

import junit.framework.TestCase;
import junit.*;
import java.lang.Object;
import java.io.*;
//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

public class UrlValidatorTest extends TestCase {

    // object used to write log files
    public FileWriter out;

    public UrlValidatorTest(String testName) throws IOException {
        super(testName);
        
        // delete old log file and create new one
        File file = new File("testErrorLog.txt");
        file.delete();
        out = new FileWriter("testErrorLog.txt");
    }

    public void testManualTest() throws IOException {
        UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        
        // test basic http url
        String url = "http://www.google.com";
        assertTrue(urlval.isValid(url)); // true
        
        // test url with / at the end
        url = "http://www.google.com/";
        assertTrue(urlval.isValid(url)); // true
        
        // test url with no www.
        url = "http://google.com";
        assertTrue(urlval.isValid(url)); // true
        
        try {
            // test basic url with https
            assertTrue(urlval.isValid("https://www.google.com"));
        } catch (Throwable e) {
            String str = "Method testManualTest: " + e + " on url: " + url + "\r\n";
            out.append(str);
        }
        
        try {
            // test https url with / at the end
            assertTrue(urlval.isValid("https://www.google.com/"));
        } catch (Throwable e) {
            String str = "Method testManualTest: " + e + " on url: " + url + "\r\n";
            out.append(str);
        }
        
        try {
            // test https url with www at the end
            assertTrue(urlval.isValid("https://google.com"));
        } catch (Throwable e) {
            String str = "Method testManualTest: " + e + " on url: " + url + "\r\n";
            out.append(str);
        }
        System.out.println("testManualTest finished. Errors written to testErrorLog.txt");
    }

    public void testManualTest2() throws IOException {
        // test urls with default constructed UrlValidator object
        UrlValidator urlval = new UrlValidator();
        
        String[] url = { "http://www.google.com", "https://www.google.com", "http://google.com" };
        for (int i = 0; i < 3; i++) {
            try {
                assertTrue(urlval.isValid(url[i]));
            } catch (Throwable e) {
                String str = "Method testManualTest2: " +  e + " on url: " + url + "\r\n";
                out.append(str);
            }
        }
        System.out.println("testManualTest2 finished. Errors written to testErrorLog.txt");
    }

    // Partition 1: valid and invalid schemes/protocols
    public void testYourFirstPartition() throws IOException {
        UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] validScheme = { "http://", "https://", "ftp:///", "h3t://" };
        String[] invalidScheme = { "xxx://", "yyy://", "x://", "y://" };
        String[] correctAuthority = { "www.google.com", "google.com", "oregonstate.edu", "reddit.com" };
        String url = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                if (i < 4) {
                    // test valid schemes
                    url = validScheme[i] + correctAuthority[j];
                    try {
                        assertTrue(urlval.isValid(url));
                    } catch (Throwable e) {
                        String str = "Method testYourFirstPartition: " +  e + " on url: " + url + "\r\n";
                        out.append(str);
                    }
                } else {
                    // test invalid schemes
                    url = invalidScheme[i % 4] + correctAuthority[j];
                    try {
                        assertFalse(urlval.isValid(url));
                    } catch (Throwable e) {
                        String str = "Method testYourFirstPartition: " + e + " on url: " + url + " in method testYourFirstPartition\r\n";
                        out.append(str);
                    }
                }
            }
        }
        System.out.println("testYourFirstPartition finished. Errors written to testErrorLog.txt");
    }

    // test valid and invalid domains
    public void testYourSecondPartition() throws IOException {
        UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] validAuthority = { "www.google.com", "255.255.255.255", "google.com", "255.com" };
        String[] invalidAuthority = { "1.2.3.4.5", "1.2.3.4.", ".1.2.3.4", "go.a" };
        String[] correctScheme = { "http://", "https://", "ftp://", "h3t://" };
        String url = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                if (i < 4) {
                    // test valid authority
                    url = correctScheme[j] + validAuthority[i];
                    try {
                        assertTrue(urlval.isValid(url));
                    } catch (Throwable e) {
                        String str = "Method testYourSecondPartition: " + e + " on url: " + url + "\r\n";
                        out.append(str);
                    }
                } else {
                    // test valid authority
                    url = correctScheme[j] + invalidAuthority[i % 4];
                    try {
                        assertFalse(urlval.isValid(url));
                    } catch (Throwable e) {
                        String str = "Method testYourSecondPartition: " + e + " on url: " + url + "\r\n";
                        out.append(str);
                    }
                }
            }
        }
        System.out.println("testYourSecondPartition finished. Errors written to testErrorLog.txt");
    }

    // test valid and invalid subdirectories
    public void testYourThirdPartition() throws IOException {
        UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] validPath = { "/test1", "/t123", "/~alotaima", "" };
        String[] invalidPath = { "/..", "/.", "/..//", "/../hghgh" };
        String[] validPathOptions = { "/test1", "/t123", "/$23/file", "" };
        String[] invalidPathOptions = { "/#", "/..", "/../file", "/#/file" };
        String[] correctScheme = { "http://www.google.com", "https://www.google.com", "ftp://www.google.com", "h3t://www.google.com" };
        String url = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int z = 0; z < 4; z++) {
                    if (i < 4 && j < 4) {
                        // test urls with valid directories and subdirectories 
                        url = correctScheme[z] + validPath[i] + validPathOptions[j];
                        try {
                            assertTrue(urlval.isValid(url));
                        } catch (Throwable e) {
                            String str ="Method testYourThirdPartition: " + e + " on url: " + url + "\r\n";
                            out.append(str);
                        }
                    } else {
                        if (i < 4)
                            // test with valid directories
                            url = correctScheme[z] + validPath[i];
                        else
                            // test with invalid directories
                            url = correctScheme[z] + invalidPath[i % 4];
                        if (j < 4)
                            // test with valid subdirectories
                            url = url + validPathOptions[j];
                        else
                            // test with invalid subdirectories
                            url = url + invalidPathOptions[j % 4];
                        try {
                            assertFalse(urlval.isValid(url));
                        } catch (Throwable e) {
                            String str ="Method testYourThirdPartition: " + e + " on url: " + url + "\r\n";
                            out.append(str);
                        }
                    }
                }
            }
        }
        System.out.println("testYourThirdPartition finished. Errors written to testErrorLog.txt");
    }
    
    // You need to create more test cases for your Partitions if you need to

    public void testIsValid() throws IOException { // You can use this function for programming based
        UrlValidator urlval = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] invalidScheme = { "http://", "https://", "ftp://", "h3t://", "xxx://", "yyy://", "x://", "y://" };
        String[] invalidAuthority = { "www.google.com", "255.255.255.255", "google.com", "255.com", "1.2.3.4.5", "1.2.3.4.", ".1.2.3.4", "go.a" };
        String[] invalidPort = { ":80", ":0", ":65535", "", ":-1", ":12dda", ":4ds", ":4fdf" };
        String[] invalidPath = { "/test1", "/t123", "/~alotaima", "", "/..", "/.", "/..//", "/../hghgh" };
        String[] invalidPathOptions = { "/test1", "/t123", "/$23/file", "", "/#", "/..", "/../file", "/#/file" };
        String[] invalidUrlQuery = { "?action=view", "?action=edit&mode=up", "?action=list", "" };

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        for (int z = 0; z < 8; z++) {
                            for (int w = 0; w < 4; w++) {
                                String urlValid = invalidScheme[i] + invalidAuthority[j]
                                        + invalidPort[x] + invalidPath[y] + invalidPathOptions[z]
                                        + invalidUrlQuery[w];
                                try {
                                    if (i > 3 || j > 3 | x > 3 || y > 3 || z > 3) {
                                        // test with an invalid url part
                                        assertFalse(urlval.isValid(urlValid));
                                    } else {
                                        // test with all valid url parts
                                        assertTrue(urlval.isValid(urlValid));
                                        System.out.println(urlValid);
                                    }
                                } catch (Throwable e) {
                                    String str ="Method testIsValid: " + e + " on url: " + urlValid + "\r\n";
                                    out.append(str);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("testIsValid finished. Errors written to testErrorLog.txt");
    }
}
