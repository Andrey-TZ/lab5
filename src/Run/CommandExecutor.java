package Run;

import Commands.AbstractCommand;
import Commands.Help;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 */
public class CommandExecutor {
   private final CollectionManager collectionManager;
   private final HashMap<String, AbstractCommand> commands;
   public CommandExecutor(CollectionManager collectionManager){
      this.collectionManager = collectionManager;
      this.commands = new HashMap<>();

      commands.put("help", new Help(this.collectionManager));


   }

   public void inputMode(){
      Scanner commandReader = new Scanner(System.in);

      while (true){
         System.out.println("Enter a command");
      }
   }
}
