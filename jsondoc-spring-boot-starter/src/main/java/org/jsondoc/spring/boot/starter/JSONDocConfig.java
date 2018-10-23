package org.jsondoc.spring.boot.starter;

import org.jsondoc.springintegration.controller.JSONDocController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JSONDocProperties.class)
@ConditionalOnClass(JSONDocController.class)
public class JSONDocConfig {
    final static String JSONDOC_PROPERTIES_PREFIX = "jsondoc";

	private final JSONDocProperties properties;

	@Autowired
	public JSONDocConfig(JSONDocProperties properties) {
		this.properties = properties;
	}

	@Bean
	public JSONDocController jController() {
		JSONDocController jsondocController = new JSONDocController(this.properties.getVersion(), this.properties.getBasePath(), this.properties.getPackages());
		jsondocController.setPlaygroundEnabled(this.properties.isPlaygroundEnabled());
		jsondocController.setDisplayMethodAs(this.properties.getDisplayMethodAs());
		return jsondocController;
	}

}
