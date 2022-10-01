package com.f2erg.vexctyhub.adapters;

import com.f2erg.vexctyhub.server.data.type.GameType;
import com.f2erg.vexctyhub.server.data.type.ServerType;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ServerTypeTypeAdapter implements JsonDeserializer<ServerType>, JsonSerializer<ServerType> {

    @Override
    public JsonElement serialize(ServerType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.name());
    }

    @Override
    public ServerType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String raw = json.getAsString();
        try {
            return GameType.fromId(Integer.parseInt(raw));
        } catch (NumberFormatException ignored) {
        }
        return ServerType.valueOf(raw);
    }

}
