package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver){

        setDriver(driver);
        driver.get("https://telranedu.web.app/");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public <T extends BasePage> T openTopMenu(String topMenuItem){
        WebElement menuItem = driver.findElement(By.xpath("//a[contains(text(),'"+topMenuItem+"')]"));
        menuItem.click();

        switch (topMenuItem){
            case "HOME":
                return (T) new HomePage(driver); // верни страничку (объект) и передай драйвер, чтобы мы могли с ней взаимодействовать
            case "ABOUT":
                return (T) new AboutPage(driver);
            case "LOGIN":
                return (T) new LoginPage(driver);

            default: throw new IllegalArgumentException("Somethings wrong " + topMenuItem);
        }
    }
}
