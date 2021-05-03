package com.dictionary.entity;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class DictionaryEntityTest {
	
	private Map<String, List<String>> dictionary = new HashMap<>();
	
	@Before
	public void init() {

	}
	
	@Test
	public void getDictionary() {
		assertEquals(true,dictionary.isEmpty());
	}
}
