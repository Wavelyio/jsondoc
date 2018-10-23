module jsondoc.springboot.starter{
    requires jsondoc.core;
    requires jsondoc.springintegration;
    requires spring.context;
    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires spring.boot;

    exports com.axonmobileiot.jsondoc.spring.boot.starter;
}