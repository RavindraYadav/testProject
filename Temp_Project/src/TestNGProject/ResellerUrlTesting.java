package TestNGProject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.Constant;

public class ResellerUrlTesting {
	String baseUrl;
	static WebDriver driver;
	static String baseUrl2="https://namail.corp.adobe.com";

@BeforeSuite(alwaysRun=true)
public void setUp() throws Exception {
		//driver = new InternetExplorerDriver();
	
	    //For Chrome browser setup
		System.setProperty("webdriver.chrome.driver","C:/Users/vikumars/Desktop/MCUI Automation/chromedriver_win_23.0.1240.0/chromedriver.exe");
		driver=new ChromeDriver();
		
		//For FireFox browser setup
		//driver = new FirefoxDriver(new FirefoxBinary(new java.io.File("C:/Program Files (x86)/Mozilla Firefox/firefox.exe")), new FirefoxProfile());
		baseUrl = "https://stage.reseller.adobe.com";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl+"/");
	}
	
		@DataProvider(name = "DP1")
		public Object[][] createData1() throws Exception{
		   
			Object[][] retObjArr=getTableArray("src\\Resources\\Data\\data1.xls","DataPool", "homePageLinks");
		    
		    return(retObjArr);
		}

	@Test(dataProvider="DP1")
	public void homePageUrls(String linkName, String expectedPageTitle)
	{
		//Store the current window handle
		String resellerwindow=driver.getWindowHandle();	
		
		 //Perform the click operation that opens new window
		driver.findElement(By.linkText(linkName)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //Switch to new window opened
		for(String newwindow:driver.getWindowHandles())
		{
			driver.switchTo().window(newwindow);
		}
		//Perform the actions on new window
		String actualpagetitle=driver.getTitle();
		System.out.println("Actual page title: "+actualpagetitle);
		if(expectedPageTitle.equals(actualpagetitle))
		{
			System.out.println("Not a broken link");
		}
		else
		{
			System.out.println("Broken link");
		}
		driver.close();
		
		//Switch back to original browser (first window)

        driver.switchTo().window(resellerwindow);
	}
	
	
	@DataProvider(name = "DP2")
	public Object[][] createData2() throws Exception{
	   
		Object[][] retObjArr=getTableArray("src\\Resources\\Data\\data1.xls","DataPool", "resellerPageLinks");
	    
	    return(retObjArr);
	}
	@Test
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
	//Check reseller link page
	@Test(dataProvider="DP2", dependsOnMethods="loginToReseller")
	public void resellerPageUrls(String linkName, String expectedPageTitle) throws InterruptedException
	{
		//Store the current window handle
		String resellerwindow=driver.getWindowHandle();	
							
		 //Perform the click operation that opens new window
		driver.findElement(By.linkText(linkName)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		 //Switch to new window opened
		for(String newwindow:driver.getWindowHandles())
		{
			driver.switchTo().window(newwindow);
		}
		//Perform the actions on new window
		String actualpagetitle=driver.getTitle();
		System.out.println("Actual page title: "+actualpagetitle);
		if(expectedPageTitle.equals(actualpagetitle))
		{
			System.out.println("Not a broken link");
		}
		else
		{
			System.out.println("Broken link");
		}
		driver.close();
		
		//Switch back to original browser (first window)
		driver.switchTo().window(resellerwindow);
		System.out.println("focus on reseller dialog");
	}
		//logout from reseller
		@Test
		public void resellerLogout()
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id(Constant.Dom_sign_out_dropdown)).click();
			driver.findElement(By.xpath(Constant.Dom_sign_out_btn_xpath)).click();
			System.out.print("\n Sign out Sucessfully"); 
			driver.manage().deleteAllCookies();
			driver.close();
		}
		
		//Data read through excel sheet
		public Object[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws BiffException, IOException {
			String[][] tabArray=null;
			
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			
	        Sheet sheet = workbook.getSheet(sheetName); 
	        
	        int startRow,startCol, endRow, endCol,ci,cj;
	        
	        Cell tableStart=sheet.findCell(tableName);
	        
	        startRow=tableStart.getRow();
	        startCol=tableStart.getColumn();
	
	        Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                
	
	        endRow=tableEnd.getRow();
	        endCol=tableEnd.getColumn();
	        
	        System.out.println("startRow="+startRow+", endRow="+endRow+", " + "startCol="+startCol+", endCol="+endCol);
	        
	        tabArray=new String[endRow-startRow-1][endCol-startCol-1];
	        ci=0;
	        for (int i=startRow+1;i<endRow;i++,ci++){
	            cj=0;
	            for (int j=startCol+1;j<endCol;j++,cj++){
	                tabArray[ci][cj]=sheet.getCell(j,i).getContents();
	            }
	        }
	    
	        return(tabArray);
		}
		
		@AfterMethod(alwaysRun=true)
		public void catchException(ITestResult result)
		{
			//System.out.println("result" + result);
	        String methodName = result.getName();
	        	        
			if(!result.isSuccess())
			{
			 	try {
					File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					//String path="C:\\Users\\vikumars\\workspace\\Java_Junit_Testing\\output\\screenshots\\"+ scrFile.getName();
					String path="C:\\Users\\vikumars\\workspace\\Temp_Project\\output\\screenshots\\"+methodName+"_"+scrFile.getName();
					//System.out.println("Get the file name "+)
					FileUtils.copyFile(scrFile, new File(path));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	/*		
		@AfterMethod
			public static void tearDown() {
			System.out.println("Execution completed.....");
			//driver.quit();
		    }
		*/
}//End of class
