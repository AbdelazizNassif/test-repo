package filesReaders;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonFileReader {
    String fileName;

    public JsonFileReader(String fileName) {
        this.fileName = fileName;
    }

    public JSONObject getJsonBody()
    {
        JSONParser parser = new JSONParser();
        Object obj = null ;
        try {
            obj = parser.parse(new FileReader("./src/test/resources/testData/" + fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }
    public String getJsonStringValueByKey( String jsonKey) {
        JSONObject jsonObject = getJsonBody();
        String value = (String) jsonObject.get(jsonKey);
        return value;
    }
    public Object getJsonValueByKey( String jsonKey) {
        JSONObject jsonObject = getJsonBody();
        return  jsonObject.get(jsonKey);
    }

    public int getJsonIntegerValueByKey( String jsonKey) {
        JSONObject jsonObject = getJsonBody();
        int value = (int) jsonObject.get(jsonKey);
        return value;
    }
}
