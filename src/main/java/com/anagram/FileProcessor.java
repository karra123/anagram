package com.anagram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileProcessor  {
	public static Logger logger = LoggerFactory.getLogger(FileProcessor.class);
	
	public Map<Integer, Set<String>> mapOfWords = new HashMap<>();
	
	private String fileName;

	public FileProcessor(String fileName) {
		super();
		this.fileName = fileName;
		load();
	}
	
	private void load(){
	    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	    	String currentLine;
			while ((currentLine = br.readLine()) != null) {
				logger.debug("Line read = " + currentLine);
				String[] words = currentLine.split(" ");
				for (String wd : words){
					addWord(getWordProcessed(wd));
				}
			}
	    }
	    catch (Exception e){
	    	
	    }
		
	}
		
	private String getWordProcessed(String value){
		String COMMA = ",";
		String PERIOD = ".";
		String SEMICOLON = ";";
		String retVal = "";
		if (!StringUtils.isEmpty(value)){
			if (value.endsWith(COMMA) || value.endsWith(PERIOD) || value.endsWith(SEMICOLON)){
				retVal = value.substring(0, value.length()-1);
			}
			else {
				retVal = value;
			}
		}
		return retVal.trim().toLowerCase();
	}
	
	private void addWord(String value){
		if (!StringUtils.isEmpty(value)){
			Integer length = value.length();
			Set<String> set = mapOfWords.get(length);
			if (set==null){
				set = new HashSet<>();
			}
			set.add(value);
			mapOfWords.put(length, set);
		}
	}
	

	public Map<Integer, Set<String>> getMapOfWords(){
		return mapOfWords;
	}
}
