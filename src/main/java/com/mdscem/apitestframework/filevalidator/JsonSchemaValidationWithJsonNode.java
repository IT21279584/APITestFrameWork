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

public class JsonSchemaValidationWithJsonNode {

    public static void main(String[] args){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);

        try{
            //Load json schema from schema file
            JsonNode schemaNode = objectMapper.readTree(new File("/home/kmedagoda/Documents/Rest-Assured-testing-framework-auth/src/test/java/api/test/filevalidator/schema.json"));
            JsonSchema schema = jsonSchemaFactory.getSchema(schemaNode);

            //Get jsonNode from a method
            JsonNode testCasesAsJsonNode = getTestCasesAsJsonNode();

            //Validate that jsonNode against schema
            Set<ValidationMessage> validationErrors = schema.validate(testCasesAsJsonNode);

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
    //this is the method for return sample json node
    private static JsonNode getTestCasesAsJsonNode() {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = "[\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"url\": \"http://localhost:8081/api/projects/project/save\",\n" +
                "      \"method\": \"POST\",\n" +
                "      \"payload\": {\n" +
                "        \"project\": {\n" +
                "          \"projectName\": \"saasf\",\n" +
                "          \"projectCode\": \"saasf\"\n" +
                "        },\n" +
                "        \"userID\": 3\n" +
                "      },\n" +
                "      \"expectedResponseCode\": 200,\n" +
                "      \"testName\": \"project_save\",\n" +
                "      \"expectedResponseBody\": {\n" +
                "        \"projectName\": \"saasf\",\n" +
                "        \"projectCode\": \"saasf\",\n" +
                "        \"users\": {\n" +
                "          \"id\": 3,\n" +
                "          \"firstName\": \"thusitha\",\n" +
                "          \"lastName\": \"dinesh\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"requiresAuthentication\": true,\n" +
                "      \"authToken\": \"tokennnn\",\n" +
                "      \"saveResponse\": {\n" +
                "        \"projectID\": \"projectID\"\n" +
                "      },\n" +
                "      \"priority\": 1\n" +
                "    }\n" +
                "]";

        JsonNode jsonNode = null;
        try {
            // Convert the JSON string into a JsonNode
            jsonNode = objectMapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }
}
