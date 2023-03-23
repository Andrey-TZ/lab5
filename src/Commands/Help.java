package Commands;

import Run.CollectionManager;

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
    public void execute(String[] args) {

    }
}
