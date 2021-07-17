package com.truemeds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.truemeds.model.Contents;
import com.truemeds.model.Processed;
import com.truemeds.service.ContentsProcessService;
import java.util.List;

@RestController
@RequestMapping("/process")
public class ProcessController {

	
	@Autowired
    ContentsProcessService service;

    @GetMapping("")
    public List<Contents> list() {
        return service.getAllContents();
    }

    @GetMapping("contents")
    public List<String> processContents() {
    	service.processContents();
        return service.getResults();
    }
    @GetMapping("output")
    public List<String> fetchProcessedRecords(){
		return service.getResults();
    	
    }
}
