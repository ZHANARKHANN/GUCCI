package com.gucci.layers.web.page.signup_login;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AccountCreatedPage extends BasePage<AccountCreatedPage> {
    public SelenideElement accountCreatedMessage = $x("//b[text()='Account Created!']");
    public SelenideElement continueButton = $x("//a[@data-qa='continue-button']");

    @Override
    public AccountCreatedPage waitForPageLoaded() {
        accountCreatedMessage.shouldBe(Condition.visible);
        return this;
    }

    @Step("click continue button")
    public <T extends BasePage<T>> T clickContinueButton (Class<T> nextPageClass) {
        elementManager.click(continueButton);
        return Selenide.page(nextPageClass);
    }
}