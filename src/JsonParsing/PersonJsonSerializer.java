package JsonParsing;

import Exceptions.EmptyFieldException;
import Exceptions.WrongFieldException;
import Model.Person;
import Model.StudyGroup;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonJsonSerializer implements JsonDeserializer<Person>, JsonSerializer<Person> {

    /**
     * @param jsonElement
     * @param type
     * @param jsonDeserializationContext
     * @return
     * @throws JsonParseException
     */
    @Override
    public Person deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        String name = object.get("name").getAsString();
        try {
            Date birthday = new SimpleDateFormat("dd-MM-yyyy").parse(object.get("birthday").getAsString());
            Float height = object.get("height").getAsFloat();
            return new Person(name, birthday, height);
        } catch (ParseException e) {
            System.out.println("День Рождения человека введен не верно!");
            return null;
        } catch (WrongFieldException | EmptyFieldException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    /**
     * @param person
     * @param type
     * @param jsonSerializationContext
     * @return
     */
    @Override
    public JsonElement serialize(Person person, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("name", person.getName());
        String date = person.getBirthday();
        object.addProperty("birthday", date);
        object.addProperty("height", person.getHeight());


        return object;
    }
}
