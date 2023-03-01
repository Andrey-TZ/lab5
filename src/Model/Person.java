package Model;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private Float height; //Поле может быть null, Значение поля должно быть больше 0

    public Person(String name, java.util.Date birthday, Float height){
        this.name = name;
        this.birthday = birthday;
        this.height = height;
    }
}
