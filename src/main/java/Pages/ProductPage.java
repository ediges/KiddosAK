package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class ProductPage extends BasePage{
    public static final String EXIT_PROMO = "//i[@class='_close-icon']";
    public static final String PROMO_HEADER = "//div[text()='Enjoy 10% off your first order!']";
    public static final String ENGLISH_BOOK = "//div[contains(text(), 'I Love to Brush My Teeth')]";
    public static final String BOOK_FORMAT_DROPDOWN = "//select[@id='SingleOptionSelector-0']";
    public static final String HARDCOVER_FORMAT = "//option[@value='Hardcover']";
    public static final String BOOK_QUANTITY = "//input[@id='Quantity']";
    public static final String ADD_TO_CART_BUTTON = "//button[@id='AddToCart-product-template']";
    public static final String QUANTITY_IN_CART = "//input[@class='cart__qty-input']";
    public static final String UPDATE_BUTTON = "//input[@class='btn btn--secondary cart__update cart__update--large small--hide']";
    public static final String TOTAL_PRICE_IN_CART = "//span[@class='cbb-price-digits']";
    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    public void checkAndClosePromoTab() {
        if(elementExists(PROMO_HEADER)) {
            clickElementByXpath(EXIT_PROMO);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(EXIT_PROMO)));
        }
    }

    public boolean isPageTitleVisible(String xpath) {
        return elementExists(xpath);
    }

    public void selectBook() {
        clickElementByXpath(ENGLISH_BOOK);
    }

    public void selectBookFormat() {
        List<WebElement> elements = webDriver.findElements(By.xpath(BOOK_FORMAT_DROPDOWN));
        elements.get(0).click();

        elements = webDriver.findElements(By.xpath(HARDCOVER_FORMAT));
        elements.get(0).click();
    }

    public void changeNumberOfBooks(String quantity) {
        List<WebElement> elements = webDriver.findElements(By.xpath(BOOK_QUANTITY));
        WebElement quantityField = elements.get(0);
        quantityField.clear();
        quantityField.sendKeys(quantity);
    }

    public void changeQuantityInCart(String quantity) {
        clearInputBox(QUANTITY_IN_CART);
        sendTextToElementByXpath(QUANTITY_IN_CART, quantity);
    }

    public void addBooksToCart() {
        List<WebElement> elements = webDriver.findElements(By.xpath(ADD_TO_CART_BUTTON));
        elements.get(0).click();
    }

    public void updateCart() {
        findElementByXpath(UPDATE_BUTTON).click();
    }

    public String getBooksAmountInCart() {
        return findElementByXpath(QUANTITY_IN_CART).getAttribute("value");
    }

    public String getTotalPriceInCart() {
        List<WebElement> elements = webDriver.findElements(By.xpath(TOTAL_PRICE_IN_CART));
        return elements.get(1).getText();
    }
}
