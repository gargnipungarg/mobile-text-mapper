package com.org.msci;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WordsUtil {

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

	/*public Stream<String> populateAllWordsWithStream(String input, int length, int index) {
		char[] charArray = input.toCharArray();
		int singleInputNumber = Character.getNumericValue(charArray[index]);
		Stream<String> result = null;
		List<Character> charsList = phoneMapping.get(singleInputNumber);

		if(length > index+1) {
			for(int i = 0; i < charsList.size(); i++){
				if(i==0) {		// first time - use recursion and then store the result of that recursion using dynamic programming
					Supplier<Stream<String>> nextIterationResult = () -> populateAllWordsWithStream(input, length, index + 1);
					String concatStr = charsList.get(i).toString();
					result = nextIterationResult.get().map(s-> {
						String str = concatStr+s;
						populateDictionary(str);
						return str;
					});
					solutionsDS.put(input.substring(index+1), nextIterationResult);
				}else {
					// get the result stored in dynamic programming data structure
					Stream<String> previousIteration = solutionsDS.get(input.substring(index+1)).get();
					String concatStr = charsList.get(i).toString();
					result = Stream.concat(result, previousIteration.map(s-> {
						String str = concatStr+s;
						populateDictionary(str);
						return str;
					}));
					//solutionsDS.get(input.substring(index+1)).get().forEach(System.out::println);
				}
			}
		}else{
			List<String> list = new ArrayList<>();
			for(int i = 0; i < charsList.size(); i++)
				list.add(charsList.get(i).toString());
			result= list.stream();
		}
		return result;
	}*/


	public List<String> populateWords(String input, int length, int index) {
		char[] charArray = input.toCharArray();
		List<String> stringList = new LinkedList<>();
		int singleInputNumber = Character.getNumericValue(charArray[index]);
		List<Character> mappings = phoneMapping.get(singleInputNumber);
		if(index > 0) { 
			for(int i = 0; i < mappings.size(); i++){
				List<String> nextIterationResult;
				if(i==0) {		// first time - use recursion and then store the result of that recursion using dynamic programming
					nextIterationResult = populateWords(input, length, index - 1);   
					solutionsDS.put(input.substring(0,index), nextIterationResult);
				}else {
					// get the result stored in dynamic programming data structure
					nextIterationResult = solutionsDS.get(input.substring(0,index));
				}
				String concatStr = mappings.get(i).toString();
				List<String> thisIterationResult = nextIterationResult.stream()
						.map(s-> {
							String str = s+concatStr;
							populateDictionary(str);
							return str;
						})
						.filter(s -> feasibleString(s,length))
						.collect(Collectors.toList());
				stringList.addAll(thisIterationResult);
			}
		}else{
			for(int i = 0; i < mappings.size(); i++)
				stringList.add(mappings.get(i).toString());
		}
		return stringList;
	}

	public List<String> generatePhrases(List<String> allWords){

		return allWords.stream()
				.filter(s -> plausiblePhrases(s))
				.filter(s -> checkPhrases(s))
				.map(s -> getPhrases(s))
				.collect(Collectors.toList());
	}

	public boolean plausiblePhrases(String s) {
		boolean flag = false;
		for(String word : dictionaryMatches) {
			if(s.startsWith(word)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean checkPhrases(String word) {
		for(String s : dictionaryMatches) 
			word = word.contains(s) ? word.replace(s, "") : word;
			return word == "" ? true : false;
	}

	public String getPhrases(String word) {
		//System.out.println(word);
		int counter = 0;
		List<String> sortedMatches = dictionaryMatches.stream()
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
		return returnPhrase.trim();
	}

	public boolean feasibleString(String str, int inputLength) {
		boolean flag = false;
		if(str.length() > inputLength/2) {						
			for(String s : dictionaryMatches) {
				if(str.startsWith(s)) {
					if(str.length()/4 >= 2) {
						str=str.replace(s, "");
					}else {
						flag = true;
						break;
					}
				}
			}
		}else {
			for(String s: dictionaryMatches) {
				if(str.contains(s)) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				for(String s : dictionaryData) {
					if(s.contains(str)) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	public boolean populateDictionary(String str) {

		if(checkExistInDictionary(str)) 
			populateDictionaryMatches(str);
		return checkPartialExistInDictionary(str);
	}

	public boolean checkExistInDictionary(String word) {
		return dictionaryData.contains(word);
	}

	public void populateDictionaryMatches(String s) {
		dictionaryMatches.add(s);
	}

	public boolean checkPartialExistInDictionary(String word) {

		for(String s: dictionaryMatches) 
			if(word.indexOf(s) != -1) 
				word=word.substring(word.indexOf(s)+s.length());
		if(checkExistInDictionary(word)) {
			populateDictionaryMatches(word);
			return true;
		}
		return false;
	}
}
