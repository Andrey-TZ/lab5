package Utils;

import Model.StudyGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.Scanner;

public class JsonWorker {
    private static String requestFileName() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static char[] read() {
        InputStreamReader reader;
        File file;
        while (true) {
            try {
                file = new File(requestFileName());
                reader = new InputStreamReader(new FileInputStream(file));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("файл с таким именем не найден");
            }

        }
        char[] fileContent = new char[(int) file.length()];
        try {
            reader.read(fileContent);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileContent;

    }

    public static Hashtable<Integer, StudyGroup> collectionFromJson() {

        char[] fileContent = read();
        String jsonString = String.valueOf(fileContent);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StudyGroup.class, new CustomJsonSerializer());
        Type collectionType = new TypeToken<Hashtable<Integer, StudyGroup>>() {
        }.getType();
        Gson gson = builder.create();
        Hashtable<Integer, StudyGroup> studyGroupMap = gson.fromJson(jsonString, collectionType);
        System.out.println(studyGroupMap);

        String json = gson.toJson(studyGroupMap);
        return studyGroupMap;
    }
}
