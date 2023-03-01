package Commands;

import Model.StudyGroup;

import java.util.Hashtable;
public abstract class AbstractCommand {
    private String name;
    private String help;

    public abstract void execute(String args[], Hashtable<Integer, StudyGroup> hashSet, CommandsManager);

    public String getName() {
        return name;
    }

    public String getHelp(){
        return help;
    }
}

