package Commands;

import Exceptions.EmptyFieldException;
import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Exceptions.WrongFieldException;
import JsonParsing.JsonParser;
import Model.Person;
import Utils.CollectionManager;
import Utils.ScriptManager;

import java.io.*;
import java.util.HashMap;

/**
 * Execute all commands from the file
 */
public class ExecuteScript extends AbstractCommand {
    HashMap<String, AbstractCommand> commands;

    public ExecuteScript(HashMap<String, AbstractCommand> commands) {
        this.commands = commands;
        this.name = "execute_script file_name";
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
                switch (commands.get(command[0]).name){
                    case "filter_less_than_group_admin {groupAdmin}":{
                        Person groupAdmin = new Person();
                        ScriptManager manager = new ScriptManager(reader);
                        try {
                            groupAdmin.setName(manager.requestString());
                            groupAdmin.setBirthday(reader.readLine());
                            groupAdmin.setHeight(Integer.parseInt(reader.readLine()));
                        }
                        catch(NullPointerException e){
                            System.out.println("В файле не хватило данных для заполнения groupAdmin");
                        } catch (WrongFieldException | EmptyFieldException e) {
                            System.out.println("В файле находились ");;
                        }
                    }
                }

                try {
                    commands.get(command[0]).execute(command, collectionManager);

                } catch (NullPointerException e) {
                    System.out.println("Не удалось найти команду " + command[0]);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Такой файл не найден");
        } catch (IOException e) {
            System.out.println("Не получилось прочитать файл, попробуйте с другим файлом");
        }
        collectionManager.addToHistory(this);

    }

}
