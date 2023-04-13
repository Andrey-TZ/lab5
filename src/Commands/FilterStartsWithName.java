package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Model.StudyGroup;
import Utils.CollectionManager;

import java.util.Set;

/**
 * Command to display all elements whose "name" value starts from the given one
 */
public class FilterStartsWithName extends AbstractCommand{
    public FilterStartsWithName(){
        this.name = "filter_starts_with_name {name}";
        this.description = "вывести элементы, значения \"name\" которых начинается с заданной подстроки";
    }
    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        if(args.length < 2) throw new NotEnoughArgumentsException("команда требует аргумент \"name\"");
        String name = args[1];
        Set<StudyGroup> groups = collectionManager.filterStartsWithName(name);
        for(StudyGroup group : groups){
            System.out.println(group);
        }
        collectionManager.addToHistory(this);
    }
}
