import java.util.Hashtable;

import Model.StudyGroup;
import Utils.CollectionManager;
import Utils.CommandExecutor;
import JsonParsing.JsonParser;

public class Main {
    public static void main(String[] args){

        JsonParser parser = new JsonParser();
        Hashtable<Integer, StudyGroup> group = parser.collectionFromJson(args);
        CollectionManager collectionManager = new CollectionManager(group);
        CommandExecutor executor = new CommandExecutor(collectionManager);
        executor.inputMode();
    }
}