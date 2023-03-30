package Commands;

import Run.CollectionManager;
import com.beust.jcommander.Parameters;

@Parameters(commandNames = {"help"}, commandDescription = "Возвращает информацию о всех командах")
public class Help extends AbstractCommand{
    private final CollectionManager collectionManager;
    public Help(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        name = "Помощь";
        help = "Возвращает информацию о всех командах";
    }

    /**
     * @param args
     */
    @Override
    public void execute() {
        System.out.println("It's a help command");
    }
}
