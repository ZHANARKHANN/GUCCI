import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selection.CartPage;
import com.gucci.layers.web.page.selection.ProductsPage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import com.gucci.layers.web.page.signup_login.SignUpPage;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.gucci.data.Sections.CART;
import static com.gucci.data.Sections.PRODUCTS;
import static io.qameta.allure.Allure.step;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class SearchProductTest extends BaseWebTest {

    @Test
    @DisplayName("Search Product Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_9")
    @Link("https://automationexercise.com/test_cases")
    public void searchProductTest() {

        var productsPage = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(PRODUCTS, ProductsPage.class)
                .waitForPageLoaded()
                .fillSearchInput("Premium Polo T-Shirts");
        softly.assertThat(productsPage.searchedProductsHeader.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'SEARCHED PRODUCTS' - must be visible")
                .isTrue();
        step("Verify all the products related to search are visible", () -> {
            softly.assertThat(productsPage.listOfSearchedProducts)
                    .as("Verify searchProductList")
                    .allMatch(SelenideElement::isDisplayed);
        });
        softly.assertAll();
    }

    @Test
    @DisplayName("Search Products and Verify Cart After Login Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_20")
    @Link("https://automationexercise.com/test_cases")
    public void searchProductsVerifyCartAfterLoginTest() {
        var productsPage = open("", HomePage.class)
                .switchBetweenSection(PRODUCTS, ProductsPage.class)
                .waitForPageLoaded()
                .fillSearchInput("Top");
        softly.assertThat(productsPage.searchedProductsHeader.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'SEARCHED PRODUCTS' - must be visible")
                .isTrue();
        step("Verify all the products related to search are visible", () -> {
            softly.assertThat(productsPage.listOfSearchedProducts)
                    .as("Verify searchProductList")
                    .allMatch(SelenideElement::isDisplayed);
        });
        var cartPage = productsPage.clickAllAddToCartButtons()
                .clickCartButton();
        softly.assertThat(cartPage.productsInCartElements)
                .as("'Products in cart' - must be visible")
                .allMatch(SelenideElement::isDisplayed);
        var loginPage = cartPage.clickSignupLoginPage()
                .waitForPageLoaded()
                .fillSignUpName(firstUser.getName())
                .fillSignUpEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signupNewUser(firstUser)
                .waitForPageLoaded()
                .clickContinueButton(HomePage.class)
                .verifyLoggedAsUserNameIsVisible(firstUser.getName())
                .clickLogoutButton();
        open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection("Signup / Login", LoginPage.class);

        softly.assertThatCode(() -> loginPage.loginToYourAccountHeader.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Login to your account' - must be visible")
                .doesNotThrowAnyException();

        loginPage.fillLoginEmail(firstUser.getEmail())
                .fillLoginPassword(firstUser.getPassword())
                .clickLoginBtn(HomePage.class)
                .switchBetweenSection(CART, CartPage.class);
        softly.assertThat(cartPage.productsInCartElements)
                .as("'Products in cart' - must be visible")
                .allMatch(SelenideElement::isDisplayed);
        softly.assertAll();
    }
}
