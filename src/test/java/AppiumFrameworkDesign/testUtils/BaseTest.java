package AppiumFrameworkDesign.testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import SaarkarTesting.pageObjects.android.FormPage;
import SaarkarTesting.utils.AppiumUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage firstPage;
	@BeforeClass(alwaysRun= true)
	public void serverStart() throws IOException {
		// starting appium server automativcally
//		service = new AppiumServiceBuilder()
//				.withAppiumJS(
//						new File("C:\\Users\\arkav\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//				.withIPAddress("127.0.0.1").usingPort(4723).build();
//
//		service.start();
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//SaarkarTesting//resources//GlobalData.properties");
		prop.load(fis);
//		ipAdress coming from maven or properties file using java ternary operations
		String ip=System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress"): prop.getProperty("ipAddress");
		
		String port= prop.getProperty("port");
//		service= startServerClient("127.0.0.1",4723);
		service= startServerClient(ip,Integer.parseInt(port));

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("ArkavEmulator");
		options.setChromedriverExecutable("C:\\Appium\\driver\\chromedriver_91.4\\chromedriver.exe");
//		options.setApp("C:\\Selenium\\PROG3\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\AppiumFrameworkDesign\\testData\\General-Store.apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
//		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		firstPage = new FormPage(driver);
		
	}

	@AfterClass(alwaysRun= true)
	public void serverStop() {
		driver.quit();
		service.stop();
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
	public void gestureLongPress(WebElement ele) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),"duration",2000));
		Thread.sleep(2000);
	}
	public Double formatStringDouble(String cartPrice) {
		String cartValueString= cartPrice.substring(1);
		Double cartValueDouble= Double.parseDouble(cartValueString);
		return cartValueDouble;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to String
				String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
				// string to HashMap
				// Jackson Databing dependency add to pom.xml
				ObjectMapper mapper = new ObjectMapper();
				List<HashMap<String, String>> data = mapper.readValue(jsonContent,
						new TypeReference<List<HashMap<String, String>>>() {
						});
				return data;
	}
}
