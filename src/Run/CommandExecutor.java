package Run;

import Commands.AbstractCommand;
import Commands.Help;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 */
public class CommandExecutor {
   private final CollectionManager collectionManager;
   private final HashMap<String, AbstractCommand> commands;
   private final JCommander jc = new JCommander();
   public CommandExecutor(CollectionManager collectionManager){
      this.collectionManager = collectionManager;
      this.commands = new HashMap<>();
      AbstractCommand help = new Help(this.collectionManager);
      jc.addCommand(help);
      commands.put("help", new Help(this.collectionManager));


   }

   public void inputMode(){
      Scanner commandReader = new Scanner(System.in);
      while (true){
         System.out.println("Enter a command");
         String[] arguments = commandReader.nextLine().trim().toLowerCase().split(" ");
         try{
         jc.parse(arguments);
         String name = jc.getParsedCommand().trim();
            System.out.println(name);
         commands.get(name).execute();}
         catch (MissingCommandException e){
            System.out.println("Ну удалось обнаружить команду: "+ arguments[0].trim());
         }

      }
   }
}
