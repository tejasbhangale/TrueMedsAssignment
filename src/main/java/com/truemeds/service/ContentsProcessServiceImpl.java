package com.truemeds.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truemeds.dao.ContentsManager;
import com.truemeds.dao.ProcessedManager;
import com.truemeds.model.Contents;
import com.truemeds.model.Processed;

@Service
@Transactional
public class ContentsProcessServiceImpl implements ContentsProcessService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	ContentsManager contentManager;
	
	@Autowired
	ProcessedManager processedManager;

	@Override
	public List<Contents> getAllContents() {
		 List<Contents> inputList= (List<Contents>) contentManager.findAll();
		 logger.debug("Total records fetched :{}", inputList.size());
		 return inputList;

	}
	
	
	public List<Processed> getAllProcessed() {
		 List<Processed> processedList= (List<Processed>) processedManager.findAll();
		 logger.debug("Total records fetched :{}", processedList.size());
		 return processedList;

	}
	int count=0;
	@Override
	public List<Processed> processContents() {
		List<Contents> allContents = getAllContents();
		List<Processed> processedList = new ArrayList<>();
		for (Contents content : allContents) {
			count=0;
			String processedString = removeDuplicates(content.getInputString(), ' ');
			logger.info("Input string: {}, output: {},step: {}",content.getInputString(),processedString,count);
			Processed processedContents = new Processed();
			processedContents.setId(content.getId());
			processedContents.setFinalOutput(processedString);
			processedContents.setCount(count);
			processedContents.setCreationDate(new Date());
			processedList.add(processedContents);
		}
		return saveProcessedContents(processedList);
		
	}

	private List<Processed> saveProcessedContents(List<Processed> processedList) {
		logger.debug("Saving records {}",processedList.size());
		return processedManager.saveAll(processedList);
		
	}

	private String removeDuplicates(String str, char ch) {

		if (str == null || str.length() <= 1) {
			return str;
		}
		count +=1 ;		
		
		int i = 0;
		while (i < str.length()) {
			if (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
				int j = i;
				while (j + 1 < str.length() && str.charAt(j) == str.charAt(j + 1)) {
					j++;
				}
				char lastChar = i > 0 ? str.charAt(i - 1) : ch;

				String processedStr = removeDuplicates(str.substring(j + 1, str.length()), lastChar);

				str = str.substring(0, i);

				while (processedStr.length() > 0 && str.length() > 0 && processedStr.charAt(0) == str.charAt(str.length() - 1)) {
					
					while (processedStr.length() > 0 && processedStr.charAt(0) != ch
							&& processedStr.charAt(0) == str.charAt(str.length() - 1)) {
						processedStr = processedStr.substring(1, processedStr.length());
						
					}
					str = str.substring(0, str.length() - 1);
				}
				str = str + processedStr;
				i = j;
			} else {
				i++;
			}
		}
		
		return str;
	}

	@Override
	public List<String> getResults() {
		List<Contents> contList= getAllContents();
		List<String> results = new LinkedList<>();
		List<Processed> proList = getAllProcessed();
		for(int i = 0 ; i< contList.size(); i++) {
			Contents contents = contList.get(i);
			Processed processed = proList.get(i);
			String str = "Input: " + contents.getInputString() + " Output: " +processed.getFinalOutput() + " Steps : " +processed.getCount(); 
			results.add(str);
		}
		return results;
	}
}
