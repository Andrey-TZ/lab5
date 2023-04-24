package Utils;

import Exceptions.EmptyFieldException;
import Exceptions.WrongFieldException;
import Model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Helps to get values from terminal.
 */
public class CLIManager {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Read Strong from terminal.
     *
     * @return CLI read result. Can be empty string.
     */
    private String requestString() throws NumberFormatException {
        if (!scanner.hasNextLine()) System.exit(0);
        return scanner.nextLine();
    }


    /**
     * Read float from terminal.
     *
     * @return Float number or null, if input is empty.
     * @throws NumberFormatException if input does not float number.
     */
    private Float requestFloat() throws NumberFormatException {
        if (!scanner.hasNextLine()) System.exit(0);
        String number = scanner.nextLine();
        if (number.length() == 0) return null;
        return Float.parseFloat(number);
    }

    /**
     * Read long from terminal.
     *
     * @return Long number or null, if input is empty.
     * @throws NumberFormatException if input does not long number.
     */
    private Long requestLong() throws NumberFormatException {
        if (!scanner.hasNextLine()) System.exit(0);
        String number = scanner.nextLine();
        if (number.length() == 0) return null;
        return Long.parseLong(number);
    }

    /**
     * Read date from terminal.
     *
     * @return Date in format "dd-MM-yyyy"
     */
    private Date requestDate() {
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        while (true) {
            try {
                if (!scanner.hasNextLine()) System.exit(0);
                date = dateFormat.parse(requestString());
                if (date.after(new Date())) {
                    System.out.println("Нельзя ставить дату позже сегодняшней!");
                    System.out.println("Попробуйте снова: ");
                    continue;
                }
                break;
            } catch (ParseException e) {
                System.out.println("Проверьте корректность данных");
                System.out.println("Попробуйте ещё раз: ");
            }
        }
        return date;
    }

    /**
     * Request Semester from terminal. Method will show all the options. Not case-sensitive.
     *
     * @return Semester enum object or null, if input is empty.
     * @throws IllegalArgumentException if input does not match to any option.
     * @see Semester
     */
    private Semester requestSemester() throws IllegalArgumentException {
        for (Semester semester : Semester.values()) {
            System.out.print(semester.toStringWithValue() + " ");
        }
        System.out.println();
        String option = requestString().strip();
        if (option.length() == 0) return null;
        return Semester.valueOf(option.toUpperCase());
    }

    /**
     * Request FormOfEducation from terminal. Method will show all the options. Not case-sensitive.
     *
     * @return FormOfEducation enum object
     * @throws IllegalArgumentException if input does not match to any option.
     * @see FormOfEducation
     */
    private FormOfEducation requestFormOfEducation() throws IllegalArgumentException {
        for (FormOfEducation formOfEducation : FormOfEducation.values()) {
            System.out.print(formOfEducation.toStringWithValue() + " ");
        }
        System.out.println();
        String option = requestString().strip();
        if (option.length() == 0) return null;
        return FormOfEducation.valueOf(option.toUpperCase());
    }

    /**
     * Requests Coordinates from terminal.
     *
     * @return Coordinates object
     * @see Model.Coordinates
     */
    private Coordinates requestCoordinates() {
        Coordinates coordinates = new Coordinates();

        //request X coordinate
        while (true) {
            try {
                System.out.print("Введите координату X: ");
                coordinates.setX(requestFloat());
                break;
            } catch (WrongFieldException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("X координата должна быть числом!");
            }
        }

        //request Y coordinate
        while (true) {
            try {
                System.out.print("Введите координату Y: ");
                coordinates.setY(requestFloat());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Y координата должна быть числом!");
            }
        }
        return coordinates;
    }

    /**
     * Requests adminGroup from terminal.
     *
     * @return Person object
     * @see Model.Person
     */
    public Person requestAdminGroup() {
        Person adminGroup = new Person();

        // request name
        while (true) {
            try {
                System.out.print("Введите имя админа групы: ");
                String name = requestString().toLowerCase();
                if (name.isEmpty()) return null;
                adminGroup.setName(name.substring(0, 1).toUpperCase() + name.substring(1));
                break;
            } catch (EmptyFieldException e) {
                System.out.println(e.getMessage());
            }
        }

        //request birthdate
        while (true) {
            try {
                System.out.print("Введите дату рождения админа в формате ДД-ММ-ГГГГ: ");
                adminGroup.setBirthday(requestDate());
                break;
            } catch (EmptyFieldException e) {
                System.out.println(e.getMessage());
            }
        }

        //request height
        while (true) {
            try {
                System.out.print("Введите рост: ");
                adminGroup.setHeight(requestFloat());
                break;
            } catch (WrongFieldException | EmptyFieldException e) {
                System.out.println(e.getMessage());
            }
        }

        return adminGroup;
    }

    /**
     * Get StudyGroup by fields from CLI
     * Method will ask each field. If input value is incorrect method will ask to enter it again.
     *
     * @param studyGroup object to add fields
     */

    public void requestStudyGroup(StudyGroup studyGroup) {
        //request coordinates
        studyGroup.setCoordinates(requestCoordinates());

        //request name
        while (true) {
            try {
                System.out.print("Введите название группы: ");
                studyGroup.setName(requestString());
                break;
            } catch (EmptyFieldException e) {
                System.out.println(e.getMessage());
            }
        }

        // request studentsCount
        while (true) {
            try {
                System.out.print("Введите количество студентов: ");
                studyGroup.setStudentsCount(requestLong());
                break;
            } catch (WrongFieldException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Количество студентов должно быть числом");
            }
        }

        //request formOfEducation
        while (true) {
            try {
                System.out.print("Выберите форму обучения: ");
                studyGroup.setFormOfEducation(requestFormOfEducation());
                break;
            } catch (EmptyFieldException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Форма обучения должна быть одной из предложенных");
            }
        }

        //request semester
        while (true) {
            try {
                System.out.print("Выберите семестр: ");
                studyGroup.setSemesterEnum(requestSemester());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Семестр должен быть одним из предложенных");
            }
        }
        // request groupAdmin can be null
        studyGroup.setGroupAdmin(requestAdminGroup());


    }

}
