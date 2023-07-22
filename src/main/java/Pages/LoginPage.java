package Pages;

import Utils.SharedDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage extends BasePage {
    public static final String LOGIN_SCREEN_HEADER = "//h1[text()='Login']";
    public static final String EMAIL = "//input[@id='CustomerEmail']";
    public static final String PASSWORD = "//input[@id='CustomerPassword']";
    public static final String SIGN_IN_BUTTON = "//input[@value='Sign In']";
    public static final String ERROR_MESSAGE = "//li[text()='Incorrect email or password.']";
    public static final String CHALLENGE_MESSAGE = "//p[@class='shopify-challenge__message']";
    Logger logger = LogManager.getLogger(LoginPage.class);

    public boolean isPageTitleVisible() {
        return elementExists(LOGIN_SCREEN_HEADER);
    }

    public void sendTextToEmailField(String text) {
        sendTextToElementByXpath(EMAIL, text);
    }

    public void sendTextToPasswordField(String text) {
        sendTextToElementByXpath(PASSWORD, text);
    }

    public void clickToSignInButton() {
        clickElementByXpath(SIGN_IN_BUTTON);
    }

    public WebElement getErrorMessage(String xpath) {
        WebElement element = findElementByXpath(xpath);
        return element;
    }

    public String getInvalidEmailErrorMessage() throws InterruptedException {
        Thread.sleep(1000);
        String message = findElementByXpath(EMAIL).getAttribute("validationMessage");
        return message;
    }

    public void closeCaptcha() throws InterruptedException {
        if(elementExists(CHALLENGE_MESSAGE)) {
            Thread.sleep(15000);  //Please click on Captcha checkbox manually and follow the instructions, then click on Submit button to continue test
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CHALLENGE_MESSAGE)));
        }
    }
}
