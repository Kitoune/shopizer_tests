package org.shopizer_test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageShopingCart {

	@FindBy (xpath="//tbody/tr[1]/td[1]/div/div[2]")
	WebElement link_item1;
	
	@FindBy (xpath="//tbody/tr[2]/td[1]/div/div[2]")
	WebElement link_item2;
	
	@FindBy (xpath="(//span[@class='amount'])[2]")
	WebElement total_price;
	
	@FindBy(xpath="//a[contains(text(),'Recalculer')]")
	WebElement btn_recalculer;
	
	
}
