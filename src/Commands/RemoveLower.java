package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.NotEnoughLinesException;
import Exceptions.WrongArgumentException;
import Model.StudyGroup;
import Utils.CollectionManager;
import Utils.CLIManager;
import Utils.ScriptManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

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

    @Override
    public void executeFromFile(BufferedReader reader, String[] args, CollectionManager collectionManager) throws NotEnoughLinesException, WrongArgumentException, NotEnoughArgumentsException, IOException {
        if (collectionManager.isEmpty()) {
            System.out.println("Элементов для удаления нет!");
            return;
        }
        ScriptManager manager = new ScriptManager(reader);
        StudyGroup group = new StudyGroup();
        manager.requestStudyGroup(group);
        collectionManager.removeLower(group);
        collectionManager.addToHistory(this);
    }
}
