package org.shopizer_test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

public class Test003 {

	private String browser=System.getProperty("browser");
	WebDriver driver;
	String product;


	String str_homePage="Page d'accueil";
	String str_bedroom="BEDROOM";

	String str_item1 = "Compact night table";
	String str_item2 = "Antique recycled wood storage";


	String nb_item ="2";
	String price ="US$1,329.98";
	String new_price = "US$2,659.96";


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
	}

	@After
	public void teardDown() {
		driver.quit();
	}

	@Test
	public void testCheckObject() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 20);

		// Step 1 : Acces à l'application

		driver.get("http://192.168.102.179:8090/shopizer/shop");

		PageShop page_shop = PageFactory.initElements(driver, PageShop.class);

		//Step 2 : Selection de la partie Bedroom 

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Bedroom')])[1]")));
		PageCategoryBedroom page_bedroom = page_shop.accessBedroom(driver);

		/** Presence d'une arborescence indiquant le chemin  **/
		assertEquals(str_homePage,page_bedroom.link_homePage.getText());
		assertEquals(str_bedroom,page_bedroom.link_bedroom.getText());

		// Step 3 : Ajout au panier
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loadingoverlay']")));
		page_bedroom.btn_addToCart_item1.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loadingoverlay']")));
		page_bedroom.btn_addToCart_item2.click();
		Thread.sleep(2000);
		assertEquals(nb_item,page_bedroom.icon_nb_item.getText().substring(1,2));

		// Step 4 : Aller dans le panier
		page_bedroom.link_shoppingCart.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loadingoverlay']")));

		if(browser.equals("firefox")) {
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("viewShoppingCartPage();");
			}
		}
		else  {

			PageShopingCart page_shopCart = page_bedroom.checkShopCart(driver);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loadingoverlay']")));

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[1]/div/div[2]")));

			assertEquals(str_item1,page_shopCart.link_item1.getText());

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[2]/td[1]/div/div[2]")));

			assertEquals(str_item2,page_shopCart.link_item2.getText());

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='amount'])[2]")));

			assertEquals(price,page_shopCart.total_price.getText());


			// Step 5 : Augmenter la quantite 

			WebElement input1_quantity= driver.findElement(By.xpath("//tr[1]/td/input[@type=\"number\"]"));
			input1_quantity.click();
			TechnicalTools.fillFields(input1_quantity, nb_item);

			WebElement input2_quantity= driver.findElement(By.xpath("//tr[2]/td/input[@type=\"number\"]"));
			input2_quantity.click();
			TechnicalTools.fillFields(input2_quantity, nb_item);

			assertEquals(price,page_shopCart.total_price.getText());

			page_shopCart.btn_recalculer.click();

			assertEquals(price,page_shopCart.total_price.getText());
		}
	}
}