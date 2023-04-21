package Commands;

import Utils.CollectionManager;

import java.util.HashMap;

/**
 * Command to display information about all available commands
 */
public class Help extends AbstractCommand {
    private final HashMap<String, AbstractCommand> commands;

    public Help(HashMap<String, AbstractCommand> commands) {
        this.commands = commands;
        this.description = "вывести справку по доступным командам";
        this.name = "help";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) {
        for (String command : this.commands.keySet()) {
            System.out.printf("%-41s - %s\n", commands.get(command).getName(), this.commands.get(command).getDescription());
        }
        collectionManager.addToHistory(this);
    }

}
