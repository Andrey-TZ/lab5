package Run;

import Model.StudyGroup;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Класс, работающий с коллекцией
 */
public class CollectionManager {
    private ArrayList<String> commandsHistory;
    private final Hashtable<Integer, StudyGroup> groups;
    public CollectionManager(Hashtable<Integer, StudyGroup> groups){
        this.groups = groups;
    }
}
