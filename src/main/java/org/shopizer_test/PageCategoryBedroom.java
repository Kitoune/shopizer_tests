package org.shopizer_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCategoryBedroom extends PageTopMenu {

	@FindBy (xpath="(//a[contains(text(),\"Page d'accueil\")])[1]")
	WebElement link_homePage;
	
	@FindBy (xpath="(//a[contains(text(),\"Bedroom\")])[1]")
	WebElement link_bedroom;
	
	@FindBy (xpath="//*[@id=\"productsContainer\"]/div[1]/div[2]/div/div/a")
	WebElement btn_addToCart_item1;
	
	@FindBy (xpath="//*[@id=\"productsContainer\"]/div[2]/div[2]/div/div/a")
	WebElement btn_addToCart_item2;
	
	@FindBy (xpath="//*[@id=\"miniCartSummary\"]/a/font/strong")
	WebElement icon_nb_item;
	
	@FindBy (xpath="//div[@id=\"miniCartSummary\"]")
	WebElement link_shoppingCart;
	
	@FindBy (xpath="//*[@id=\"miniCartDetails\"]/li[5]/a")
	WebElement btn_payement;
	
		
	public void click_add_to_cart(){
		
		btn_addToCart_item1.click();
		btn_addToCart_item2.click();	
	}
	
	public PageShopingCart checkShopCart(WebDriver d) throws InterruptedException {
		
		Actions action = new Actions(d);
		WebElement we = d.findElement(By.xpath("//*[@id=\"miniCartDetails\"]"));
		action.moveToElement(we).moveToElement(d.findElement(By.xpath("//*[@id='miniCartDetails']/li[5]/a"))).build().perform();
		d.findElement(By.xpath("//*[@id='miniCartDetails']/li[5]/a")).click();
		return PageFactory.initElements(d, PageShopingCart.class);
	}
	
}
