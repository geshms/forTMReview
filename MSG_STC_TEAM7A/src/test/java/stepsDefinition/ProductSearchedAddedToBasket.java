/**
 * 
 */
package stepsDefinition;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.BaseUtil;

/**
 * @author GMS
 * @date Jan 5, 2021
 * @project MSG_STC_TEAM7A
 */
public class ProductSearchedAddedToBasket extends BaseUtil {

	public BaseUtil base;

	public ProductSearchedAddedToBasket(BaseUtil base) {
		this.base = base;
	}

	@Given("^I am on RS_uk homepage$")
	public void i_am_on_RS_uk_homepage() {

		try {

			base.log.info("Get to RS-uk Page & confirm sign in link is located..");

			// check element exists
			boolean result = base.keyword.checkElementExists("RS-uk_logo_link");
			Assert.assertTrue(result);
			base.log.info("User is on the RS-uk webpage");
		} catch (Exception e) {
			base.log.fatal("Exception - User is NOT on the RS-uk webpage");
			Assert.fail(e.getMessage());
		} catch (AssertionError e) {
			base.log.error("AssertionError - User is NOT on the RS-uk webpage");
			Assert.fail(e.getMessage());
		}

	}

	@When("^I search a \"([^\"]*)\"$")
	public void i_search_a(String valid_product) {
		try {
			base.keyword.searchforProduct(valid_product);
			base.keyword.waitUntilPageLoadsComplelety();
		} catch (Exception e) {
			base.log.fatal("Exception - Unable to locate search text box/search button");
			Assert.fail("Test-Failed:Unable to locate search text box/search button");
		}
	}

	@And("^I add first product to the basket$")
	public void i_add_first_product_to_the_basket() {
		try {
			boolean check_First_Product = base.keyword.checkElementExists("firstProductaddToBasketButton");
			Assert.assertTrue(check_First_Product);
		} catch (Exception e) {
			base.log.fatal("Exception - Unable to locate first Product");
			Assert.fail(e.getMessage());
		} catch (AssertionError e) {
			base.log.error("AssertionError - Element does not exist in the page");
			Assert.fail("Test-Failed:first product element add option does not exist in the page");
		}

		try {
			base.keyword.addProductToBasket();
			base.keyword.waitUntilPageLoadsComplelety();
		} catch (Exception e) {
			base.log.fatal("Exception - Unable to locate add Product to basket link");
			Assert.fail("Test-Failed:Unable to locate add Product to basket link");
		}
	}

	@And("^I view the basket$")
	public void i_view_the_basket() {
		try {
			// base.keyword.actionMovetoElement("productBasketLink");
			base.log.info("Window Scroll up so that view the basket link is visible");
			JavascriptExecutor js = (JavascriptExecutor) base.driver;
			js.executeScript("window.scrollBy(0,-500)", "");
			base.keyword.waitUntilElementIsVisible("productBasketLink");
			base.keyword.getToBasket();
			base.keyword.waitUntilPageLoadsComplelety();
		} catch (Exception e) {
			base.log.fatal("Exception - Unable to locate the view basket link");
			Assert.fail("Test-Failed:Unable to locate the view basket link");
		}
	}

	@Then("^My basket page should show \"([^\"]*)\"$")
	public void my_basket_page_should_show(String valid_product) {
		try {
			boolean check_My_Basket_Exist = base.keyword.checkElementExists("Mybasketitemdescription");
			Assert.assertTrue(check_My_Basket_Exist);
		} catch (Exception e) {
			base.log.fatal("Exception - Unable to locate the my basket item element");
			Assert.fail(e.getMessage());
		} catch (AssertionError e) {
			base.log.error("AssertionError - My basket  does not exist in the page");
			Assert.fail("Test-Failed: My basket does not exist in the page");
		}

		try {
			boolean check_valid_product_exist = base.keyword.verifyPartialText("Mybasketitemdescription",
					valid_product);
			Assert.assertTrue(check_valid_product_exist);
			base.log.info("Verified that Added Item is present in the  My Basket");
		} catch (Exception e) {
			base.log.fatal("Exception - Unable to locate the my basket item element");
			Assert.fail(e.getMessage());
		} catch (AssertionError e) {
			base.log.error("AssertionError - My basket  does not contain the added item");
			Assert.fail("Test-Failed: My basket  does not contain the added item");
		}
	}
}
