//import org.openqa.selenium.chrome.ChromeDriver
//
//driver = {
//  File file = new File("C:/Users/Pedro/IdeaProjects/TA2/chromedrivers/chromedriver.exe");
////    File file = new File("/home/ess/TA2/chromedrivers/chromedriverlinux64");
//    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath()  );
//    new ChromeDriver();
//}
//
//baseUrl = "http://localhost:8070/"

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile

driver = {
 File file = new File("chromedrivers/chromedriverlinux64"); //configurar com o enderço correto do chromedriver.
    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
    new ChromeDriver();
} 

environments {
   // run as “grails -Dgeb.env=chrome test-app”
   // See: http://code.google.com/p/selenium/wiki/ChromeDriver
   chrome {
      driver = { File file = new File("chromedrivers/chromedriverlinux64"); //configurar com o enderço correto do chromedriver.
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            new ChromeDriver(); }
   }

    // run as �grails -Dgeb.env=firefox test-app�
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
        driver = { new FirefoxDriver() }
    }
}
