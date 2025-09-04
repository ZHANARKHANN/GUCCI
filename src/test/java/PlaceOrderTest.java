import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selection.CartPage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import com.gucci.layers.web.page.signup_login.SignUpPage;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.gucci.data.Sections.CART;
import static com.gucci.data.Sections.SIGN_IN_LOGIN;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class PlaceOrderTest extends BaseWebTest {

    @Test
    @DisplayName("Place Order: Register while Checkout")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_14")
    @Link("https://automationexercise.com/test_cases")
    public void registerWhileCheckoutTest() {

        var homePage = open("", HomePage.class)
                .waitForPageLoaded()
                .addProductToCart("Blue Top")
                .addProductToCart("Sleeveless Dress")
                .clickContinueShopping()
                .switchBetweenSection(CART, CartPage.class)
                .waitForPageLoaded()
                .clickProceedCheckout()
                .clickLoginRegister()
                .fillSignUpName(firstUser.getName())
                .fillSignUpEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signupNewUser(firstUser)
                .waitForPageLoaded()
                .clickContinueButton(HomePage.class)
                .verifyLoggedAsUserNameIsVisible(firstUser.getName());
        var paymentPage = homePage.switchBetweenSection(CART, CartPage.class)
                .clickProceedCheckout()
                .verifyAddressDetailsText()
                .inputFormControl()
                .clickPlaceOrder()
                .fillPaymentOrder()
                .clickPayAndConfirmOrder();
        softly.assertThat(paymentPage.placeSuccessMessage.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'SEARCHED PRODUCTS' - must be visible")
                .isTrue();
        homePage.clickDeleteAccount()
                .waitForPageLoaded();
        softly.assertAll();
    }

    @Test
    @DisplayName("Place Order: Register before Checkout")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_15")
    @Link("https://automationexercise.com/test_cases")
    public void registerBeforeCheckoutTest() {

        var checkoutPage = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
                .waitForPageLoaded()
                .fillSignUpName(firstUser.getName())
                .fillSignUpEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signupNewUser(firstUser)
                .waitForPageLoaded()
                .clickContinueButton(HomePage.class)
                .verifyLoggedAsUserNameIsVisible(firstUser.getName())
                .addAllRecommendedProductsInCart()
                .switchBetweenSection(CART, CartPage.class)
                .waitForPageLoaded()
                .clickProceedCheckoutButton();
        assertAll("Delivery address and registration address konkurere",
                Stream.of(
                        () -> assertEquals(firstUser.getAddress1(), checkoutPage.deliveryAddress1.getText(), "Values is incorrect"),
                        () -> assertEquals(firstUser.getAddress2(), checkoutPage.deliveryAddress2.getText(), "Values is incorrect"),
                        () -> assertEquals(firstUser.getAddress1(), checkoutPage.billingAddress1.getText(), "Values is incorrect"),
                        () -> assertEquals(firstUser.getAddress2(), checkoutPage.billingAddress2.getText(), "Values is incorrect")
                )
        );
        var paymentPage = checkoutPage.fillCommentTextArea(faker.text().text(100))
                .fillPaymentOrder()
                .clickPayAndConfirmOrder();
        softly.assertThat(paymentPage.placeSuccessMessage.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'Congratulations! Your order has been confirmed!' should be visible")
                .isTrue();
        paymentPage.clickContinueButton()
                .clickDeleteAccount()
                .waitForPageLoaded()
                .clickContinueButton();

    }

    @Test
    @DisplayName("Place Order: Login before Checkout Checkout")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_16")
    @Link("https://automationexercise.com/test_cases")
    public void loginBeforeCheckoutTest() {

        open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
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

        var homePage = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection("Signup / Login", LoginPage.class)
                .fillLoginEmail(firstUser.getEmail())
                .fillLoginPassword(firstUser.getPassword())
                .clickLoginBtn(HomePage.class);
        softly.assertThatCode(() -> homePage.loggedInAsUsernameIsVisible.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'logged In As Username' - must be visible")
                .doesNotThrowAnyException();
            var checkoutPage = homePage.addAllRecommendedProductsInCart()
                    .addAllRecommendedProductsInCart()
                    .switchBetweenSection(CART, CartPage.class)
                    .waitForPageLoaded()
                    .clickProceedCheckoutButton();
        assertAll("Delivery address and registration address konkurere",
                Stream.of(
                        () -> assertEquals(firstUser.getAddress1(), checkoutPage.deliveryAddress1.getText(), "Values is incorrect"),
                        () -> assertEquals(firstUser.getAddress2(), checkoutPage.deliveryAddress2.getText(), "Values is incorrect"),
                        () -> assertEquals(firstUser.getAddress1(), checkoutPage.billingAddress1.getText(), "Values is incorrect"),
                        () -> assertEquals(firstUser.getAddress2(), checkoutPage.billingAddress2.getText(), "Values is incorrect")
                )
        );
        var paymentPage = checkoutPage.fillCommentTextArea(faker.text().text(100))
                .fillPaymentOrder()
                .clickPayAndConfirmOrder();
        softly.assertThat(paymentPage.placeSuccessMessage.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'Congratulations! Your order has been confirmed!' should be visible")
                .isTrue();
        paymentPage.clickContinueButton()
                .clickDeleteAccount()
                .waitForPageLoaded()
                .clickContinueButton();

    }
}
