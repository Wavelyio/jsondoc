package com.axonmobileiot.jsondoc.springintegration.scanner;
import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

public class SpringRequestMappingDerivativesTest {

    private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

    @Controller
    public class RequestMappingController {

        @GetMapping(value = "/request")
        public void get() {

        }

        @PostMapping(value = "/request")
        public void post() {

        }

        @PutMapping(value = "/request")
        public void put() {

        }

        @PatchMapping(value = "/request")
        public void patch() {

        }

        @DeleteMapping(value = "/request")
        public void delete() {

        }
    }


    @Test
    public void testGetMapping() {
        ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(RequestMappingController.class), JSONDoc.MethodDisplay.URI).iterator().next();
        Assertions.assertEquals("RequestMappingController", apiDoc.getName());
        apiDoc.getMethods().stream().anyMatch(doc -> doc.getMethod().equals("get"));
        boolean getMethodPresent = apiDoc.getMethods().stream().anyMatch(doc -> doc.getMethod().equals("get"));
        Assertions.assertTrue(getMethodPresent);

        boolean postMethodPresent = apiDoc.getMethods().stream().anyMatch(doc -> doc.getMethod().equals("post"));
        Assertions.assertTrue(postMethodPresent);

        boolean putMethodPresent = apiDoc.getMethods().stream().anyMatch(doc -> doc.getMethod().equals("put"));
        Assertions.assertTrue(putMethodPresent);

        boolean patchMethodPresent = apiDoc.getMethods().stream().anyMatch(doc -> doc.getMethod().equals("patch"));
        Assertions.assertTrue(patchMethodPresent);

        boolean deleteMethodPresent = apiDoc.getMethods().stream().anyMatch(doc -> doc.getMethod().equals("delete"));
        Assertions.assertTrue(deleteMethodPresent);
    }


}
