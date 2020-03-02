package org.shopizer_test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Test001 {

	WebDriver driver;

	@Before
	public void startup() throws ClassNotFoundException, FileNotFoundException, SQLException {
		driver = TechnicalTools.setBrowser(EBrowser.chrome);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@After
	public void teardDown() {
		driver.quit();
	}

	@Test
	public void testCheckObject() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		// Step 1 : connexion à l'adresse de l'application

		driver.get("http://localhost:8090/shopizer/shop");
		
		PageShop page_shop = PageFactory.initElements(driver, PageShop.class);
		
		PageCategoryTable page_table = page_shop.accessTable(driver);
		
		
	}

}
