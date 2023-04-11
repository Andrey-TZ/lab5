package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Run.CollectionManager;

public class Clear extends AbstractCommand{
    public Clear(){
        this.name = "clear";
        this.description = "очистить коллекцию";
    }

    /**
     * @param args
     * @param collectionManager
     * @throws NotEnoughArgumentsException
     * @throws WrongArgumentException
     */
    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        collectionManager.clear();
        collectionManager.addToHistory(this);
    }
}
