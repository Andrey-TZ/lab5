package Utils;

import Exceptions.NotEnoughLinesException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ScriptManager {
    Scanner reader;

    public ScriptManager(FileReader reader) {
        this.reader = new Scanner(reader);
    }

    public Float requestFloat() throws IOException, NumberFormatException {
        String line = reader.nextLine();
        if (line.length() == 0) return null;
        return Float.parseFloat(line);

    }

    public String requestString() throws IOException {
        return reader.nextLine();
    }

    public Date requestDate() {
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        while (true) {
            try {
                if (! reader.hasNextLine()) throw new NotEnoughLinesException ("Не получилось считать дату");
            }
        }
    }
}
