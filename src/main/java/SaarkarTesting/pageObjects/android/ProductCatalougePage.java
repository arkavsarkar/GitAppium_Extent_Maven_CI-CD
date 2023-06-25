package SaarkarTesting.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import SaarkarTesting.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalougePage extends AndroidActions {
	AndroidDriver driver;

	public ProductCatalougePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

//		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> products;

//	driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement appBartCartButton;

	public void addItemtoCartByIndex(int index) throws InterruptedException {
		products.get(index).click();
		Thread.sleep(2000);
	}
	public CartPage goToCartPage() throws InterruptedException {
	
	appBartCartButton.click();
	Thread.sleep(5000);
	return new CartPage(driver);
	}

}
