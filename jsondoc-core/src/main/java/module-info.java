module jsondoc.core {
    requires reflections;
    requires slf4j.api;
    requires validation.api;
    requires hibernate.validator;

    exports com.axonmobileiot.jsondoc.core.annotation.flow;
    exports com.axonmobileiot.jsondoc.core.annotation.global;
    exports com.axonmobileiot.jsondoc.core.annotation;
    exports com.axonmobileiot.jsondoc.core.pojo.flow;
    exports com.axonmobileiot.jsondoc.core.pojo.global;
    exports com.axonmobileiot.jsondoc.core.pojo;
    exports com.axonmobileiot.jsondoc.core.scanner.builder;
    exports com.axonmobileiot.jsondoc.core.scanner.validator;
    exports com.axonmobileiot.jsondoc.core.scanner;
    exports com.axonmobileiot.jsondoc.core.util;
}