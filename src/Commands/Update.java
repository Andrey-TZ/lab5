package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Model.StudyGroup;
import Run.CollectionManager;
import Utils.CLIManager;
import com.beust.jcommander.Parameters;

public class Update extends AbstractCommand {



    public Update() {
        this.description = "обновить значение элемента, id которого равен заданному";
        this.name = "update";
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
        if (group == null) throw  new WrongArgumentException("Элемент с таким id не найден. ");
        CLIManager cliManager = new CLIManager();
        cliManager.requestStudygroup(group);

        System.out.println("Элемент успешно обновлён");
        collectionManager.addToHistory(this);
    }
}
