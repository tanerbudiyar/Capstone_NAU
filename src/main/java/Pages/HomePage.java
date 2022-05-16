package Pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;

import Utils.ConfigReader;
import Utils.Driver;
import Utils.ExcelUtils;

public class HomePage extends BasePage {

	String bookISBN;
	List<Double> prices = new ArrayList<>();
	List<String> info = new ArrayList<>();
	String title;
	String description;

	public void getUrl() {

		Driver.getDriver().get(ConfigReader.getProperty("url"));
		basicwait(5);
	}

	public void sendISBN(String string) {
		ExcelUtils utils = new ExcelUtils();
		ArrayList<String> books = null;
		try {
			books = utils.getBookList();
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		bookISBN = books.get(Integer.valueOf(string));
		SEARCH_BOX.sendKeys(bookISBN);
		basicwait(5);
	}

	public void clickSearchBox() {
		SEARCH_BUTTON.click();
		basicwait(5);
	}

	public boolean compareTitle() {
		String PageISBN = SEARCH_TITLE.getText();
		if (PageISBN == bookISBN) {
			return true;
		} else
			return false;
	}

	public void writeInfo() {
		Collections.sort(prices);
		info = Arrays.asList(title, description, bookISBN, "$" + prices.get(prices.size() - 1).toString(),
				"$" + prices.get(0).toString());
		ExcelUtils.writeFile(info);
	}

	public void clickSearchResult() {
		basicwait(5);
		SEARCH_RESULT.click();
	}

	public void clickUsedBooks() {
		USED_BOOK_LIST.click();
		basicwait(5);
	}

	public void getAllPrice() {
		for (int i = 0; i < ALL_PRICES.size(); i++) {
			if (!ALL_PRICES.get(i).getText().isEmpty()) {

				String priceFirst = ALL_PRICES.get(i).getText().replace("$", "");
				String priceSecond = priceFirst.replace("\n", ".");
				Double price = Double.parseDouble(priceSecond);
				prices.add(price);
			}

		}
	}

	public void getBookInfo() {
		title = BOOK_TITLE.getText();
		description = BOOK_DESCRIPTION.getText();

	}
}
