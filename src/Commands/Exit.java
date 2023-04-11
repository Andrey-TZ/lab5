package Commands;

import Run.CollectionManager;

public class Exit extends AbstractCommand{
    public Exit(){
        this.description = "завершить программу (без сохранения в файл)";
    }

    /**
     * @param args
     */
    @Override
    public void execute(String[] args, CollectionManager collectionManager) {
        System.out.println("Программа будет закрыта");
        System.exit(0);
    }
}
