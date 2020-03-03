package org.shopizer_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCategoryTable extends PageTopMenu {

	
	@FindBy (xpath="//div/ol/li[1]/a")
	WebElement link_homePage;
	
	@FindBy (xpath="//div/ol/li[2]/a")
	WebElement link_tables;
	
	@FindBy (xpath="//a[@href=\"javascript:filterCategory('BRAND','1')\"]")
	WebElement btn_default;
	
	@FindBy (xpath="//a[@href=\"javascript:filterCategory('BRAND','50')\"]")
	WebElement btn_asian_wood;
	
	@FindBy (xpath="//a[@href=\"javascript:filterCategory('BRAND','100')\"]")
	WebElement btn_roots;
	
	
}
