package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Model.StudyGroup;
import Run.CollectionManager;
import Utils.CLIManager;

import java.util.Iterator;

public class RemoveLower extends AbstractCommand {
    public RemoveLower() {
        this.name = "remove_lower {element}";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        if (collectionManager.isEmpty()) {
            System.out.println("Элементов для удаления нет!");
            return;
            }
        CLIManager cliManager = new CLIManager();
        StudyGroup group = new StudyGroup(collectionManager.getMaxId());
        cliManager.requestStudygroup(group);
        collectionManager.removeLower(group);
        collectionManager.addToHistory(this);
    }
}
