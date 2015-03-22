import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import utils.Constant;
		
		public class ImportCSVFileUsingRobotClass {
			
			String baseUrl;
			static WebDriver driver;
			static String baseUrl2="https://namail.corp.adobe.com";
			Robot robot;
					  		
		@BeforeSuite(alwaysRun=true)
		public void setUp() throws Exception {
				//driver = new InternetExplorerDriver();
			
			//For Chrome browser setup
				//System.setProperty("webdriver.chrome.driver","C:/Users/vikumars/Desktop/MCUI Automation/chromedriver_win_23.0.1240.0/chromedriver.exe");
				//driver=new ChromeDriver();
				
				//For FireFox browser setup
				driver = new FirefoxDriver(new FirefoxBinary(new java.io.File("C:/Program Files (x86)/Mozilla Firefox/firefox.exe")), new FirefoxProfile());
				baseUrl = "https://stage.reseller.adobe.com";
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				robot=new Robot();
			}
				
		//login method
		
		@Test(priority= 1)
			public void loginToReseller() throws InterruptedException {
			driver.manage().deleteAllCookies();
			driver.get(baseUrl+"/");
			driver.findElement(By.id("ccm_reseller_login_inputText_emailId")).clear();
			driver.findElement(By.id("ccm_reseller_login_inputText_emailId")).sendKeys(Constant.resellerusername);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("ccm_reseller_login_password_pwd")).sendKeys(Constant.resellerloginpass);
			driver.findElement(By.linkText("Sign in")).click();
			//driver.findElement(By.linkText("Sign in")).click();
			Thread.sleep(5000);
			System.out.print("\n Login Sucessfull");
		}
	//Import csv file using robot class
		@Test(priority= 2)
		public void importCSVFile() throws InterruptedException, IOException
		{
			driver.findElement(By.id("ccm_reseller_home_div_AddSalesRep")).click();
			Thread.sleep(5000);
			String resconsole=driver.getWindowHandle();
			
			driver.findElement(By.xpath("//*[@id='content']/div[2]/span[2]/div/div[3]/div/div[1]/div[2]/input")).click();
			
			//Switch to new window opened
			for(String newwindow : driver.getWindowHandles())
			{
				driver.switchTo().window(newwindow);
			}
			//Perform the actions on new window
			type("C");
			robot.delay(60);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_SEMICOLON);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.delay(40);
			type("\\Users\\vikumars\\Desktop\\CSV files\\import.txt");	
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			//robot.keyRelease(KeyEvent.VK_ENTER);
			
			//Switch back to original browser (first window)
			driver.switchTo().window(resconsole);
			
			Thread.sleep(5000);
			driver.findElement(By.linkText("Invite")).click();
		}

		private void type(String s) {
		// TODO Auto-generated method stub
		byte[] bytes=s.getBytes();
		for(byte b : bytes)
		{
			int code = b;
			 // keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if(code >96 && code < 123)
				code=code-32;
			robot.delay(40);
		    robot.keyPress(code);
		    robot.keyRelease(code);
		}
			
		
	}
	
}
