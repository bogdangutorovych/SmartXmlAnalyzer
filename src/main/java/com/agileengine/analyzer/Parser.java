package com.agileengine.analyzer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Parser {

    private static Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    private static String CHARSET_NAME = "utf8";
    private String originFilePath;
    private String sampleFilePath;

    public Parser(String originFilePath, String sampleFilePath) {
        this.originFilePath = originFilePath;
        this.sampleFilePath = sampleFilePath;
    }

    public Element findElementById(String targetElementId) throws IOException {
        File file = new File(originFilePath);
        Document document = Jsoup.parse(file, CHARSET_NAME);
        return Optional.ofNullable(document.getElementById(targetElementId))
                .orElseThrow(() -> new RuntimeException(String.format("Can't find any element with id %s.", targetElementId)));
    }

    public Elements findElementsByTag(String tag) throws IOException {
        File file = new File(sampleFilePath);
        Document document = Jsoup.parse(file, CHARSET_NAME);
        return Optional.ofNullable(document.getElementsByTag(tag))
                .orElseThrow(() -> new RuntimeException(String.format("Can't find any element by tag [%s].", tag)));
    }


}
