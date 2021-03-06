package org.shopizer_test;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

public class Test001 {

	private String browser=System.getProperty("browser");

	WebDriver driver;
	String product;


	String str_homePage="Page d'accueil";
	String str_tables="Tables";

	private static Logger log = Logger.getLogger(Test001.class);

	@Before
	public void startup() throws ClassNotFoundException, FileNotFoundException, SQLException {

		if(browser.equals("chrome")) {

			driver = TechnicalTools.setBrowser(EBrowser.chrome);

		}
		else if (browser.equals("firefox")) {

			driver = TechnicalTools.setBrowser(EBrowser.firefox);

		} 
		else if (browser.equals("ie")) {

			driver = TechnicalTools.setBrowser(EBrowser.ie);

		}
		else {
			//Default browser
			driver = TechnicalTools.setBrowser(EBrowser.firefox);
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		PropertyConfigurator.configure(
		          App.class.getClassLoader().getResource("src/log4j.properties"));
		
	}

	@After
	public void teardDown() {
		driver.quit();
	}

	@Test
	public void testCheckObject() throws InterruptedException {
	
		
		ArrayList<String> list_product = new ArrayList<String>();
		list_product.add("Natural Root Console");
		list_product.add("Asian Rosewood Console");
		list_product.add("Edge Console");
		list_product.add("Coffee Table Accacia");


		ArrayList<String> list_price = new ArrayList<String>();
		list_price.add("US$1,499.99");
		list_price.add("US$499.99");
		list_price.add("US$749.99");
		list_price.add("US$399.99");

		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Step 1 : Acces à l'application
		log.info("Lancement du navigateur");
		driver.get("http://192.168.102.179:8090/shopizer/shop");
		log.info("Navigateur lancé");

		PageShop page_shop = PageFactory.initElements(driver, PageShop.class);

		//Step 2 : Selection de la partie Table 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Table')])[1]")));
		PageCategoryTable page_table = page_shop.accessTable(driver);

		/** Presence d'une arborescence indiquant le chemin  **/
		assertEquals("Présence de l'arborescence : ",str_homePage,page_table.link_homePage.getText());
		assertEquals("Présence de l'arborescence : ",str_tables,page_table.link_tables.getText());

		for (int i = 1; i<=driver.findElements(By.xpath("//div[@id=\"productsContainer\"]/div")).size(); i++)
		{
			// Step 3 : Verification de la présence d'elements

			WebElement link_product = driver.findElement(By.xpath("//div[@id=\"productsContainer\"]/div["+i+"]/div[2]/a/h3"));


			assertEquals("l'element n'est pas présent : ",list_product.get(i-1),link_product.getText());

			// Step 4 : Verification des prix 

			WebElement link_price = driver.findElement(By.xpath("//div[@id=\"productsContainer\"]/div["+i+"]/div[2]/h4/span"));
			log.info("Verification des prix, Valeur attendue="+list_price.get(i-1)+"\nValeur actuelle="+link_price.getText());
			assertEquals("Le prix est incorrect : ",list_price.get(i-1),link_price.getText());
		}

		//Step 5 : Selection du filtre DEFAULT


		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loadingoverlay']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href=\"javascript:filterCategory('BRAND','1')\"]")));
		page_table.btn_default.click();

		/** Presence des elements **/
		for (int i = 1; i<=driver.findElements(By.xpath("//div[@id=\"productsContainer\"]/div")).size(); i++)
		{

			WebElement product_Default = driver.findElement(By.xpath("//div[@id=\"productsContainer\"]/div["+i+"]/div[2]/a/h3"));

			product = product_Default.getText();

			assertTrue("L'élement n'est pas présent dans le filtre DEFAULT : ",product_Default.isDisplayed());

		}

		//Step 6 : Selection du filtre Asian wood
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loadingoverlay']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href=\"javascript:filterCategory('BRAND','50')\"]")));
		page_table.btn_asian_wood.click();
		for (int i = 1; i<=driver.findElements(By.xpath("//div[@id=\"productsContainer\"]/div")).size(); i++)
		{
			WebElement product_asian_wood = driver.findElement(By.xpath("//div[@id=\"productsContainer\"]/div["+i+"]/div[2]/a/h3"));

			product = product_asian_wood.getText();

			assertTrue("L'élement n'est pas présent dans le filtre Asian wood : ",product_asian_wood.isDisplayed());

		}

		//Step 7 : Selection du filtre Roots
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loadingoverlay']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href=\"javascript:filterCategory('BRAND','100')\"]")));
		page_table.btn_roots.click();
		for (int i = 1; i<=driver.findElements(By.xpath("//div[@id=\"productsContainer\"]/div")).size(); i++)
		{

			WebElement product_root = driver.findElement(By.xpath("//div[@id=\"productsContainer\"]/div["+i+"]/div[2]/a/h3"));

			product = product_root.getText();

			assertTrue("L'élement n'est pas présent dans le filtre Root : ",product_root.isDisplayed());

		}

	}

}


