package MCUI;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import utils.Constant;

public class SalesRepWorkFlow {
	String baseUrl;
	static WebDriver driver;
	static String baseUrl2="https://namail.corp.adobe.com";
	
	@Before			
public void setUp() throws Exception {
		//driver = new InternetExplorerDriver();
		//System.setProperty("webdriver.chrome.driver", "C:/Users/vikumars/AppData/Local/Google/Chrome/Application/chrome.exe");
		//driver=new ChromeDriver();
		driver = new FirefoxDriver(new FirefoxBinary(new java.io.File("C:/Users/vikumars/AppData/Local/Mozilla Firefox/firefox.exe")), new FirefoxProfile());
		baseUrl = "https://stage.reseller.adobe.com";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl+"/");
		}
@Test
	public void salesRepworkFlow() throws InterruptedException
	{
		//loginToReseller();
		//addSalesRep();
		acceptSalesRepInvite();
	}
	
	//login to reseller
		public void loginToReseller() {
			driver.findElement(By.id("ccm_reseller_login_inputText_emailId")).clear();
			driver.findElement(By.id("ccm_reseller_login_inputText_emailId")).sendKeys(Constant.resellerusername);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("ccm_reseller_login_password_pwd")).sendKeys(Constant.resellerloginpass);
			driver.findElement(By.linkText("Sign in")).click();
			System.out.print("\n Login Sucessfull");
	
}
		//Add Sales-rep
		public void addSalesRep() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("ccm_reseller_home_div_AddSalesRep")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("emailplaceholder")).clear();
			driver.findElement(By.id("emailplaceholder")).sendKeys(Constant.salesrepemailID);
			driver.findElement(By.linkText("Invite")).click();
			System.out.print("\n Sales Rep invitation Sucessfull"); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("ccm_reseller_home_img_drpdwnArrow")).click();
			driver.findElement(By.xpath(".//*[@id='shSignOut']/a")).click();
			System.out.print("\n Sign out Sucessfully"); 
			driver.manage().deleteAllCookies();
		 	}
		
		//Accept sales rep invitation	
		public void acceptSalesRepInvite() throws InterruptedException {
 		driver.get(baseUrl2+"/");
		Thread.sleep(3000);
		driver.findElement(By.id("username")).sendKeys(Constant.emailID);
		driver.findElement(By.id("password")).sendKeys(Constant.password);
		driver.findElement(By.xpath(Constant.Dom_click_logon_xpath)).click();
		Thread.sleep(5000);
	
		driver.findElement(By.id("txtSch")).sendKeys(Constant.salesrepemailID);  //Enter email ID here 
		driver.findElement(By.xpath(Constant.Dom_click_Search_button_xpath)).click();
		System.out.println("search email sucessfuly");
		Thread.sleep(2000);
		driver.findElement(By.xpath(Constant.Dom_click_Searched_email_xpath)).click();
		System.out.println("search link clicked");
		
		//Code for Switch to new window and perform actions on new window
		
		String emailWindow=driver.getWindowHandle();
		
		//driver.findElement(By.xpath("..//*[@id=':ew']")).click();//Click on searched invitation mail
		driver.findElement(By.linkText("here")).click();
			//Thread.sleep(10000);
		
		Set s=driver.getWindowHandles();
		
		Iterator ite=s.iterator();

		while(ite.hasNext())
		{
		    String resellerConsole=ite.next().toString();
		    if(!resellerConsole.contains(emailWindow))
		    {
		                driver.switchTo().window(resellerConsole);
		                System.out.println("Window switched");
		                Thread.sleep(5000);
		               	driver.findElement(By.id("signin_createaccount")).click();
		        		Thread.sleep(5000);
		        		driver.switchTo().frame("ims_signup_frame");
		        		//driver.findElement(By.id("email_address")).sendKeys(Constant.salesrepemailID);
		        		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		        		System.out.println("Sleep over");
		        		driver.findElement(By.id("password")).sendKeys("tester");
		        		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		        		driver.findElement(By.id("retype_password")).sendKeys("tester");
		        		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		        		driver.findElement(By.id("first_name")).sendKeys("F_name");
		        		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		        		driver.findElement(By.id("last_name")).sendKeys("L_name");
		        		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		        		 		                                
		        		Select dateofbirth=new Select(driver.findElement(By.xpath(".//*[@id='month']")));
		      				    dateofbirth.selectByVisibleText("October");
		    		    System.out.println("Month gets selected");	
		        		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		        		Select day=new Select(driver.findElement(By.id("day")));
		        		 		day.selectByVisibleText("12");
		        		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		        		Select year=new Select(driver.findElement(By.id("year")));		
		        		 		year.selectByVisibleText("1974");
		        		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		        		
		        		driver.findElement(By.id("create_account")).click();
		    }
		}
 	}
}


