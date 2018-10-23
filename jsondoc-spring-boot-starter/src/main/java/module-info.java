module jsondoc.springboot.starter{
    requires transitive jsondoc.core;
    requires transitive jsondoc.springintegration;
    requires spring.context;
    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires spring.boot;

    exports com.axonmobileiot.jsondoc.spring.boot.starter;
}