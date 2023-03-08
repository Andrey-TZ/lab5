import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Map;

import Model.StudyGroup;
import Utils.CustomJsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        File file = new File("D:/Admin/Programming/lab5/src/1.json");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        char[] fileContent = new char[(int) file.length()];
        reader.read(fileContent);
        String jsonString = String.valueOf(fileContent);
        reader.close();
        LocalDateTime time = LocalDateTime.parse("2023-03-06T20:14:21.307979500");


        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StudyGroup.class, new CustomJsonSerializer());
        Type collectionType = new TypeToken<Hashtable<Integer, StudyGroup>>(){}.getType();
        Gson gson = builder.create();
        Map<Integer, StudyGroup> studyGroupMap = gson.fromJson(jsonString, collectionType);
        System.out.println(studyGroupMap);

        String json =gson.toJson(studyGroupMap);
        System.out.println(studyGroupMap.get(1));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        while (true){
//            String x = reader.readLine();
//            System.out.println(x);
//        }
    }
}