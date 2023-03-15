package Utils;

import Exceptions.WrongFieldException;
import Model.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;


/**
 * Класс, дающий шаблон, как преобразовывать содержимое json в java class и обратно.
 */
public class StudyGroupJsonSerializer implements JsonDeserializer<StudyGroup>, JsonSerializer<StudyGroup> {
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson;
    {
        builder.registerTypeAdapter(Coordinates.class, new CoordinatesJsonSerializer());
        builder.registerTypeAdapter(Person.class, new PersonJsonSerializer());
        gson = builder.create();
    }
    /**
     * Десериализует jsonElement в объект класса StudyGroup
     *
     * @param jsonElement
     * @param type
     * @param context
     * @return объект класса StudyGroup
     */
    public StudyGroup deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) {
        JsonObject object = jsonElement.getAsJsonObject();
        int id = object.get("id").getAsInt();
        String name = object.get("name").getAsString();
        Coordinates coordinates = gson.fromJson(object.get("coordinates").getAsJsonObject(), Coordinates.class);
        LocalDateTime creationDate = LocalDateTime.parse(object.get("date").getAsString());
        long studentsCount = object.get("studentsCount").getAsLong();
        FormOfEducation formOfEducation = FormOfEducation.valueOf(object.get("formOfEducation").getAsString());

        Semester semester;
        if (object.get("semesterEnum").getAsString() == "null") {
            semester = null;

        } else {
            semester = gson.fromJson(object.get("semesterEnum"), Semester.class);
        }
        Person groupAdmin;
        if(object.get("groupAdmin").isJsonObject()){
            groupAdmin = gson.fromJson(object.get("groupAdmin").getAsJsonObject(), Person.class);
        }
        else{groupAdmin = null;}

//        String person = object.get("groupAdmin").getAsString();
//        if (person.equals( "null")) {
//            groupAdmin = null;
//        } else {
//            groupAdmin = gson.fromJson(object.get("groupAdmin"), Person.class);
//        }
        try {
            return new StudyGroup(id, name, coordinates, creationDate, studentsCount, formOfEducation, semester, groupAdmin);
        } catch (WrongFieldException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonElement serialize(StudyGroup studyGroup, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Coordinates.class, new CoordinatesJsonSerializer());
        builder.registerTypeAdapter(Person.class, new PersonJsonSerializer());
        Gson gson = builder.create();

        object.addProperty("id", studyGroup.getId());
        object.addProperty("name", studyGroup.getName());
        object.add("coordinates", gson.toJsonTree(studyGroup.getCoordinates(), Coordinates.class));
        String date = studyGroup.getCreationDate();
        object.addProperty("date", date);
        object.addProperty("studentsCount", studyGroup.getStudentsCount());
        object.add("formOfEducation", gson.toJsonTree(studyGroup.getFormOfEducation()));
        if (studyGroup.getSemesterEnum() == (null)) {
            object.addProperty("semesterEnum", "null");
        } else {
            object.add("semesterEnum", gson.toJsonTree(studyGroup.getSemesterEnum(), Semester.class));
        }
        if (studyGroup.getGroupAdmin() == (null)) {
            object.addProperty("groupAdmin", "null");
        } else {
            object.add("groupAdmin", gson.toJsonTree(studyGroup.getGroupAdmin()));
        }
        return object;
    }
}
