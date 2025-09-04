package com.gucci.layers.web.page.signup_login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {
    public SelenideElement signUpHeaderIsVisible = $x("//h2[text()='New User Signup!']");
    public SelenideElement loginToYourAccountHeader = $x("//h2[text()='Login to your account']");
    public SelenideElement signUpName = $x("//input[@name='name']");
    public SelenideElement signUpEmail = $x("//input[@data-qa='signup-email']");
    public SelenideElement signUpButton = $x("//button[@data-qa='signup-button']");
    public SelenideElement loginEmailInput = $x("//input[@data-qa='login-email']");
    public SelenideElement loginPasswordInput = $x("//input[@data-qa='login-password']");
    public SelenideElement loginButton = $x("//button[@data-qa='login-button']");
    public SelenideElement incorrectEmailOrPasswordError = $x("//input[@placeholder='Password']/following-sibling::p[text()='Your email or password is incorrect!']");
    public SelenideElement homePageBtn = $x("//a[normalize-space(text())='Home']");
    public SelenideElement emailAlreadyExistError = $x("//p[normalize-space(text())='Email Address already exist!']");

    @Override
    public LoginPage waitForPageLoaded() {
        loginToYourAccountHeader.shouldHave(Condition.exactText("Login to your account"));
        return this;
    }

    @Step("input user name {0}")
    public LoginPage fillSignUpName(String name) {
        elementManager.input(signUpName, name);
        return this;
    }

    @Step("input user email {0}")
    public LoginPage fillSignUpEmail(String email) {
        elementManager.input(signUpEmail, email);
        return this;
    }

    @Step("input user email {0}")
    public LoginPage fillLoginEmail(String email) {
        elementManager.input(loginEmailInput, email);
        return this;
    }

    @Step ("input password {0}")
    public LoginPage fillLoginPassword (String password) {
        elementManager.input(loginPasswordInput, password);
        return this;
    }

    @Step("click sign up button")
    public <T extends BasePage<T>> T clickSignUpBtn(Class<T> nextPageClass) {
        elementManager.click(signUpButton);
        return Selenide.page(nextPageClass);
    }

    @Step("click login button")
    public <T extends BasePage<T>> T clickLoginBtn(Class<T> nextPageClass) {
        elementManager.click(loginButton);
        return Selenide.page(nextPageClass);
    }

    @Step("click login button negative case")
    public LoginPage clickLoginPageButtonNegativeCase () {
        elementManager.click(loginButton);
        return this;
    }

    @Step ("click sign up button negative case")
    public LoginPage clickSignUpButtonNegativeCase () {
        elementManager.click(signUpButton);
        return this;
    }


}
