package org.jsondoc.core.scanner.builder;

import java.util.Iterator;
import java.util.Set;

import org.jsondoc.core.pojo.ApiObjectDoc;
import org.jsondoc.core.pojo.ApiObjectFieldDoc;
import org.jsondoc.core.scanner.DefaultJSONDocScanner;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.jsondoc.core.util.pojo.HibernateValidatorPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JSONDocApiObjectBuilderTest {
	
	private JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();
	
	@Test
	public void testApiObjectDocWithHibernateValidator() {
		Set<ApiObjectDoc> apiObjectDocs = jsondocScanner.getApiObjectDocs(Set.of(HibernateValidatorPojo.class));
		Iterator<ApiObjectDoc> iterator = apiObjectDocs.iterator();
		ApiObjectDoc next = iterator.next();
		Set<ApiObjectFieldDoc> fields = next.getFields();
		for (ApiObjectFieldDoc apiObjectFieldDoc : fields) {
			if(apiObjectFieldDoc.getName().equals("id")) {
				Iterator<String> formats = apiObjectFieldDoc.getFormat().iterator();
				Assertions.assertEquals("a not empty id", formats.next());
				Assertions.assertEquals("length must be between 2 and 2147483647", formats.next());
				Assertions.assertEquals("must be less than or equal to 9", formats.next());
			}
		}
	}

}
