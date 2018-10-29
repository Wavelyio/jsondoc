package com.axonmobileiot.jsondoc.core.util;

import java.lang.reflect.Field;

import javax.validation.constraints.*;

import com.axonmobileiot.jsondoc.core.pojo.ApiObjectFieldDoc;

public class JSONDocJavaXValidatorProcessor {

	private final static String AssertFalse_message = "must be false";
	private final static String AssertTrue_message = "must be true";
	private final static String DecimalMax_message = "must be less than %s %s";
	private final static String DecimalMin_message = "must be greater than %s %s";
	private final static String Digits_message = "numeric value made of <%s digits>.<%s digits>)";
	private final static String Future_message = "must be in the future";
	private final static String Max_message = "must be less than or equal to %s";
	private final static String Min_message = "must be greater than or equal to %s";
	private final static String NotNull_message = "may not be null";
	private final static String Null_message = "must be null";
	private final static String Past_message = "must be in the past";
	private final static String Pattern_message = "must match %s";
	private final static String Size_message = "size must be between %s and %s";
	private final static String Email_message = "must be a well-formed email address";
	private final static String NotBlank_message = "may not be empty";
	private final static String NotEmpty_message = "may not be empty";

	public static void processHibernateValidatorAnnotations(Field field, ApiObjectFieldDoc apiPojoFieldDoc) {
			if (field.isAnnotationPresent(AssertFalse.class)) {
				apiPojoFieldDoc.addFormat(AssertFalse_message);
			}

			if (field.isAnnotationPresent(AssertTrue.class)) {
				apiPojoFieldDoc.addFormat(AssertTrue_message);
			}
			
			if (field.isAnnotationPresent(NotNull.class)) {
				apiPojoFieldDoc.addFormat(NotNull_message);
			}

			if (field.isAnnotationPresent(Null.class)) {
				apiPojoFieldDoc.addFormat(Null_message);
			}
			
			if (field.isAnnotationPresent(Size.class)) {
				Size annotation = field.getAnnotation(Size.class);
				apiPojoFieldDoc.addFormat(String.format(Size_message, annotation.min(), annotation.max()));
			}
			
			if (field.isAnnotationPresent(NotBlank.class)) {
				apiPojoFieldDoc.addFormat(NotBlank_message);
			}

			if (field.isAnnotationPresent(NotEmpty.class)) {
				apiPojoFieldDoc.addFormat(NotEmpty_message);
			}

			if (field.isAnnotationPresent(DecimalMax.class)) {
				DecimalMax annotation = field.getAnnotation(DecimalMax.class);
				apiPojoFieldDoc.addFormat(String.format(DecimalMax_message, annotation.inclusive() ? "or equal to" : "", annotation.value()));
			}

			if (field.isAnnotationPresent(DecimalMin.class)) {
				DecimalMin annotation = field.getAnnotation(DecimalMin.class);
				apiPojoFieldDoc.addFormat(String.format(DecimalMin_message, annotation.inclusive() ? "or equal to" : "", annotation.value()));
			}

			if (field.isAnnotationPresent(Future.class)) {
				apiPojoFieldDoc.addFormat(Future_message);
			}

			if (field.isAnnotationPresent(Past.class)) {
				apiPojoFieldDoc.addFormat(Past_message);
			}

			if (field.isAnnotationPresent(Digits.class)) {
				Digits annotation = field.getAnnotation(Digits.class);
				apiPojoFieldDoc.addFormat(String.format(Digits_message, annotation.integer(), annotation.fraction()));
			}

			if (field.isAnnotationPresent(Max.class)) {
				Max annotation = field.getAnnotation(Max.class);
				apiPojoFieldDoc.addFormat(String.format(Max_message, annotation.value()));
			}

			if (field.isAnnotationPresent(Min.class)) {
				Min annotation = field.getAnnotation(Min.class);
				apiPojoFieldDoc.addFormat(String.format(Min_message, annotation.value()));
			}

			if (field.isAnnotationPresent(Pattern.class)) {
				Pattern annotation = field.getAnnotation(Pattern.class);
				apiPojoFieldDoc.addFormat(String.format(Pattern_message, annotation.regexp()));
			}

			if (field.isAnnotationPresent(Email.class)) {
				apiPojoFieldDoc.addFormat(Email_message);
			}

	}

}
