package org.shopizer_test;

import static org.junit.Assert.*;

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
	String str_homePage="Page d'accueil";
	String str_tables="Tables";

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
		
		// Step 1 : Acces à l'application
		driver.get("http://localhost:8090/shopizer/shop");
		
		PageShop page_shop = PageFactory.initElements(driver, PageShop.class);
		
		//Step 2 : Selection de la partie Table 
		PageCategoryTable page_table = page_shop.accessTable(driver);
		
		/** Presence d'une arborescence indiquant le chemin  **/
		assertEquals(str_homePage,page_table.link_homePage.getText());
		assertEquals(str_tables,page_table.link_tables.getText());
		
		
		// Step 3 : Verification de la présence d'elements
		assertTrue(page_table.link_item1.isDisplayed());
		assertTrue(page_table.link_item2.isDisplayed());
		assertTrue(page_table.link_item3.isDisplayed());
		assertTrue(page_table.link_item4.isDisplayed());
		
		// Step 4 : Verification des prix 
		System.out.println(page_table.price_item1.getText());
		System.out.println(page_table.price_item2.getText());
		System.out.println(page_table.price_item3.getText());
		System.out.println(page_table.price_item4.getText());
	}

}
