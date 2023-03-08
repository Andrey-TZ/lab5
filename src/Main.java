import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Map;

import Model.StudyGroup;
import Utils.CustomJsonSerializer;
import Utils.JsonWorker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class Main {
    public static void main(String[] args){
        Hashtable<Integer, StudyGroup> group = JsonWorker.collectionFromJson();
        System.out.println(group.get(1));
    }
}