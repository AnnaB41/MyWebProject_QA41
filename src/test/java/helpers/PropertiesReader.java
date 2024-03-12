package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static final String PROPERTIES_FILE_PATH = "src/test/resources/resources.properties";

    public static String getProperty(String key){ // метод, который принимает строчку и возвращает строчку

        Properties properties = new Properties(); // объект класса пропертис

        try(FileInputStream fis = new FileInputStream(PROPERTIES_FILE_PATH)){// читаем наш файл
           properties.load(fis);   // загружаем данные файла из объекта пропертис
            return properties.getProperty(key); // возвращаем значение по ключу
        }catch (IOException exception){
            exception.printStackTrace();
            return null;
        }



    }
}
