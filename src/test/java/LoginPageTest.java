import Utils.UseCaseBase;
import Pages.LoginPage;
import Pages.MainPage;
import org.junit.jupiter.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

public class LoginPageTest extends UseCaseBase {
    public static final String ERROR_MESSAGE_TEXT = "Incorrect email or password.";
    public static final String INVALID_EMAIL_ERROR = "Please include an '@' in the email address.";
    private static MainPage mainPage;
    private static final Logger logger = LogManager.getLogger(MainPageTest.class);

    @BeforeAll
    public static void classSetUp() {
        mainPage = new MainPage();
    }

    @BeforeEach
    public void beforeTest() {
        mainPage.navigateToMainPage();
    }

    @Test
    public void loginPageLoadTest() {
        logger.info("Login page load test");
        LoginPage loginPage = mainPage.openLoginTab();
        boolean isLoaded = loginPage.isPageTitleVisible();
        assertTrue(isLoaded);
    }

    @ParameterizedTest
    @CsvSource({"Sam, 111111", "natan, ''", "AAAAAAAA, aaaaaaaaaaa", "8888888888, ''", "david134678, ''"})
    @DisplayName("Verify that appropriate error message displayed when trying to login with email, that not contain '@' symbol")
    public void loginWithInvalidEmail(String email, String password) throws InterruptedException {
        logger.info("Login with invalid email test");
        LoginPage loginPage = mainPage.openLoginTab();

        loginPage.sendTextToEmailField(email);
        loginPage.sendTextToPasswordField(password);
        loginPage.clickToSignInButton();

        String actualErrorMessage = loginPage.getInvalidEmailErrorMessage();

        assertNotNull(actualErrorMessage);
        assertTrue(actualErrorMessage.contains(INVALID_EMAIL_ERROR));
    }

//    @Test
//    public void loginWithEmptyCredentials() throws InterruptedException {
//        logger.info("Login with empty credentials test");
//        LoginPage loginPage = mainPage.openLoginTab();
//
//        loginPage.sendTextToEmailField("");
//        loginPage.sendTextToPasswordField("");
//        loginPage.clickToSignInButton();
//        loginPage.closeCaptcha();
//
//        WebElement errorMessage = loginPage.getErrorMessage(LoginPage.ERROR_MESSAGE);
//        assertNotNull(errorMessage);
//        assertEquals(ERROR_MESSAGE_TEXT, errorMessage.getText());
//    }
}
