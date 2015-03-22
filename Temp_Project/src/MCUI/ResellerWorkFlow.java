package MCUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.javascript.host.Document;

import utils.Constant;

public class ResellerWorkFlow {
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
		}
	@Test
	public void addCustomer() throws InterruptedException 
	{
		loginToReseller(); 
		inviteCustomer();
		acceptInviteCustomer();
		addMoreSeat();
		managedby();
		changeBreadcrumb();
	}
	//login method
		public void loginToReseller() throws InterruptedException {
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
	
	  //Invite Customer through reseller
	
		public void inviteCustomer() throws InterruptedException {
		driver.findElement(By.xpath(Constant.customerdashboard_btn)).click();
		System.out.print("\n Customer Dashboard launch Sucessfull");
		
		Thread.sleep(5000);
			driver.findElement(By.id(Constant.addcustomer_btn)).click();
			Thread.sleep(5000);
			driver.findElement(By.id(Constant.org_name_text_box)).sendKeys(Constant.orgname);
			//Drop  down Market Segment on Add customer form.
			driver.findElement(By.xpath(Constant.sel_mgket_seg_dropdown)).click();
			System.out.println("market clicked");
			Thread.sleep(2000);	
			driver.findElement(By.id(Constant.sel_mgket_seg_type)).click();
			Thread.sleep(2000);
			
			//Drop  down country Segment on Add customer form.
			driver.findElement(By.xpath(Constant.Sel_country_dropdown)).click();	
			Thread.sleep(2000);
			driver.findElement(By.xpath(Constant.Sel_country__from_dropdown)).click();
			
			//new Select(driver.findElements(By.xpath(".//*[@id='input_country_chzn_o_96']")).s);
			//Fill Address fields
			driver.findElement(By.id("input_address1")).sendKeys("365-Park");
			driver.findElement(By.id("input_address2")).sendKeys("Avenue");
			driver.findElement(By.id("input_city")).sendKeys("CA");
		
			Thread.sleep(10000);
			//Drop  down State Provience Segment on Add customer form.
			driver.findElement(By.xpath(Constant.sel_state_dropdown)).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath(Constant.sel_state_from_dropdown)).click();
		
			//Zip Code Entry
			driver.findElement(By.id("input_zip")).sendKeys("95011");
			//Customer detail
			driver.findElement(By.id("input_fname")).sendKeys("Selenium"); //First Name
	
			driver.findElement(By.id("input_lname")).sendKeys("test"); //Last Name  
	
			driver.findElement(By.id("input_email")).sendKeys(Constant.Add_cus_emailid); //Customer Email Address
				
			driver.findElement(By.id("btn_submit")).click();
			Thread.sleep(6000);
			System.out.print("\n Add Customer Sucessfull");
			//sign out feature
			driver.findElement(By.id(Constant.Dom_sign_out_dropdown)).click();
			driver.findElement(By.xpath(Constant.Dom_sign_out_btn_xpath)).click();
			System.out.print("\n Sign out Sucessfully"); 
			driver.manage().deleteAllCookies();
	}
	
	//accept Customer  invite 

	public void acceptInviteCustomer() throws InterruptedException {
		driver.get(baseUrl2+"/");
		Thread.sleep(30000);
		driver.findElement(By.id("username")).sendKeys(Constant.emailID);
		driver.findElement(By.id("password")).sendKeys(Constant.password);
		driver.findElement(By.xpath(Constant.Dom_click_logon_xpath)).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("txtSch")).sendKeys(Constant.Add_cus_emailid);  //Enter email ID here 
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
		    String adminConsoleWindow=ite.next().toString();
		    if(!adminConsoleWindow.contains(emailWindow))
		    {
		                driver.switchTo().window(adminConsoleWindow);
		                System.out.println("Window switched");
		                Thread.sleep(15000);
		                System.out.println("Sleep over");
		                
		                //Switch to ifrmae
		        		driver.switchTo().frame("ims_signup_frame");
		        		//Click on create account	        				        		
		        		driver.findElement(By.id("create_account")).click();
		        		Thread.sleep(7000);
		        		driver.findElement(By.id("email_address")).sendKeys(Constant.Add_cus_emailid);
		        		driver.findElement(By.id("password")).sendKeys("tester");
		        		driver.findElement(By.id("retype_password")).sendKeys("tester");
		        		driver.findElement(By.id("first_name")).sendKeys("F_name");
		        		driver.findElement(By.id("last_name")).sendKeys("L_name");
		        		 		                                
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
		        		
		        		WebElement accepttermspage=driver.findElement(By.id("tocCheckBox"));
		        		if(accepttermspage.isDisplayed())
		        		{
		        			accepttermspage.click();
		        		}
		        		driver.findElement(By.linkText("Accept Terms & Conditions")).click();
		        		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		        		driver.findElement(By.xpath(Constant.Dom_admin_console_btn)).click();
		        		WebElement termofuse=driver.findElement(By.id("consent"));
		        		if(termofuse.isDisplayed())
		        		{
		        			termofuse.click();
		        		}
		        		driver.findElement(By.id("confirm")).click();
		        		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		        		driver.findElement(By.xpath(".//*[@id='header']/p[1]/div/div/div[2]/div[2]")).click();
		        		driver.findElement(By.linkText("Sign out")).click();
		        		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		        		driver.manage().deleteAllCookies();
		         }
			} 
		}
			//Add more and remove seat

	public void addMoreSeat() throws InterruptedException {
			this.loginToReseller();
			driver.findElement(By.xpath(Constant.customerdashboard_btn)).click();
			System.out.print("\n Customer Dashboard launch Sucessfull");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Details")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			String addnewproduct="Add a new product";
			if(addnewproduct.equalsIgnoreCase(driver.findElement(By.className("newProd")).getText()))
			{
				driver.findElement(By.className("newProd")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(By.id("input_numberOfSeats")).sendKeys("2");
				driver.findElement(By.id("addButton")).click();
			}
			else
			{
				driver.findElement(By.className("moreSeats")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(By.id("input_numberOfSeats")).sendKeys("2");
				driver.findElement(By.id("addButton")).click();
				System.out.println("Your seat's add successfully ");
			}
			
			//Removing seat from auth history table
			String fulfillmentstatus="Pending";
			for(int i=1;i<20;)
			{
				if(!(fulfillmentstatus.equalsIgnoreCase(driver.findElement(By.xpath(".//*[@id='ccm_reseller_custDetails_table_authHistory']/tbody/tr["+i+"]/td[6]/a")).getText())))
					{
						i=i+1;
						System.out.println("Your seat is already removed" );
					}
				else
					{
						driver.findElement(By.xpath(".//*[@id='ccm_reseller_custDetails_table_authHistory']/tbody/tr["+i+"]/td[6]/span/a/img")).click();
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						driver.findElement(By.id("btn_ok_delete")).click();
						System.out.println("Your seat is removed " );
						break;
					}
			}
		}

		//Changed managed by 
	
		public void managedby() throws InterruptedException {
			Thread.sleep(5000);
			driver.findElement(By.id("change_manager")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.xpath(".//*[@id='manageby_dropdown_chzn']/a")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(".//*[@id='manageby_dropdown_chzn']/div/ul/li[1]")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("btn_change")).click();
			System.out.println("Managed by changed successfully :");
		}

		//Change BreadCrumb feature and logout
	
		public void changeBreadcrumb() throws InterruptedException {
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.linkText("CUSTOMERS")).click();
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath(".//*[@id='header']/p[1]/div/div[3]/a[1]")).click();
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id(Constant.Dom_sign_out_dropdown)).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(Constant.Dom_sign_out_btn_xpath)).click();
			System.out.print("\n Sign out Sucessfully"); 
			driver.manage().deleteAllCookies();
		}
		
		@AfterClass	
		public static void tearDown() {
			System.out.println("Execution completed.....");
			//driver.quit();
			
		}
		
}