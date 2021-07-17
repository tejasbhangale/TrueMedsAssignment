package com.truemeds.service;

import java.util.List;

import com.truemeds.model.Contents;
import com.truemeds.model.Processed;

public interface ContentsProcessService {

	public List<Contents> getAllContents();

	List<Processed> processContents();

	public List<String> getResults();
}
