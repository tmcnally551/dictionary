package com.dictionary.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dictionary.entity.DictionaryEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//dictionary actions and data presentation
public class DictionaryService {
	
	//create and get in memory dictionary
	private Map<String,List<String>> dictionary = new DictionaryEntity().getDictionary();
	private ObjectMapper mapper = new ObjectMapper();
	
	
	/**
	 * 
	 * @param key and value
	 */
	public void addMember(String key, String value) {
		List<String> values = getMemberList(key);
		if (values != null && values.contains(value)) {
			printErrorValueExists();
		} else {
			List<String> currentList = dictionary.get(key);
			if(currentList == null || currentList.isEmpty()) {
				currentList = new ArrayList<>();
			} 
			currentList.add(value);
			dictionary.put(key, currentList);
			print(") Added");
		}
	}
	
	/**
	 * clears the dictionary
	 */
	public void clear() {
		dictionary.clear();
		print(") cleared");
	}
	
	/**
	 * 
	 * @return
	 */
	public void getJSON() {
		String jsonDictionary = null;
		try {
			jsonDictionary = mapper.writeValueAsString(dictionary);
		} catch (JsonProcessingException e) {
			System.out.println("Error creating JSON string from dictionary, " + e);
		}
		print(jsonDictionary);
	}
	
	/**
	 * 
	 */
	public void getKeys(String sort){
		if(dictionary.keySet().isEmpty()) {
			printEmptySet();
		} else {
			if(Boolean.valueOf(sort)) {
				List<String> sortedKeys = new ArrayList<>(dictionary.keySet());
				Collections.sort(sortedKeys);
				printList(sortedKeys);
			} else {
				printList(new ArrayList<>(dictionary.keySet()));
			}
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return values
	 */
	public List<String> getMemberList(String key){
		return dictionary.get(key);
	}
	
	/**
	 * 
	 * @param key
	 */
	public void keyExists(String key) {
		print(String.valueOf(containsKey(key)));
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void remove(String key, String value) {
		if(containsKey(key)) {
			List<String> members = getMemberList(key);
			if (!members.contains(value)) {
				printErrorNoValue();
			} else {
				if(members.size() == 1) {
					dictionary.remove(key);
				} else {
					members.remove(value);
					dictionary.put(key, members);
				}
				print(") Removed");
			}
		} else {
			printErrorNoKey();
		}
	}
	
	/**
	 * 
	 * @param key
	 */
	public void removeAll(String key) {
		if(containsKey(key)) {
			dictionary.remove(key);
			print(") Removed");
		} else {
			printErrorNoKey();
		}
	}
	
	public boolean isEmpty() {
		return dictionary.isEmpty();
	}
	
	/**
	 * 
	 */
	public void printAllMembers(String sort) {
		List<String> keys = new ArrayList<>(dictionary.keySet());
		if(!keys.isEmpty()) {
			List<String> members = new ArrayList<>();
			for(String key : keys) {
				members.addAll(getMemberList(key));
			}
			if(Boolean.valueOf(sort)) {
				Collections.sort(members);
			}
			printList(members);
		} else {
			printEmptySet();
		}
	}
	
	/**
	 * 
	 * @param key
	 */
	public void printMembers(String key){
		printList(dictionary.get(key));
	}
	
	/**
	 * 
	 */
	public void printItems(String sort) {
		List<String> keys = new ArrayList<>(dictionary.keySet());
		if(!keys.isEmpty()) {
			if(Boolean.valueOf(sort)) {
				Collections.sort(keys);
			}
			List<String> items = new ArrayList<>();
			for(String key : keys) {
				List<String> members = getMemberList(key);
				for(String member : members) {
					items.add(key + ": " + member);
				}
			}
			printList(items);
		} else {
			printEmptySet();
		}
	}
	
	/**
	 * 
	 * @param key
	 */
	public void valueExists(String key,String value) {
		boolean containsKey = containsKey(key);
		if(!containsKey) {
			print(String.valueOf(containsKey));
		} else {
			print(String.valueOf(getMemberList(key).contains(value)));
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return keys
	 */
	public boolean containsKey(String key) {
		return dictionary.containsKey(key);
	}
	
	//dictionary service utility methods
	private void printList(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ") " + list.get(i));
		}
	}

	private void printEmptySet() {
		System.out.println("(empty set)");
	}
	
	private void printErrorValueExists() {
		print(") ERROR, value already exists");
	}
	
	private void printErrorNoValue() {
		print(") ERROR, value does not exist");
	}
	
	private void printErrorNoKey() {
		print(") ERROR, key does not exist");
	}
	
	private void print(String action) {
		System.out.println(action);
	}
}
