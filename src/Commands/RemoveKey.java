package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.NotEnoughLinesException;
import Exceptions.WrongArgumentException;
import Utils.CollectionManager;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Command to delete element from collection by given key
 */
public class RemoveKey extends AbstractCommand {
    public RemoveKey() {
        this.name = "remove_key key";
        this.description = "удалить элемент коллекции по ключу";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        if (args.length < 2) throw new NotEnoughArgumentsException("команда требует аргумент \"key\"");
        int key;
        try {
            key = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("аргумент должен быть числом!");
        }
        collectionManager.removeByKey(key);
        System.out.println("Элемент с ключом \"" + key + "\" успешно удалён");
        collectionManager.addToHistory(this);
    }

    @Override
    public void executeFromFile(BufferedReader reader, String[] args, CollectionManager collectionManager) throws NotEnoughLinesException, WrongArgumentException, NotEnoughArgumentsException {
        execute(args, collectionManager);
    }
}
