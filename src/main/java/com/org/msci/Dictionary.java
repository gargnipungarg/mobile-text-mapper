package com.org.msci;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
	
	public Set<String> populateDictionary() {
		Set<String> allWords = new HashSet<>();

		try { 
			URL oracle = new URL("http://www.mieliestronk.com/corncob_lowercase.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				allWords.add(inputLine);
			}
			in.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return allWords;
	}
}
