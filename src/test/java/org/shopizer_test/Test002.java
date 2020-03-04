package org.shopizer_test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test002 {

	private String BROWSER=System.getProperty("browser");
	WebDriver driver;


	@Before
	public void startup() throws ClassNotFoundException, FileNotFoundException, SQLException {

		if(BROWSER.equals("chrome")) {

			//driver = new ChromeDriver();
			driver = TechnicalTools.setBrowser(EBrowser.chrome);

		}
		else if (BROWSER.equals("firefox")) {
			//driver = new FirefoxDriver();
			driver = TechnicalTools.setBrowser(EBrowser.firefox);

		} 
		else if (BROWSER.equals("ie")) {
			//driver = new InternetExplorerDriver();
			driver = TechnicalTools.setBrowser(EBrowser.ie);

		}
		else {
			//Default browser
			driver = TechnicalTools.setBrowser(EBrowser.chrome);
		}

		//driver = TechnicalTools.setBrowser(EBrowser.chrome);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@After
	public void teardDown() {
		driver.quit();
	}

	@Test
	public void testCheckMarchandise() throws InterruptedException {

		ArrayList<String> list_product = new ArrayList<String>();

		list_product.add("Compact Night Table");
		list_product.add("Product With Options");
		list_product.add("Edge Console");
		list_product.add("Table Decoration Storage");
		list_product.add("Thai Flat Cussion");
		list_product.add("Asian Rosewood Console");
		list_product.add("Antique Recycled Wood Storage");
		list_product.add("Coffee Table Accacia");


		ArrayList<String> list_price = new ArrayList<String>();

		list_price.add("59.99");
		list_price.add("129.99");
		list_price.add("99.99");
		list_price.add("749.99");
		list_price.add("64.99");
		list_price.add("499.99");
		list_price.add("1,199.99");
		list_price.add("399.99");

		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Step 1 : Acces à l'application

		driver.get("http://localhost:8090/shopizer/shop");

		PageShop page_shop = PageFactory.initElements(driver, PageShop.class);

		List<WebElement> list_WebElement = driver.findElements(By.xpath("//div[@id='featuredItemsContainer']/div"));
		List<String> list_expected_title = new ArrayList<String>();
		List<String> list_expected_price = new ArrayList<String>();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"featuredItemsContainer\"]/div[1]//h3")));

		for(int i=1; i<=list_WebElement.size();i++)
		{
			//Step 2 : Presence de tous les elements

			list_expected_title.add(driver.findElement(By.xpath("//div[@id=\"featuredItemsContainer\"]/div["+i+"]//h3")).getText());
			assertTrue(list_product.contains(list_expected_title.get(i-1)));

			//Step 2 : Verification des prix	

			list_expected_price.add(driver.findElement(By.xpath("//div[@id=\"featuredItemsContainer\"]/div["+i+"]//h4/span")).getText().substring(3));
			assertTrue(list_expected_price.contains(list_price.get(i-1)));
		}


	}
}