package Commands;

import Model.StudyGroup;

import java.util.Hashtable;
public abstract class AbstractCommand {
    protected String name;
    protected String help;

    public abstract void execute(String[] args);

    public String getName() {
        return name;
    }

    public String getHelp(){
        return help;
    }
}

