package Model;

public enum FormOfEducation {
    DISTANCE_EDUCATION("дистанционное обучение"),
    FULL_TIME_EDUCATION("дневное очное обучение"),
    EVENING_CLASSES("вечернее обучение");

    private String meaning;

    FormOfEducation(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return meaning;
    }
}
