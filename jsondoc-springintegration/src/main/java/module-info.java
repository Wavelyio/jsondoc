module jsondoc.springintegration {
    requires jsondoc.core;
    requires spring.context;
    requires spring.web;
    requires spring.core;
    requires reflections;
    requires spring.beans;

    exports com.axonmobileiot.jsondoc.springintegration.controller;
}