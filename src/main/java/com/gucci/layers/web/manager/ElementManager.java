package com.gucci.layers.web.manager;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.signup_login.SignUpPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ElementManager {
    private final int DELAY = 30;

    public ElementManager click(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(DELAY))
                .shouldBe(enabled, Duration.ofSeconds(DELAY))
                .shouldNotHave(attribute("disabled"), Duration.ofSeconds(DELAY))
                .shouldBe(clickable, Duration.ofSeconds(DELAY))
                .click();
        return this;
    }

    public SignUpPage clickLogin_SignupBTN() {
        return new SignUpPage();
    }

    public ElementManager input(SelenideElement element, String text) {
        element
                .shouldBe(visible, Duration.ofSeconds(DELAY))
                .shouldBe(enabled, Duration.ofSeconds(DELAY))
                .scrollTo()
                .sendKeys(text);
        return this;
    }

    public String getText(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(DELAY))
                .shouldNotBe(empty, Duration.ofSeconds(DELAY))
                .scrollTo();
        return element.getText();
    }

    public ElementManager verifyAttributeValue (SelenideElement element, String attribute, String expectedValue) {
        element
                .shouldBe(visible, Duration.ofSeconds(DELAY))
                .shouldNotBe(empty, Duration.ofSeconds(DELAY))
                .shouldHave(Condition.attribute(attribute, expectedValue));
        return this;
    }

    public ElementManager selectByValue (SelenideElement element, String value) {
        element
                .shouldBe(visible, Duration.ofSeconds(DELAY))
                .selectOption(value);
        return this;
    }

    public ElementManager selectByText (SelenideElement element, String text) {
        element
                .shouldBe(visible, Duration.ofSeconds(DELAY))
                .selectOption(text);
        return this;
    }

    public ElementManager scrollToElement(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(DELAY))
                .scrollTo();
        return this;
    }

    public ElementManager clearAndType(SelenideElement element, String text) {
        element
                .shouldBe(visible, Duration.ofSeconds(DELAY))
                .shouldBe(enabled, Duration.ofSeconds(DELAY))
                .clear();
        element.sendKeys(text);
        return this;
    }

    public ElementManager hover(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(DELAY))
                .hover();
        return this;
    }

    public ElementManager doubleClick(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(DELAY))
                .doubleClick();
        return this;
    }

    public ElementManager shouldBeVisible(SelenideElement element) {
        element.shouldBe(visible, Duration.ofSeconds(DELAY));
        return this;
    }

    public ElementManager shouldNotExist(SelenideElement element) {
        element.shouldNot(exist, Duration.ofSeconds(DELAY));
        return this;
    }

    public ElementManager waitUntilDisappear(SelenideElement element) {
        element.should(disappear, Duration.ofSeconds(DELAY));
        return this;
    }

    public String getAttribute(SelenideElement element, String attribute) {
        element.shouldBe(visible, Duration.ofSeconds(DELAY));
        return element.getAttribute(attribute);
    }

    public ElementManager jsScrollToElement(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
        return this;
    }

    public ElementManager jsClick(SelenideElement element) {
        executeJavaScript("arguments[0].click();", element);
        return this;
    }

    public ElementManager waitForPageLoad() {
        executeJavaScript("return document.readyState").equals("complete");
        return this;
    }


}
