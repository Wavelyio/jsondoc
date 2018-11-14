package com.axonmobileiot.jsondoc.springintegration.scanner.page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class PageScannerController {

    @GetMapping
    public ResponseEntity<Page<String>> getSimCards(@PageableDefault(size = 20) @SortDefault(sort = "icc", direction = Sort.Direction.DESC) Pageable pageable) {
        return null;
    }
}
