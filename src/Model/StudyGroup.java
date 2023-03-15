package Model;

import Exceptions.WrongFieldException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Objects;

public class StudyGroup {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    public static int idSetter = 1;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum = null; //Поле может быть null
    private Person groupAdmin = null; //Поле может быть null

    public StudyGroup(String name, Coordinates coordinates, long studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) throws WrongFieldException {
        setId(idSetter++);
        setCoordinates(coordinates);
//        this.creationDate = LocalDateTime.now();
        setStudentsCount(studentsCount);
        setFormOfEducation(formOfEducation);
        setSemesterEnum(semesterEnum);
        setGroupAdmin(groupAdmin);

    }


    public StudyGroup(int id, String name, Coordinates coordinates, LocalDateTime creationDate, long studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) throws WrongFieldException {
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        this.creationDate = creationDate;
        setStudentsCount(studentsCount);
        setFormOfEducation(formOfEducation);
        setSemesterEnum(semesterEnum);
        setGroupAdmin(groupAdmin);
        //idSetter = id;
    }

    public void setId(int id) throws WrongFieldException {
        if (id < 1){
            throw  new WrongFieldException("id должно быть больше 0");
        }
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NullPointerException {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("Имя не может быть пустой строкой");
        } else {
            this.name = name;
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws NullPointerException {
        if (coordinates == null) throw new NullPointerException("Координаты не могут быть пустыми");
        else this.coordinates = coordinates;
    }

//    public java.time.LocalDateTime getCreationDate() {
//        return creationDate;
//    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(long studentsCount) throws WrongFieldException {
        if (studentsCount < 1) throw new WrongFieldException("Количество студентов должно быть больше 0 ");
        else this.studentsCount = studentsCount;

    }

    public String getCreationDate(){
        return creationDate.toString();
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        if (formOfEducation == null) throw new NullPointerException();
        else this.formOfEducation = formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    @Override
    public String toString() {
        return "Студенческая группа c " +
                "id = " + id + ":" +
                "\nназвание: '" + name + "'" +
                ", координаты: " + coordinates +
                ", дата создания: " + creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                ", количество студентов: " + studentsCount +
                ", форма обучения: " + formOfEducation +
                ", семестр: " + semesterEnum +
                ", админ группы: " + groupAdmin;
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(id, name, coordinates, creationDate, studentsCount, formOfEducation, semesterEnum, groupAdmin);
    }

    ;
}
