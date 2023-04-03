package com.cydeo.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties = new Properties();


    {
        try {
           FileInputStream file = new FileInputStream("configuration.properties");

            properties.load(file);

            file.close();

        } catch (IOException e) {
            System.out.println("File not found in the configurationReader Class");
            throw new RuntimeException(e);
        }
    }

    public static String getProperty (String keyword){
        return properties.getProperty(keyword);
    }

}
