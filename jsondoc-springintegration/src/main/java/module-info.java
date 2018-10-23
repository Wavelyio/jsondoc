module jsondoc.springintegration {
    exports org.jsondoc.springintegration.controller;
    requires jsondoc.core;
    requires spring.context;
    requires spring.web;
    requires spring.core;
    requires reflections;
    requires spring.beans;
}