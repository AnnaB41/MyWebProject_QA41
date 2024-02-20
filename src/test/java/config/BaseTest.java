package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.BasePage;

import java.time.Duration;

public class BaseTest { // Эта строка объявляет начало определения класса BaseTest. Класс является шаблоном
    // или чертежом для создания объектов.

    private  static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>(); // Эта строка объявляет статическое
    //     приватное поле driverThreadLocal, которое является обхектом класса ThreadLocal
    // ThreadLocal это класс в Java, который позволяет создавать локальные переменные, специфичные для каждого потока
    // Каждый поток имеет свою собственную копию переменной, хранящейся в ThreadLocal, и доступ может быть получен только из
    //      соответствующего потока
    // Это полезно, когда вам нужно создать объект, который будет униикальным для каждого потока, и при этом изолировать состояние
    //      между потоками

    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    @BeforeMethod
    @Parameters("browser") // by default используется тот браузер, что написан в нижней строчке
    public void setUp(@Optional("chrome") String browser){
        // Этот блок кода проверяет, являетя ли значение параметра browser равным "chrome".
        // Если да, то он настраивает ChromeDriver и добавляет опции для запуска браузера на английском языке

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en");// выбор языка
            // options.addArguments("--headless"); // не показывает как все происходит
            driverThreadLocal.set(new ChromeDriver(options));
        }
        // Аналогично предыдущему блоку, но если browser равен "firefox".
        // Если да, то он настраивает FirefoxDriver и добавляет опции для запуска браузера на английском языке
        else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("intl.accept_languages", "en");
            driverThreadLocal.set(new FirefoxDriver(options));
        }
        else if (browser.equalsIgnoreCase("safari")){
            SafariOptions options = new SafariOptions();
            options.setCapability("language", "en");
           driverThreadLocal.set(new SafariDriver());
        } else if (browser.equalsIgnoreCase("edge")) {
          // настройки для  edge
            EdgeOptions options = new EdgeOptions();
            options.setCapability("language", "en");
            //  options.addArguments("--headless");
            driverThreadLocal.set(new EdgeDriver(options));
         }
        else{throw new IllegalArgumentException("Invalid browser "+browser);}

        // Этот блок кода получает веб-драйвер с помощью метода getDriver(), максимизирует окно барузера
        // устанавливает время ожидания загрузки страницы и неявного ожидания, а затем устанавливает этот драйвер для BasePage
        WebDriver driver = getDriver();
        driver.manage().window().maximize();// расширить окно браузера по максимуму
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(20000));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(20000)); // неявное ожидание
        BasePage.setDriver(driver);
    }

    @AfterMethod
    // после выполнения тестового сценария
    public void tearDown(){
        WebDriver driver = getDriver();
        if (driver != null){
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
