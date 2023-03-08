package Exceptions;

public class EmptyFieldException extends Exception{
    public EmptyFieldException(){
        super("поле не может быть пустым!");
    }
}
