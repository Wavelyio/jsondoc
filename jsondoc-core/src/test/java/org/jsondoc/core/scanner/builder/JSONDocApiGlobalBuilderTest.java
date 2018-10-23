package org.jsondoc.core.scanner.builder;

import org.jsondoc.core.annotation.global.ApiChangelog;
import org.jsondoc.core.annotation.global.ApiChangelogSet;
import org.jsondoc.core.annotation.global.ApiGlobal;
import org.jsondoc.core.annotation.global.ApiGlobalSection;
import org.jsondoc.core.annotation.global.ApiMigration;
import org.jsondoc.core.annotation.global.ApiMigrationSet;
import org.jsondoc.core.pojo.global.ApiGlobalDoc;
import org.jsondoc.core.pojo.global.ApiGlobalSectionDoc;
import org.jsondoc.core.scanner.DefaultJSONDocScanner;
import org.jsondoc.core.scanner.JSONDocScanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class JSONDocApiGlobalBuilderTest {
	
	private JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();
	
	@ApiGlobal(
		sections = { 
			@ApiGlobalSection(title = "title", paragraphs = {
				"Paragraph 1", "Paragraph 2", "/jsondocfile./src/main/resources/text.txt" }
			) 
		}
	)
	private class Global {
		
	}
	
	@ApiGlobal(
		sections = { 
			@ApiGlobalSection(title = "section1", paragraphs = { "Paragraph 1" }), 
			@ApiGlobalSection(title = "abc", paragraphs = { "Paragraph 1", "Paragraph2" }), 
			@ApiGlobalSection(title = "198xyz", paragraphs = { "Paragraph 1", "Paragraph2", "Paragraph3", "Paragraph4" }), 
		}
	)
	private class MultipleGlobalSections {
		
	}
	
	
	@ApiChangelogSet(changlogs = { @ApiChangelog(changes = { "Change #1" }, version = "1.0") })
	private class Changelog {
		
	}
	
	@ApiMigrationSet(migrations = { @ApiMigration(fromversion = "1.0", steps = { "Step #1" }, toversion = "1.1") })
	private class Migration {
		
	}
	
	@ApiGlobal(
		sections = { 
			@ApiGlobalSection(title = "title", paragraphs = {
				"Paragraph 1", "Paragraph 2", "/jsondocfile./src/main/resources/text.txt" }
			) 
		}
	)
	@ApiChangelogSet(changlogs = { @ApiChangelog(changes = { "Change #1" }, version = "1.0") })
	@ApiMigrationSet(migrations = { @ApiMigration(fromversion = "1.0", steps = { "Step #1" }, toversion = "1.1") })
	private class AllTogether {
		
	}
	
	@Test
	public void testApiGlobalDoc() {
		ApiGlobalDoc apiGlobalDoc = jsondocScanner.getApiGlobalDoc(Set.of(Global.class), Set.of(), Set.of());
		Assertions.assertNotNull(apiGlobalDoc);
		Assertions.assertEquals(1, apiGlobalDoc.getSections().size());
		ApiGlobalSectionDoc sectionDoc = apiGlobalDoc.getSections().iterator().next();
		Assertions.assertEquals("title", sectionDoc.getTitle());
		Assertions.assertEquals(3, sectionDoc.getParagraphs().size());
		
		apiGlobalDoc = jsondocScanner.getApiGlobalDoc(Set.of(), Set.of(Changelog.class), Set.of());
		Assertions.assertNotNull(apiGlobalDoc);
		Assertions.assertEquals(1, apiGlobalDoc.getChangelogset().getChangelogs().size());

		apiGlobalDoc = jsondocScanner.getApiGlobalDoc(Set.of(MultipleGlobalSections.class), Set.of(), Set.of());
		Assertions.assertNotNull(apiGlobalDoc);
		Assertions.assertEquals(3, apiGlobalDoc.getSections().size());
		
		ApiGlobalSectionDoc[] apiGlobalSectionDocs = apiGlobalDoc.getSections().toArray(new ApiGlobalSectionDoc[0]);
		Assertions.assertEquals("section1", apiGlobalSectionDocs[0].getTitle());
		Assertions.assertEquals("abc", apiGlobalSectionDocs[1].getTitle());
		Assertions.assertEquals("198xyz", apiGlobalSectionDocs[2].getTitle());
		
		apiGlobalDoc = jsondocScanner.getApiGlobalDoc(Set.of(), Set.of(), Set.of(Migration.class));
		Assertions.assertNotNull(apiGlobalDoc);
		Assertions.assertEquals(1, apiGlobalDoc.getMigrationset().getMigrations().size());
		
		apiGlobalDoc = jsondocScanner.getApiGlobalDoc(Set.of(AllTogether.class), Set.of(AllTogether.class), Set.of(AllTogether.class));
		Assertions.assertNotNull(apiGlobalDoc);
		Assertions.assertEquals(1, apiGlobalDoc.getSections().size());
		Assertions.assertEquals(1, apiGlobalDoc.getMigrationset().getMigrations().size());
		Assertions.assertEquals(1, apiGlobalDoc.getChangelogset().getChangelogs().size());
	}

}
