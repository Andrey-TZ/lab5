package Utils;

import Exceptions.WrongFieldException;
import Model.Coordinates;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CoordinatesJsonSerializer implements JsonSerializer<Coordinates>, JsonDeserializer<Coordinates>
{
    /**
     * @param jsonElement
     * @param type
     * @param jsonDeserializationContext
     * @return
     * @throws JsonParseException
     */
    @Override
    public Coordinates deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Float x = object.get("x").getAsFloat();
        float y = object.get("y").getAsFloat();
        try {
            return new Coordinates(x, y);
        }
        catch (WrongFieldException e){
            System.out.println("Координата X введена неверно");
        throw new RuntimeException();
        }
    }

    /**
     * @param coordinates
     * @param type
     * @param jsonSerializationContext
     * @return
     */
    @Override
    public JsonElement serialize(Coordinates coordinates, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("x", coordinates.getX());
        object.addProperty("y", coordinates.getY());
        return object;
    }
}
