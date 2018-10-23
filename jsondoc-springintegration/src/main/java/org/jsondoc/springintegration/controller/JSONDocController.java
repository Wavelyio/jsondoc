package org.jsondoc.springintegration.controller;

import java.util.List;

import org.jsondoc.core.pojo.JSONDoc;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.jsondoc.springintegration.scanner.Spring5JSONDocScanner;
import org.springframework.core.SpringVersion;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JSONDocController {
	private final static String JSONDOC_DEFAULT_PATH = "/jsondoc";

	private String version;
	private String basePath;
	private List<String> packages;
	private JSONDocScanner jsondocScanner;
	private boolean playgroundEnabled = true;
	private MethodDisplay displayMethodAs = MethodDisplay.URI;

	public JSONDocController(String version, String basePath, List<String> packages) {
		this.version = version;
		this.basePath = basePath;
		this.packages = packages;
		jsondocScanner = new Spring5JSONDocScanner();
	}

	public boolean isPlaygroundEnabled() {
		return playgroundEnabled;
	}

	public void setPlaygroundEnabled(boolean playgroundEnabled) {
		this.playgroundEnabled = playgroundEnabled;
	}

	public MethodDisplay getDisplayMethodAs() {
		return displayMethodAs;
	}

	public void setDisplayMethodAs(MethodDisplay displayMethodAs) {
		this.displayMethodAs = displayMethodAs;
	}

	@GetMapping(value = JSONDocController.JSONDOC_DEFAULT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONDoc getApi() {
		return jsondocScanner.getJSONDoc(version, basePath, packages, playgroundEnabled, displayMethodAs);
	}

}
