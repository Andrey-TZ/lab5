package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import JsonParsing.JsonParser;
import Run.CollectionManager;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ExecuteScript extends AbstractCommand{
    HashMap<String, AbstractCommand> commands;
    public ExecuteScript(HashMap<String, AbstractCommand> commands){
        this.commands = commands;
        this.name = "execute_script {file_name}";
        this.description = "исполнить скрипт из указанного файла";
    }
    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        JsonParser parser = new JsonParser();
        if (args.length < 2) throw new NotEnoughArgumentsException("команда требует аргумент \"file_name\"");

        try {
            File file = new File(args[1]);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] command = line.split("\\s+");
                try {
                    commands.get(command[0]).execute(command, collectionManager);

                }
                catch (NullPointerException e){
                    System.out.println("Не удалось найти команду " + command[0]);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        collectionManager.addToHistory(this);

    }

}
