package Model;

public enum FormOfEducation {
    DISTANCE_EDUCATION("дистанционное обучение", "DISTANCE_EDUCATION"),
    FULL_TIME_EDUCATION("дневное очное обучение", "FULL_TIME_EDUCATION"),
    EVENING_CLASSES("вечернее обучение", "EVENING_CLASSES");

    private final String meaning;
    private final String value;

    FormOfEducation(String meaning, String value) {
        this.meaning = meaning;
        this.value = value;
    }

    @Override
    public String toString() {
        return meaning;
    }

    public String toStringWithValue(){
        return toString() + "(" + value + ")";
    }
}
