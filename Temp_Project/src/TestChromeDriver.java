import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class TestChromeDriver {
	WebDriver driver;
	static String baseurl;
	@Before
	public void setUp()
	{
		//File file =new File("C:/Users/vikumars/AppData/Local/Google/Chrome/Application/chrome.exe");	
		System.setProperty("webdriver.chrome.driver","C:/Users/vikumars/Desktop/MCUI Automation/chromedriver_win_23.0.1240.0/chromedriver.exe");
		//File file =new File("C:/Users/vikumars/Desktop/MCUI Automation/chromedriver_win_23.0.1240.0/chromedriver.exe");
		//ChromeOptions options=new ChromeOptions();
		//options.setBinary(file);
		driver=new ChromeDriver();
		baseurl="http://dev.reseller.adobe.com";
	}
	@Test
	public void openurl()
	{
		driver.get(baseurl+"/");
	}
}
