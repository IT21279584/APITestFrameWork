package com.mdscem.apitestframework.fileprocessor.filereader;

import com.fasterxml.jackson.databind.JsonNode;
import com.mdscem.apitestframework.filevalidator.JsonSchemaValidationWithJsonNode;

import java.util.List;

public class ApiTestMain {
    public static void main(String[] args) {
        TestCaseLoader testCaseLoader = new TestCaseLoader("/home/kmedagoda/APITestFrameWork/src/main/resources/testcases.json");


        JsonNode testCases = testCaseLoader.loadTestCases();
        if (testCases != null) {
            JsonSchemaValidationWithJsonNode.validateFile(testCases);
        }
    }
}