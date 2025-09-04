package com.gucci.layers.web.page.selection;

import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.signup_login.DeleteAccountPage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class CheckoutPage extends BasePage<CheckoutPage> {

    public SelenideElement checkoutHeader = $x("//li[text()='Checkout']");
    public SelenideElement deliveryAddress1 = $x("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][2]");
    public SelenideElement billingAddress1 = $x("//ul[@id='address_invoice']//li[@class='address_address1 address_address2'][2]");
    public SelenideElement deliveryAddress2 = $x("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][3]");
    public SelenideElement billingAddress2 = $x("//ul[@id='address_invoice']//li[@class='address_address1 address_address2'][3]");
    public SelenideElement deleteAccountButton = $x("//a[@href ='/delete_account']");
    public SelenideElement signupLoginSectionButton = $x("//a[text()=' Signup / Login']");
    public SelenideElement commentTextArea = $x("//textarea[@class='form-control']");
    public SelenideElement placeOrderButton = $x("//a[text()='Place Order']");




    @Override
    public CheckoutPage waitForPageLoaded() {
        checkoutHeader.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    public DeleteAccountPage clickDeleteAccount() {
        elementManager.click(deleteAccountButton);
        return page(DeleteAccountPage.class);
    }

    @Step("Click Signup/Login Button")
    public LoginPage clickSignupLoginButton () {
        elementManager.jsClick(signupLoginSectionButton);
        return page(LoginPage.class);
    }

    @Step("Fill text Area and confirm it {0}")
    public PaymentPage fillCommentTextArea (String text) {
        elementManager.input(commentTextArea, text);
        elementManager.click(placeOrderButton);
        return page(PaymentPage.class);
    }



}
