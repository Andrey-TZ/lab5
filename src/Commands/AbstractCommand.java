package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.NotEnoughLinesException;
import Exceptions.WrongArgumentException;
import Utils.CollectionManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
     * Method to execute command from file
     * @param reader file reader
     * @param args arguments from file
     * @param collectionManager object working with collection
     * @throws NotEnoughLinesException
     * @throws WrongArgumentException
     * @throws NotEnoughArgumentsException
     */

    public abstract void executeFromFile(BufferedReader reader, String[] args, CollectionManager collectionManager) throws NotEnoughLinesException, WrongArgumentException, NotEnoughArgumentsException, IOException;

    /**
     *
     * @return name of the command
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return description of the command
     */
    public String getDescription() {
        return description;
    }
}

