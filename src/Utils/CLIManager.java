package Utils;

import Exceptions.EmptyFieldException;
import Exceptions.WrongFieldException;
import Model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CLIManager {
    private static Scanner in;

    private String requestString() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private Integer requestInt() {
        Scanner scanner = new Scanner(System.in);
        String integer = scanner.nextLine();
        if (integer.length() == 0) return null;
        return Integer.parseInt(integer);
    }

    private Float requestFloat() throws NumberFormatException {
        Scanner scanner = new Scanner((System.in));
        String number = scanner.nextLine();
        if (number.length() == 0) return null;
        return Float.parseFloat(number);
    }

    private Long requestLong() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        if (number.length() == 0) return null;
        return Long.parseLong(number);
    }

    private Date requestDate() {
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        while (true) {
            try {
                date = dateFormat.parse(requestString());
                if (date.after(new Date())){
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

    private Semester requestSemester() throws IllegalArgumentException {
        for (Semester semester : Semester.values()) {
            System.out.print(semester.toStringWithValue() + " ");
        }
        System.out.println();
        String option = requestString().strip();
        if (option.length() == 0) return null;
        return Semester.valueOf(option.toUpperCase());
    }

    private FormOfEducation requestFormOfEducation() throws IllegalArgumentException {
        for (FormOfEducation formOfEducation : FormOfEducation.values()) {
            System.out.print(formOfEducation.toStringWithValue() + " ");
        }
        System.out.println();
        String option = requestString().strip();
        if (option.length() == 0) return null;
        return FormOfEducation.valueOf(option.toUpperCase());
    }

    private Coordinates requestCoordinates() {
        Coordinates coordinates = new Coordinates();
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

    public Person requestAdminGroup() {
        Person adminGroup = new Person();

        while (true) {
            try {
                System.out.print("Введите имя админа групы: ");
                String name = requestString().toLowerCase();
                if (name.isEmpty()) return null;
                adminGroup.setName(name.substring(0,1).toUpperCase() + name.substring(1));
                break;
            } catch (EmptyFieldException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Введите дату рождения админа в формате ДД-ММ-ГГГГ: ");
                adminGroup.setBirthday(requestDate());
                break;
            } catch (EmptyFieldException e) {
                System.out.println(e.getMessage());
            }
        }
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

    public StudyGroup requestStudygroup(StudyGroup studyGroup) {
        studyGroup.setCoordinates(requestCoordinates());
        while (true) {
            try {
                System.out.print("Введите название группы: ");
                studyGroup.setName(requestString());
                break;
            } catch (EmptyFieldException e) {
                System.out.println(e.getMessage());
            }
        }
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

        while (true) {
            try {
                System.out.print("Выберите семестр: ");
                studyGroup.setSemesterEnum(requestSemester());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Семестр должен быть одним из предложенных");
            }
        }

        studyGroup.setGroupAdmin(requestAdminGroup());

        return studyGroup;
    }

}
