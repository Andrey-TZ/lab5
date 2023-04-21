package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Utils.CollectionManager;

/**
 * Command to insert element to collection with given key
 */
public class Insert extends AbstractCommand{
    public Insert(){
        this.name = "insert key";
        this.description = "добавить новый элемент с заданным ключом";
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
        collectionManager.insert(key);
        System.out.println("Элемент успешно добавлен");
        collectionManager.addToHistory(this);
    }
}
