package Commands;

import Run.CollectionManager;
import com.beust.jcommander.Parameters;

import java.util.HashMap;

@Parameters(commandNames = {"help"}, commandDescription = "Возвращает информацию о всех командах")
public class Help extends AbstractCommand{
    private final HashMap<String, AbstractCommand> commands;
    public Help(HashMap<String, AbstractCommand> commands){
        this.commands = commands;
        this.description = "вывести справку по доступным командам";
        this.name = "help";
    }

    /**
     * @param args
     */
    @Override
    public void execute(String[] args, CollectionManager collectionManager) {
        for (String command : this.commands.keySet()){
            System.out.printf("%-35s - %s\n", command, this.commands.get(command).getDescription());
        }
        collectionManager.addToHistory(this);
    }

}
