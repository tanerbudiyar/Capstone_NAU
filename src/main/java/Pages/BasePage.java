package Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.Driver;

public class BasePage {
	public BasePage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	@FindBy(id="twotabsearchtextbox")
	public WebElement SEARCH_BOX;
	
	@FindBy(id="nav-search-submit-button")
	public WebElement SEARCH_BUTTON;
	
	@FindBy(xpath="//div[@class='a-section a-spacing-small a-spacing-top-small']")
	public WebElement SEARCH_TITLE;
	
	
	@FindBy(xpath="//div[@class='aok-relative']")
	public WebElement SEARCH_RESULT;
	
	@FindBy(xpath="//span[@class='olp-used olp-link'][1]")
	public WebElement USED_BOOK_LIST;
	
	@FindBy(xpath="//span[@class='a-price']")
	public List<WebElement> ALL_PRICES;
	
	
	@FindBy(id="productTitle")
	public WebElement BOOK_TITLE;
	
	@FindBy(id="bookDescription_feature_div")
	public WebElement BOOK_DESCRIPTION;
	
	
	public void basicwait(int wait){
		try {
			Thread.sleep(wait*1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
} 
