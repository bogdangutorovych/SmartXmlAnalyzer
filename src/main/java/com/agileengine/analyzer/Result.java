package com.agileengine.analyzer;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Result implements Comparable<Result> {

    private static Logger LOGGER = LoggerFactory.getLogger(Result.class);

    private Element element;
    private Map<String, String> matchingElements;
    private String elementPath;

    public Result(Element element) {
        this.element = element;
        this.matchingElements = new HashMap<>();
    }

    public void addMatchingElement(String key, String value) {
        this.matchingElements.put(key, value);
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Map<String, String> getMatchingElements() {
        return matchingElements;
    }

    public void setMatchingElements(Map<String, String> matchingElements) {
        this.matchingElements = matchingElements;
    }

    public String getElementPath() {
        return elementPath;
    }

    public void setElementPath(String elementPath) {
        this.elementPath = elementPath;
    }

    @Override
    public int compareTo(Result o) {
        return Integer.compare(this.getMatchingElements().size(), o.getMatchingElements().size());
    }
}
