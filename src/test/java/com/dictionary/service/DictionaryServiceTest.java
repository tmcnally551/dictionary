package com.dictionary.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DictionaryServiceTest {
	
	private DictionaryService dictionaryService = new DictionaryService();
	private String key1 = "good";
	private String key1Value1 = "yes";
	private String key1Value2 = "no";
	private String key1Value3 = "could be";
	private String key2 = "bad";
	private String key2Value1 = "yes";
	private String key2Value2 = "no";
	private String key2Value3 = "might not";
	
	@Before
	public void init() {
		dictionaryService.clear();
		dictionaryService.addMember(key1, key1Value1);
		dictionaryService.addMember(key1, key1Value2);
		dictionaryService.addMember(key1, key1Value3);
		dictionaryService.addMember(key2, key2Value1);
		dictionaryService.addMember(key2, key2Value2);
		dictionaryService.addMember(key2, key2Value3);
	}
	
	@Test
	public void testAddMember() {
		String key1Value4 = "maybe";
		String key2Value4 = "maybe";
		assertEquals(true, dictionaryService.containsKey(key1));
		assertEquals(true, dictionaryService.containsKey(key2));
		List<String> membersKeys1 = new ArrayList<>();
		membersKeys1.add(key1Value1);
		membersKeys1.add(key1Value2);
		membersKeys1.add(key1Value3);
		membersKeys1.add(key1Value4);
		List<String> membersKeys2 = new ArrayList<>();
		membersKeys2.add(key2Value1);
		membersKeys2.add(key2Value2);
		membersKeys2.add(key2Value3);
		membersKeys2.add(key2Value4);
		dictionaryService.addMember(key1, key1Value4);
		dictionaryService.addMember(key2, key2Value4);
		assertEquals(membersKeys1, dictionaryService.getMemberList(key1));
		assertEquals(membersKeys2, dictionaryService.getMemberList(key2));
	}
	
	@Test
	public void testGetMemberList(){
		List<String> members = dictionaryService.getMemberList(key1);
		assertEquals(true, !members.isEmpty());
	}
	
	@Test
	public void testKeyExists() {
		assertEquals(true,dictionaryService.containsKey(key2));
	}
	
	@Test
	public void testRemove() {
		dictionaryService.remove(key1, key1Value1);
		assertEquals(false, dictionaryService.isEmpty());
		dictionaryService.remove(key1, key1Value2);
		dictionaryService.remove(key1, key1Value3);
		assertEquals(false, dictionaryService.containsKey(key1));
		dictionaryService.remove(key2, key2Value1);
		assertEquals(true, dictionaryService.containsKey(key2));
		dictionaryService.remove(key2, key2Value2);
		dictionaryService.remove(key2, key2Value3);
		assertEquals(true, dictionaryService.isEmpty());
	}
	
	@Test
	public void testRemoveAll() {
		dictionaryService.removeAll(key1);
		assertEquals(false, dictionaryService.containsKey(key1));
		dictionaryService.removeAll(key2);
		assertEquals(false, dictionaryService.containsKey(key2));
	}

	@Test
	public void testValueExists() {
		assertEquals(true, dictionaryService.containsKey(key1));
		assertEquals(true, dictionaryService.containsKey(key2));
		assertEquals(true, dictionaryService.getMemberList(key1).contains(key1Value1));
	}
	
	@Test
	public void testClear() {
		dictionaryService.clear();
		assertEquals(true, dictionaryService.isEmpty());
	}
}
