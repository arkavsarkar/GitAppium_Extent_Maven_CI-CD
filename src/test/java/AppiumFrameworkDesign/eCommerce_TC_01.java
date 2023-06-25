package AppiumFrameworkDesign;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import AppiumFrameworkDesign.testUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class eCommerce_TC_01  extends BaseTest{
	
	
//	@BeforeMethod
//	public void preSetup() {
//		Activity action= new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
//		driver.startActivity(action);
////		driver.mobile:startActivity(action);
//		
//	}
	
	
	
	
	@Test
	public void formFillErrorMessage() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bhutan\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Bhutan']")).click();
		Thread.sleep(3000);
//		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Deru");
		//need to hide the keyboard
//		driver.hideKeyboard();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
//		Thread.sleep(3000);
		String Actual= driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
//		System.out.println(Actual);
		Assert.assertEquals(Actual, "Please your name");
		
		}
	@Test
	public void formFillPositive() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bhutan\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Bhutan']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Deru");
		//need to hide the keyboard
		driver.hideKeyboard();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
		
		
		}

}
