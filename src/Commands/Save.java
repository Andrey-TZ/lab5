package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import JsonParsing.JsonParser;
import Run.CollectionManager;

public class Save extends AbstractCommand{

    public Save(){
        this.description = "сохранить коллекцию в файл";
        this.name = "save";
    }

    /**
     * @param args
     * @param collectionManager
     * @throws NotEnoughArgumentsException
     * @throws WrongArgumentException
     */
    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        collectionManager.save(new JsonParser());
        collectionManager.addToHistory(this);
    }
}
