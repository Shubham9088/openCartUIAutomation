package org.openCart.Utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties property;

    static{
        try{
            FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/openCart/TestData/openCart.properties");
            property = new Properties();
            property.load(fs);
        } catch (IOException e) {
            System.out.println("File openCart.properties is not present "+e.getMessage());
        }
    }

    public static String getProperty(String key){
        String envValue=System.getenv(key);
        if(envValue!=null){
            return envValue;
        }
        return property.getProperty(key);
    }
}
