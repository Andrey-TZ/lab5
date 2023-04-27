package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.NotEnoughLinesException;
import Exceptions.WrongArgumentException;
import Model.Person;
import Model.StudyGroup;
import Utils.CollectionManager;
import Utils.CLIManager;
import Utils.ScriptManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * Command to display all elements whose "groupAdmin" value is less than the given one
 */
public class FilterLessThanGroupAdmin extends AbstractCommand {
    public FilterLessThanGroupAdmin() {
        this.name = "filter_less_than_group_admin {groupAdmin}";
        this.description = "вывести все элементы, значение \"groupAdmin\" которых меньше заданного";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        CLIManager cliManager = new CLIManager();
        Person groupAdmin = cliManager.requestAdminGroup();

        execute(collectionManager, groupAdmin);
    }

    @Override
    public void executeFromFile(BufferedReader reader, String[] args, CollectionManager collectionManager) throws NotEnoughLinesException, WrongArgumentException, NotEnoughArgumentsException, IOException {
        ScriptManager manager = new ScriptManager(reader);
        Person groupAdmin = manager.requestAdminGroup();

        execute(collectionManager, groupAdmin);
    }

    private void execute(CollectionManager collectionManager, Person groupAdmin) {
        if (collectionManager.isEmpty()) {
            System.out.println("Нет элементов для сравнения");
            return;
        }
        Set<StudyGroup> groups = collectionManager.filterLessThanGroupAdmin(groupAdmin);
        if (groups == null) {
            System.out.println("Элементы с заданным фильтром не найдены");
            return;
        }
        System.out.println("Найдены группы:");
        for (StudyGroup group : groups) {
            System.out.println(group);
        }
        collectionManager.addToHistory(this);
    }
}
