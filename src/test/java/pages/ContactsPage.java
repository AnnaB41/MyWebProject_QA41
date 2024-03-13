package pages;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactsPage extends  BasePage{

    @FindBy(xpath = "//button[contains(text(),'Sign')]")
    WebElement signOutButton;

    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']")
    WebElement contactExist;

    @FindBy(xpath = "//button[text()='Remove']")
    WebElement removeContact;

    @FindBy(xpath = "//h2")
    WebElement contactName;

    @FindBy(xpath = "//h3")
    WebElement contactPhone;


    public ContactsPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public String openContact() {
        contactExist.click();
        String namePhone = contactName.getText() + contactPhone.getText();
        return namePhone;
    }

    public void removeContact(){
        removeContact.click();
    }

    public String findContact() {

if(contactExist != null){
    contactExist.click();
    String namePhoneNew = contactName.getText() + contactPhone.getText();
    return namePhoneNew;}
    else return "No contacts";

        }



    public boolean clickBySignOutButton(){
        if (signOutButton != null){
            signOutButton.click();
            return true;
        } else return false;

    }

    /**
     * Этот метод предназначен для получения данных о контакте из списка контактов
     * на веб-странице и сравнения полученных данных с данными переданным объектом Contact.
     * @param contact
     * @return boolean
     */
    public boolean getDataFromContactList(Contact contact){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Создается объект класса WebDriverWait,
        // который ожидает видимости элемента на странице в течение 5 секунд.
        WebElement nameInContact = wait.until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'"+contact.getName().toString()+"')]"))); // Ожидается появление элемента с именем контакта, переданного в методе, используя XPath.
        nameInContact.click();
        WebElement editButton = driver
                .findElement(By.xpath("//button[contains(text(),'Edit')]"));
        editButton.click();
// находим элементы веб-страницы для каждого поля контакта (имя, фамилия, телефон, email, адрес и описание) и получаем их значения с помощью метода getAttribute("value").
        WebElement elementName = driver.findElement(By.xpath("//input[@placeholder='Name']"));
        String elementNameValue = elementName.getAttribute("value");

        WebElement elementLastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        String elementLastNameValue = elementLastName.getAttribute("value");

        WebElement elementPhone = driver.findElement(By.xpath("//input[@placeholder='Phone']"));
        String elementPhoneValue = elementPhone.getAttribute("value");

        WebElement elementEmail = driver.findElement(By.xpath("//input[@placeholder='email']"));
        String elementEmailValue = elementEmail.getAttribute("value");

        WebElement elementAddress = driver.findElement(By.xpath("//input[@placeholder='Address']"));
        String elementAddressValue = elementAddress.getAttribute("value");

        WebElement elementDescription = driver.findElement(By.xpath("//input[@placeholder='desc']"));
        String elementDescriptionValue = elementDescription.getAttribute("value");
// Создается новый объект Contact, в который записываются полученные значения полей контакта.
        Contact listContact = new Contact();
        listContact.setName(elementNameValue);
        listContact.setLastName(elementLastNameValue);
        listContact.setPhone(elementPhoneValue);
        listContact.setEmail(elementEmailValue);
        listContact.setAddress(elementAddressValue);
        listContact.setDescription(elementDescriptionValue);
        boolean result = listContact.equals(contact); // Выполняется сравнение переданного объекта Contact с объектом listContact, созданным на основе данных, полученных со страницы.
        return result; // Метод возвращает результат сравнения в виде логического значения true или false.
    }

}