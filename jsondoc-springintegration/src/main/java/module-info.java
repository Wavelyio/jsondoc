module jsondoc.springintegration {
    requires transitive jsondoc.core;
    requires spring.context;
    requires spring.web;
    requires spring.core;
    requires reflections;
    requires spring.beans;
    requires spring.data.commons;

    exports com.axonmobileiot.jsondoc.springintegration.controller;
}