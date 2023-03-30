import java.time.LocalDateTime;
import java.util.Hashtable;

import Model.StudyGroup;
import Run.CollectionManager;
import Run.CommandExecutor;
import Utils.CLIManager;
import Utils.JsonWorker;

public class Main {
    public static void main(String[] args){
//        CLIManager manager = new CLIManager();
//        Integer integer = manager.requestInt();
        Hashtable<Integer, StudyGroup> group = JsonWorker.collectionFromJson(args);
        System.out.println(group.get(1));
//        JsonWorker.writeJson(group);
        CollectionManager collectionManager = new CollectionManager(group);
        CommandExecutor executor = new CommandExecutor(collectionManager);
        executor.inputMode();
    }
}