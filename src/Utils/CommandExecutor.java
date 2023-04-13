package Utils;

import Commands.*;
import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;


import java.util.HashMap;
import java.util.Scanner;

/**
 * Class for interactive CLI mode
 */
public class CommandExecutor {
   private final CollectionManager collectionManager;
   private final HashMap<String, AbstractCommand> commands;

   /**
    * Constructor for command executor
    * @param collectionManager CollectionManager class object
    */

   public CommandExecutor(CollectionManager collectionManager){
      this.collectionManager = collectionManager;
      this.commands = new HashMap<>();

      commands.put("clear", new Clear());
      commands.put("execute_script", new ExecuteScript(commands));
      commands.put("exit", new Exit());
      commands.put("filter_less_than_group_admin", new FilterLessThanGroupAdmin());
      commands.put("filter_starts_with_name", new FilterStartsWithName());
      commands.put("help", new Help(commands));
      commands.put("history", new History());
      commands.put("info", new Info());
      commands.put("insert", new Insert());
      commands.put("print_unique_students_count", new PrintUniqueStudentsCount());
      commands.put("remove_key", new RemoveKey());
      commands.put("remove_lower", new RemoveLower());
      commands.put("remove_lower_key", new RemoveLowerKey());
      commands.put("save", new Save());
      commands.put("show", new Show());
      commands.put("update", new Update());
   }

   /**
    * Enter an interactive mode with CLI commands execution
    */
   public void inputMode(){
      Scanner commandReader = new Scanner(System.in);
      while (true){
         System.out.print("Введите команду: ");
         String[] arguments = commandReader.nextLine().trim().toLowerCase().split("\\s+");
         try{
            String command = arguments[0].trim();
            commands.get(command).execute(arguments, collectionManager);}
        catch (NullPointerException e){
            System.out.println("Не удалось обнаружить команду: "+ arguments[0].trim());
         }
         catch(WrongArgumentException e){
            System.out.println("Введён неподходящий аргумент! " + e.getMessage() + "Попробуйте еще раз!");
         }
         catch (NotEnoughArgumentsException e){
            System.out.println("Недостаточно аргументов. " + e.getMessage() + "Попробуйте еще раз!");
         }

      }
   }
}
