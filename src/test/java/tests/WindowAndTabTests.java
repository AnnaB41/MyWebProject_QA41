package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class WindowAndTabTests {
    public static void main(String[] args) throws InterruptedException {
       // switchTab();
        //sliderTest();
        dragAndDropTest();
    }

    public static void sliderTest() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();//можно хромдрайвер
        driver.get("https://demoqa.com/slider");
        driver.manage().window().maximize();
        String mainWindowHandler = driver.getWindowHandle(); // получаем идентификатор текущего окна,
        // сохранили как строчку

        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
        Actions action = new Actions(driver);
        action.dragAndDropBy(slider, 30, 0).build().perform();
        // передаем элемент и смещение по осям x y
        Thread.sleep(3000);


/*    один их вариантов работы со слайдером
        slider.sendKeys(Keys.ARROW_RIGHT);  Thread.sleep(1000);
        slider.sendKeys(Keys.ARROW_RIGHT);  Thread.sleep(1000);
        slider.sendKeys(Keys.ARROW_RIGHT);  Thread.sleep(1000);
        slider.sendKeys(Keys.ARROW_LEFT);  Thread.sleep(1000);
        slider.sendKeys(Keys.ARROW_LEFT);  Thread.sleep(1000);
        slider.sendKeys(Keys.ARROW_RIGHT);  Thread.sleep(1000);
*/
        driver.quit();



    }
    public static void switchTab(){
        WebDriver driver = new FirefoxDriver();//можно хромдрайвер
        driver.get("https://demoqa.com/browser-windows");
        driver.manage().window().maximize();
        String mainWindowHandler = driver.getWindowHandle(); // получаем идентификатор текущего окна,
        // сохранили как строчку

        WebElement newButton = driver.findElement(By.xpath("//button[@id='tabButton']"));
        newButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // ожидаем
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // пока число окошек будет 2
        Set<String> allWindowHandles = driver.getWindowHandles();

        String newWindowHandler = "";
        for(String windowHandle : allWindowHandles){
            if(!windowHandle.equals(mainWindowHandler)){
                newWindowHandler = windowHandle;
                break;
            }
        }

        driver.switchTo().window(newWindowHandler);//переключаемся на новое окно
        WebElement newPageElement = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
        // ищем элемент на новом окне
        newPageElement.click();
       driver.quit(); // закрываем страницы

    }

    @Test
    public static void dragAndDropTest() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();//можно хромдрайвер
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();

        WebElement elementToDrop = driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement targetElement = driver.findElement(By.xpath("//div[@id='column-b']"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(elementToDrop, targetElement).build().perform();
        Thread.sleep(5000);
        driver.quit();

    }
}
