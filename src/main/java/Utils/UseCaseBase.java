package Utils;

import Pages.BasePage;
import Utils.SharedDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class UseCaseBase {
    private static WebDriver webDriver;
    private static BasePage basePage;

    @BeforeAll
    public static void setUpMain() {
        basePage = new BasePage();
        webDriver = SharedDriver.getWebDriver(SharedDriver.Browser.CHROME);
        basePage.setWebDriver(webDriver);
    }

    @AfterAll
    public static void tearDownMain() {
        SharedDriver.closeWebDriver();
        webDriver = null;
    }
}
