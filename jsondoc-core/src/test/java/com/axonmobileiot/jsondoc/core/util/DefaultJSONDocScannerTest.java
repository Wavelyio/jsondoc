package com.axonmobileiot.jsondoc.core.util;

import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiVerb;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.scanner.DefaultJSONDocScanner;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultJSONDocScannerTest {
    private String version = "1.0";
    private String basePath = "http://localhost:8080/api";
    private ObjectMapper objectMapper = new ObjectMapper();

    private static Logger log = LoggerFactory.getLogger(DefaultJSONDocScannerTest.class);

    @Test
    public void getJSONDoc() throws IOException {
    	JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();
        JSONDoc jsondoc = jsondocScanner.getJSONDoc(version, basePath, List.of("com.axonmobileiot.jsondoc.core.util"), true, JSONDoc.MethodDisplay.URI);
        assertEquals(1, jsondoc.getApis().size());

        int countApis = 0;
        for (String string : jsondoc.getApis().keySet()) {
            countApis += jsondoc.getApis().get(string).size();
        }
        assertEquals(4, countApis);

        assertEquals(3, jsondoc.getObjects().size());
        
        int countFlows = 0;
        for (String string : jsondoc.getFlows().keySet()) {
        	countFlows += jsondoc.getFlows().get(string).size();
        }
        assertEquals(2, countFlows);

        int countObjects = 0;
        for (String string : jsondoc.getObjects().keySet()) {
            countObjects += jsondoc.getObjects().get(string).size();
        }
        assertEquals(10, countObjects);

        Set<ApiVerb> apiVerbs = getAllTestedApiVerbs(jsondoc);
        assertEquals(ApiVerb.values().length, apiVerbs.size());

        log.debug(objectMapper.writeValueAsString(jsondoc));
    }

    private Set<ApiVerb> getAllTestedApiVerbs(JSONDoc jsondoc) {
        Set<ApiVerb> apiVerbs = new HashSet<>();

        for (String string : jsondoc.getObjects().keySet()) {
            Set<ApiDoc> apiDocs = jsondoc.getApis().get(string);
            if (apiDocs != null) {
                for (ApiDoc doc : apiDocs) {
                    for (ApiMethodDoc apiMethodDoc : doc.getMethods()) {
                        apiVerbs.addAll(apiMethodDoc.getVerb());
                    }
                }
            }
        }

        return apiVerbs;
    }

}