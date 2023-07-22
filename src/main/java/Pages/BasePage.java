package Pages;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    protected static WebDriver webDriver;
    protected static WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    protected WebElement findElementByXpath(String xpath) {
        WebElement element;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        element = webDriver.findElement(By.xpath(xpath));

        return element;
    }

    protected void clickElementByXpath(String xpath) {
        logger.info("Clicking element by xpath" + xpath);
        findElementByXpath(xpath).click();
    }

    protected void sendTextToElementByXpath(String xpath, String text) {
        findElementByXpath(xpath).sendKeys(text);
    }

    protected void clearInputBox(String xpath) {
        findElementByXpath(xpath).clear();
    }

    protected boolean elementExists(String xpath) {
        try {
            logger.info("Check element with xpath exist" + xpath);
            webDriver.findElement(By.xpath(xpath));
//            findElementByXpath(xpath);  //will wait another 5 sec if not found
            return true;
        }
        catch (Exception err) {
            return false;
        }
    }

    protected String getCurrentPageUrl() {
        return webDriver.getCurrentUrl();
    }

    public void takeScreenShot(String name) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("screenshots/" + name + ".jpeg"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
