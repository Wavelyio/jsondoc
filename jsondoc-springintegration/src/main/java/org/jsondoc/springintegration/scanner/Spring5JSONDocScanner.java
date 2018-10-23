package org.jsondoc.springintegration.scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

public class Spring5JSONDocScanner extends AbstractSpringJSONDocScanner {
    @Override
    public Set<Class<?>> jsondocControllers() {
        Set<Class<?>> jsondocControllers = reflections.getTypesAnnotatedWith(Controller.class, true);
        jsondocControllers.addAll(reflections.getTypesAnnotatedWith(RestController.class, true));
        return jsondocControllers;
    }
}
