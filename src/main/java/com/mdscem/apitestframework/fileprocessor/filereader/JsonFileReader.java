package com.mdscem.apitestframework.fileprocessor.filereader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileReader implements IFileReader{
    @Override
    public JsonNode readTestCases(JsonNode content) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(content.toString());
            System.out.println("My JsonNode " + jsonNode ) ;
            return jsonNode;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
