package org.shopizer_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public abstract class PageTopMenu {

	
	@FindBy (xpath="//div[@class='dropdown header-left-menu']")
	WebElement btn_language;
	
	@FindBy (xpath="//div[@id=\"customerAccount\"]")
	WebElement menu_account;
	
	@FindBy (xpath="//a[@onclick=\"javascript:location.href='/shopizer/shop/customer/registration.html';\"]")
	WebElement btn_register;
	
	@FindBy (xpath="//a[@onclick=\"javascript:location.href='/shopizer/shop/customer/customLogon.html';\"]")
	WebElement btn_logon;
			
	@FindBy (xpath="//button/a[@href=\"/shopizer/shop/store/contactus.html\"]")
	WebElement btn_contact;
	
	@FindBy (xpath="//button[@class=\"btn btn-default searchButton\"]")
	WebElement btn_search;
	
	@FindBy (xpath="//input[@id=\"searchField\"]")
	WebElement input_search;
	
	@FindBy (xpath="//span[@class=\"lnr lnr-cart\"]")
	WebElement btn_shopcart;
	
	@FindBy (xpath="//div[1]/div/div/div/div/nav/ul/li[1]")
	WebElement btn_test_code_check;
	
	@FindBy (xpath="//div[1]/div/div/div/div/nav/ul/li[2]/a")
	WebElement btn_tables;
	
	@FindBy (xpath="//div[1]/div/div/div/div/nav/ul/li[3]/a")
	WebElement btn_living_room;
	
	@FindBy (xpath="//div[1]/div/div/div/div/nav/ul/li[4]/a")
	WebElement btn_bedroom;
	
	@FindBy (xpath="//*[@id=\"main_h\"]/div/div/div/div/nav/ul/li[4]/ul/li/a")
	WebElement btn_night_tables;
	
	@FindBy (xpath="//div[1]/div/div/div/div/nav/ul/li[5]/a")
	WebElement btn_decoration;
	
	@FindBy (xpath="//div[1]/div/div/div/div/nav/ul/li[6]/a")
	WebElement btn_late_test;
	
	@FindBy (xpath="//div[1]/div/div/div/div/nav/ul/li[7]/a")
	WebElement btn_category;
	
	@FindBy (xpath="//div[1]/div/div/div/div/nav/ul/li[8]/a")
	WebElement btn_terms_and_policy;
	
	public PageCategoryTable accessTable(WebDriver d) {

		btn_tables.click();
		return PageFactory.initElements(d, PageCategoryTable.class);
	}
	
	public PageCategoryBedroom accessBedroom(WebDriver d) {

		btn_bedroom.click();
		return PageFactory.initElements(d, PageCategoryBedroom.class);
	}
	
}
