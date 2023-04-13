package Commands;

import Utils.CollectionManager;

/**
 * Command to exit the program
 */
public class Exit extends AbstractCommand{
    public Exit(){
        this.description = "завершить программу (без сохранения в файл)";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) {
        System.out.println("Программа будет закрыта");
        System.exit(0);
    }
}
