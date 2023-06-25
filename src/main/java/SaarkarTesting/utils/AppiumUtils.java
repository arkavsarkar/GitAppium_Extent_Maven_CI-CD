package SaarkarTesting.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	 protected AppiumDriver driver;//using appium driver as this class is independent of platform
	public AppiumDriverLocalService service;
	
	public Double formatStringDouble(String cartPrice) {
		String cartValueString= cartPrice.substring(1);
		Double cartValueDouble= Double.parseDouble(cartValueString);
		return cartValueDouble;
	}
	public void waitElementToAppeared(WebElement ele, String type, String expectedText, AppiumDriver driver) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(10));
		wt.until(ExpectedConditions.attributeContains(
				ele, type, expectedText));
	}

		public AppiumDriverLocalService startServerClient(String ipAddress, int port) {
			service = new AppiumServiceBuilder()
					.withAppiumJS(
							new File("C:\\Users\\arkav\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					.withIPAddress(ipAddress).usingPort(port).build();

			service.start();
			return service;
		}
	public String getScreenShot(String testCaseName, AppiumDriver driver2) throws IOException {
		File source = driver2.getScreenshotAs(OutputType.FILE);
		String destination= System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;// return for extent report to attach to the report
	}
}
