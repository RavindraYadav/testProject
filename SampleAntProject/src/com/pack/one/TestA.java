package com.pack.one;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestA {
	WebDriver driver;
	@BeforeClass
	public void setUp() {
		System.out.println("*** In class - Test A ***");
		File f=new File("F:\\CloneProject\\NewTest\\SampleAntProject\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
		driver=new ChromeDriver();
		driver.get("https://news.google.com");
	}

	@Test
	public void testOne() {
		System.out.println("helloA");
	}
	
	@Test
	public void testTwo() {
		System.out.println("helloA2");
	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("*** End of class***");
		driver.quit();
	}
}