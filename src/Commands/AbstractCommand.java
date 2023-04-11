package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Model.StudyGroup;
import Run.CollectionManager;

import java.util.Hashtable;
public abstract class AbstractCommand {
    protected String name;
    protected String description;

    public abstract void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException;

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }
}

