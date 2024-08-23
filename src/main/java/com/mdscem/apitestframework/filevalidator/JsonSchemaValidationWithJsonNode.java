package com.mdscem.apitestframework.filevalidator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public  class JsonSchemaValidationWithJsonNode {

    public static void validateFile(JsonNode jsonNode){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);

        try{
            //Load json schema from schema file
            JsonNode schemaNode = objectMapper.readTree(new File("/home/kmedagoda/Documents/Rest-Assured-testing-framework-auth/src/test/java/api/test/filevalidator/schema.json"));
            JsonSchema schema = jsonSchemaFactory.getSchema(schemaNode);

            //Validate that jsonNode against schema
            Set<ValidationMessage> validationErrors = schema.validate(jsonNode);

            // Check if there are any validation errors
            if (validationErrors.isEmpty()) {
                System.out.println("JSON is valid against the schema.");
            } else {
                System.out.println("JSON is not valid. Errors:");
                for (ValidationMessage error : validationErrors) {
                    System.out.println(error.getMessage());
                }
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
