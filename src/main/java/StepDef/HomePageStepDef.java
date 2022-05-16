package StepDef;

import Pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageStepDef {
	HomePage homePage = new HomePage();

	@Given("The user is on amazon home page")
	public void the_user_is_on_amazon_home_page() {
		homePage.getUrl();
	}

	@When("The user provides selected book ISBN\\/Title as {string}")
	public void the_user_provides_selected_book_isbn_title_as(String string) {
		homePage.sendISBN(string);
	}

	@When("The user clicks on search button")
	public void the_user_clicks_on_search_button() {
		homePage.clickSearchBox();
	}

	@Then("The user clicks on corresponding search result")
	public void click_on_search_result() {
		homePage.clickSearchResult();
	}
	
	@And("The user gets book info")
	public void the_user_gets_book_info() {
		homePage.getBookInfo();
	}
	 

	@And("The user clicks on used book link")
	public void click_on_used_books() {
		homePage.clickUsedBooks();
	}

	@Then("The user gets list of prices")
	public void the_user_gets_list_of_prices() {
		homePage.getAllPrice();
	}

	@Then("File should be ready to send")
	public void file_should_be_ready_to_send() {
		homePage.writeInfo();
	}
	


}
