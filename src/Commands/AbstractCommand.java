package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Utils.CollectionManager;

/**
 * Abstract class for all commands.
 * Implementations - all command classes
 * @author Andrey Vorotnikov
 */

public abstract class AbstractCommand {
    protected String name;
    protected String description;

    /**
     *  Method to execute command
     * @param args command line arguments
     * @param collectionManager object working with collection
     * @throws NotEnoughArgumentsException  when argument's count does not match requirements.
     * @throws WrongArgumentException when argument doesn't match requirements.
     */
    public abstract void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException;

    /**
     *
     * @return name of the command
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return description of the comand
     */
    public String getDescription() {
        return description;
    }
}

