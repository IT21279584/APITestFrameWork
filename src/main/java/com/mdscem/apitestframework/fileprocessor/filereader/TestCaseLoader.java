package com.mdscem.apitestframework.fileprocessor.filereader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestCaseLoader {

    private String fileName;
    private FileReaderContext fileReaderContext;
    private ObjectMapper objectMapper;

    public TestCaseLoader(String fileName) {
        this.fileName = fileName;
        this.fileReaderContext = selectFileReader(fileName);
    }

    private FileReaderContext selectFileReader(String fileName) {
        IFileReader iFileReader;

        if (fileName.endsWith(".json")) {
            iFileReader = new JsonFileReader();
        } else if (fileName.endsWith(".yaml") || fileName.endsWith(".yml")) {
            iFileReader = new YamlFileReader();
        } else {
            throw new IllegalArgumentException("Unsupported file type " + fileName);
        }
        return new FileReaderContext(iFileReader);
    }

    public JsonNode loadTestCases() {

        JsonNode loadedTestCases = null;
        try {
            Path filePath = Paths.get(fileName);
            if (!Files.exists(filePath)) {
                System.err.println("Could not find the file: " + fileName);
                //return loadedTestCases;
            }

            String fileContent = new String(Files.readAllBytes(filePath));

            loadedTestCases = fileReaderContext.loadTestCases(fileContent);

            // Convert the processed JsonNode back to a list of TestCase
            //testCases = objectMapper.convertValue(loadedTestCases, new TypeReference<List<TestCase>>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedTestCases;
    }

}