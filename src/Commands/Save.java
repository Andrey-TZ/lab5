package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import JsonParsing.JsonParser;
import Utils.CollectionManager;

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
        collectionManager.save(new JsonParser());
        collectionManager.addToHistory(this);
    }
}
