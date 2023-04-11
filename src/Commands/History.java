package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Run.CollectionManager;

public class History extends AbstractCommand {
    public History() {
        this.name = "history";
        this.description = "вывести последние 15 команд без их аргументов";
    }

    /**
     * @param args
     * @param collectionManager
     * @throws NotEnoughArgumentsException
     * @throws WrongArgumentException
     */
    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        String[] history = collectionManager.getHistory();
        System.out.print("Последние команды: ");
        for (String command : history) {
            if (command != null) {
                System.out.print(command + ", ");
            }
        }
        System.out.println();
        collectionManager.addToHistory(this);
    }
}
