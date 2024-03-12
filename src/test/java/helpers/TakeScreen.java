package helpers;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static config.BaseTest.getDriver;

public class TakeScreen {

    // результат работы должен быть прикреплен как Атачмент
    @Attachment(value = "Failure screenshot", type = "image/png") // value - названиеб type - тип вложения
    public static byte[] takeScreenshot(String testName){

        try{String screenshotName = testName + "_" + System.currentTimeMillis() +".png"; // создается уник.имя скриншота
            // имя + текущее время в милисекундах
            File screenshotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            // сохраняем скриншот в виде файла
            // (TakesScreenshot) getDriver() приведение объекта getDriver() к интерфейсу (TakesScreenshot)
            FileUtils.copyFile(screenshotFile, new File("screenshots/"+screenshotName));
            //копирует файл скриншота в директорию скриншотс (директория должна быть в проекте, если ее нет, то создать ее)
            return Files.readAllBytes(Paths.get("screenshots\\"+screenshotName));
            //верни содержимое скриншота ???
        }catch (IOException e){
            return null;
        }
    }

}
