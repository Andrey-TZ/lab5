import java.util.Hashtable;

import Model.StudyGroup;
import Utils.CollectionManager;
import Utils.CommandExecutor;
import JsonParsing.JsonParser;

public class Main {
    public static void main(String[] args){

        CollectionManager collectionManager = new CollectionManager(args);
        CommandExecutor executor = new CommandExecutor(collectionManager);
        executor.inputMode();
    }
}