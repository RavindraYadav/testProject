
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import utils.Constant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;


public class AdminConsole_1 {
	private static WebDriver driver;
	//private String baseUrl1;
	private static String baseUrl2;
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() throws Exception {
		//driver = new InternetExplorerDriver();
		//System.setProperty("webdriver.chrome.driver", "C:/Users/vikumars/AppData/Local/Google/Chrome/Application/chrome.exe");
		//driver=new ChromeDriver();
		driver = new FirefoxDriver(new FirefoxBinary(new java.io.File("C:/Users/vikumars/AppData/Local/Mozilla Firefox/firefox.exe")), new FirefoxProfile());
		baseUrl2="https://namail.corp.adobe.com";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().deleteAllCookies();
	}
	@Test
	public void acceptInvitation() throws Exception
	{
		//User login on web client
		driver.get(baseUrl2+"/");
		Thread.sleep(3000);
		driver.findElement(By.id("username")).sendKeys(Constant.emailID);
		driver.findElement(By.id("password")).sendKeys(Constant.password);
		driver.findElement(By.xpath(Constant.Dom_click_logon_xpath)).click();
		Thread.sleep(5000);
	
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
		                //WebDriverWait waiting=new WebDriverWait(driver,20);
		        		//waiting.until( ExpectedConditions.invisibilityOfElementLocated(By.id("create_account")));
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
		        		
		        		/*//Using Java Script with clicking using DOM model:
		        		JavascriptExecutor js = (JavascriptExecutor) driver;
		                StringBuilder stringBuilder = new StringBuilder();
		                stringBuilder.append("document.getElementsByTagName('option')[12].click()");
		                js.executeScript(stringBuilder.toString()); 
		                
		               //Js query to select the drop down
		                JavascriptExecutor js = (JavascriptExecutor) driver;
		                StringBuilder stringBuilder = new StringBuilder();
		               	stringBuilder.append("var x=$(\'"+October+"\');");
		                stringBuilder.append("x.click();");
		                js.executeScript(stringBuilder.toString());
		                */
		                                
		        		Select dateofbirth=new Select(driver.findElement(By.xpath(".//*[@id='month']")));
		      			//dateofbirth.deselectAll();
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
		        		driver.manage().deleteAllCookies();
		    	}
			}
		}
		@After
		public void tearDown() throws Exception {
		//driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
				}
			}
		private void fail(String verificationErrorString) {
			// TODO Auto-generated method stub
			
		}
}

