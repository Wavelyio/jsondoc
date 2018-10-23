package org.jsondoc.springintegration.controller.object;

import org.jsondoc.core.pojo.ApiObjectDoc;
import org.jsondoc.core.pojo.JSONDoc;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.jsondoc.springintegration.scanner.Spring5JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Spring3JSONDocObjectScannerTest {
    private String version = "1.0";
    private String basePath = "http://localhost:8080/api";

    private static Logger log = LoggerFactory.getLogger(Spring3JSONDocObjectScannerTest.class);

    @Test
    public void getJSONDoc() {
        JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();
        JSONDoc jsondoc = jsondocScanner.getJSONDoc(version, basePath, List.of("org.jsondoc.springmvc.controller"), true, MethodDisplay.URI);

        Map<String, Set<ApiObjectDoc>> objects = jsondoc.getObjects();
        for (Set<ApiObjectDoc> values : objects.values()) {
            for (ApiObjectDoc apiObjectDoc : values) {
                System.out.println(apiObjectDoc.getName());
            }
        }

    }

    @Test
    public void findsNestedObject() {
        JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();
        JSONDoc jsondoc = jsondocScanner.getJSONDoc(version, basePath, List.of("org.jsondoc.springmvc.controller"), true, MethodDisplay.URI);

        Map<String, Set<ApiObjectDoc>> objects = jsondoc.getObjects();
        for (Set<ApiObjectDoc> values : objects.values()) {
            assertContainsDoc(values, "NestedObject1");
        }
    }

    @Test
    public void findsDeeplyNestedObjects() {
        JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();
        JSONDoc jsondoc = jsondocScanner.getJSONDoc(version, basePath, List.of("org.jsondoc.springmvc.controller"), true, MethodDisplay.URI);

        Map<String, Set<ApiObjectDoc>> objects = jsondoc.getObjects();
        for (Set<ApiObjectDoc> values : objects.values()) {
            assertContainsDoc(values, "NestedObject2");
            assertContainsDoc(values, "NestedObject3");
        }
    }


    public void assertContainsDoc(Set<ApiObjectDoc> values, String name) {
        for (ApiObjectDoc apiObjectDoc : values) {
            if(apiObjectDoc.getName().equals(name)) {
                return;
            }
        }
        Assertions.fail("Could not find ApiObjectDoc with name " + name);
    }

}