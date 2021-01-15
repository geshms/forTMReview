package webConnector;

import util.BaseUtil;

public class AppSpecificKeywords extends GenericKeywords {

	public AppSpecificKeywords(BaseUtil base) {
		super(base);
	}

	public void getToSignInPage() {
		try {
			base.log.info("Clicking Sign In link");

			base.keyword.waitUntilElementIsVisible("signinLink");
			base.keyword.click("signinLink");

			base.keyword.waitUntilPageLoadsComplelety();

			// Check email text is located
			base.keyword.waitUntilElementIsVisible("signInheaderTitleLabel");
		} catch (Exception e) {
			base.log.info(e.getMessage());
		}
	}

	public void searchforProduct(String data) {
		try {
			base.log.info("Check if the Search box is loaded");
			base.keyword.waitUntilElementIsVisible("searchTextBox");

			// Enter product name
			base.log.info("Enter Product in Search box and click Search");
			base.keyword.type("searchTextBox", data);

			// Click search button
			base.keyword.click("searchButton");
			base.keyword.sleep(Integer.parseInt( base.property.getProperty("waitTime")));

		} catch (Exception e) {
			base.log.info(e.getMessage());
		}
	}

	public void addProductToBasket() {
		try {

			base.log.info("Adding the first Product from search results to the product basket");

			// Click add to basket button in the first product link
			base.keyword.click("firstProductaddToBasketButton");
			base.keyword.sleep(Integer.parseInt( base.property.getProperty("waitTime")));

		} catch (Exception e) {
			base.log.info(e.getMessage());
		}
	}

	public void getToBasket() {
		try {

			// Open product basket to validate the result
			base.log.info("Click on Product Basket");
			base.keyword.click("productBasketLink");
			base.keyword.sleep(Integer.parseInt( base.property.getProperty("waitTime")));

		} catch (Exception e) {
			base.log.info(e.getMessage());
		}
	}

}
