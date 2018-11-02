package com.agileengine.analyzer;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Analyzer {

    private static Logger LOGGER = LoggerFactory.getLogger(Analyzer.class);

    public List<Result> getMatchingResults(Element original, Elements similar) {

        List<Result> results = similar.stream()
                .map(Result::new)
                .collect(Collectors.toList());

        addMatchingAttributes(original, results);
        addMatchingTextFields(original, results);

        List<Result> matchingResults = results.stream()
                .collect(groupingBy(Function.identity(), TreeMap::new, toList()))
                .lastEntry()
                .getValue();

        matchingResults.forEach(result -> result.setElementPath(getElementPath(result.getElement())));

        return matchingResults;
    }

    private void addMatchingAttributes(Element original, List<Result> results) {
        Attributes originalAttributes = original.attributes();

        Map<String, String> originalAttrMap = originalAttributes.asList().stream()
                .collect(Collectors.toMap(Attribute::getKey, Attribute::getValue));

        results.forEach(result -> result.getElement().attributes().asList().stream()
                .filter(attr -> originalAttrMap.containsKey(attr.getKey())
                        && originalAttrMap.get(attr.getKey()).equals(attr.getValue()))
                .forEach(attr -> result.addMatchingElement(attr.getKey(), attr.getValue()))
        );
    }

    private void addMatchingTextFields(Element original, List<Result> results) {
        results.stream()
                .filter(result -> result.getElement().hasText() && original.hasText()
                        && result.getElement().text().equals(original.text()))
                .forEach(result -> result.addMatchingElement("text", result.getElement().text()));
    }

    private String getElementPath(Element element) {
        List<String> pathElements = element.parents().stream()
                .map(this::formatElementPosition)
                .collect(Collectors.toList());
        Collections.reverse(pathElements);
        pathElements.add(formatElementPosition(element));
        return String.join(" / ", pathElements);
    }

    private String formatElementPosition(Element element) {
        int index = element.elementSiblingIndex();
        String name = element.tagName();
        return index > 0 ? String.format("%s[%s]", name, index) : String.format("%s", name);
    }

}
