package tests;

import config.BaseTest;
import helpers.TopMenuItem;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTest extends BaseTest {

    @Test(description = "registration without password")
    public void phoneBookTest_001() throws InterruptedException {

        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        loginPage.fillEmailField("myemail1@mail.com").clickByRegistrationButton();
        Thread.sleep(5000); // останови на 5 сек

    }

    @Test(description = "registration with password")
    public void phoneBookTest_002() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        loginPage.fillEmailField("myemail3@mail.com").fillPasswordField("Tt123456$").clickByRegistrationButton();
        Thread.sleep(10000); // останови на 10 сек

    }

    @Test(description = "login with email and password")
    public void phoneBookTest_003() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        loginPage.fillEmailField("myemail3@mail.com").fillPasswordField("Tt123456$").clickByLoginButton();
        Thread.sleep(10000); // останови на 10 сек

    }
}
