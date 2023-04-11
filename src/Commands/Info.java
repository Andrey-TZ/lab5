package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Run.CollectionManager;

public class Info extends AbstractCommand {
    public Info() {
        this.name = "info";
        this.description = "вывести информацию о коллекции";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        System.out.println(collectionManager.info());
        collectionManager.addToHistory(this);
    }
}
