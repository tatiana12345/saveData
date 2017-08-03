package com.excel.saveData;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ExceleUtilities.Constants2_Lec153;
import ExceleUtilities.ExcelUtility_SingleDataSet;
import ExceleUtilities.utilitiesLK;
import ExceleUtilities.ReportNamePath;

import org.testng.Assert;

//Save data in EXCEL sheet

public class UsingExcel_SingleDataSet {
	
	private WebDriver wd;
	utilitiesLK UT;
	ExtentTest test;
	ExtentReports report;

  @BeforeClass
  public void setUp() throws Exception {
	  
	  report = ReportNamePath.getInstance();
	  test = report.startTest("Verify login impossible with invalid credentials and LOG DATA to excel sheet");
	  
	  System.setProperty("webdriver.gecko.driver", "/Users/tatianakesler/Desktop/Selenium/installation/geckodriver");
	
	  wd = new FirefoxDriver();
	  UT = new utilitiesLK(wd, test);
	  
	  //browser
	  wd.manage().window().maximize();
	  wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  wd.get(Constants2_Lec153.URL);
	 
	  test.log(LogStatus.INFO, "Browser started");
	  test.log(LogStatus.INFO, "Window maximized");
	 
	  UT.clickLearnNow();
	  
	  //TEll the code about the location of Excel file
	  ExcelUtility_SingleDataSet.setExcelFile(Constants2_Lec153.FilePath + Constants2_Lec153.FileName, "Sheet1");
	  
  }
  
  
@Test
  public void testPostTOExcel_Table() throws Exception {
	  String uername = ExcelUtility_SingleDataSet.getCellData(1, 0);
	  String password = ExcelUtility_SingleDataSet.getCellData(1, 1);
	  
	  //Click login button
	  UT.Click_Login();
	  Thread.sleep(2000);
	  
	  //Enter username
	  UT.typeEmail(uername);
	  
	  //Enter password
	  UT.typePassword(password);
	  
	  //Click login button
	  UT.clickSubmit();
	  Thread.sleep(2000);
	  
	  //Find if error message provided
	  boolean result = UT.LoginButton_isPresent();
		Assert.assertTrue(result);
		test.log(LogStatus.PASS, "Test passed, user's unable to login with invalid credentials");
	  
	  //Set the test result in the excel file
	  ExcelUtility_SingleDataSet.setCellData("Pass", 1, 2);
	  test.log(LogStatus.INFO, "Data saved in the Excel sheet: " + Constants2_Lec153.FileName);
	  
  
  }

  @AfterClass
  public void afterClass() {
	  wd.quit();
	  report.endTest(test);
	  report.flush();
  
  }

}
