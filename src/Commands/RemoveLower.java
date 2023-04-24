package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Model.StudyGroup;
import Utils.CollectionManager;
import Utils.CLIManager;

/**
 * Command to remove all elements from the collection witch less than a given one
 */
public class RemoveLower extends AbstractCommand {
    public RemoveLower() {
        this.name = "remove_lower {element}";
        this.description = "удаляет элементы коллекции меньшие чем введённый";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        if (collectionManager.isEmpty()) {
            System.out.println("Элементов для удаления нет!");
            return;
            }
        CLIManager cliManager = new CLIManager();
        StudyGroup group = new StudyGroup();
        cliManager.requestStudyGroup(group);
        collectionManager.removeLower(group);
        collectionManager.addToHistory(this);
    }
}
