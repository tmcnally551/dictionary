package com.dictionary.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryEntity {
	
	//in memory dictionary unordered
	private Map<String, List<String>> dictionary = new HashMap<>();
	
	public Map<String, List<String>> getDictionary() {
		return dictionary;
	}

	public void setDictionary(Map<String, List<String>> dictionary) {
		this.dictionary = dictionary;
	}
}
