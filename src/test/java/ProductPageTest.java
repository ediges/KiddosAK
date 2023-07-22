import Pages.ProductPage;
import Utils.UseCaseBase;
import Pages.MainPage;
import org.junit.jupiter.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.jupiter.api.Assertions.*;

public class ProductPageTest extends UseCaseBase {
    private static MainPage mainPage;
    private static final Logger logger = LogManager.getLogger(ProductPageTest.class);
    public static final String ENGLISH_ONLY_PAGE_HEADER = "//h1[text()='English Only']";
    public static final String BOOK_PAGE_HEADER = "//h1[contains(text(), 'I Love to Brush My Teeth')]";
    public static final String CART_PAGE_HEADER = "//h1[text()='Your cart']";

    @BeforeAll
    public static void classSetUp() {
        mainPage = new MainPage();
    }

    @BeforeEach
    public void beforeTest() {
        mainPage.navigateToMainPage();
    }

    @Test
    public void selectedLanguagePageLoadTest() {
        logger.info("Selected language page load test");
        ProductPage productPage = mainPage.selectBooksLanguage();
        boolean isLoaded = productPage.isPageTitleVisible(ENGLISH_ONLY_PAGE_HEADER);
        assertTrue(isLoaded);
    }

    @Test
    public void booksDetailsVisibilityTest() {
        logger.info("Specific book details page load test");
        ProductPage productPage = mainPage.selectBooksLanguage();
        productPage.selectBook();
        boolean isVisible = productPage.isPageTitleVisible(BOOK_PAGE_HEADER);
        assertTrue(isVisible);
    }

    @Test
    public void cartPageVisibilityTest() {
        logger.info("Cart page load test");
        ProductPage productPage = mainPage.selectBooksLanguage();

        productPage.selectBook();
        productPage.selectBookFormat();
        productPage.changeNumberOfBooks("5");
        productPage.addBooksToCart();

        boolean isLoaded = productPage.isPageTitleVisible(CART_PAGE_HEADER);
        assertTrue(isLoaded);
    }

    @Test
    public void cartUpdateTest() {
        logger.info("Cart update test");
        ProductPage productPage = mainPage.selectBooksLanguage();

        productPage.selectBook();
        productPage.selectBookFormat();
        productPage.changeNumberOfBooks("5");
        productPage.addBooksToCart();

        String price = productPage.getTotalPriceInCart();

        productPage.changeQuantityInCart("6");
        productPage.updateCart();
        String updatedAmount = productPage.getBooksAmountInCart();
        assertEquals("6", updatedAmount);

        String updatedPrice = productPage.getTotalPriceInCart();
        assertNotEquals(price, updatedPrice);
    }
}
