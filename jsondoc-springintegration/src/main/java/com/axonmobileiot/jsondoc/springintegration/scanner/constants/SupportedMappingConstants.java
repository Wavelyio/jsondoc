package com.axonmobileiot.jsondoc.springintegration.scanner.constants;

import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.util.Set;

public class SupportedMappingConstants {
    public static final Set<Class<? extends Annotation>> mappingAnnotations =
            Set.of(RequestMapping.class, GetMapping.class, PostMapping.class, PatchMapping.class, PutMapping.class, DeleteMapping.class);
}
