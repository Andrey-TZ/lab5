package Commands;

import Exceptions.NotEnoughArgumentsException;
import Exceptions.WrongArgumentException;
import Utils.CollectionManager;

import java.util.Set;

/**
 * Command to display all unique values of students_count param
 */

public class PrintUniqueStudentsCount extends AbstractCommand{
    public PrintUniqueStudentsCount(){
        this.name = "print_unique_students_count";
        this.description = "вывести уникальные значения \"students count\"";
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws NotEnoughArgumentsException, WrongArgumentException {
        Set<Long> uniqueCounts = collectionManager.getUniqueStudentsCount();
        System.out.println("Все уникальные значения \"students count\": ");
        int index = 0;
        for (Long count: uniqueCounts){
            System.out.print(count);
            if(index++ < uniqueCounts.size()){
                System.out.print(",");
            }
            System.out.print(" ");
        }
        System.out.println();
    }
}
