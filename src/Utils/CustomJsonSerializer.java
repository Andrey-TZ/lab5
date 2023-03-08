package Utils;

import Exceptions.WrongFieldException;
import Model.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class CustomJsonSerializer implements JsonDeserializer<StudyGroup> {
    public StudyGroup deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        JsonObject object = json.getAsJsonObject();
        int id = object.get("id").getAsInt();
        String name = object.get("name").getAsString();
        JsonObject coordObject = object.get("coordinates").getAsJsonObject();
        Coordinates coordinates = new Coordinates(coordObject.get("x").getAsFloat(), coordObject.get("y").getAsFloat());
        LocalDateTime creationDate = LocalDateTime.parse(object.get("date").getAsString());
        long studentsCount = object.get("studentsCount").getAsLong();
        FormOfEducation formOfEducation = FormOfEducation.valueOf(object.get("formOfEducation").getAsString());


        Gson gson = new Gson();
        Semester semester = gson.fromJson(object.get("semesterEnum"), Semester.class);
        Person person = gson.fromJson(object.get("groupAdmin"), Person.class);
        try {
            return new StudyGroup(id, name, coordinates, creationDate, studentsCount, formOfEducation, semester, person);
        } catch (WrongFieldException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
