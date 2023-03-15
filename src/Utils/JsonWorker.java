package Utils;

import Model.Coordinates;
import Model.StudyGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.Scanner;

public class JsonWorker {
    private static String file;
    private static final Type collectionType = new TypeToken<Hashtable<Integer, StudyGroup>>() {}.getType();

    private static String requestFileName() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static char[] read() {
        InputStreamReader reader;
        File fileRead;
        while (true) {
            try {
                file = requestFileName();
                fileRead = new File(file);
                reader = new InputStreamReader(new FileInputStream(fileRead));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("файл с таким именем не найден");
            }

        }
        char[] fileContent = new char[(int) fileRead.length()];
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
        builder.registerTypeAdapter(StudyGroup.class, new StudyGroupJsonSerializer());
        Gson gson = builder.create();
        System.out.println(jsonString);

        return gson.fromJson(jsonString, collectionType);
    }

    public static void writeJson(Hashtable<Integer, StudyGroup> collection) {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StudyGroup.class, new StudyGroupJsonSerializer());
        builder.registerTypeAdapter(Coordinates.class, new CoordinatesJsonSerializer());
        Gson gson = builder.create();
        String collectionJsonString = gson.toJson(collection, collectionType);
        System.out.println(collectionJsonString);
        writeToFile(collectionJsonString);

    }

    private static void writeToFile(String jsonString) {
        while (true) {
            try {
                file = requestFileName();
                PrintWriter out = new PrintWriter(new FileWriter(file));
                out.write(jsonString);
                out.close();
                break;
            } catch (IOException e) {
                System.out.println("не удалось записать данные в этот файл");
            }

        }
    }
}
