package Utils;

import Exceptions.EmptyFieldException;
import Exceptions.NotEnoughLinesException;
import Exceptions.WrongFieldException;
import Model.*;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
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

    public Long requestLong() throws IOException, NumberFormatException {
        String line = reader.nextLine();
        if (line.length() == 0) return null;
        return Long.parseLong(line);
    }

    public String requestString() throws IOException {
        return reader.nextLine();
    }

    public Date requestDate() throws NotEnoughLinesException {
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        while (true) {
            try {
                if (!reader.hasNextLine()) throw new NotEnoughLinesException("Не хватает строки со значением даты!");
                date = dateFormat.parse((requestString()));
                return date;
            } catch (ParseException e) {
                continue;
            } catch (IOException e) {
                throw new NotEnoughLinesException("Не хватает строки со значением даты!");
            }
        }
    }

    private Semester requestSemester() {
        try {
            String option = requestString().strip().toLowerCase();
            if (option.length() == 0) return null;
            return Semester.valueOf(option.toUpperCase());
        } catch (IOException | IllegalArgumentException e) {
            return null;
        }

    }

    private FormOfEducation requestFormOfEducation() throws NotEnoughLinesException {
        while (true) {
            try {
                String option = requestString().strip().toLowerCase();
                if (option.length() == 0) continue;
                return FormOfEducation.valueOf(option.toUpperCase());
            } catch (IOException e) {
                throw new NotEnoughLinesException("Не хватает строки со значением формы обучения!");
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
    }

    private Coordinates requestCoordinates() throws NotEnoughLinesException {
        Coordinates coordinates = new Coordinates();

        while (true) {
            try {
                coordinates.setX(requestFloat());
                break;
            } catch (WrongFieldException | NumberFormatException e) {
                continue;
            } catch (IOException e) {
                throw new NotEnoughLinesException("Не хватает строки со значением Y координаты");
            }
        }

        while (true) {
            try {
                coordinates.setY(requestFloat());
                break;
            } catch (NumberFormatException e) {
                continue;
            } catch (IOException e) {
                coordinates.setY(0);
            }
        }
        return coordinates;
    }

    public Person requestAdminGroup() throws NotEnoughLinesException {
        Person adminGroup = new Person();

        try {
            String name = requestString().toLowerCase();
            if (name.isEmpty()) return null;
            adminGroup.setName(name.substring(0, 1).toUpperCase() + name.substring(1));

        } catch (EmptyFieldException | IOException e) {
            return null;
        }

        while (true) {
            try {
                adminGroup.setBirthday(requestDate());
                break;
            } catch (EmptyFieldException e) {
                continue;
            }
        }

        while (true) {
            try {
                adminGroup.setHeight(requestFloat());
                break;
            } catch (WrongFieldException | EmptyFieldException e) {
                continue;
            } catch (IOException e) {
                throw new NotEnoughLinesException("Не хватает строки со значением роста админа группы!");
            }
        }
        return adminGroup;
    }

    public StudyGroup requestStudyGroup(StudyGroup studyGroup) throws NotEnoughLinesException {
        studyGroup.setCoordinates(requestCoordinates());

        while (true) {
            try {
                studyGroup.setName(requestString());
                break;
            } catch (EmptyFieldException e) {
                continue;
            } catch (IOException e) {
                throw new NotEnoughLinesException("Не хватает строки с названием группы!");
            }
        }

        while (true) {
            try {
                studyGroup.setStudentsCount(requestLong());
                break;
            } catch (WrongFieldException | NumberFormatException e) {
                continue;
            } catch (IOException e) {
                throw new NotEnoughLinesException("Не хватает строки с количеством студентов в группе!");
            }
        }
        while (true) {
            try {
                studyGroup.setFormOfEducation(requestFormOfEducation());
                break;
            } catch (EmptyFieldException e) {
                continue;
            }
        }

        studyGroup.setSemesterEnum(requestSemester());
        studyGroup.setGroupAdmin(requestAdminGroup());

        return studyGroup;
    }
}
