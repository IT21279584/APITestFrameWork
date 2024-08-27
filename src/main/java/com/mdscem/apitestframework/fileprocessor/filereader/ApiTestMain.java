package com.mdscem.apitestframework.fileprocessor.filereader;

import java.util.List;

public class ApiTestMain {
    public static void main(String[] args) {
        TestCaseLoader jsonLoader = new TestCaseLoader("/home/hansakasudusinghe/Documents/APITestFrameWork/src/main/resources/testcases.yaml");
        List<TestCase> testCases = jsonLoader.loadTestCases();
        if (testCases != null) {
            testCases.forEach(testCase -> System.out.println("Loaded from Data: " + testCase.getTestName()));
        }
    }
}