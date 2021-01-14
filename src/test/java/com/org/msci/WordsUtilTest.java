package com.org.msci;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class WordsUtilTest {

	@Test()
	public void populateAllWordsTest() {
		WordsUtil util = new WordsUtil();
		String input = "56835282";
		List<String> populateWords = util.populateWords(input, input.length(), input.length()-1);
		assertTrue(populateWords.size() == 7);
	}
	
	@Test
	public void generatePhrasesTest(){
		WordsUtil util = new WordsUtil();
		String input = "56835282";
		List<String> words = util.populateWords(input, input.length(), input.length()-1);
		List<String> result = words.stream()
				.filter(s -> util.plausiblePhrases(s))
				.filter(s -> util.checkPhrases(s))
				.map(s -> util.getPhrases(s))
				.collect(Collectors.toList());
		assertTrue(result.size() == 4);
	}
	
	@Test
	public void plausiblePhrasesTest() {
		boolean flag = false;
		String s = "lovejava";
		for(String word : Arrays.asList("love","def","java","abc")) {
			if(s.startsWith(word)) {
				flag = true;
				break;
			}
		}
		assertTrue(flag);
	}
	
	@Test
	public void checkPhrasesTest() {
		String word = "lovejava";
		boolean flag = false;
		for(String s : Arrays.asList("love","def","java","abc")) 
			word = word.contains(s) ? word.replace(s, "") : word;
		flag =  word == "" ? true : false;
		assertTrue(flag);
	}
	
	@Test
	public void getPhrases() {
		String word = "lovejava";
		int counter = 0;
		List<String> sortedMatches = Arrays.asList("love","def","java","abc").stream()
				.sorted((a, b) -> b.length() - a.length())
				.collect(Collectors.toList());

		String returnPhrase=word;
		for(String s : sortedMatches) { 
			if(counter >= word.length()) 
				break;
			if(word.contains(s)) {
				returnPhrase = returnPhrase.replace(s, s+" ");
				counter += s.length();
			}
		}
		assertTrue(returnPhrase.trim().equals("love java"));
	}

	
}
