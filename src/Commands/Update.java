package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Model.StudyGroup;
import Utils.CollectionManager;
import Utils.CLIManager;

/**
 * Command to update the element by its key
 */
public class Update extends AbstractCommand {
    public Update() {
        this.description = "обновить значение элемента, id которого равен заданному";
        this.name = "update id";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        if (args.length < 2) throw new NotEnoughArgumentsException("команда требует аргумент \"id\"");
        int id;
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("аргумент должен быть числом! ");
        }

        StudyGroup group = collectionManager.getById(id);
        if (group == null) throw new WrongArgumentException("Элемент с таким id не найден. ");
        CLIManager cliManager = new CLIManager();
        cliManager.requestStudyGroup(group);

        System.out.println("Элемент успешно обновлён");
        collectionManager.addToHistory(this);
    }
}
