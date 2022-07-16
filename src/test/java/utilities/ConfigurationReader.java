package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties=new Properties();

    static {

        try {
            FileInputStream file=new FileInputStream("configuration.properties");
            properties.load(file);
            file.close();  // it will be better to close like that.. to have more control
        } catch (IOException e) {
            System.out.println("File is not found in the ConfigurationReader class");
            e.printStackTrace();
        }

    }

    public static String getProperty(String keyword){

        return properties.getProperty(keyword);


    }


}
