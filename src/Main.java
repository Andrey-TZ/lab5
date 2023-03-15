import java.time.LocalDateTime;
import java.util.Hashtable;

import Model.StudyGroup;
import Utils.JsonWorker;

public class Main {
    public static void main(String[] args){
        System.out.println(LocalDateTime.now().toString());
        Hashtable<Integer, StudyGroup> group = JsonWorker.collectionFromJson();
        System.out.println(group.get(1));
        JsonWorker.writeJson(group);
    }
}