package Utils;

import Model.StudyGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class JsonWorker {
    private static String requestFileName(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public   InputStreamReader read() {
        InputStreamReader reader;
        while (true) {
            try {
                File file = new File(requestFileName());
                reader = new InputStreamReader(new FileInputStream(file));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("файл с таким именем не найден");
            }

        }
        return reader;

    }
    public  Hashtable<Integer, StudyGroup> read() throws IOException {

        reader = read();
        char[] fileContent = new char[(int) file.length()];
        reader.read(fileContent);
        String jsonString = String.valueOf(fileContent);
        reader.close();
        LocalDateTime time = LocalDateTime.parse("2023-03-06T20:14:21.307979500");


        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StudyGroup.class, new CustomJsonSerializer());
        Type collectionType = new TypeToken<Hashtable<Integer, StudyGroup>>(){}.getType();
        Gson gson = builder.create();
        Hashtable<Integer, StudyGroup> studyGroupMap = gson.fromJson(jsonString, collectionType);
        System.out.println(studyGroupMap);

        String json =gson.toJson(studyGroupMap);
        return studyGroupMap;
    }
}
