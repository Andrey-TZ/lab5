package Model;

import Exceptions.EmptyFieldException;
import Exceptions.WrongFieldException;

import java.util.Date;

/**
 * Класс человека
 */
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private Float height; //Поле может быть null, Значение поля должно быть больше 0

    /**
     * @param name     - имя человека
     * @param birthday - день рождения человека
     * @param height   - рост человека
     * @throws EmptyFieldException при передаче пустой строки или null
     * @throws WrongFieldException при передаче неверного аргумента (например, строки вместо числа)
     */
    public Person(String name, java.util.Date birthday, Float height) throws EmptyFieldException, WrongFieldException {
        setName(name);
        setBirthday(birthday);
        setHeight(height);
    }

    public void setName(String name) throws EmptyFieldException {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else throw new EmptyFieldException();
    }

    public String getName() {
        return name;
    }

    public void setBirthday(Date birthday) throws EmptyFieldException {
        if (birthday != null) {
            this.birthday = birthday;
        } else throw new EmptyFieldException();
    }

    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param height праметр сеттера роста
     */
    public void setHeight(Float height) throws EmptyFieldException, WrongFieldException {
        if (height == null) {
            throw new EmptyFieldException();
        } else if (height < 1) {
            throw new WrongFieldException("Рост должен быть больше 0!");
        } else this.height = height;
    }

    /**
     * @return рост
     */
    public Float getHeight() {
        return height;
    }



}
