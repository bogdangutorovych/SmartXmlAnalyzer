package com.agileengine.analyzer;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ConsoleWriter {

    private static Logger LOGGER = LoggerFactory.getLogger(ConsoleWriter.class);

    private static final String ATTRIBUTE_FORMAT = "| %-30s | %-60s |%n";

    public void printOriginalAttributes(Element original) {
        System.out.println();
        System.out.println("Original element attributes:");
        printHeader();
        original.attributes().asList()
                .forEach(attribute -> System.out.format(ATTRIBUTE_FORMAT,
                        attribute.getKey(),
                        attribute.getValue()));
        Optional.ofNullable(original.text())
                .ifPresent(s -> System.out.format(ATTRIBUTE_FORMAT, "text", s));
        printFooter();
    }

    public void printMatchedAttributes(List<Result> results) {
        System.out.format("Found %s matched element(s):\n", results.size());

        results.stream()
                .map(result -> {
                    printElementPath(result);
                    return result.getMatchingElements();
                })
                .forEach(map -> {
                            printHeader();
                            map.forEach((key, value) -> System.out.format(ATTRIBUTE_FORMAT, key, value));
                            printFooter();
                        }
                );
    }

    private void printHeader() {
        System.out.format(
                "+--------------------------------+--------------------------------------------------------------+%n");
        System.out.format(
                "| Attribute name                 | Attribute value                                              |%n");
        System.out.format(
                "+--------------------------------+--------------------------------------------------------------+%n");
    }

    private void printElementPath(Result result) {
        System.out.format("Path: %s\n", result.getElementPath());
    }

    private void printFooter() {
        System.out.format(
                "+--------------------------------+--------------------------------------------------------------+%n%n");
    }

}
