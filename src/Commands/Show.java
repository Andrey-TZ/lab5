package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Utils.CollectionManager;

/**
 * Command to display all elements from the collection
 */
public class Show extends AbstractCommand{
    public Show(){
        this.name = "show";
        this.description = "вывести все элементы коллекции";
    }
    /**
     * @param args
     * @param collectionManager
     * @throws NotEnoughArgumentsException
     * @throws WrongArgumentException
     */
    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        collectionManager.show();
        collectionManager.addToHistory(this);
    }
}
