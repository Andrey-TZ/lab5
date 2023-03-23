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

    /**
     * команда для считывания названия файла
     * @return название файла
     */
    private static String requestFileName() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Чтение файла
     * @return содержимое файла в виде массива char
     */
    private static char[] read(String file) {
        InputStreamReader reader;
        File fileRead;
        while (true) {
            try {
                fileRead = new File(file);
                reader = new InputStreamReader(new FileInputStream(fileRead));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("файл с таким именем не найден, введите новый");
                file = requestFileName();
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

    /**
     * Парсинг json
     * @return Hashtable объектов StudyGroup
     */
    public static Hashtable<Integer, StudyGroup> collectionFromJson(String[] args) {
        String fileName;
        if(args.length != 0){
            fileName = args[0];
        } else{
            System.out.println("Пожалуйста, укажите путь к файлу");
            fileName = requestFileName();
        }

        char[] fileContent = read(fileName);
        String jsonString = String.valueOf(fileContent);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StudyGroup.class, new StudyGroupJsonSerializer());
        Gson gson = builder.create();
        System.out.println(jsonString);

        return gson.fromJson(jsonString, collectionType);
    }

    /**
     * Преобразует коллекцию в String
     * @param collection коллекция объектов StudyGroup
     */
    public static void writeJson(Hashtable<Integer, StudyGroup> collection) {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StudyGroup.class, new StudyGroupJsonSerializer());
        builder.registerTypeAdapter(Coordinates.class, new CoordinatesJsonSerializer());
        Gson gson = builder.create();
        String collectionJsonString = gson.toJson(collection, collectionType);
        System.out.println(collectionJsonString);
        writeToFile(collectionJsonString);

    }

    /**
     * Записывает в файл строку
     * @param jsonString строка с коллекцией
     */
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
