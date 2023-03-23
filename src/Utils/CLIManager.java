package Utils;

import Exceptions.WrongFieldException;
import Model.Coordinates;
import Model.FormOfEducation;
import Model.Semester;

import java.util.Scanner;

public class CLIManager {
    private static Scanner in;

    private String requestString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Integer requestInt() {
        Scanner scanner = new Scanner(System.in);
        String integer = scanner.nextLine();
        if (integer.length() == 0) return null;
        return Integer.parseInt(integer);
    }

    private Float requestFloat() {
        Scanner scanner = new Scanner((System.in));
        String number = scanner.nextLine();
        if (number.length() == 0) return null;
        return Float.parseFloat(number);
    }

    private Long requestLong() {
        Scanner scanner = new Scanner((System.in));
        String number = scanner.nextLine();
        if (number.length() == 0) return null;
        return Long.parseLong(number);
    }

    private Semester requestSemester() {
        for (Semester semester : Semester.values()) {
            System.out.print(semester + " ");
        }
        System.out.println();
        String option = requestString().strip();
        if (option.length() == 0) return null;
        return Semester.valueOf(option.toUpperCase());
    }

    private FormOfEducation requestFormOfEducation() {
        for (FormOfEducation formOfEducation : FormOfEducation.values()) {
            System.out.print(formOfEducation + " ");
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
                System.out.println("Введите координату X");
                coordinates.setX(requestFloat());
                break;
            } catch (WrongFieldException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("X координата должна быть числом");
            }
        }

        while (true) {
            try {
                System.out.println("Введите координату Y");
                coordinates.setY(requestFloat());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Y координата должна быть числом");
            }
        }
        return coordinates;
    }

}
