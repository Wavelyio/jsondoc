module jsondoc.core {
    requires reflections;
    requires slf4j.api;
    requires validation.api;
    requires hibernate.validator;
    exports org.jsondoc.core.annotation.flow;
    exports org.jsondoc.core.annotation.global;
    exports org.jsondoc.core.annotation;
    exports org.jsondoc.core.pojo.flow;
    exports org.jsondoc.core.pojo.global;
    exports org.jsondoc.core.pojo;
    exports org.jsondoc.core.scanner.builder;
    exports org.jsondoc.core.scanner.validator;
    exports org.jsondoc.core.scanner;
    exports org.jsondoc.core.util;
}