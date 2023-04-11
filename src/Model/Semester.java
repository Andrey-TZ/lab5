package Model;

public enum Semester {
    SECOND("второй", "SECOND"),
    FOURTH("четвёртый", "FOURTH"),
    FIFTH("пятый", "FIFTH"),
    SIXTH("шестой", "SIXTH");


    private final String meaning;
    private final String value;

    Semester(String meaning, String value) {
        this.meaning = meaning;
        this.value = value;
    }

    @Override
    public String toString() {
        return meaning;
    }

    public String toStringWithValue() {return toString() + "(" + value + ")";}
}
