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

    public static String getTestDataProperty(String key){
        String envValue=System.getenv(key);
        if(envValue!=null){
            return envValue;
        }
        return  testDataProperty.getProperty(key);
    }

    public static String getLocatorDataProperty(String key){
        return  lacatorDataProperty.getProperty(key);
    }

    public static  String getLocatorProperty(String key){
        return  locatorProperty.getProperty(key);
    }
}
