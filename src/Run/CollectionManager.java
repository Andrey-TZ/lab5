package Run;

import Commands.AbstractCommand;
import Exceptions.WrongArgumentException;
import JsonParsing.JsonParser;
import Model.Person;
import Model.StudyGroup;
import Utils.CLIManager;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Класс, работающий с коллекцией
 */
public class CollectionManager {
    private final String[] commandsHistory;
    private ZonedDateTime creationDate;
    private int historyIndex = 0;
    private final Hashtable<Integer, StudyGroup> groups;

    public CollectionManager(Hashtable<Integer, StudyGroup> groups) {
        this.groups = groups;
        this.commandsHistory = new String[15];
        this.creationDate = ZonedDateTime.now();
    }

    public String info() {
        String result = "Информация о коллекции:\n";
        result += "Инициализировано: " + this.creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        result += "\nТип коллекции: " + this.groups.getClass().getName();
        result += "\nКоличество элементов: " + groups.size();
        return result;
    }

    public boolean isEmpty() {
        return groups.isEmpty();
    }

    public StudyGroup getById(int id) {
        for (StudyGroup group : groups.values()) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }

    public void show() {
        for (StudyGroup group : groups.values()) {
            System.out.println(group);
        }
    }

    public int getMaxId() {
        int max = -1;
        for (StudyGroup group : groups.values()) {
            max = Integer.max(max, group.getId());
        }
        return max;
    }

    public void insert(Integer key) throws WrongArgumentException {
        if (isKeyExist(key)) throw new WrongArgumentException("Элемент с таким ключом уже существует!");
        StudyGroup group = new StudyGroup(getMaxId());
        CLIManager cliManager = new CLIManager();
        cliManager.requestStudygroup(group);
        groups.put(key, group);

    }

    public boolean isKeyExist(Integer key) {
        return groups.containsKey(key);
    }

    public void removeByKey(Integer key) {
        if (groups.containsKey(key)) {
            groups.remove(key);
        } else System.out.println("Элемент с таким ключом не найден!");
    }

    public void clear() {
        groups.clear();
        System.out.println("Коллекция успешно очищена!");
    }

    public void save(JsonParser parser) {
        parser.writeJson(groups);
        System.out.println("Коллекция успешно сохранена!");
    }

    public void executeScript(String file_name) {
    }

    public void removeLower(StudyGroup group) {
        int counter = 0;

        for (Integer key : groups.keySet()) {
            if (groups.get(key).compareTo(group) < 0) {
                groups.remove(key);
                counter += 1;
            }
        }
        System.out.println("Удалено элементов: " + counter);

    }


    public void removeLowerKey(Integer key) {
        int deleted = 0;
        for (Integer keys : groups.keySet()) {
            if (keys < key) {
                groups.remove(keys);
                deleted++;
            }
        }
        if (deleted > 0) System.out.println("Успешно удалено элементов: " + deleted);
        else System.out.println("Не удалось найти ни одного элемента по таким параметрам");
    }

    public Set<StudyGroup> filterStartsWithName(String name) {
        Set<StudyGroup> groupStartsWithName = new LinkedHashSet<>();
        for (StudyGroup group : groups.values()) {
            if (group.getName().startsWith(name)){
                groupStartsWithName.add(group);
            }
        }
        return groupStartsWithName;
    }

    public Set<StudyGroup> filterLessThanGroupAdmin(Person groupAdmin) {
        Set<StudyGroup> groupsWithLessAdmin = new LinkedHashSet<>();
        if (groupAdmin == null) return null;
        for(StudyGroup group: groups.values()){
            if(group.getGroupAdmin() == null) groupsWithLessAdmin.add(group);
            else if (group.getGroupAdmin().compareTo(groupAdmin) < 0){
                groupsWithLessAdmin.add(group);
            }
        }
        return groupsWithLessAdmin;
    }

    public Set<Long> getUniqueStudentsCount() {
        Set<Long> uniq = new LinkedHashSet<>();
        for (StudyGroup group : groups.values()) {
            uniq.add(group.getStudentsCount());
        }
        return uniq;
    }

    public void addToHistory(AbstractCommand command) {
        if (historyIndex < 15) {
            commandsHistory[historyIndex++] = command.getName();
        } else {
            for (int i = 0; i < 14; i++) {
                commandsHistory[i] = commandsHistory[i + 1];
            }
            commandsHistory[14] = command.getName();
        }
    }

    public String[] getHistory() {
        return commandsHistory;
    }

}
