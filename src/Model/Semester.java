package Model;

public enum Semester {
    SECOND("второй"),
    FOURTH("четвёртый"),
    FIFTH("пятый"),
    SIXTH("шестой");


    private String meaning;

    Semester(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return meaning;
    }
}
