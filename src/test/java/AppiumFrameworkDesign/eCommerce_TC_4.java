package AppiumFrameworkDesign;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import AppiumFrameworkDesign.testUtils.BaseTest;
import SaarkarTesting.pageObjects.android.CartPage;
import SaarkarTesting.pageObjects.android.FormPage;
import SaarkarTesting.pageObjects.android.ProductCatalougePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_TC_4 extends BaseTest {
	
	@Test(dataProvider= "getData",groups= "Smoke")
	public void formFill(HashMap<String, String> input) throws InterruptedException {
		firstPage.setName(input.get("name"));
		firstPage.setGender(input.get("gender"));
		firstPage.setCountry(input.get("country"));
		ProductCatalougePage secondPage = firstPage.submitForm();

		secondPage.addItemtoCartByIndex(0);
		secondPage.addItemtoCartByIndex(0);
		
		CartPage thirdPage= secondPage.goToCartPage();
		
		Double sum = thirdPage.cartSumAmount();
		Double checkoutDouble = thirdPage.cartSumDisplay();
		Assert.assertEquals(checkoutDouble, sum);
		thirdPage.orderPlace();
		


	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//AppiumFrameworkDesign//testData//GeneralStore_Testdata.json");
//		return new Object[][] {{"Arkav","Male", "Bhutan"}};
		return new Object[][] {{data.get(0)}};		
	}

}
