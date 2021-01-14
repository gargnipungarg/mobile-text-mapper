package com.org.msci;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Problem Statement: 

  Please read all given below given instruction and consider all scenarios to get the best score*

		Write a program that takes a number and produces all possible phrases consisting of words that match
		this number while using all digits and inserting spaces between words as needed.
		 
		Use a digit to char mapping printed on the telephone keyboards and a word list as a dictionary
		(e.g. http://www.mieliestronk.com/corncob_lowercase.txt). Digit to char mapping is (like on the
		standard phone):
		 
		2 =&gt; [ ‘a’, ‘b’, ‘c’ ]
		3 =&gt; [ ‘d’, ‘e’, ‘f’ ]
		...
		9 =&gt; [ ‘w’, ‘x’, ‘y’, ‘z’ ]
		 
		For example, if input is “56835282” and dictionary contains words “love” and “java” and “lava”, the
		output must contain strings “love java” and “love lava”.
		Please provide a full solution. Please pay close attention to the performance. The code must work fast
		and be scalable. Please test on a long number (for instance 20 digits) and measure time.
 * 
 */

public class Application {

	public static void main(String[] args) {

		// Timestamp
		LocalDateTime now = LocalDateTime.now(); 

		// Input String
		String str = "";
		if(args.length > 0 && null != args[0])
			str = args[0];
		else {
			System.out.println("Please input a runtime argument and try again.");
			System.exit(0);
		}

		WordsUtil util = new WordsUtil();

		// Populate all possible words in data structure
		//Stream<String> allWords = util.populateAllWordsWithStream(str,str.length(),0);			// With Streams
		List<String> allWords = util.populateWords(str,str.length(),str.length()-1);

		//allWords.stream().forEach(System.out::println);

		// Feasible phrases
		System.out.println("Dictionary Word Matches : ");
		System.out.println(util.getDictionaryMatches());
		System.out.println("\nPhrases found : ");
		util.generatePhrases(allWords).forEach(System.out::println);

		System.out.println("\nCompletion Time : "+(LocalDateTime.now().getSecond() - now.getSecond()) +" seconds");

	}
}
