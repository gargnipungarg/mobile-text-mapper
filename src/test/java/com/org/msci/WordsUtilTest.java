package com.org.msci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordsUtilTest {

	static final Map<Integer, List<Character>> phoneMapping = CharacterNumberMapping.phoneMapping;

	// Populate data dictionary
	private Set<String> dictionaryData = new Dictionary().populateDictionary();

	//Matching dictionary words in input String
	private Set<String> dictionaryMatches = new HashSet<>();

	// Data structure to store solutions previous iterations -- using dynamic programming 
	//private Map<String, Supplier<Stream<String>>> solutionsDS  = new HashMap<>();			// withStreams method
	private Map<String, List<String>> solutionsDS  = new HashMap<>();

	public Set<String> getDictionaryData() {
		return dictionaryData;
	}

	public Set<String> getDictionaryMatches() {
		return dictionaryMatches;
	}

	public static Map<Integer, List<Character>> getPhonemapping() {
		return phoneMapping;
	}

	/*public Map<String, Supplier<Stream<String>>> getSolutionsDS() {
		return solutionsDS;
	}*/
	
	public Map<String, List<String>> getSolutionsDS() {
		return solutionsDS;
	}

	public List<String> populateAllWords(String input, int length, int index) {
		char[] charArray = input.toCharArray();
		List<String> stringList = new LinkedList<>();
		int singleInputNumber = Character.getNumericValue(charArray[index]);
		if(length > index+1) { 
			for(int i = 0; i < phoneMapping.get(singleInputNumber).size(); i++){
				if(i==0) {		// first time - use recursion and then store the result of that recursion using dynamic programming
					List<String> nextIterationResult = populateAllWords(input, length, index + 1);   
					String concatStr = phoneMapping.get(singleInputNumber).get(i).toString();
					List<String> thisIterationResult = nextIterationResult.stream().map(s-> {
						String str = concatStr+s;
						populateDictionary(str);
						return str;
					}).collect(Collectors.toList());
					stringList.addAll(thisIterationResult);
					solutionsDS.put(input.substring(index+1), nextIterationResult);
				}else {
					// get the result stored in dynamic programming data structure
					//System.out.println(solutionsDS.get(input.substring(index+1)));
					List<String> result = solutionsDS.get(input.substring(index+1));
					String concatStr = phoneMapping.get(singleInputNumber).get(i).toString();
					result = result.stream().map(s-> {
						String str = concatStr+s;
						populateDictionary(str);
						return str;
					}).collect(Collectors.toList());
					stringList.addAll(result);
				}
			}
			return stringList;
		}else{
			for(int i = 0; i < phoneMapping.get(singleInputNumber).size(); i++)
				stringList.add(phoneMapping.get(singleInputNumber).get(i).toString());
			return stringList;
		}
	}

	public List<String> generatePhrases(List<String> allWords){
		List<String> list = new ArrayList<>();
		Object[] array = this.dictionaryMatches.toArray();
		for(int i=0; i< array.length-1;i++) {
			for(int j=i+1; j< array.length ;j++) {
				if(allWords.contains(array[i].toString() + array[j].toString())) {
					list.add(array[i].toString() + " "+ array[j].toString());
				}
			}
		}
		return list;
	}

	private boolean populateDictionary(String str) {

		if(checkExistInDictionary(str)) 
			populateDictionaryMatches(str);
		return checkPartialExistInDictionary(str);
	}

	private boolean checkExistInDictionary(String word) {
		return dictionaryData.contains(word);
	}

	private void populateDictionaryMatches(String s) {
		dictionaryMatches.add(s);
	}

	private boolean checkPartialExistInDictionary(String word) {

		for(String s: dictionaryMatches) 
			if(word.indexOf(s) != -1) 
				word=word.substring(0,word.indexOf(s));
		if(checkExistInDictionary(word)) {
			populateDictionaryMatches(word);
			return true;
		}
		return false;
	}
}
