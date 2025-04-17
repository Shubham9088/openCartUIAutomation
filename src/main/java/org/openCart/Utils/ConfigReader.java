package org.openCart.Utils;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties testDataProperty;
    private static final Properties locatorProperty;
    private static final Properties lacatorDataProperty;

    static{
       testDataProperty=loadProperties(System.getProperty("user.dir")+"/src/main/java/org/openCart/TestData/openCart.properties");
       locatorProperty=loadProperties(System.getProperty("user.dir")+"/src/main/java/org/openCart/Locator/locator.properties");
       lacatorDataProperty=loadProperties(System.getProperty("user.dir")+"/src/main/java/org/openCart/Locator/locatorData.properties");
    }

    /**
     * Reads the property file
     * @param filepath property file to read
     * @return Properties object
     * @author shchak
     */
    public static Properties loadProperties(String filepath){
        Properties property=new Properties();
        try{
            FileInputStream fs=new FileInputStream(filepath);
            property.load(fs);
        }catch(Exception e){
            System.out.println("File "+filepath+" is not present "+e.getMessage());
        }
        return property;
    }

    /**
     * Returns the value of the specified key from the openCart.properties file.
     * If an environment variable with the same key exists, its value is returned instead.
     * @param key the key to look up in the environment variables or the properties file
     * @return the value associated with the key
     * @author shchak
     */
    public static String getTestDataProperty(String key){
        String envValue=System.getenv(key);
        if(envValue!=null){
            return envValue;
        }
        return  testDataProperty.getProperty(key);
    }

    /**
     * Returns the value of the specified key from the locatorData.properties file
     * @param key the key to look up in the properties file
     * @return the value associated with the key
     * @author shchak
     */
    public static String getLocatorDataProperty(String key){
        return  lacatorDataProperty.getProperty(key);
    }

    /**
     * Returns the value of the specified key from the locator.properties file
     * @param key the key to look up in the properties file
     * @return the value associated with the key
     * @author shchak
     */
    public static  String getLocatorProperty(String key){
        return  locatorProperty.getProperty(key);
    }
}
