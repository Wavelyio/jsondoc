package com.axonmobileiot.jsondoc.springintegration.scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.util.Set;

public class Spring5JSONDocScanner extends AbstractSpringJSONDocScanner {
    @Override
    public Set<Class<?>> jsondocControllers() {
        Set<Class<?>> jsondocControllers = reflections.getTypesAnnotatedWith(Controller.class, true);
        jsondocControllers.addAll(reflections.getTypesAnnotatedWith(RestController.class, true));
        return jsondocControllers;
    }

    @Override
    public Set<Class<? extends Annotation>> mappingAnnotations() {
        return Set.of(RequestMapping.class, GetMapping.class, PostMapping.class, PatchMapping.class, PutMapping.class, DeleteMapping.class);
    }
}
