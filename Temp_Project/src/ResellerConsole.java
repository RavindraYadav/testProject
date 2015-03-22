
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import utils.Constant;

public class ResellerConsole {
	private static final String[] String = null;
	private static final CharSequence emailid = null;
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() throws Exception {
		//driver = new InternetExplorerDriver();
		//System.setProperty("webdriver.chrome.driver", "C:/Users/vikumars/AppData/Local/Google/Chrome/Application/chrome.exe");
		//driver=new ChromeDriver();
		driver = new FirefoxDriver(new FirefoxBinary(new java.io.File("C:/Users/vikumars/AppData/Local/Mozilla Firefox/firefox.exe")), new FirefoxProfile());
		baseUrl = "https://stage.reseller.adobe.com";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// Reseller login
	@Test
	public void testResellerLogin() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("ccm_reseller_login_inputText_emailId")).clear();
		driver.findElement(By.id("ccm_reseller_login_inputText_emailId")).sendKeys("virsharm+stgreseller@adobetest.com");
		driver.findElement(By.id("ccm_reseller_login_password_pwd")).clear();
		driver.findElement(By.id("ccm_reseller_login_password_pwd")).sendKeys("tester");
		driver.findElement(By.linkText("Sign in")).click();
		//driver.findElement(By.linkText("Sign in")).click();
		Thread.sleep(20000);
		System.out.print("\n Login Sucessfull");
/*
	// Add Sales-rep	
		
		driver.findElement(By.id("ccm_reseller_home_div_AddSalesRep")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("emailplaceholder")).clear();
		driver.findElement(By.id("emailplaceholder")).sendKeys("ankurch+V5seleniumtest@adobetest.com");
		driver.findElement(By.linkText("Invite")).click();
		System.out.print("\n Sales Rep invitation Sucessfull"); */ 
		Thread.sleep(30000); 
		
		driver.findElement(By.xpath(".//*[@id='ccm_reseller_home_div_vendorName']/span[2]/a")).click();
		System.out.print("\n Customer Dashboard launch Sucessfull");
		
		Thread.sleep(5000);
		
		

			driver.findElement(By.id("ccm_reseller_custDash_a_AddCust")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("input_orgname")).sendKeys("adobe Systems Incorparated");
			driver.findElement(By.id("input_dept")).clear();
			driver.findElement(By.id("input_dept")).sendKeys("Testing");
			driver.findElement(By.id("input_orgname")).clear();
			driver.findElement(By.id("input_orgname")).sendKeys("Customer Org Selenium");
	
		
			//Drop  down Market Segment on Add customer form.
			driver.findElement(By.xpath("//*[@id='input_marketseg_chzn']/a")).click();
			Thread.sleep(2000);	
			driver.findElement(By.id("input_marketseg_chzn_o_1")).click();
			Thread.sleep(2000);
			//Drop  down country Segment on Add customer form.
			driver.findElement(By.xpath(".//*[@id='input_country_chzn']/a")).click();	
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='input_country_chzn_o_96']")).click();
			//new Select(driver.findElements(By.xpath(".//*[@id='input_country_chzn_o_96']")).s);
			//Fill Address fields
			driver.findElement(By.id("input_address1")).sendKeys("365-Park");
			driver.findElement(By.id("input_address2")).sendKeys("Avenue");
			driver.findElement(By.id("input_city")).sendKeys("CA");
		
			Thread.sleep(10000);
			//Drop  down State Provience Segment on Add customer form.
			driver.findElement(By.xpath("//*[@id='input_states_chzn']/a")).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='input_states_chzn_o_1']")).click();
		
			//Zip Code Entry
			driver.findElement(By.id("input_zip")).sendKeys("95011");
			//Customer detail
			driver.findElement(By.id("input_fname")).sendKeys("Selenium"); //First Name
	
			driver.findElement(By.id("input_lname")).sendKeys("test"); //Last Name  */
	
			driver.findElement(By.id("input_email")).sendKeys(Constant.Add_cus_emailid); //Customer Email Address
				
			driver.findElement(By.id("btn_submit")).click();
			Thread.sleep(60000);
		
			System.out.print("\n Add Customer Sucessfull"); 
			
			
	
		//Customer detail page and Adding seats for the customer 
		
	/*
		Thread.sleep(3000); 
		
	
		List<WebElement> lstEmails = new ArrayList<WebElement>();
		lstEmails = driver.findElements(By.className("custadminemail"));
		
		for(int i=0; i<lstEmails.size();i++){
			WebElement x = lstEmails.get(i);
			if(x.getText().equalsIgnoreCase("ankurch+cunov27@adobetest.com")){
				
				driver.findElement(By.xpath("//*[@id='content']/div[2]/div[5]/span/div["+(i+1)+"]/div/div[2]/a")).click();
				break;
			}
		} */

		Thread.sleep(3000);
		
		/*		
		// Remove Purchase Authrisation
			driver.findElement(By.className("delete_row")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("btn_ok_delete")).click();
			Thread.sleep(10000);
			System.out.print("\n Remove Successfully"); */ 
	/*	
		driver.findElement(By.cssSelector("span.moreSeats")).click();
		driver.findElement(By.id("input_numberOfSeats")).clear();
		driver.findElement(By.id("input_numberOfSeats")).sendKeys("2");
		driver.findElement(By.id("addButton")).click(); 
		Thread.sleep(10000);
	
		Thread.sleep(5000);
		driver.findElement(By.id("change_manager")).click();
		driver.findElement(By.xpath(".//*[@id='manageby_dropdown_chzn']/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("manageby_dropdown_chzn_o_1")).click();
		driver.findElement(By.id("btn_change")).click();
		System.out.print("\n Manage by Sucessfully");
		
		//navigate back to member page then sign out and clear cookies
		driver.findElement(By.linkText("CUSTOMERS")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath(".//*[@id='header']/p[1]/div/div[3]/a[1]")).click();
		Thread.sleep(5000);
		System.out.print("\n Navigation to member page Sucessfull");
		driver.findElement(By.id("ccm_reseller_home_img_drpdwnArrow")).click();*/
		driver.findElement(By.xpath(".//*[@id='shSignOut']/a")).click();
		System.out.print("\n Sign out Sucessfully"); 
		driver.manage().deleteAllCookies();
		
	}  


public String getElementXPath(WebDriver driver, WebElement element) {
    return (String)((JavascriptExecutor)driver).executeScript("gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();", element);
}

	/*
	@After
	public void tearDown() throws Exception {
		//	driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		} 
	}*/

	private void fail(String verificationErrorString) {
		// TODO Auto-generated method stub
		
	}
}