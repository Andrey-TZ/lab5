package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Model.Person;
import Model.StudyGroup;
import Utils.CollectionManager;
import Utils.CLIManager;

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
        if (collectionManager.isEmpty()) {
            System.out.println("Нет элементов для сравнения");
            return;
        }
        Person groupAdmin = cliManager.requestAdminGroup();

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
