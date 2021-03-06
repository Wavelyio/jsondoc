package com.axonmobileiot.jsondoc.core.annotation;

import com.axonmobileiot.jsondoc.core.util.JSONDocDefaultType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is to be used on your method and represents the body object
 * passed the request.
 * 
 * @see ApiObject
 * @author Fabio Maffioletti
 *
 */
@Documented
@Target(value = { ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiBodyObject {

	/**
	 * Specify this element if you need to use the ApiBodyObject annotation on the method declaration and not inside the method's signature. This is to be able to document old style servlets'
	 * methods like doGet and doPost. This element, even if specified, is not taken into account when the annotation is put inside the method's signature.
	 * @return
	 */
	public Class<?> clazz() default JSONDocDefaultType.class;
	
}
