package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.NotEnoughLinesException;
import Exceptions.WrongArgumentException;
import JsonParsing.JsonParser;
import Utils.CollectionManager;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Command to save collection to the same file
 */
public class Save extends AbstractCommand{

    public Save(){
        this.description = "сохранить коллекцию в файл";
        this.name = "save";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        collectionManager.save();
        collectionManager.addToHistory(this);
    }

    @Override
    public void executeFromFile(BufferedReader reader, String[] args, CollectionManager collectionManager) throws NotEnoughLinesException, WrongArgumentException, NotEnoughArgumentsException {
        execute(args, collectionManager);
    }
}
