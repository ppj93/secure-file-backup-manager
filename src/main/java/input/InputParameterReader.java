package input;

import java.util.Properties;

public class InputParameterReader {
    private static Properties properties = new Properties();

    public static void loadProperties() {
        try {
            //load a properties file from class path, inside static method
            properties.load(InputParameterReader.class.getClassLoader().getResourceAsStream("input.properties"));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
