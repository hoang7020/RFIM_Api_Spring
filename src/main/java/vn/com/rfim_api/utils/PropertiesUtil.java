package vn.com.rfim_api.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static PropertiesUtil instance = null;

    private FileReader reader;
    private Properties properties;

    private static PropertiesUtil getInstance() {
        if (instance == null) {
            instance = new PropertiesUtil();
        }
        return instance;
    }

    public PropertiesUtil() {
        try {
            reader = new FileReader(".\\src\\main\\resources\\messages.properties");
            properties = new Properties();
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public static String getString(String key) {
        return getInstance().getProperties().getProperty(key);
    }
}
