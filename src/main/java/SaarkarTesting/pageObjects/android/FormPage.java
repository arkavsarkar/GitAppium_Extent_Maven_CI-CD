package SaarkarTesting.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import SaarkarTesting.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{
	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

//	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Deru");
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement name;
	
	//		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement genderFemale;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement gendermale;
	
	
//	driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement Country;
	
//driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submit;

	public void setName(String personname) {
		name.sendKeys(personname);
		driver.hideKeyboard();
	}
	public void setGender(String gender) {
		if(gender.contains("female")) {
			genderFemale.click();
		}
		gendermale.click();
	}
	
	public void setCountry(String countryName) {
		
		Country.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
		
		
	}
	public ProductCatalougePage submitForm() {
		submit.click();
		return new ProductCatalougePage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
