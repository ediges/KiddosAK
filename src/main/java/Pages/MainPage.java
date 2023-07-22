package Pages;

import Consts.Consts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MainPage extends BasePage{
    private static final Logger logger = LogManager.getLogger(MainPage.class);
    private static final String LOGO_IMG = "//img[@itemprop='logo']";
    private static final String CONTACT_US_OPTION = "//a[text()='Contact us']";
    public static final String LOGIN_BUTTON = "//a[@class='site-header__account']";
    public static final String LANGUAGE_DROPDOWN = "//a[@class='site-nav__link site-nav__link--main' and contains(text(), ' Books by language')]";
    public static final String ENGLISH_ONLY_OPTION = "//a[text()='English Only']";

    public void navigateToMainPage() {
        webDriver.get(Consts.MAIN_URL);
    }

    public boolean isLogoVisible() {
        Boolean isVisible =  elementExists(LOGO_IMG);
        return isVisible;
    }

    public ContactUsPage openContactUsTab() {
        clickElementByXpath(CONTACT_US_OPTION);
        return new ContactUsPage();
    }

    public LoginPage openLoginTab() {
        clickElementByXpath(LOGIN_BUTTON);
        return new LoginPage();
    }

    public ProductPage selectBooksLanguage() {
        clickElementByXpath(LANGUAGE_DROPDOWN);
        List<WebElement> elem = webDriver.findElements(By.xpath(ENGLISH_ONLY_OPTION));
        elem.get(0).click();

        return new ProductPage();
    }
}
