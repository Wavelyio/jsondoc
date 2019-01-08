package com.axonmobileiot.jsondoc.springintegration.scanner;

import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.pojo.display.MethodDisplay;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import com.axonmobileiot.jsondoc.springintegration.scanner.page.PageScannerController;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PageScannerTest {

    private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

    @Test
    public void scanControllerWithPage() {
        Set<Class<?>> controllers = new LinkedHashSet<>();
        controllers.add(PageScannerController.class);
        JSONDoc jsondoc = jsondocScanner.getJSONDoc("1.0", "http://localhost:8080/api", List.of("com.axonmobileiot.jsondoc.springintegration.scanner.page", Page.class.getPackage().getName()), true, MethodDisplay.URI);
        System.out.println("32523525");
    }
}
