package filesReaders;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    String fileName ;
    public PropertyFileReader(String fileName) {
        this.fileName = fileName;
    }

    public Properties getAllProperties() {
        Properties fileProperties = null;
        try {
            FileReader fileReader = new FileReader( "./src/main/resources/properties/" + fileName) ;
            fileProperties=new Properties();
            fileProperties.load(fileReader);
        }
        catch (IOException ioException)
        {
            System.out.println("There is io exception happened");
        }
        return fileProperties;
    }
    public synchronized String getPropertyByKey(String propertyKey) {
        Properties fileProperties = getAllProperties ();
        return fileProperties.getProperty(propertyKey);
    }
    public synchronized boolean getBooleanProperty(String propertyKey) {
        Properties fileProperties = getAllProperties ();
        return Boolean.parseBoolean(fileProperties.getProperty(propertyKey));
    }
}
