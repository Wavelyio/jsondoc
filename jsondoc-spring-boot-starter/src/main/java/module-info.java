module jsondoc.springboot.starter{
    requires spring.context;
    requires jsondoc.springintegration;
    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires jsondoc.core;
    exports org.jsondoc.spring.boot.starter;
}