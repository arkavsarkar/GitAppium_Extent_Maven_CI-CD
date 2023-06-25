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

public class CartPage extends AndroidActions {
	AndroidDriver driver;
	Double sum = 0.0;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

//			 cartList = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> cartList;
	
//	driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement cartListTotal;
	
//	WebElement termsnCond = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsnCond;
//	driver.findElement(By.id("android:id/button1")).click();
	@AndroidFindBy(id = "android:id/button1")
	private WebElement termClose;
//			driver.findElement(AppiumBy.className("android.widget.Button")).click();
	@AndroidFindBy(className = "android.widget.Button")
	private WebElement submitOrderButtton;

	public Double cartSumAmount() throws InterruptedException {
		for (int i = 0; i < cartList.size(); i++) {
			String cartPrice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i)
					.getText();
			Double cartValueDouble = formatStringDouble(cartPrice);
			sum = sum + cartValueDouble;

		}
		
		Thread.sleep(2000);
		System.out.println(sum);
		return sum;

	}
	public Double cartSumDisplay() {
		Double checkoutDisplayAmount = formatStringDouble(cartListTotal.getText());
		System.out.println(checkoutDisplayAmount);
		return checkoutDisplayAmount;
		
	}
	public void termNConditionPage() throws InterruptedException {
		
		gestureLongPress(termsnCond);
		// clicking close on terms and condition
		Thread.sleep(10000);
		termClose.click();
	}
	public void orderPlace() throws InterruptedException {
		
		
		// clicking check out and navigating to google page.
		submitOrderButtton.click();
		Thread.sleep(10000);
		
	}

}
