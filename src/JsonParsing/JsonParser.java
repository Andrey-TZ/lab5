package JsonParsing;

import Model.Coordinates;
import Model.StudyGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.naming.NoPermissionException;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Scanner;

public class JsonParser {
    private static Path path;
    private static Path getPath(){
        return path;
    }
    private  final Type collectionType = new TypeToken<Hashtable<Integer, StudyGroup>>() {}.getType();

    /**
     * Команда для считывания названия файла
     * @return название файла
     */
    private  Path requestFilePath() {
        Scanner scanner = new Scanner(System.in);
        return Paths.get(scanner.nextLine());
    }

    /**
     * Чтение файла
     * @return содержимое файла в виде массива char
     */
    public char[] read(Path path) {
        InputStreamReader reader;
        File file;
        while (true) {
            try {
                file = new File(path.toUri());
                reader = new InputStreamReader(new FileInputStream(file));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("файл с таким именем не найден, введите новый");
                path = requestFilePath();
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

    /**
     * Парсинг json
     * @return Hashtable объектов StudyGroup
     */
    public  Hashtable<Integer, StudyGroup> collectionFromJson(String[] args) {

        if(args.length != 0){
            path = Paths.get(args[0]);
        } else{
            System.out.println("Пожалуйста, укажите путь к файлу");
            path = requestFilePath();
        }

        char[] fileContent = read(path);
        String jsonString = String.valueOf(fileContent);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(collectionType, new HashTableSerializer());
        Gson gson = builder.create();

        return gson.fromJson(jsonString, collectionType);
    }

    /**
     * Преобразует коллекцию в String
     * @param collection коллекция объектов StudyGroup
     */
    public  void writeJson(Hashtable<Integer, StudyGroup> collection) {

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
    private  void writeToFile(String jsonString) {
        while (true) {
            try {
                if (!Files.exists(path)){
                    Files.createFile(path);
                }
                if(! Files.isReadable(path)) throw new NoPermissionException("Не получается прочитать файл");
                if(! Files.isWritable(path)) throw new NoPermissionException("Не получается записать данные в этот файл");


                PrintWriter out = new PrintWriter(new FileWriter(path.toFile()));
                out.write(jsonString);
                out.close();
                break;
            } catch (IOException e) {
                System.out.println("не удалось записать данные в этот файл. Введите путь к другому файлу!");
                path = requestFilePath();
            } catch (NoPermissionException e) {
                System.out.println("Нет доступа к " + path + " - " + e.getMessage());
                path = requestFilePath();
            }
        }
    }
}
