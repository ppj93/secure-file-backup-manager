package input;

import com.sun.tools.javac.Main;

import java.io.IOException;
import java.util.Properties;

public class InputReader {
    private static Properties properties = new Properties();

    public static void loadProperties() {
        try {
            //load a properties file from class path, inside static method
            properties.load(InputReader.class.getClassLoader().getResourceAsStream("input.properties"));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
