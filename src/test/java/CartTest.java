import com.codeborne.selenide.Condition;
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
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.gucci.data.Sections.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class CartTest extends BaseWebTest {

    @Test
    @DisplayName("Add Products in Cart Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_12")
    @Link("https://automationexercise.com/test_cases")
    public void AddProductInCartTest() {

        var cartPage = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(PRODUCTS, ProductsPage.class)
                .hoverFirstProductAndClickAddToCart()
                .clickContinueShoppingButton()
                .hoverSecondProductAndClickAddToCart()
                .clickViewCartButton();
        softly.assertThat(cartPage.productsInCartElements)
                .as("Verify searchProductList")
                .allMatch(SelenideElement::isDisplayed);
        softly.assertAll();
    }


    @Test
    @DisplayName("Verify Product quantity in Cart Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_13")
    @Link("https://automationexercise.com/test_cases")
    public void VerifyProductQuantityInCartTest() {

        var productDetailsPage = open("", HomePage.class)
                .waitForPageLoaded()
                .clickRandomViewProduct();
        softly.assertThat(productDetailsPage.productInformation.isDisplayed())
                .as("'ProductInformation' должен быть видимым")
                .isTrue();
        var cartPage = productDetailsPage.changeQuantity("4")
                .clickAddToCart()
                .clickViewCart();
        softly.assertThat(cartPage.productInCart.isDisplayed())
                .as("'Product' должен быть видимым в корзине")
                .isTrue();
        softly.assertThat(cartPage.productsQuantity.getText()).isEqualTo("4");
        softly.assertAll();
    }

    @Test
    @DisplayName("Remove Products From Cart Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_17")
    @Link("https://automationexercise.com/test_cases")
    public void RemoveProductsFromCartTest() {

        var cartPage = open("", HomePage.class)
                .waitForPageLoaded()
                .addProductsToCartAndOpenCart()
                .waitForPageLoaded()
                .clickDeleteProductFromCart();
        cartPage.cartIsEmptyMessage.shouldHave(Condition.exactText("Cart is empty!"));
    }

    @Test
    @DisplayName("View & Cart Brand Products Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_19")
    @Link("https://automationexercise.com/test_cases")
    public void viewCartBrandProductsTest() {
        var productsPage = open("", HomePage.class)
                .switchBetweenSection(PRODUCTS, ProductsPage.class);
        softly.assertThat(productsPage.brandsOnLeftSideBar.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'BRANDS' on left side bar should be visible")
                .isTrue();
        productsPage.clickRandomBrandOnLeftSideBar();
        softly.assertThat(productsPage.brandOnTitle.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'BRAND' on title should be visible")
                .isTrue();
        productsPage.clickRandomBrandOnLeftSideBar();
        softly.assertThat(productsPage.brandOnTitle.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'BRAND' on title should be visible")
                .isTrue();
        softly.assertAll();
    }

    @Test
    @DisplayName("Add to cart from Recommended items Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_22")
    @Link("https://automationexercise.com/test_cases")
    public void addToCartFromRecommendedItemsTest() {
        var homePAge = open("", HomePage.class)
                .scrollDownToFooter();
        softly.assertThat(homePAge.recommendedItemsHeader.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'Recommended Items' header is visible")
                .isTrue();
        var cartPage = homePAge.addAllRecommendedProductsInCart()
                .switchBetweenSection(CART, CartPage.class);
        softly.assertThat(cartPage.productsInCartElements)
                .as("Verify searchProductList")
                .allMatch(SelenideElement::isDisplayed);
        softly.assertAll();
    }

    @Test
    @DisplayName("Verify address details in checkout page Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_23")
    @Link("https://automationexercise.com/test_cases")
    public void verifyAddressInCheckoutPageTest() {
        var checkoutPage = open("", HomePage.class)
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
        checkoutPage.clickDeleteAccount()
                .waitForPageLoaded()
                .clickContinueButton();
    }

    @Test
    @DisplayName("Download Invoice after purchase order Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_24")
    @Link("https://automationexercise.com/test_cases")
    public void downloadInvoiceAfterPurchaseOrderTest() {

        var checkoutPage = open("", HomePage.class)
                .waitForPageLoaded()
                .addAllRecommendedProductsInCart()
                .switchBetweenSection(CART, CartPage.class)
                .waitForPageLoaded()
                .clickProceedCheckoutButton()
                .clickSignupLoginButton()
                .fillSignUpName(firstUser.getName())
                .fillSignUpEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signupNewUser(firstUser)
                .waitForPageLoaded()
                .clickContinueButton(HomePage.class)
                .verifyLoggedAsUserNameIsVisible(firstUser.getName())
                .switchBetweenSection(CART, CartPage.class)
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
        paymentPage.clickDownloadInvoiceButton()
                .clickContinueButton()
                .clickDeleteAccount()
                .waitForPageLoaded()
                .clickContinueButton();


    }


}




