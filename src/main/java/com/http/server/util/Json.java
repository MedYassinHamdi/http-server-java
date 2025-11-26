package com.http.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

/**
 * The `Json` utility class provides methods for working with JSON data.
 * It uses the Jackson library to parse, convert, and generate JSON.
 * @autor Yassin Hamdi
 */

public class Json {

    /**
     * instance of `ObjectMapper` used for JSON operations.
     */
    private static ObjectMapper mapper = defaultObjectMapper() ;

    /**
     * Configures and returns a default `ObjectMapper` instance.
     *
     * @return a configured `ObjectMapper` instance.
     */
    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper om = new ObjectMapper() ;

        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) ;

        return om ;
    }

    /**
     * Parses a JSON string and returns it as a `JsonNode`.
     *
     * @param jsonSrc the JSON string to parse.
     * @return the parsed `JsonNode`.
     * @throws JsonProcessingException if the JSON string cannot be parsed.
     */
    public static JsonNode parse(String jsonSrc) throws JsonProcessingException {
        return  mapper.readTree(jsonSrc);
    }

    /**
     * Converts a `JsonNode` to an object of the specified class.
     *
     * @param <A> the type of the object to convert to.
     * @param node the `JsonNode` to convert.
     * @param clazz the class of the object to convert to.
     * @return the converted object.
     * @throws JsonProcessingException if the conversion fails.
     */
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return mapper.treeToValue(node, clazz) ;
    }

    /**
     * Converts an object to a `JsonNode`.
     *
     * @param obj the object to convert.
     * @return the resulting `JsonNode`.
     */
    public static JsonNode toJson(Object obj){
        return mapper.valueToTree(obj) ;
    }


    /**
     * Converts a `JsonNode` to a compact JSON string.
     *
     * @param node the `JsonNode` to convert.
     * @return the JSON string representation of the `JsonNode`.
     * @throws JsonProcessingException if the conversion fails.
     */
    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node,false);
    }

    /**
     * Converts a `JsonNode` to a pretty-printed JSON string.
     *
     * @param node the `JsonNode` to convert.
     * @return the pretty-printed JSON string representation of the `JsonNode`.
     * @throws JsonProcessingException if the conversion fails.
     */
    public static String stringifyP(JsonNode node) throws JsonProcessingException {
        return generateJson(node,true);
    }

    /**
     * Generates a JSON string from an object, with optional pretty-printing.
     *
     * @param obj the object to convert to JSON.
     * @param p whether to pretty-print the JSON string.
     * @return the JSON string representation of the object.
     * @throws JsonProcessingException if the conversion fails.
     */
    private static String generateJson(Object obj,boolean p) throws JsonProcessingException {

        ObjectWriter ow = mapper.writer() ;
        if(p){
            ow = ow.with(SerializationFeature.INDENT_OUTPUT);
        }

        return ow.writeValueAsString(obj) ;
    }



}
