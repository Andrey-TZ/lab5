import java.time.LocalDateTime;
import java.util.Hashtable;

import Model.StudyGroup;
import Utils.CLIManager;
import Utils.JsonWorker;

public class Main {
    public static void main(String[] args){
        CLIManager manager = new CLIManager();
        Integer integer = manager.requestInt();
        System.out.println(integer);
//        Hashtable<Integer, StudyGroup> group = JsonWorker.collectionFromJson(args);
//        System.out.println(group.get(1));
//        JsonWorker.writeJson(group);

    }
}