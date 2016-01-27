package com.peiyuan.model.parser;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hexun on 2016/1/27.
 */
public class DefaultParser<T> implements JsonDeserializer<List<T>> {

    @Override
    public List<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement s = json;
        Type type = typeOfT;
        JsonDeserializationContext jsonDeserializationContext = context;
        return null;
    }

}
