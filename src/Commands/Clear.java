package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Utils.CollectionManager;


/**
 * Command which removes all elements of the collection
 */
public class Clear extends AbstractCommand{
    public Clear(){
        this.name = "clear";
        this.description = "очистить коллекцию";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        collectionManager.clear();
        collectionManager.addToHistory(this);
    }
}
