package tests;
import config.BaseTest;
import helpers.*;
import io.qameta.allure.Allure;
import jdk.jfr.Description;
import model.Contact;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTest1 extends BaseTest {

    @Test(description = "The test checks the empty field warning declaration.")
    public void registrationWithoutPassword() throws InterruptedException {

        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        String expectedString = "Wrong";
        Alert alert =  loginPage.fillEmailField("myemail1@mail.com").clickByRegistrationButton();
        // Thread.sleep(5000); // останови на 5 сек
        boolean isAlertHandled = AlertHandler.handleAlert(alert, expectedString);
        Assert.assertTrue(isAlertHandled);

    }
    @Test
    @Description("User already exist. Login and add contact.")
    public void loginOfAnExistingUserAddContact() throws InterruptedException {
        Allure.description("User already exist. Login and add contact.!");
        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Step 1");
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        Allure.step("step 2");
        lpage.fillEmailField(PropertiesReader.getProperty("existingUserEmail"))
                .fillPasswordField(PropertiesReader.getProperty("existingUserPassword"))
                .clickByLoginButton();
        Allure.step("Step 3");
        mainPage.openTopMenu(TopMenuItem.ADD.toString());
        AddPage addPage = new AddPage(getDriver());
        Contact newContact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(10,5,3),
                AddressGenerator.generateAddress(),
                "new description");
        newContact.toString();
        addPage.fillFormAndSave(newContact);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.getDataFromContactList(newContact));
        TakeScreen.takeScreenshot("screen");
        Thread.sleep(3000);

    }


    @Test
    public void addContact() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());

        loginPage.fillEmailField("myemail5@mail.com").
                fillPasswordField("Tt123456$").
                clickByLoginButton();



//        loginPage.fillEmailField(PropertiesReader.getProperty("existingUserEmail")).
//                fillPasswordField(PropertiesReader.getProperty("existingUserPassword")).
//                clickByLoginButton();

        Thread.sleep(3000); // останови на 10 сек
        AddPage addPage = mainPage.openTopMenu(TopMenuItem.ADD.toString());
        Thread.sleep(3000); // останови на 10 сек
        Contact newContact = new Contact(NameAndLastNameGenerator.generateName(), NameAndLastNameGenerator.generateLastName(), PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,2,2), AddressGenerator.generateAddress(), "jjjj");
        addPage.fillFormAndSave(newContact);
        Thread.sleep(3000); // останови на 10 сек
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.getDataFromContactList(newContact));
    }

    @Test
    public void addUserAddContact() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver());
//        String email = EmailGenerator.generateEmail(5,3,2);
//        String password = PasswordStringGenerator.generateString();
//        System.out.println("Email: " + email + " Password " + password);
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        loginPage.fillEmailField(EmailGenerator.generateEmail(5,3,2)).fillPasswordField(PasswordStringGenerator.generateString()).clickByRegistrationButton();
        Thread.sleep(2000);
////        loginPage.fillEmailField(email).fillPasswordField(password).clickByLoginButton();
////        Thread.sleep(2000);
        AddPage addPage = mainPage.openTopMenu(TopMenuItem.ADD.toString());
        Thread.sleep(2000);
        Contact contact = new Contact(NameAndLastNameGenerator.generateName(), NameAndLastNameGenerator.generateLastName(), PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,2,2), AddressGenerator.generateAddress(), "jjjj");
        addPage.fillFormAndSave(contact);
        Thread.sleep(3000);

//    @Test(description = "registration with password")
//    public void phoneBookTest_002() throws InterruptedException {
//        MainPage mainPage = new MainPage(getDriver());
//        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
//        loginPage.fillEmailField("myemail5@mail.com").fillPasswordField("Tt123456$").clickByRegistrationButton();
//        Thread.sleep(10000); // останови на 10 сек
//
//    }
//
//    @Test(description = "login with email and password")
//    public void phoneBookTest_003() throws InterruptedException {
//        MainPage mainPage = new MainPage(getDriver());
//        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
//        loginPage.fillEmailField("myemail4@mail.com").fillPasswordField("Tt123456$").clickByLoginButton();
//        Thread.sleep(10000); // останови на 10 сек
//    }


    }}
