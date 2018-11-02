package com.agileengine.analyzer;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Runner {

    private static Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private static String originFilePath;
    private static String sampleFilePath;
    private static final String defaultTargetElementId = "make-everything-ok-button";

    public static void main(String[] args) throws IOException {

        try {
            originFilePath = args[0];
            sampleFilePath = args[1];
        } catch (Exception e) {
            System.out.println("Please enter at least 2 arguments!");
            System.exit(0);
        }

        String originElementId = (args.length > 2 ? args[2] : defaultTargetElementId);

        Parser parser = new Parser(originFilePath, sampleFilePath);
        Analyzer analyzer = new Analyzer();
        ConsoleWriter consoleWriter = new ConsoleWriter();

        Element foundElement = parser.findElementById(originElementId);
        Elements foundElementsByTag = parser.findElementsByTag(foundElement.tagName());

        List<Result> matchingResults = analyzer.getMatchingResults(foundElement, foundElementsByTag);

        consoleWriter.printOriginalAttributes(foundElement);
        consoleWriter.printMatchedAttributes(matchingResults);

    }
}
