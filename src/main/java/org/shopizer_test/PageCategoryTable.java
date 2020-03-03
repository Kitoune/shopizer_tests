package org.shopizer_test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageCategoryTable extends PageTopMenu {

	
	@FindBy (xpath="//div/ol/li[1]/a")
	WebElement link_homePage;
	
	
	@FindBy (xpath="//div/ol/li[2]/a")
	WebElement link_tables;
	
	@FindBy (xpath="(//a[@href=\"/shopizer/shop/product/natural-root-console.html\"]/h3/..)[1]")
	WebElement link_item1;
	
	@FindBy (xpath="(//a[@href=\"/shopizer/shop/product/asian-rosewood-console.html\"]/h3/..)[1]")
	WebElement link_item2;
	
	@FindBy (xpath="(//a[@href=\"/shopizer/shop/product/edge-console.html\"]/h3/..)[1]")
	WebElement link_item3;
	
	@FindBy (xpath="(//a[@href=\"/shopizer/shop/product/coffee-table-accacia.html\"]/h3/..)[1]")
	WebElement  link_item4;
	
	@FindBy (xpath="(//a/h3[contains(text(),'Natural root console')]/../../h4/span)[1]")
	WebElement price_item1;
	
	@FindBy (xpath="(//a/h3[contains(text(),'Asian rosewood console')]/../../h4/span)[1]")
	WebElement price_item2;
	
	@FindBy (xpath="(//a/h3[contains(text(),'Edge console')]/../../h4/span)[1]")
	WebElement price_item3;
	
	@FindBy (xpath="(//a/h3[contains(text(),'Coffee table Accacia')]/../../h4/span)[1]")
	WebElement price_item4;
}
