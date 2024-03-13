package helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesWriter {

    public static void main(String[] args) {

        writeProperties("myKey1", "myValue25", false);  // тест нашего метода
    }
    private static final String PROPERTIES_FILE_PATH = "src/test/resources/data.properties"; //путь к нашему файлу, название файла

// создаем метод, в который передаем ключ и значение, очищать или нет зависит от булево

    public static void writeProperties(String key, String value, boolean cleanFile){ //или перезаписываем, или добавляем строчку

        Properties properties = new Properties(); // создаем элемент класса пропертис, он позволяет достучаться до значений, которые нужно изменить
        FileInputStream fileInputStream = null;  // сущности, с которыми будем работать, работают с файлами. Этот читает
        FileOutputStream fileOutputStream = null;  // сущности, с которыми будем работать, работают с файлами
       try{
        if(!Files.exists(Paths.get(PROPERTIES_FILE_PATH))){  // еслли файл не существует по пути
            Files.createFile(Paths.get(PROPERTIES_FILE_PATH)); // то создай файл по этому пути
        }
         fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH); //поток ввода из файла с указанием пути, чтение

        properties.load(fileInputStream); // загружаем свойства в файл
        if(cleanFile){ // если флажок нужно очистить файл
            properties.clear();  // удалить все в файле
        }
        properties.setProperty(key, value); // установи туда какие-то значения
        fileOutputStream = new FileOutputStream(PROPERTIES_FILE_PATH);  //
        properties.store(fileOutputStream, null); // записывает значения в файл

    } catch (IOException e){e.printStackTrace();}  // покажи все ошибки
       finally {  // этот блок всегда выполняется
           {
               try{
                   if(fileInputStream != null){ // если поток существует, закрой его
                       fileInputStream.close();
                   }
                   if(fileOutputStream != null){
                       fileOutputStream.close();
                   }
               }catch (IOException exception){exception.printStackTrace();
           }
       }

       }
    }}



