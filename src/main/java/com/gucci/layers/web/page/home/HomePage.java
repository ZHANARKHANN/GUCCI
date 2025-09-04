package com.gucci.layers.web.page.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.selection.CartPage;
import com.gucci.layers.web.page.selection.ProductDetailsPage;
import com.gucci.layers.web.page.selection.ProductsPage;
import com.gucci.layers.web.page.signup_login.DeleteAccountPage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import com.gucci.layers.web.page.signup_login.SignUpPage;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage<HomePage> {

    Random random = new Random();

    public SelenideElement header = $(By.id("header"));
    public SelenideElement featuresItems = $(".features_items");
    public SelenideElement leftSideBar = $(".left-sidebar");
    public ElementsCollection brands = leftSideBar.$$x(".//div[@class='brands-name']//li[not(span[@class='pull-right'])]");
    public SelenideElement homeOrange = $x("//a[@href='/' and contains(@style, 'orange')]");
    public SelenideElement signupLoginBtn = $x("//a[@href='/login']");
    public SelenideElement logoutBtn = $x("//i[@class='fa fa-lock']");
    public SelenideElement loggedInAsUsernameIsVisible = header.$("b");
    public SelenideElement deleteAccountButton = $x("//a[@href ='/delete_account']");
    public SelenideElement contactUsBtn = $x("//a[normalize-space(text())='Contact us']");
    public SelenideElement testCasesBtn = $x("//a[text()=' Test Cases']");
    public SelenideElement productsBtn = $x("//i[@class='material-icons card_travel']");
    public SelenideElement subscriptionHeader = $x("//div[@class='single-widget']/h2");
    public SelenideElement subscribeEmailInput = $(By.id("susbscribe_email"));
    public SelenideElement subscribeEmailBtn = $(By.id("subscribe"));
    public SelenideElement subscribedAlert = $x("//div[@class='alert-success alert']");
    public SelenideElement cart = $x("//a[@href='/view_cart']/i");
    public SelenideElement left_sidebar = $(".left-sidebar");
    public SelenideElement loggedInAsUserHeader = header.$("b");
    public SelenideElement deleteAccountBtn = header.$("a[href='/delete_account']");
    public SelenideElement footer = $("#footer .footer-widget");
    public SelenideElement subscription = footer.$("h2");
    public SelenideElement cartBtn = $("a[href='/view_cart']");
    public SelenideElement viewCartBtn = $x("//a[@href='/view_cart']/u[text()='View Cart']");
    public SelenideElement continueShopping = $x("//button[text()='Continue Shopping']");
    public SelenideElement viewProduct = $x("//a[@href='/product_details/1']");
    public SelenideElement single_widget = $(".single-widget");
    public SelenideElement single_widgetHeader = single_widget.$("h2");
    public SelenideElement inputSubscriptionEmail = single_widget.$("input[id='susbscribe_email']");
    public SelenideElement subscriptionBtn = single_widget.$("button");
    public SelenideElement womenCategory = $x("//a[@href='#Women']");
    public SelenideElement womenTopsSubCategory = $x("//a[text() = 'Tops ']");
    public SelenideElement arrowButton = $(By.id("subscribe"));
    public SelenideElement youHaveBeenSuccessfullySubscribed = $x("//div[text()='You have been successfully subscribed!']");
    public SelenideElement categoryInLeftSideBar = $x("//h2[text()='Category']");
    public SelenideElement womenCategoryInLeftSideBar = $x("//a[normalize-space()='Women']");
    public SelenideElement recommendedItemsHeader = $x("//h2[text()='recommended items']");
    public SelenideElement scrollUpArrowButton = $(By.id("scrollUp"));


    public ElementsCollection viewProducts = $$x("//a[text()='View Product']");
    public ElementsCollection womenSubcategories = $$x("//div[@id='Women']//div[@class='panel-body']//ul//li/a");
    public ElementsCollection listOfRecommendedItemsAddToCart = $$x("//div[@class='recommended_items']//a[text()='Add to cart']");
    public ElementsCollection listOffFullFledgetPracticeHeaders = $$x("//h2[text()='Full-Fledged practice website for Automation Engineers']");

    public SelenideElement firstProduct = $x("//a[@data-product-id='1']/parent::div/parent::div[@class='single-products']");
    public SelenideElement secondProduct = $x("//a[@data-product-id='2']/parent::div/parent::div[@class='single-products']");
    public SelenideElement firstProductsAddToCartButton = $x("//div/parent::div[@class='product-overlay']//a[@data-product-id='1']");
    public SelenideElement secondProductsAddToCartButton = $x("//div/parent::div[@class='product-overlay']//a[@data-product-id='2']");
    public SelenideElement continueShoppingButton = $x("//button[text()='Continue Shopping']");
    public SelenideElement viewCartButton = $x("//u[text()='View Cart']");

    @Override
    public HomePage waitForPageLoaded() {
        homeOrange.shouldHave(Condition.attribute("style", "color: orange;"));
        return this;
    }


    @Step("Add products to cart")
    public CartPage addProductsToCartAndOpenCart() {
        elementManager.hover(firstProduct);
        elementManager.click(firstProductsAddToCartButton);
        elementManager.click(continueShoppingButton);
        elementManager.hover(secondProduct);
        elementManager.click(secondProductsAddToCartButton);
        elementManager.click(viewCartButton);
        return page(CartPage.class);
    }


    @Step("Click Random Products View Product")
    public ProductDetailsPage clickRandomViewProduct() {
        int oneof34 = random.nextInt(34);
        elementManager.click(viewProducts.get(oneof34));
        return page(ProductDetailsPage.class);
    }

    @Step("Click sign up / login button")
    public LoginPage clickSignupLoginButton() {
        elementManager.click(signupLoginBtn);
        return page(LoginPage.class);
    }

    public <T> T switchBetweenSection(String section, Class<T> pageClass) {
        header.shouldBe(Condition.visible);

        SelenideElement sectionElement = header.$(By.xpath(
                ".//ul[@class='nav navbar-nav']//a[normalize-space(text())='" + section + "']"));

        elementManager.click(sectionElement);

        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать экземпляр " + pageClass.getSimpleName(), e);
        }
    }

    public List<String> getBrands() {
        List<String> brandsList = new ArrayList<>();
        for (SelenideElement element : brands) {
            brandsList.add(elementManager.getText(element));
        }
        return brandsList;
    }

    @Step("Add product to cart from HomePage")
    public HomePage addProductToCart(String productName) {
        SelenideElement modal = $(".modal-selector"); // реальный селектор модалки
        if (modal.isDisplayed()) {
            modal.$(".close-btn").click(); // закрываем модалку
            modal.should(Condition.disappear); // ждём, пока исчезнет
        }

        SelenideElement addToCartButton = $(By.xpath(
                "(//p[normalize-space(text())='" + productName + "']/ancestor::div[contains(@class,'product')]//a[contains(@class,'add-to-cart')])[1]"
        ));

        Selenide.executeJavaScript("arguments[0].click();", addToCartButton);
        return this;
    }

    @Step("click continue shopping")
    public HomePage clickContinueShopping() {
        elementManager.click(continueShopping);
        return this;
    }

    @Step("View cart")
    public CartPage clickViewCart() {
        viewCartBtn.shouldBe(Condition.visible, Duration.ofSeconds(10));
        elementManager.click(viewCartBtn);
        return page(CartPage.class);
    }


    public HomePage verifyLoggedAsUserNameIsVisible(String name) {
        loggedInAsUsernameIsVisible.shouldHave(Condition.exactText(name));
        return this;
    }

    public DeleteAccountPage clickDeleteAccount() {
        elementManager.click(deleteAccountButton);
        return page(DeleteAccountPage.class);
    }

    public LoginPage clickLogoutButton() {
        elementManager.click(logoutBtn);
        return page(LoginPage.class);
    }

    public HomePage scrollDownToFooter() {
        elementManager.scrollToElement(footer);
        return this;
    }

    public HomePage scrollUpToHeader() {
        elementManager.scrollToElement(header);
        return this;
    }

    @Step("Enter email address in input and click arrow button{0}")
    public HomePage fillEmailInputAndClickArrowButton(String email) {
        elementManager.input(inputSubscriptionEmail, email);
        elementManager.click(arrowButton);
        return this;
    }

    @Step("Click women category")
    public HomePage clickWomenCategory() {
        elementManager.click(womenCategoryInLeftSideBar);
        return this;
    }

    @Step("Click random women`s subcategory")
    public ProductsPage clickRandomWomenSubcategory() {
        int oneOf3 = random.nextInt(3);
        elementManager.click(womenSubcategories.get(oneOf3));
        return page(ProductsPage.class);
    }

    @Step("Add to cart recommended products")
    public HomePage addAllRecommendedProductsInCart() {
        for (int i = 0; i < listOfRecommendedItemsAddToCart.size(); i++) {
                elementManager.jsClick(listOfRecommendedItemsAddToCart.get(i));
                elementManager.click(continueShoppingButton);
        }
        return this;
    }

    @Step("Click scroll up Arrow button")
    public HomePage clickScrollUpArrowButton () {
        elementManager.click(scrollUpArrowButton);
        return this;
    }


}
