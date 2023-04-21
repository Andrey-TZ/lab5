package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Utils.CollectionManager;

/**
 * Command to display the last 15 used commands
 */
public class History extends AbstractCommand {
    public History() {
        this.name = "history";
        this.description = "вывести последние 15 команд без их аргументов";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        String[] history = collectionManager.getHistory();
        System.out.print("Последние команды: ");
        for (String command : history) {
            if (command != null) {
                System.out.print(command + "  ");
            }
        }
        System.out.println();
        collectionManager.addToHistory(this);
    }
}
