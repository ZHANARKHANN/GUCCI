package com.gucci.layers.web.page.selection;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ContactUsPage extends BasePage<ContactUsPage> {
    public SelenideElement getInTouchHeader = $x("//h2[text()='Get In Touch']");
    public SelenideElement inputName = $x("//input[@name = 'name']");
    public SelenideElement inputEmail = $x("//input[@name = 'email']");
    public SelenideElement inputSubject = $x("//input[@name = 'subject']");
    public SelenideElement inputMessage = $x("//textarea[@id = 'message']");
    public SelenideElement submitBtn = $x("//input[@data-qa='submit-button']");
    public SelenideElement uploadFile = $x("//input[@name = 'upload_file']");
    public SelenideElement successHeader = $x("//div[@class='status alert alert-success' " +
            "and normalize-space(text())='Success! Your details have been submitted successfully.']");
    public SelenideElement homeBtn = $x("//span[normalize-space(text())='Home']");

    @Override
    public ContactUsPage waitForPageLoaded() {
        getInTouchHeader.shouldBe(Condition.visible);
        return this;
    }


    @Step("Enter name, email, subject and message")
    public ContactUsPage fillContactUsForm(String name, String email, String subject, String message) {
        elementManager.input(inputName, name)
                .input(inputEmail, email)
                .input(inputSubject, subject)
                .input(inputMessage, message)
                .input(uploadFile, "C:/Users/User/Pictures/Screenshots/Снимок экрана 2025-04-03 150136.png");
        return this;
    }

    @Step("Click Submit btn")
    public ContactUsPage clickSubmitButton() {
        elementManager.click(submitBtn);
        return this;
    }

    @Step("accept alert")
    public ContactUsPage clickAlertAccept() {
        alertHelper.acceptAlertIfPresented();
        return this;
    }

    @Step("click home btn")
    public HomePage clickHome() {
        elementManager.click(homeBtn);
        return Selenide.page(HomePage.class);
    }
}