package SaarkarTesting.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils{
public AndroidDriver driver;
	public AndroidActions(AndroidDriver driver) {
		this.driver= driver;
	}

	public void swipeGesture(WebElement firstPic, String direction) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) firstPic).getId(),
//optional		"left", 100, "top", 100, "width", 200, "height", 200,

						"direction", direction, "percent", 0.75));
		Thread.sleep(2000);
	}

	public void scrollGesture(String direction) {
		// No prior data
		// it will scroll until there are elements to be scrolled
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 600, "direction", direction, "percent", 50.0));
		} while (canScrollMore);
	}
	public void scrollToText(String text) {
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}
	public void gestureLongPress(WebElement ele) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),"duration",2000));
		Thread.sleep(2000);
	}

}
