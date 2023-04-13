package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Utils.CollectionManager;

/**
 * Command to remove all elements from the collection, which has keys less than a given one
 */
public class RemoveLowerKey extends AbstractCommand{
    public RemoveLowerKey(){
        this.name = "remove_lower_key {key}";
        this.description = "удалить элементы, ключ которых меньше, чем заданный";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        if(args.length < 2) throw new NotEnoughArgumentsException("команда требует аргумент \"key\"");
        int key;
        try{
            key = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e){
            throw new WrongArgumentException("аргумент должен быть числом!");
        }
        collectionManager.removeLowerKey(key);
        System.out.print("Элемент с ключом \"");
        System.out.print(key);
        System.out.println("\" успешно удалён");
        collectionManager.addToHistory(this);
    }
}
