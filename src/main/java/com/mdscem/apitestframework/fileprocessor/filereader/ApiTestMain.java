package com.mdscem.apitestframework.fileprocessor.filereader;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class ApiTestMain {
    public static void main(String[] args) {
        TestCaseLoader testCaseLoader = new TestCaseLoader("/home/hansakasudusinghe/Documents/APITestFrameWork/src/main/resources/testcases.yaml");


        JsonNode testCases = testCaseLoader.loadTestCases();
        if (testCases != null) {
            System.out.println("Loaded from Data: " + testCases.toPrettyString());
        }
    }
}